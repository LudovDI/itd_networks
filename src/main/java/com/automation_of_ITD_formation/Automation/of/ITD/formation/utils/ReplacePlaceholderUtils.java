package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.AosrData;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import java.util.ArrayList;
import java.util.List;

public class ReplacePlaceholderUtils {
    public static void replacePlaceholderInTable(XWPFDocument document, String placeholder, String replacement, int startLineLengthLimit, int nextLineLengthLimit) {
        for (XWPFTable table : document.getTables()) {
            for (int rowIndex = 0; rowIndex < table.getRows().size(); rowIndex++) {
                XWPFTableRow row = table.getRow(rowIndex);
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replacePlaceholderInParagraphWithTableHandling(paragraph, placeholder, replacement, table, rowIndex, startLineLengthLimit, nextLineLengthLimit);
                    }
                }
            }
        }
    }

    public static void replacePlaceholderInParagraphWithTableHandling(XWPFParagraph paragraph, String placeholder, String replacement, XWPFTable table, int startRowIndex, int startLineLengthLimit, int nextLineLengthLimit) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (XWPFRun run : runs) {
            String text = run.getText(0);
            if (text != null && text.contains(placeholder)) {
                String[] parts = replacement.split(" ");
                StringBuilder newText = new StringBuilder();
                XWPFRun currentRun = run;
                int lineLengthLimit = startLineLengthLimit;
                for (String part : parts) {
                    if (newText.length() + part.length() > lineLengthLimit) {
                        currentRun.setText(newText.toString(), 0);
                        newText = new StringBuilder(part).append(" ");
                        startRowIndex++;
                        if (startRowIndex < table.getRows().size()) {
                            XWPFTableRow nextRow = table.getRow(startRowIndex);
                            XWPFTableCell nextCell = nextRow.getCell(0);
                            nextCell.setText("");
                            paragraph = nextCell.getParagraphArray(0);
                            runs = paragraph.getRuns();
                            if (runs.isEmpty()) {
                                currentRun = paragraph.createRun();
                            } else {
                                currentRun = runs.get(0);
                            }
                            copyRunFormat(run, currentRun, 9, false);
                            lineLengthLimit = nextLineLengthLimit;
                        }
                    } else {
                        newText.append(part).append(" ");
                    }
                }
                currentRun.setText(newText.toString().trim(), 0);
            }
        }
    }

    public static void copyRunFormat(XWPFRun sourceRun, XWPFRun targetRun, int fontSize, boolean setTextBool) {
        if (setTextBool) {
            targetRun.setText(sourceRun.text());
        }
        targetRun.setFontFamily(sourceRun.getFontFamily());
        if (sourceRun.getFontSize() == -1) {
            targetRun.setFontSize(fontSize);
        } else {
            targetRun.setFontSize(sourceRun.getFontSize());
        }
        targetRun.setBold(sourceRun.isBold());
        targetRun.setItalic(sourceRun.isItalic());
        targetRun.setUnderline(sourceRun.getUnderline());
        targetRun.setColor(sourceRun.getColor());
        targetRun.setTextPosition(sourceRun.getTextPosition());
        if (sourceRun.getCTR().isSetRPr()) {
            targetRun.getCTR().setRPr(sourceRun.getCTR().getRPr());
        }
    }

    public static void replacePlaceholder(XWPFDocument document, String placeholder, String replacement) {
        boolean placeholderReplaced = false;
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        if (!placeholderReplaced) {
                            placeholderReplaced = replacePlaceholderInParagraph(paragraph, placeholder, replacement);
                        }
                    }
                }
            }
        }
        placeholderReplaced = false;
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            if (!placeholderReplaced) {
                placeholderReplaced = replacePlaceholderInParagraph(paragraph, placeholder, replacement);
            }
        }
    }

    public static boolean replacePlaceholderInParagraph(XWPFParagraph paragraph, String placeholder, String replacement) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (XWPFRun run : runs) {
            String text = run.getText(0);
            if (text != null && text.contains(placeholder)) {
                text = text.replaceFirst(placeholder, "");
                run.setText(text, 0);

                String[] lines = replacement.split("\n");
                for (int i = 0; i < lines.length; i++) {
                    run.setText(lines[i]);
                    if (i < lines.length - 1) {
                        run.addBreak();
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void duplicateRowWithPlaceholders(XWPFDocument document, List<AosrData> aosrToLogList) {
        XWPFTable table = document.getTables().getLast();

        XWPFTableRow templateRow = null;
        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                if (cell.getText().contains("Num")) {
                    templateRow = row;
                    break;
                }
            }
            if (templateRow != null) {
                break;
            }
        }

        if (templateRow == null) {
            throw new RuntimeException("Template row not found in the table.");
        }

        int templateRowIndex = table.getRows().indexOf(templateRow);

        int rowsToAdd = aosrToLogList.size() - 1;

        for (int i = 0; i < rowsToAdd; i++) {
            XWPFTableRow newRow = table.insertNewTableRow(templateRowIndex + 1);
            for (int j = 0; j < templateRow.getTableCells().size(); j++) {
                XWPFTableCell newCell = newRow.addNewTableCell();
                copyCell(templateRow.getCell(j), newCell);
            }
        }
    }

    public static void copyCell(XWPFTableCell sourceCell, XWPFTableCell targetCell) {
        targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());

        targetCell.removeParagraph(0);
        for (int i = targetCell.getParagraphs().size() - 1; i >= 0; i--) {
            targetCell.removeParagraph(i);
        }

        for (XWPFParagraph sourceParagraph : sourceCell.getParagraphs()) {
            XWPFParagraph targetParagraph = targetCell.addParagraph();
            copyParagraph(sourceParagraph, targetParagraph);
        }

        setCellBorders(targetCell);
    }

    public static void copyParagraph(XWPFParagraph sourceParagraph, XWPFParagraph targetParagraph) {
        targetParagraph.getCTP().setPPr(sourceParagraph.getCTP().getPPr());

        for (XWPFRun sourceRun : sourceParagraph.getRuns()) {
            XWPFRun targetRun = targetParagraph.createRun();
            copyRunFormat(sourceRun, targetRun, 12, true);
        }
    }
    public static void setCellBorders(XWPFTableCell cell) {
        CTTcPr tcPr = cell.getCTTc().addNewTcPr();
        CTTcBorders borders = tcPr.addNewTcBorders();

        borders.addNewTop().setVal(STBorder.SINGLE);
        borders.addNewBottom().setVal(STBorder.SINGLE);
        borders.addNewLeft().setVal(STBorder.SINGLE);
        borders.addNewRight().setVal(STBorder.SINGLE);
    }

    public static void replacePlaceholder(XWPFDocument document, String placeholder, String replacement, int startLineLengthLimit, int nextLineLengthLimit) {
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replacePlaceholderInParagraph(paragraph, placeholder, replacement, startLineLengthLimit, nextLineLengthLimit);
                    }
                }
            }
        }
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replacePlaceholderInParagraph(paragraph, placeholder, replacement, startLineLengthLimit, nextLineLengthLimit);
        }
    }

    public static void replacePlaceholderInParagraph(XWPFParagraph paragraph, String placeholder, String replacement, int startLineLengthLimit, int nextLineLengthLimit) {
        List<XWPFRun> runs = paragraph.getRuns();
        List<XWPFRun> runsToModify = new ArrayList<>();

        for (XWPFRun run : runs) {
            String text = run.getText(0);
            if (text != null && text.contains(placeholder)) {
                runsToModify.add(run);
            }
        }

        for (XWPFRun run : runsToModify) {
            String text = run.getText(0).replaceFirst(placeholder, "");
            run.setText(text, 0);

            String[] words = replacement.split(" ");
            StringBuilder newText = new StringBuilder();
            XWPFRun currentRun = run;
            int lineLengthLimit = startLineLengthLimit;
            for (String word : words) {
                if (newText.length() + word.length() + 1 > lineLengthLimit) {
                    currentRun.setText(newText.toString().trim(), 0);
                    newText = new StringBuilder(word).append(" ");
                    currentRun.addBreak();
                    XWPFRun newRun = paragraph.insertNewRun(paragraph.getRuns().size());
                    copyRunFormat(run, newRun, 14, false);
                    currentRun = newRun;
                    lineLengthLimit = nextLineLengthLimit;
                } else {
                    newText.append(word).append(" ");
                }
            }
            currentRun.setText(newText.toString().trim(), 0);
        }
    }
}
