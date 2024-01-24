package com.agg.certificados.services.certificationServices.service;

import com.agg.certificados.dtos.response.BandejaCertificacionesResponseDto;
import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.entity.Certification;
import com.agg.certificados.repositories.certificationRepository.ICertificationRepository;
import com.agg.certificados.repositories.dataGeneratorRepository.IDataGeneratorRepository;
import com.aspose.pdf.Document;
import com.aspose.pdf.HorizontalAlignment;
import com.aspose.pdf.VerticalAlignment;
import com.aspose.pdf.WatermarkArtifact;
import com.aspose.pdf.facades.EncodingType;
import com.aspose.pdf.facades.FontStyle;
import com.aspose.pdf.facades.FormattedText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;


@Service
public class CertificationService implements ICertificationService {

    private Logger logger = LoggerFactory.getLogger(CertificationService.class);

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private IDataGeneratorRepository dataGeneratorRepository;
    @Autowired
    private ICertificationRepository certificationRepository;

    @Override
    public List<BandejaCertificacionesResponseDto> getCertifications(String create_date,String number_certification, String number_id) {

        List<Object[]> data = certificationRepository.getBandejaCertifications(create_date,number_certification, number_id);
        List<BandejaCertificacionesResponseDto> bandeja = new ArrayList<>();
        for (Object[] prueba : data) {

            BandejaCertificacionesResponseDto dto = new BandejaCertificacionesResponseDto();
            dto.id_certification = (BigInteger)prueba[0];
            dto.number_certification = (String) prueba[1];
            dto.create_date = (Date) prueba[2];
            dto.name = (String) prueba[3];
            dto.number_id = (String) prueba[4];
            dto.file_certificate_botadero = (String) prueba[5];
            dto.file_certificate_bascula = (String) prueba[6];

            bandeja.add(dto);
        }


        return bandeja;


    }
    @Override
    public String generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);
        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();

            // Convertir el contenido del ByteArrayOutputStream a Base64
            byte[] pdfBytes = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(pdfBytes);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Transactional
    public FileBase64ResponseDto generateCertificates(DataGeneratorResponseDto dto){


        Certification certification = createCertification(dto.id_data_generator);
        Map<String, Object> data = MapeoDatos(dto, certification);


        //Llamar el base64 del qr
        byte[] qrCode = generateQRCode(dto,100,100);
        String qrcode = Base64.getEncoder().encodeToString(qrCode);

        data.put("qrcode",qrcode);
        //-----------------------


        //Mirar como convertir la img en base64

        String image;

        try {
            image = loadImageAsBase64("/static/images/logo.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data.put("image",image);

        //-----------------------------------------------------------

        String certificateBotadero = generatePdfFile("certificacion-botadero",data,"Certificacion "+ certification.final_number_certification +".pdf");
        String certificateBascula = generatePdfFile("certificacion-bascula",data,"CertificacionBascula "+ certification.final_number_certification +".pdf");
        String certificateCalibracionbascula="";
        try {
            certificateCalibracionbascula = waterMark(certification.final_number_certification);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        certification.fileCertificateBotadero = certificateBotadero;
        certification.fileCertificateBascula = certificateBascula;

        //Save certification
        certificationRepository.save(certification);

        FileBase64ResponseDto dtoResponse = new FileBase64ResponseDto();
        dtoResponse.fileCertificateBotadero = certificateBotadero;
        dtoResponse.fileCertificateBascula = certificateBascula;
        dtoResponse.fileBascula = certificateCalibracionbascula;
        dtoResponse.number_final_certification = certification.final_number_certification;

        return dtoResponse;

    }

    public Certification createCertification(Long idDataGenerator){
        Certification certification = new Certification();

        certification.create_date = LocalDate.now();
        certification.data_generator_id = dataGeneratorRepository.findById(idDataGenerator).orElse(null);

        Long number_certification = certificationRepository
                .findByMaxNumberCertification(LocalDate.now().getYear());

        if (number_certification == null) {
            certification.number_certification = 1L;
        }else{
            certification.number_certification = number_certification + 1L;
        }

        certification.final_number_certification =  String.valueOf(certification.create_date.getYear()) + "-" +
                String.format("%03d",certification.number_certification);

        return certification;
    }

    public byte[] generateQRCode(DataGeneratorResponseDto qrContent, int width, int height) {
        try {

            //String json = new ObjectMapper().writeValueAsString(qrContent);
            String json = "https://chat.openai.com/";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String,Object> MapeoDatos(DataGeneratorResponseDto dto, Certification certification){
        //Mapeo de la informacion del generador y toda la relacionada a el
        Map<String, Object> data = new HashMap<>();

        data.put("generator",dto);

        data.put("certification",certification);

        data.put("certification_date",DateFormat(certification));

        String numeroFormateado = String.format("%03d", certification.number_certification);

        data.put("number_certification",numeroFormateado);
        return data;
    }

    private String MonthOfYear(int number_month){

        if (number_month == 1)
            return "Enero";
        else if (number_month == 2) {
            return "Febrero";
        }
        else if (number_month == 3 ) {
            return "Marzo";
        }
        else if (number_month == 4 ) {
            return "Abril";
        }
        else if (number_month == 5 ) {
            return "Mayo";
        }
        else if (number_month == 6) {
            return "Junio";
        }
        else if (number_month == 7) {
            return "Julio";
        }
        else if (number_month == 8 ) {
            return "Agosto";
        }
        else if (number_month == 9) {
            return "Septiembre";
        }
        else if (number_month == 10 ) {
            return "Octubre";
        }
        else if (number_month == 11 ) {
            return "Noviembre";
        }
        else if (number_month == 12) {
            return "Diciembre";
        }else {
            return "";
        }

    }

    private String DateFormat(Certification certification){
        int month = certification.create_date.getMonthValue();
        int day = certification.create_date.getDayOfMonth();
        int year = certification.create_date.getYear();

        String date = day + " días del mes de " + MonthOfYear(month) + " del " + year;
        return date;
    }

    public String loadImageAsBase64(String imagePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(imagePath);
        byte[] imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private String waterMark(String final_number_certification) throws IOException {

        String path = "templates/calibracion-bascula.pdf";
        // Obtener el recurso del archivo PDF
        Resource resource = new ClassPathResource(path);

        // Leer el contenido del archivo PDF como un arreglo de bytes
        byte[] pdfBytes = Files.readAllBytes(Path.of(resource.getURI()));



// Load PDF document
        Document doc = new Document(pdfBytes);

// Create a formatted text
        FormattedText formattedText = new FormattedText("Certificacion N° "+final_number_certification, java.awt.Color.RED, FontStyle.Courier, EncodingType.Identity_h, true, 25.0F);

// Create watermark artifact and set its properties
        WatermarkArtifact artifact = new WatermarkArtifact();
        artifact.setText(formattedText);
        artifact.setArtifactHorizontalAlignment (HorizontalAlignment.Center);
        artifact.setArtifactVerticalAlignment (VerticalAlignment.Center);
        artifact.setRotation (25);
        artifact.setOpacity (0.5);
        artifact.setBackground (false);

// Add watermark to the first page of PDF
        doc.getPages().get_Item(1).getArtifacts().add(artifact);
        doc.getPages().get_Item(2).getArtifacts().add(artifact);
        doc.getPages().get_Item(3).getArtifacts().add(artifact);
        doc.getPages().get_Item(4).getArtifacts().add(artifact);


// Save watermarked PDF document
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        doc.save(byteArrayOutputStream);

        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
