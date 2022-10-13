package com.example.onedemo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zxd
 * @Date 2022/10/9 16:27
 */
public class ExcelUtil {
    public static void main(String[] args) {
        Workbook wb = createWorkbook();
        //输出文件
        String path = "/Users/rbl/IdeaProjects/oneDemo/src/main/resources/temporaryfile/" + "样例.xlsx";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HSSFWorkbook 有导出行数限制,老版本 2003版本
     * XSSFWorkbook 大数据量,无行数限制,有OOM问题,支持新版本 2007版本
     * SXSSFWorkbook 大数据量,无行数限制,分批次持久化到硬盘,可无内存限制,无OOM问题,支持新版本
     */
    private static Workbook createWorkbook() {
        //创建工作薄
        XSSFWorkbook wb = new XSSFWorkbook();
        //建立sheet对象
        XSSFSheet sheet = wb.createSheet("成绩表");
        //在sheet里创建第一行，参数为行索引
        XSSFRow row = sheet.createRow(0);
        //创建单元格
        XSSFCell cell = row.createCell(0);
        //设置单元格内容
        cell.setCellValue("学生成绩表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //设置行高和列宽
        setOther(sheet, row);
        //设置字体
        setFont(font);
        //设置样式
        setCellStyle(cellStyle);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        return wb;
    }


    /**
     * 设置其他
     *
     * @param sheet
     * @param row
     */
    private static void setOther(XSSFSheet sheet, XSSFRow row) {
        //设置列宽行高
        //设置自适应列宽
        sheet.setDefaultColumnWidth(0);
        //自定义列宽
        sheet.setColumnWidth(0, (short) (35.7 * 10));
        //自定义行高 1pt(excel)=20 twips (poi)
        row.setHeight((short) (20 * 40));

        //冻结行和列
//        sheet.createFreezePane(1, 1);
    }


    /**
     * 设置字体
     *
     * @param font
     */
    private static void setFont(Font font) {
        //默认字体为宋体
        font.setFontName("宋体");
        //设置字体大小
        font.setFontHeight((short) 358);
        //设置字体颜色
        font.setColor(IndexedColors.INDIGO.getIndex());
        //设置字体加粗
        font.setBold(true);
        //设置字体斜体
        font.setItalic(true);
        //设置字体下划线
        font.setUnderline(Font.U_SINGLE);
        //设置字体上标下标
        font.setTypeOffset(Font.SS_SUPER);
        //设置字体删除线
        font.setStrikeout(true);
    }

    /**
     * 设置样式
     *
     * @param cellStyle
     */
    private static void setCellStyle(CellStyle cellStyle) {
        //设置背景颜色
        cellStyle.setFillForegroundColor(IndexedColors.PINK1.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //设置自动换行
        cellStyle.setWrapText(true);

        //边框样式
        //设置上边框线条类型
        cellStyle.setBorderTop(BorderStyle.THIN);
        //设置右边框线条类型
        cellStyle.setBorderRight(BorderStyle.THIN);
        //设置下边框线条类型
        cellStyle.setBorderBottom(BorderStyle.THIN);
        //设置左边框线条类型
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //设置上边框线条颜色
        cellStyle.setTopBorderColor(IndexedColors.BLUE_GREY.getIndex());
        //设置右边框线条颜色
        cellStyle.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());
        //设置下边框线条颜色
        cellStyle.setBottomBorderColor(IndexedColors.BLUE_GREY.getIndex());
        //设置左边框线条颜色
        cellStyle.setLeftBorderColor(IndexedColors.BLUE_GREY.getIndex());

        //对齐方式
        //设置水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

    }
}
