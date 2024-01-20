package com.agg.certificados.services.reportServices.reportCvc;

import com.agg.certificados.dtos.request.ReportCvcRequestDto;
import com.agg.certificados.dtos.response.ReportCvcResponseDto;
import com.agg.certificados.entity.DataGenerator;
import com.agg.certificados.entity.ManagerDataGenerator;
import com.agg.certificados.entity.QuantitiesRcd;
import com.agg.certificados.repositories.dataGeneratorRepository.IDataGeneratorRepository;
import com.agg.certificados.repositories.managerDataGeneratorRepository.IManagerDataGeneratorRepository;
import com.agg.certificados.repositories.quantitiesRcdRepository.IQuantitiesRcdRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ReportCvcService implements IReportCvcService{

    @Autowired
    private IDataGeneratorRepository dataGeneratorRepository;
    @Autowired
    private IQuantitiesRcdRepository quantitiesRcdRepository;

    @Autowired
    private IManagerDataGeneratorRepository managerDataGeneratorRepository;





    public String setData(ReportCvcRequestDto dto){

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Hoja 1");

        columnWith(sheet);

        title(sheet,dto.start_date,dto.end_date,wb);
        header(sheet, wb);

        List<ReportCvcResponseDto> lista = setRowData(dto);

        insertData(sheet,lista,wb);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] array;
        try {
            wb.write(bos);
            array = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(array);
    }

    public void insertData(Sheet sheet, List<ReportCvcResponseDto> lista, Workbook wb){
        CellStyle cellStyleName = wb.createCellStyle();

        //Hace negrilla la letra
        Font font = wb.createFont();
        font.setBold(true);
        cellStyleName.setFont(font);

        //Borde
        cellStyleName.setBorderTop(BorderStyle.THIN);
        cellStyleName.setBorderBottom(BorderStyle.THIN);
        cellStyleName.setBorderLeft(BorderStyle.THIN);
        cellStyleName.setBorderRight(BorderStyle.THIN);

        cellStyleName.setAlignment(HorizontalAlignment.CENTER); // Centra horizontalmente
        cellStyleName.setVerticalAlignment(VerticalAlignment.CENTER); // Centra verticalmente

        CellStyle cellStyle = wb.createCellStyle();

        //Borde
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        cellStyle.setAlignment(HorizontalAlignment.CENTER); // Centra horizontalmente
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Centra verticalmente

        int finalRow = 5;
        for (int index = 0; index < lista.size(); index++) {
            Row rowInsert = sheet.createRow(index + 5);

            Cell cellName = rowInsert.createCell(0);
            cellName.setCellValue(lista.get(index).name);
            cellName.setCellStyle(cellStyleName);

            Cell cellNumberId = rowInsert.createCell(1);
            cellNumberId.setCellValue(lista.get(index).number_id);
            cellNumberId.setCellStyle(cellStyle);

            Cell cellLegalRep = rowInsert.createCell(2);
            cellLegalRep.setCellValue(lista.get(index).legal_representative);
            cellLegalRep.setCellStyle(cellStyle);

            Cell cellActivities = rowInsert.createCell(3);
            cellActivities.setCellValue(lista.get(index).activities_by_manager);
            cellActivities.setCellStyle(cellStyle);

            Cell cellPuntoLimpio = rowInsert.createCell(4);
            cellPuntoLimpio.setCellValue(lista.get(index).t_puntolimpio);
            cellPuntoLimpio.setCellStyle(cellStyle);

            Cell cellAprov = rowInsert.createCell(5);
            cellAprov.setCellValue(lista.get(index).t_aprovechamiento);
            cellAprov.setCellStyle(cellStyle);

            Cell cellFinalmente = rowInsert.createCell(6);
            cellFinalmente.setCellValue(lista.get(index).t_dispuesto_finalmente);
            cellFinalmente.setCellStyle(cellStyle);

            finalRow +=1;
        }

        Row rowInsert = sheet.createRow(finalRow+1);

        Cell cellTotal = rowInsert.createCell(3);
        cellTotal.setCellValue("Total: ");
        cellTotal.setCellStyle(cellStyleName);


        Cell cellTotalPuntoLimpio = rowInsert.createCell(4);
        cellTotalPuntoLimpio.setCellValue(0);
        cellTotalPuntoLimpio.setCellStyle(cellStyle);

        cellTotalPuntoLimpio.setCellFormula("SUM(E6:E"+finalRow+")");

        Cell cellAprov = rowInsert.createCell(5);
        cellAprov.setCellValue(0);
        cellAprov.setCellStyle(cellStyle);

        cellAprov.setCellFormula("SUM(F6:F"+finalRow+")");

        Cell cellFinalmente = rowInsert.createCell(6);
        cellFinalmente.setCellValue(0);
        cellFinalmente.setCellStyle(cellStyle);

        cellFinalmente.setCellFormula("SUM(G6:G"+finalRow+")");


    }

    public List<ReportCvcResponseDto> setRowData(ReportCvcRequestDto dto){
        List<DataGenerator> data = dataGeneratorRepository.getbyIdBotadero(dto.id_botadero, dto.start_date, dto.end_date);

        List<ReportCvcResponseDto> listReportDto = new ArrayList<>();
        for (DataGenerator item: data){
            List<QuantitiesRcd> dataQuantities = quantitiesRcdRepository.findByIdDataGenerator(item.id_data_generator);
            List<ManagerDataGenerator> dataManagerGenerator = managerDataGeneratorRepository.findByIdDataGenerator(item.id_data_generator);

            ReportCvcResponseDto reportDto = new ReportCvcResponseDto();

            reportDto.name = item.name;
            reportDto.legal_representative = item.legal_representative;
            reportDto.number_id = item.number_id;
            reportDto.t_aprovechamiento = (getSumQuantitiesRcd(dataQuantities)).get(0);
            reportDto.t_dispuesto_finalmente = (getSumQuantitiesRcd(dataQuantities)).get(1);

            //Queda pendiente este, por que por el momento no se esta utilizando
            reportDto.t_puntolimpio = 0L;
            reportDto.activities_by_manager = getActivitiesByManager(dataManagerGenerator);

            listReportDto.add(reportDto);
        }
        return listReportDto;

    }

    private String getActivitiesByManager(List<ManagerDataGenerator> dataManagerGenerator){
        StringBuilder names = new StringBuilder();
        int index = 0;

        for (ManagerDataGenerator item : dataManagerGenerator) {
            names.append(item.manager_id.name.toUpperCase());

            // Verificar si no es el último elemento
            if (index < dataManagerGenerator.size() - 1) {
                names.append(" - ");
            }

            index++;
        }

        return names.toString();
    }

    private List<Long> getSumQuantitiesRcd(List<QuantitiesRcd> dataQuantities ){
        Long dataAprovechado = 0L;
        Long dataFinalmente = 0L;
        for (QuantitiesRcd item: dataQuantities){
            if (item.type_rcd_id.id_type_rcd == 1){
                dataAprovechado += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 2){
                dataAprovechado += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 3){
                dataAprovechado += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 4){
                dataAprovechado += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 5) {
                dataAprovechado += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 6){
                dataFinalmente += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 7){
                dataFinalmente += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 8){
                dataFinalmente += item.quantity_rcd;
            }else if (item.type_rcd_id.id_type_rcd == 9){
                dataFinalmente += item.quantity_rcd;
            }
        }
        List<Long> response = new ArrayList<>();
        response.add(0,dataAprovechado);
        response.add(1,dataFinalmente);
        return response;
    }

    public void title(Sheet sheet, String start_date, String end_date, Workbook wb){

        // Crea un estilo de celda con texto vertical
        CellStyle cellStyle = wb.createCellStyle();

        //Hace negrilla la letra
        Font font = wb.createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        cellStyle.setAlignment(HorizontalAlignment.CENTER); // Centra horizontalmente
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Centra verticalmente

        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));

        Row rowOne = sheet.createRow(0);
        Cell cellOne = rowOne.createCell(0);
        cellOne.setCellValue("ANEXO III. FORMATOPARA EL REPORTE ANUAL DE GESTORES A ");
        cellOne.setCellStyle(cellStyle);

        sheet.addMergedRegion(CellRangeAddress.valueOf("A2:G2"));

        Row rowTwo = sheet.createRow(1);
        Cell cellTwo = rowTwo.createCell(0);
        cellTwo.setCellValue("AUTORIDAD AMBIENTAL COMPETENTE REGIONAL O URBANA");
        cellTwo.setCellStyle(cellStyle);

        sheet.addMergedRegion(CellRangeAddress.valueOf("A3:G3"));

        Row rowThree = sheet.createRow(2);
        Cell cellThree = rowThree.createCell(0);
        cellThree.setCellValue(start_date + " - " + end_date);
        cellThree.setCellStyle(cellStyle);

    }

    public void header(Sheet sheet, Workbook wb){

        // Crea un estilo de celda con texto vertical
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellStyleNombre = wb.createCellStyle();

        //Hace negrilla la letra
        Font font = wb.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyleNombre.setFont(font);

        //Poner vertical la celda
        cellStyle.setRotation((short) 90); // Configura la rotación del texto

        //Borde
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        //Borde
        cellStyleNombre.setBorderTop(BorderStyle.THIN);
        cellStyleNombre.setBorderBottom(BorderStyle.THIN);
        cellStyleNombre.setBorderLeft(BorderStyle.THIN);
        cellStyleNombre.setBorderRight(BorderStyle.THIN);

        //Filas
        Row rowFive = sheet.createRow(4);

        Cell cellFive = rowFive.createCell(0);
        cellFive.setCellValue("NOMBRE O RAZÓN SOCIAL");

        // Aplica el estilo a la celda
        cellFive.setCellStyle(cellStyleNombre);

        Cell cellFiveOne = rowFive.createCell(1);
        cellFiveOne.setCellValue("NÚMERO DE IDENTIFICACIÓN O NIT");

        // Aplica el estilo a la celda
        cellFiveOne.setCellStyle(cellStyle);

        Cell cellFiveTwo = rowFive.createCell(2);
        cellFiveTwo.setCellValue("REPRESENTANTE LEGAL");

        // Aplica el estilo a la celda
        cellFiveTwo.setCellStyle(cellStyle);

        Cell cellFiveThree = rowFive.createCell(3);
        cellFiveThree.setCellValue("ACTIVIDADES REALIZADAS POR EL GESTOR");

        // Aplica el estilo a la celda
        cellFiveThree.setCellStyle(cellStyle);

        Cell cellFiveFour = rowFive.createCell(4);
        cellFiveFour.setCellValue("TONELADAS AÑO DE RCD ALMACENADO EN PUNTO LIMPIO");

        // Aplica el estilo a la celda
        cellFiveFour.setCellStyle(cellStyle);

        Cell cellFiveFive = rowFive.createCell(5);
        cellFiveFive.setCellValue("TONELADAS AÑO DE RCD APROVECHADO");

        // Aplica el estilo a la celda
        cellFiveFive.setCellStyle(cellStyle);

        Cell cellFiveSix = rowFive.createCell(6);
        cellFiveSix.setCellValue("TONELADAS AÑO DE RCD DISPUESTO FINALMENTE");

        // Aplica el estilo a la celda
        cellFiveSix.setCellStyle(cellStyle);
    }

    public void columnWith(Sheet sheet){
        sheet.setColumnWidth(0,29 * 256);
        sheet.setColumnWidth(1,20 * 256);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 256);
        sheet.setColumnWidth(6,20 * 256);

    }

}
