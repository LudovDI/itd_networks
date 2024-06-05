package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class UpdateSheetUtils {
    public static void updateSheet(XSSFWorkbook workbook, String sheetName, int startRowIndex, List<List<String>> values) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("Sheet " + sheetName + " does not exist.");
            return;
        }

        for (int i = 0; i < values.size(); i++) {
            List<String> rowValues = values.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if (row == null) {
                row = sheet.createRow(startRowIndex + i);
            }

            for (int j = 0; j < rowValues.size(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellValue(rowValues.get(j));
            }
            row.setHeightInPoints(60);

            if (i < values.size() - 1) {
                int lastRowNum = sheet.getLastRowNum();
                int nextRowIndex = startRowIndex + i + 1;

                if (nextRowIndex <= lastRowNum) {
                    sheet.shiftRows(nextRowIndex, lastRowNum, 1);
                }

                Row newRow = sheet.createRow(nextRowIndex);
                for (int j = 0; j < rowValues.size(); j++) {
                    Cell oldCell = row.getCell(j);
                    Cell newCell = newRow.createCell(j);
                    if (oldCell != null) {
                        CellStyle newCellStyle = workbook.createCellStyle();
                        newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                        newCell.setCellStyle(newCellStyle);
                    }
                }
            }
        }
    }

    public static void setTextInCell(XSSFWorkbook workbook, String sheetName, int rowIndex, int columnIndex, String text) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("Sheet " + sheetName + " does not exist.");
            return;
        }

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }

        cell.setCellValue(text);
    }
}
