package com.agg.certificados.services.reportServices.reportCvc;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class ReportCvcService implements IReportCvcService{

    Workbook wb = new Workbook() {
        @Override
        public int getActiveSheetIndex() {
            return 0;
        }

        @Override
        public void setActiveSheet(int i) {

        }

        @Override
        public int getFirstVisibleTab() {
            return 0;
        }

        @Override
        public void setFirstVisibleTab(int i) {

        }

        @Override
        public void setSheetOrder(String s, int i) {

        }

        @Override
        public void setSelectedTab(int i) {

        }

        @Override
        public void setSheetName(int i, String s) {

        }

        @Override
        public String getSheetName(int i) {
            return null;
        }

        @Override
        public int getSheetIndex(String s) {
            return 0;
        }

        @Override
        public int getSheetIndex(Sheet sheet) {
            return 0;
        }

        @Override
        public Sheet createSheet() {
            return null;
        }

        @Override
        public Sheet createSheet(String s) {
            return null;
        }

        @Override
        public Sheet cloneSheet(int i) {
            return null;
        }

        @Override
        public Iterator<Sheet> sheetIterator() {
            return null;
        }

        @Override
        public int getNumberOfSheets() {
            return 0;
        }

        @Override
        public Sheet getSheetAt(int i) {
            return null;
        }

        @Override
        public Sheet getSheet(String s) {
            return null;
        }

        @Override
        public void removeSheetAt(int i) {

        }

        @Override
        public Font createFont() {
            return null;
        }

        @Override
        public Font findFont(boolean b, short i, short i1, String s, boolean b1, boolean b2, short i2, byte b3) {
            return null;
        }

        @Override
        public short getNumberOfFonts() {
            return 0;
        }

        @Override
        public int getNumberOfFontsAsInt() {
            return 0;
        }

        @Override
        public Font getFontAt(short i) {
            return null;
        }

        @Override
        public Font getFontAt(int i) {
            return null;
        }

        @Override
        public CellStyle createCellStyle() {
            return null;
        }

        @Override
        public int getNumCellStyles() {
            return 0;
        }

        @Override
        public CellStyle getCellStyleAt(int i) {
            return null;
        }

        @Override
        public void write(OutputStream outputStream) throws IOException {

        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public int getNumberOfNames() {
            return 0;
        }

        @Override
        public Name getName(String s) {
            return null;
        }

        @Override
        public List<? extends Name> getNames(String s) {
            return null;
        }

        @Override
        public List<? extends Name> getAllNames() {
            return null;
        }

        @Override
        public Name getNameAt(int i) {
            return null;
        }

        @Override
        public Name createName() {
            return null;
        }

        @Override
        public int getNameIndex(String s) {
            return 0;
        }

        @Override
        public void removeName(int i) {

        }

        @Override
        public void removeName(String s) {

        }

        @Override
        public void removeName(Name name) {

        }

        @Override
        public int linkExternalWorkbook(String s, Workbook workbook) {
            return 0;
        }

        @Override
        public void setPrintArea(int i, String s) {

        }

        @Override
        public void setPrintArea(int i, int i1, int i2, int i3, int i4) {

        }

        @Override
        public String getPrintArea(int i) {
            return null;
        }

        @Override
        public void removePrintArea(int i) {

        }

        @Override
        public Row.MissingCellPolicy getMissingCellPolicy() {
            return null;
        }

        @Override
        public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {

        }

        @Override
        public DataFormat createDataFormat() {
            return null;
        }

        @Override
        public int addPicture(byte[] bytes, int i) {
            return 0;
        }

        @Override
        public List<? extends PictureData> getAllPictures() {
            return null;
        }

        @Override
        public CreationHelper getCreationHelper() {
            return null;
        }

        @Override
        public boolean isHidden() {
            return false;
        }

        @Override
        public void setHidden(boolean b) {

        }

        @Override
        public boolean isSheetHidden(int i) {
            return false;
        }

        @Override
        public boolean isSheetVeryHidden(int i) {
            return false;
        }

        @Override
        public void setSheetHidden(int i, boolean b) {

        }

        @Override
        public SheetVisibility getSheetVisibility(int i) {
            return null;
        }

        @Override
        public void setSheetVisibility(int i, SheetVisibility sheetVisibility) {

        }

        @Override
        public void addToolPack(UDFFinder udfFinder) {

        }

        @Override
        public void setForceFormulaRecalculation(boolean b) {

        }

        @Override
        public boolean getForceFormulaRecalculation() {
            return false;
        }

        @Override
        public SpreadsheetVersion getSpreadsheetVersion() {
            return null;
        }

        @Override
        public int addOlePackage(byte[] bytes, String s, String s1, String s2) throws IOException {
            return 0;
        }

        @Override
        public Iterator<Sheet> iterator() {
            return null;
        }
    };
    Sheet hoja = wb.createSheet("Hoja 1");

    Cell fila = hoja.getRow(1).createCell(1);

}
