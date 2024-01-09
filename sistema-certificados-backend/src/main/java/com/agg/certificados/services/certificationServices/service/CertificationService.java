package com.agg.certificados.services.certificationServices.service;

import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.entity.Certification;
import com.agg.certificados.repositories.certificationRepository.CertificationRepository;
import com.agg.certificados.repositories.certificationRepository.ICertificationRepository;
import com.agg.certificados.repositories.dataGeneratorRepository.IDataGeneratorRepository;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

            String base64String = Base64.getEncoder().encodeToString(pdfBytes);

            return base64String;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Transactional
    public FileBase64ResponseDto generateCertificates(DataGeneratorResponseDto dto){

        //Mapeo de la informacion del generador y toda la relacionada a el
        Map<String, Object> data = new HashMap<>();

        data.put("generator",dto);

        Certification certification = createCertification(dto.id_data_generator);

        data.put("certification",certification);

        int month = certification.create_date.getMonthValue();
        int day = certification.create_date.getDayOfMonth();
        int year = certification.create_date.getYear();

        String date = day + " días del mes de " + MonthOfYear(month) + " del " + year;

        data.put("certification_date",date);

        String numeroFormateado = String.format("%03d", certification.number_certification);

        data.put("number_certification",numeroFormateado);

        String ruta_image = "/templates/logo.jpeg";
        data.put("image",ruta_image);

        String certificateBotadero = generatePdfFile("certificacion-botadero",data,"Certificacion "+ certification.final_number_certification +".pdf");
//        String certificateBotadero = generatePdfFile("certificacion-botadero",data,"Certificacion.pdf"); Hacer esto con las otras dos certificaciones
        String certificateBascula = generatePdfFile("certificacion-bascula",data,"CertificacionBascula "+ certification.final_number_certification +".pdf");


        certification.fileCertificateBotadero = certificateBotadero;
        certification.fileCertificateBascula = certificateBascula;
        //certification.fileCertificateBascula = "certificateBascula";

        //Save certification
        certificationRepository.save(certification);

        FileBase64ResponseDto dtoResponse = new FileBase64ResponseDto();
        dtoResponse.fileCertificateBotadero = certificateBotadero;
        dtoResponse.fileCertificateBascula = certificateBascula;
        dtoResponse.fileBascula = "Falta mapear";

        return dtoResponse;

    }

    public Certification createCertification(Long idDataGenerator){
        Certification certification = new Certification();

        certification.create_date = LocalDate.now();
        certification.data_generator_id = dataGeneratorRepository.findById(idDataGenerator).orElse(null);

        Long number_certification = certificationRepository
                .findByMaxNumberCertification(idDataGenerator,LocalDate.now().getYear());

        if (number_certification == null) {
            certification.number_certification = 1L;
        }else{
            certification.number_certification = number_certification + 1L;
        }

        certification.final_number_certification =  String.valueOf(certification.create_date.getYear()) + "-" +
                String.format("%03d",certification.number_certification);

        return certification;
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

}
