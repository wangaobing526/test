package com.dhcc.ecm.business.util;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 操作Excel文件的类
 * 
 * @ClassName: JexcelSample
 * @Description: TODO
 * @author jeffrey
 * @date 2012-4-23 上午10:49:05
 * 
 */
public class NFExcelStyle {

    public static boolean isNumeric(String s) {
        if ((s != null) && (s != ""))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    /**
     * 案卷封面样式
     * 
     * @return
     */
    public static HSSFCellStyle getFrontHeader(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 18);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setFillForegroundColor(HSSFColor.BLACK.index);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setDottedBorder(hssfcellstyle);
        return hssfcellstyle;
    }

    /**
     * 
     * @Title: setDottedBorder
     * @Description: 加虚线
     * @param hssfcellstyle
     * @return HSSFCellStyle
     * @author jeffrey
     * @throws
     */
    private static HSSFCellStyle setDottedBorder(HSSFCellStyle hssfcellstyle) {
        hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
        hssfcellstyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
        hssfcellstyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
        hssfcellstyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT);
        hssfcellstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        hssfcellstyle.setRightBorderColor(HSSFColor.BLACK.index);
        hssfcellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        hssfcellstyle.setTopBorderColor(HSSFColor.BLACK.index);
        return hssfcellstyle;
    }

    /**
     * 设置日期头的样式
     * 
     * @return
     */
    public static HSSFCellStyle getDateHeader(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setFillForegroundColor(HSSFColor.BLACK.index);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return hssfcellstyle;
    }

    /**
     * 设置日期的样式
     * 
     * @return
     */

    public static HSSFCellStyle getDateCell(String alignment, String patterm, HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        if (alignment.toLowerCase().equals("left")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        } else if (alignment.toLowerCase().equals("right")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        } else if (alignment.toLowerCase().equals("center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        } else {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }
        HSSFDataFormat format = workbook.createDataFormat();
        hssfcellstyle.setDataFormat(format.getFormat(patterm));
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setBorder(hssfcellstyle);
        return hssfcellstyle;
    }

    /**
     * 设置标题样式
     * 
     * @return
     */
    public static HSSFCellStyle getTitle(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 18);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setFillForegroundColor(HSSFColor.BLACK.index);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return hssfcellstyle;
    }

    public static HSSFCellStyle getTableUnBoldHeader(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = getTableHeader(workbook);
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 11);
        hssfcellstyle.setFont(font);
        return hssfcellstyle;
    }

    /**
     * 设置表头
     * 
     * @return
     */
    public static HSSFCellStyle getTableHeader(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 12);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setFillForegroundColor(HSSFColor.BLACK.index);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle.setWrapText(true);
        hssfcellstyle = setBorder(hssfcellstyle);
        return hssfcellstyle;
    }
    public static HSSFCellStyle getTableColorHeader(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle.setWrapText(true);
        hssfcellstyle = setWhiteBorder(hssfcellstyle);
        
        hssfcellstyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);// 设置背景色
        hssfcellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return hssfcellstyle;
    }
    /**
     * 行样式设置
     * 
     * @return
     */
    public static HSSFCellStyle getTableColorRow(HSSFWorkbook workbook, String type) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        if ("number".equals(type.toLowerCase())) {
            HSSFDataFormat format = workbook.createDataFormat();
            hssfcellstyle.setDataFormat(format.getFormat("#"));
        } else if ("date".equals(type.toLowerCase())) {
            HSSFDataFormat format = workbook.createDataFormat();
            hssfcellstyle.setDataFormat(format.getFormat("yyyy年MM月dd日"));
        }
        hssfcellstyle.setFont(font);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle.setWrapText(true);
        hssfcellstyle = setWhiteBorder(hssfcellstyle);
        return hssfcellstyle;
    }
    /**
     * 行样式设置
     * 
     * @return
     */
    public static HSSFCellStyle getTableRow(HSSFWorkbook workbook, String type) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        if ("number".equals(type.toLowerCase())) {
            HSSFDataFormat format = workbook.createDataFormat();
            hssfcellstyle.setDataFormat(format.getFormat("#"));
        } else if ("date".equals(type.toLowerCase())) {
            HSSFDataFormat format = workbook.createDataFormat();
            hssfcellstyle.setDataFormat(format.getFormat("yyyy年MM月dd日"));
        }
        hssfcellstyle.setFont(font);
        hssfcellstyle.setFillForegroundColor(HSSFColor.BLACK.index);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle.setWrapText(true);
        hssfcellstyle = setBorder(hssfcellstyle);
        return hssfcellstyle;
    }

    public static HSSFCellStyle setBorder(HSSFCellStyle hssfcellstyle) {
        hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        hssfcellstyle.setRightBorderColor(HSSFColor.BLACK.index);
        hssfcellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        hssfcellstyle.setTopBorderColor(HSSFColor.BLACK.index);
        return hssfcellstyle;
    }
    public static HSSFCellStyle setWhiteBorder(HSSFCellStyle hssfcellstyle) {
        hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setLeftBorderColor(HSSFColor.GREY_25_PERCENT.index);
        hssfcellstyle.setRightBorderColor(HSSFColor.GREY_25_PERCENT.index);
        hssfcellstyle.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);
        hssfcellstyle.setTopBorderColor(HSSFColor.GREY_25_PERCENT.index);
        return hssfcellstyle;
    }

    public static HSSFCellStyle setBorderByParam(HSSFCellStyle hssfcellstyle, String type) {
        if ("bottom".equals(type.toLowerCase())) {
            hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            hssfcellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        } else if ("top".equals(type.toLowerCase())) {
            hssfcellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            hssfcellstyle.setTopBorderColor(HSSFColor.BLACK.index);
        } else if ("left".equals(type.toLowerCase())) {
            hssfcellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            hssfcellstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        } else if ("right".equals(type.toLowerCase())) {
            hssfcellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            hssfcellstyle.setRightBorderColor(HSSFColor.BLACK.index);
        } else {
            hssfcellstyle = setBorder(hssfcellstyle);
        }

        return hssfcellstyle;
    }

    /**
     * 设置标题样式
     * 
     * @return
     */
    public static HSSFCellStyle getTitleUnBold(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setBorder(hssfcellstyle);
        hssfcellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
        hssfcellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return hssfcellstyle;
    }

    /**
     * 设置封面标题样式 UnBold
     * 
     * @return
     */
    public static HSSFCellStyle getTitleUnBold(String alignment, HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
        if (alignment.toLowerCase().equals("left")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        } else if (alignment.toLowerCase().equals("right")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        }  else if (alignment.toLowerCase().equals("right-center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        } else if (alignment.toLowerCase().equals("center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        } else if (alignment.toLowerCase().equals("left-bottom")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
        } else if (alignment.toLowerCase().equals("left-top")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        } else if (alignment.toLowerCase().equals("left-center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        }else {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }
        hssfcellstyle.setWrapText(true);
        return hssfcellstyle;
    }

    /**
     * 设置封面标题值 UnBold
     * 
     * @return
     */
    public static HSSFCellStyle getTitleValueUnBold(String alignment, HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 11);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
        if (alignment.toLowerCase().equals("left")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        } else if (alignment.toLowerCase().equals("left-bottom")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            hssfcellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        } else if (alignment.toLowerCase().equals("right")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        } else if (alignment.toLowerCase().equals("center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        } else if (alignment.toLowerCase().equals("left-bottom")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
        } else if (alignment.toLowerCase().equals("left-top")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        } else if (alignment.toLowerCase().equals("center-center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        } else {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }
        return hssfcellstyle;
    }

    /**
     * 正常的字符串的样式 带背景颜色
     * 
     * @return
     */
    public static HSSFCellStyle getNormolCell(HSSFWorkbook workbook) {// 12号字体,上下左右居中,带黑色边框
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);

        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setBorder(hssfcellstyle);
        return hssfcellstyle;
    }

    /**
     * 正常的字符串的样式 带背景颜色
     * 
     * @return
     */
    public static HSSFCellStyle getNormolCell(String alignment, HSSFWorkbook workbook) {// 12号字体,上下左右居中,带黑色边框
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        if (alignment.toLowerCase().equals("left")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        } else if (alignment.toLowerCase().equals("right")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        } else if (alignment.toLowerCase().equals("center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        } else {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setBorder(hssfcellstyle);
        return hssfcellstyle;
    }

    /**
     * 备注信息的样式，不带背景颜色 和支持自动换行
     * 
     * @return
     */
    public static HSSFCellStyle getNotesCell(String alignment, HSSFWorkbook workbook) {// 12号字体,上下左右居中,带黑色边框
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        if (alignment.toLowerCase().equals("left")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        } else if (alignment.toLowerCase().equals("right")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        } else if (alignment.toLowerCase().equals("center")) {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        } else {
            hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setBorder(hssfcellstyle);
        hssfcellstyle.setWrapText(true);
        return hssfcellstyle;
    }

    /**
     * 数字框的样式
     * 
     * @Title: getNumberCell
     * @Description: TODO
     * @param @return
     * @return WritableCellFormat
     * @throws
     */
    public static HSSFCellStyle getNumberCell(HSSFWorkbook workbook) {// 12号字体,上下左右居中,带黑色边框
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        HSSFDataFormat format = workbook.createDataFormat();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        hssfcellstyle.setFont(font);
        hssfcellstyle.setDataFormat(format.getFormat("#"));
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle = setBorder(hssfcellstyle);
        hssfcellstyle.setWrapText(true);
        return hssfcellstyle;
    }
    
    public static void createMergedCell(HSSFWorkbook wb, HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol, boolean isNewRow,
            HSSFCellStyle hssfcellstyle, String cellValue, boolean num) {
        CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
        sheet.addMergedRegion(region);
        if (isNewRow) {
            for (int i = startRow; i < endRow + 1; i++) {
                HSSFRow row = sheet.createRow(i);
                row.setHeight((short)280);
                for (int j = startCol; j < endCol + 1; j++) {
                    row.createCell(j).setCellStyle(hssfcellstyle);
                }
            }
        } else {
            for (int i = startRow; i < endRow + 1; i++) {
                HSSFRow row = sheet.getRow(i);
                if(row == null){
                    row = sheet.createRow(i);
                }
                row.setHeight((short)280);
                for (int j = startCol; j < endCol + 1; j++) {
                    row.createCell(j).setCellStyle(hssfcellstyle);
                }
            }
        }
        if (StringUtil.isNotEmpty(cellValue)) {
            if (NFExcelStyle.isNumeric(cellValue) && num) {
                try {
                    sheet.getRow(startRow).getCell(startCol).setCellValue(Integer.parseInt(cellValue));
                } catch (NumberFormatException e) {
                    sheet.getRow(startRow).getCell(startCol).setCellValue(cellValue);
                }
            } else {
                sheet.getRow(startRow).getCell(startCol).setCellValue(cellValue);
            }
        }
    }

    public static void createMergedCell(HSSFWorkbook wb, HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol, boolean isNewRow,
        HSSFCellStyle hssfcellstyle, String cellValue) {
    	//num : true判断数字类型,false不判断
    	
    	createMergedCell(wb, sheet, startRow, endRow, startCol, endCol, isNewRow, hssfcellstyle, cellValue, true);
    }

    @SuppressWarnings("unused")
    public static void createCellStyle(HSSFWorkbook wb, HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol, boolean isNewRow,
        HSSFCellStyle hssfcellstyle) {
        for (int i = startRow; i < endRow + 1; i++) {
            HSSFRow row = null;
            if (isNewRow) {
                row = sheet.createRow(i);
            } else {
                row = sheet.getRow(i);
            }
            for (int j = startCol; j < endCol + 1; j++) {
                HSSFCellStyle tempstyle = wb.createCellStyle();
                if (i == startRow) {
                    tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "top");
                    if (j == startCol) {
                        tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "left");
                    } else if (j == endCol) {
                        tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "right");
                    }
                    row.createCell(j).setCellStyle(tempstyle);
                } else if (i == endRow) {
                    tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "bottom");
                    if (j == startCol) {
                        tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "left");
                    } else if (j == endCol) {
                        tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "right");
                    }
                    row.createCell(j).setCellStyle(tempstyle);
                }
                if (j == startCol && i != startRow && i != endRow) {
                    tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "left");
                    row.createCell(j).setCellStyle(tempstyle);
                } else if (j == endCol && i != startRow && i != endRow) {
                    tempstyle = NFExcelStyle.setBorderByParam(tempstyle, "right");
                    row.createCell(j).setCellStyle(tempstyle);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
    }

}