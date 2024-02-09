package com.agg.certificados.services.certificationServices.service;

import com.agg.certificados.dtos.response.*;
import com.agg.certificados.entity.Certification;
import com.agg.certificados.repositories.certificationRepository.ICertificationRepository;
import com.agg.certificados.repositories.dataGeneratorRepository.IDataGeneratorRepository;
import com.agg.certificados.utils.TypeRcdEnum;
import com.agg.certificados.utils.TypeWeightEnum;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.List;


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
    public List<BandejaCertificacionesResponseDto> getCertifications(String create_date, String number_certification, String number_id) {

        List<Object[]> data = certificationRepository.getBandejaCertifications(create_date, number_certification, number_id);
        List<BandejaCertificacionesResponseDto> bandeja = new ArrayList<>();
        for (Object[] prueba : data) {

            BandejaCertificacionesResponseDto dto = new BandejaCertificacionesResponseDto();
            dto.id_certification = (BigInteger) prueba[0];
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

    //@Transactional
    public FileBase64ResponseDto generateCertificates(DataGeneratorResponseDto dto, boolean isNew, Long consecutive) {


        Certification certification = createCertification(dto.id_data_generator, isNew, consecutive);
        Map<String, Object> data = MapeoDatos(dto, certification);


        //Llamar el base64 del qr
        byte[] qrCode = generateQRCode(dto, 100, 100, dto.id_data_generator);
        String qrcode = Base64.getEncoder().encodeToString(qrCode);

        data.put("qrcode", qrcode);
        //-----------------------

        String image;

//        try {
//            image = loadImageAsBase64("/static/images/logo.png");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //Mirar como convertir la img en base64

        image = "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";

        data.put("image", image);

        //-----------------------------------------------------------

        String certificateBotadero = generatePdfFile
                ("certificacion-botadero", data, "Certificacion " + certification.final_number_certification + ".pdf");

        String certificateBascula = "";

        String certificateCalibracionbascula = "";

        if (Objects.equals(dto.type_weight.id_type_weight, TypeWeightEnum.Kilos.getNumberId())){
            certification.fileCertificateBascula = "";
            certificateCalibracionbascula = "";
        }else{
            certificateBascula = generatePdfFile
                    ("certificacion-bascula", data, "CertificacionBascula " + certification.final_number_certification + ".pdf");
            certification.fileCertificateBascula = certificateBascula;

            try {
                certificateCalibracionbascula = waterMark(certification.final_number_certification);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        certification.fileCertificateBotadero = certificateBotadero;

        //Save certification
        certificationRepository.save(certification);

        FileBase64ResponseDto dtoResponse = new FileBase64ResponseDto();
        dtoResponse.fileCertificateBotadero = certificateBotadero;
        dtoResponse.fileCertificateBascula = certificateBascula;
        dtoResponse.fileBascula = certificateCalibracionbascula;
        dtoResponse.number_final_certification = certification.final_number_certification;

        return dtoResponse;

    }

    public Certification createCertification(Long idDataGenerator, boolean isNew, Long consecutive) {
        if (isNew) {
            Certification certification = new Certification();

            LocalDate fechaActual = LocalDate.now();

            LocalTime horaActual = LocalTime.now();

            LocalDateTime fechaHoraActual = LocalDateTime.of(fechaActual, horaActual);

            LocalDateTime fechaReducida = fechaHoraActual.minusHours(4);

            certification.create_date = fechaReducida.toLocalDate();

            certification.data_generator_id = dataGeneratorRepository.findById(idDataGenerator).orElse(null);

            //revisar esto, porque ya no va
            Long number_certification = certificationRepository
                    .findByMaxNumberCertification(LocalDate.now().getYear());

            certification.number_certification = consecutive;

            certification.final_number_certification = String.valueOf(certification.create_date.getYear()) + "-" + consecutive;

            return certification;
        } else {
            Certification certification = certificationRepository.findByIdDataGenerator(idDataGenerator);
            return certification;
        }

    }

    public byte[] generateQRCode(DataGeneratorResponseDto qrContent, int width, int height, Long idDataGenerator) {
        try {

            //String json = new ObjectMapper().writeValueAsString(qrContent);
            String json = "https://suministramosycontratamosagg.com/certification/" + idDataGenerator;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);

        }
    }

    private Map<String, Object> MapeoDatos(DataGeneratorResponseDto dto, Certification certification) {
        //Mapeo de la informacion del generador y toda la relacionada a el
        Map<String, Object> data = new HashMap<>();

        //Agregar el tema de si son toneladas o kilos
        String quantitiesPesaje = quantitiesPesaje(dto.quantitiesRcd, dto.type_weight);

        data.put("quantitiespesaje", quantitiesPesaje);

        data.put("generator", dto);

        data.put("certification", certification);

        data.put("certification_date", DateFormat(certification));

        String numeroFormateado = String.format("%03d", certification.number_certification);

        data.put("number_certification", numeroFormateado);

        return data;
    }

    private String MonthOfYear(int number_month) {

        if (number_month == 1)
            return "Enero";
        else if (number_month == 2) {
            return "Febrero";
        } else if (number_month == 3) {
            return "Marzo";
        } else if (number_month == 4) {
            return "Abril";
        } else if (number_month == 5) {
            return "Mayo";
        } else if (number_month == 6) {
            return "Junio";
        } else if (number_month == 7) {
            return "Julio";
        } else if (number_month == 8) {
            return "Agosto";
        } else if (number_month == 9) {
            return "Septiembre";
        } else if (number_month == 10) {
            return "Octubre";
        } else if (number_month == 11) {
            return "Noviembre";
        } else if (number_month == 12) {
            return "Diciembre";
        } else {
            return "";
        }

    }

    private String DateFormat(Certification certification) {
        int month = certification.create_date.getMonthValue();
        int day = certification.create_date.getDayOfMonth();
        int year = certification.create_date.getYear();

        String date = day + " días del mes de " + MonthOfYear(month) + " del " + year;
        return date;
    }

    public byte[] loadImageAsBase64(String imagePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(imagePath);
        return StreamUtils.copyToByteArray(resource.getInputStream());
    }

    private String waterMark(String final_number_certification) throws IOException {

        String path = "templates/calibracion-bascula.pdf";

        // Leer el contenido del archivo PDF como un arreglo de bytes
        byte[] pdfBytes = loadImageAsBase64(path);

// Load PDF document
        ByteArrayOutputStream byteArrayOutputStream;
        try (Document doc = new Document(pdfBytes)) {

// Create a formatted text
            FormattedText formattedText = new FormattedText("Certificación " + final_number_certification, Color.red, FontStyle.CourierBold, EncodingType.Identity_h, true, 25.0F);

// Create watermark artifact and set its properties
            WatermarkArtifact artifact = new WatermarkArtifact();
            artifact.setText(formattedText);
            artifact.setArtifactHorizontalAlignment(HorizontalAlignment.Center);
            artifact.setArtifactVerticalAlignment(VerticalAlignment.Center);
            artifact.setRotation(25);
            artifact.setOpacity(0.5);
            artifact.setBackground(false);

// Add watermark to the first page of PDF

            doc.getPages().get_Item(1).getArtifacts().add(artifact);
            doc.getPages().get_Item(2).getArtifacts().add(artifact);
            doc.getPages().get_Item(3).getArtifacts().add(artifact);
            doc.getPages().get_Item(4).getArtifacts().add(artifact);


// Save watermarked PDF document
            byteArrayOutputStream = new ByteArrayOutputStream();
            doc.save(byteArrayOutputStream);
        }

        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    private String quantitiesPesaje(List<QuantitiesRcdResponseDto> dto, TypeWeightResponseDto typeWeigth){
        String response = "";

        double quantityAprovechamiento = 0;
        double quantityTierra = 0;
        double quantityMaterialRcd = 0;

        for(QuantitiesRcdResponseDto item : dto){
            if(Objects.equals(item.type_rcd.id_type_rcd, TypeRcdEnum.Uno.getNumberId())){
                quantityAprovechamiento+=item.quantity_rcd;
            }
            if (Objects.equals(item.type_rcd.id_type_rcd, TypeRcdEnum.UnoUno.getNumberId())){
                quantityTierra+=item.quantity_rcd;
            }
            if (Objects.equals(item.type_rcd.id_type_rcd, TypeRcdEnum.UnoTres.getNumberId()) || Objects.equals(item.type_rcd.id_type_rcd, TypeRcdEnum.Dos.getNumberId())){
                quantityMaterialRcd+= item.quantity_rcd;
            }
        }

        if (quantityAprovechamiento > 0){
            //Concatenar si son toneladas o kilos, hacer lo mismo con el otro
            response+=  quantityAprovechamiento + " " + typeWeigth.description + " de Rcd de aprovechamiento, ";
        }
        if (quantityTierra > 0){
            response+=  quantityTierra + " " + typeWeigth.description + " de Tierra o material vegetal, ";
        }
        if (quantityMaterialRcd > 0){
            response+=  quantityMaterialRcd + " " + typeWeigth.description +" de Material RCD, ";
        }

        return response;
    }
}