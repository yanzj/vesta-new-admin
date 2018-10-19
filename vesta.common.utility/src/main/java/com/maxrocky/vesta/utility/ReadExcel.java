package com.maxrocky.vesta.utility;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Talent on 2016/12/5.
 */
public class ReadExcel {

    private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";

    /***
     * <pre>
     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
     *   xls:HSSFWorkbook
     *   xlsx：XSSFWorkbook
     *
     * @param filePath
     * @return
     * @throws IOException </pre>
     */
    public static Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }


    public static HSSFWorkbook readXls(File path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        return hssfWorkbook;
    }

    public static XSSFWorkbook readXlsx(File path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        return xssfWorkbook;
    }

    @SuppressWarnings("static-access")
    public static String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    public static String getValue(HSSFCell hssfCell) {
        if (hssfCell == null) {
            return null;
        }

        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    /**
     * 判断excel的版本：
     *
     * @param fileName
     * @return 0：不是excel  1:2003版本     2:2007版本
     */
    public static int judgeExcelVersion(String fileName) {
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            return 1;
        } else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * 操作Excel2003以前（包括2003）的版本,扩展名是.xls
     *
     * @param templetFile 文件
     * @param startrow    开始行号
     * @param startcol    开始列号
     * @param sheetnum    sheet
     * @return list
     */
    public static List<Map<String, String>> readExcelByXls(MultipartFile templetFile, int startrow, int startcol, int sheetnum) {
        List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
        try {
            HSSFWorkbook wb = new HSSFWorkbook(templetFile.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(sheetnum);                  //sheet 从0开始
            int rowNum = sheet.getLastRowNum() + 1;                     //取得最后一行的行号
            for (int i = startrow; i < rowNum; i++) {                    //行循环开始
                Map<String, String> varpd = new HashMap<String, String>();
                HSSFRow row = sheet.getRow(i);                          //行
                int cellNum = row.getLastCellNum();                     //每行的最后一个单元格位置

                for (int j = startcol; j < cellNum; j++) {               //列循环开始
                    HSSFCell cell = row.getCell(Integer.parseInt(j + ""));
                    String cellValue = null;
                    if (null != cell) {
                        switch (cell.getCellType()) {                   // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
                            case 0:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                                } else {
                                    cell.setCellType(1);
                                    cellValue = cell.getStringCellValue();
                                }
                                break;
                            case 1:
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2:
                                //cell.setCellType(1);
                                //cellValue = cell.getStringCellValue();
                                //cellValue = cell.getNumericCellValue() + "";
                                cellValue = String.valueOf(cell.getDateCellValue());
                                break;
                            case 3:
                                cellValue = "";
                                break;
                            case 4:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case 5:
                                cellValue = String.valueOf(cell.getErrorCellValue());
                                break;
                        }
                    } else {
                        cellValue = "";
                    }

                    varpd.put("var" + j, cellValue);

                }
                varList.add(varpd);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return varList;
    }

    /**
     * 是操作Excel2007的版本，扩展名是.xlsx
     *
     * @param templetFile 文件
     * @param startrow    开始行号
     * @param startcol    开始列号
     * @param sheetnum    sheet
     * @return list
     */
    public static List<Map<String, String>> readExcelByXlsx(MultipartFile templetFile, int startrow, int startcol, int sheetnum) {
        List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
        try {
            XSSFWorkbook wb = new XSSFWorkbook(templetFile.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(sheetnum);                  //sheet 从0开始
            int rowNum = sheet.getLastRowNum() + 1;                     //取得最后一行的行号

            for (int i = startrow; i < rowNum; i++) {                    //行循环开始

                Map<String, String> varpd = new HashMap<String, String>();
                XSSFRow row = sheet.getRow(i);                          //行
                int cellNum = row.getLastCellNum();                     //每行的最后一个单元格位置

                for (int j = startcol; j < cellNum; j++) {               //列循环开始

                    XSSFCell cell = row.getCell(Integer.parseInt(j + ""));
                    String cellValue = null;
                    if (null != cell) {
                        switch (cell.getCellType()) {                   // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
                            case 0:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                                } else {
                                    cell.setCellType(1);
                                    cellValue = cell.getStringCellValue();
                                }
                                break;
                            case 1:
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2:
                                cellValue = cell.getStringCellValue();
                                //cellValue = cell.getNumericCellValue() + "";
                                // cellValue = String.valueOf(cell.getDateCellValue());
                                break;
                            case 3:
                                cellValue = "";
                                break;
                            case 4:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case 5:
                                cellValue = String.valueOf(cell.getErrorCellValue());
                                break;
                        }
                    } else {
                        cellValue = "";
                    }

                    varpd.put("var" + j, cellValue);

                }
                varList.add(varpd);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return varList;
    }

    public static List<Map<String, String>> readExcel(MultipartFile templetFile, int startrow, int startcol, int sheetnum) {
        List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
        if (templetFile != null && templetFile.getSize() > 0) {
            String ofn = templetFile.getOriginalFilename();// 文件名
            String extName = ""; // 扩展名格式：
            if (ofn.lastIndexOf(".") >= 0) {
                extName = ofn.substring(ofn.lastIndexOf("."));
            }
            if (".xls".equals(extName.toLowerCase())) {
                varList = readExcelByXls(templetFile, startrow, startcol, sheetnum);
            } else if (".xlsx".equals(extName.toLowerCase())) {
                varList = readExcelByXlsx(templetFile, startrow, startcol, sheetnum);
            }
        }
        return varList;
    }

    public static HSSFWorkbook readXls(InputStream inputStream) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        return hssfWorkbook;
    }

    public static XSSFWorkbook readXlsx(InputStream inputStream) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        return xssfWorkbook;
    }

    public static String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        String cellStr = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                // 读取String
                cellStr = cell.getStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                // 得到Boolean对象的方法
                cellStr = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                // 先看是否是日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellStr = formatTime(cell.getDateCellValue());
                    if (cellStr.contains("/")) {
                        cellStr = formatStringTime(cellStr);
                    } else if (cellStr.contains("-")) {
                        cellStr = formatStrTime(cellStr);
                    }
                } else {
                    // 读取数字
                    DecimalFormat df = new DecimalFormat("0");
                    cellStr = df.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                // 读取公式
                cellStr = cell.getCellFormula().toString();
                break;
        }
        if (null != cellStr) {
            return cellStr.trim();
        }
        return "";
    }

    public static String formatTime(Date date) {
        String result = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * 处理字符串“2016/1/15”或者“2016/1/15 12:45:59”
     *
     * @param s
     * @return
     */
    public static String formatStringTime(String s) {
        if (null == s || s.isEmpty()) {
            return null;
        }
        String result = null;
        if (s.contains("/")) {
            String[] str = s.split("/");
            if (str.length != 3) {
                return result;
            }
            try {
                Date date = null;
                Calendar c = Calendar.getInstance();
                if (Integer.valueOf(str[0]) > c.get(Calendar.YEAR) || Integer.valueOf(str[1]) > 12) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str[0]);
                sb.append("-");
                sb.append(str[1]);
                sb.append("-");
                if (str[2].contains(" ")) {
                    String[] str2 = str[2].split(" ");
                    if (Integer.valueOf(str2[0]) > 31) {
                        return null;
                    } else {
                        if (null != str2[1] && !str2[1].isEmpty() && str2[1].contains(":")) {
                            String[] ss = str2[1].split(":");
                            if (ss.length != 3) {
                                return null;
                            }
                            if (Integer.valueOf(ss[0]) > 23 || Integer.valueOf(ss[1]) > 59 || Integer.valueOf(ss[2]) > 59) {
                                return null;
                            }
                            sb.append(str[2]);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date = sdf.parse(sb.toString());
                        }
                    }
                }
                if (null == date) {
                    sb.append(str[2]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf.parse(sb.toString());
                }
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                result = sdf2.format(date);
            } catch (ParseException e) {
                return null;
            }
        }
        return result;
    }

    /**
     * 处理字符串“2016-1-15”或者“2016-1-15 12:45:59”
     *
     * @param s
     * @return
     */
    public static String formatStrTime(String s) {
        if (null == s || s.isEmpty()) {
            return null;
        }
        String result = null;
        if (s.contains("-")) {
            String[] str = s.split("-");
            if (str.length != 3) {
                return result;
            }
            try {
                Date date = null;
                Calendar c = Calendar.getInstance();
                if (Integer.valueOf(str[0]) > c.get(Calendar.YEAR) || Integer.valueOf(str[1]) > 12) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str[0]);
                sb.append("-");
                sb.append(str[1]);
                sb.append("-");
                if (str[2].contains(" ")) {
                    String[] str2 = str[2].split(" ");
                    if (Integer.valueOf(str2[0]) > 31) {
                        return null;
                    } else {
                        if (null != str2[1] && !str2[1].isEmpty() && str2[1].contains(":")) {
                            String[] ss = str2[1].split(":");
                            if (ss.length != 3) {
                                return null;
                            }
                            if (Integer.valueOf(ss[0]) > 23 || Integer.valueOf(ss[1]) > 59 || Integer.valueOf(ss[2]) > 59) {
                                return null;
                            }
                            sb.append(str[2]);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date = sdf.parse(sb.toString());
                        }
                    }
                }
                if (null == date) {
                    sb.append(str[2]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf.parse(sb.toString());
                }
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                result = sdf2.format(date);
            } catch (ParseException e) {
                return null;
            }
        }
        return result;

    }

    /**
     * 解析excel数据
     *
     * @param fileName
     * @param inputStream
     * @param Attributes
     * @param AttributesNotNullNumber
     * @return
     */
    public static List<List<Map<String, String>>> importData(String fileName, InputStream inputStream, List<String> Attributes, int AttributesNotNullNumber) {
        List<List<Map<String, String>>> result = new ArrayList<List<Map<String, String>>>();
        List<Map<String, String>> rightDataList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> wrongDataList = new ArrayList<Map<String, String>>();
        result.add(rightDataList);
        result.add(wrongDataList);
        Workbook wb = null;
        int version = judgeExcelVersion(fileName);
        if (0 == version) {
            return result;
        }
        try {
            wb = (1 == version) ? readXls(inputStream) : readXlsx(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            Map<String, String> wrongRowMap = new HashMap<String, String>();
            wrongRowMap.put("error", fileName + "未能正确打开");
            wrongDataList.add(wrongRowMap);
//            System.out.println(fileName + "未能正确打开");
            return result;
        }
        int number = wb.getNumberOfSheets();

        for (int k = 0; k < number; k++) {//获取excel中每个Sheet表
            Sheet sheet = wb.getSheetAt(k);
            int totalRows = sheet.getPhysicalNumberOfRows();//获取行数
            int totalCells = 0;
            if (totalRows >= 1 && sheet.getRow(0) != null) {
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();//获取单元格
            }
            List<String> firstLine = new ArrayList<String>(totalCells);
            boolean exit = false;
            for (int r = 0; (!exit) && (r < totalRows); r++) {//获取每个单元格
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                List<String> rowList = new ArrayList<String>(totalCells);
                Map<String, String> rowMap = new HashMap<String, String>(totalCells);
                boolean rowIsWrong = false;
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    cellValue = getValue(cell);
                    rowList.add(cellValue);
                }
                if (0 == r) {
                    Map<String, String> rightRowMap = new HashMap<String, String>(totalCells + 2);
                    for (int i = 0; i < rowList.size(); i++) {
                        for (Iterator<String> iter = Attributes.iterator(); iter.hasNext(); ) {
                            String entry = iter.next();
                            if (entry.equals(rowList.get(i).trim())) {
                                firstLine.add(entry);
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < AttributesNotNullNumber; i++) {
                        if (!firstLine.contains(Attributes.get(i))) {
//                            System.out.println(fileName + "中" + "sheet" + (k + 1) + "第一行不符合要求(缺少必要字段)");
                            rightRowMap.put("title", fileName + "中" + "sheet" + (k + 1) + "第一行不符合要求(与模板不符)");
                            rightDataList.add(rightRowMap);
                            exit = true;
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < firstLine.size(); i++) {
//                        String s = firstLine.get(i);
//                        for (int j = 0; (!rowIsWrong) && (j < AttributesNotNullNumber); j++) {
//                            if (s.equals(Attributes.get(j))) {
//                                if (null == rowList.get(i) || rowList.get(i).isEmpty()) {
//                                    System.out.println(fileName + "中" + "sheet" + (k + 1) + "中第第" + (r + 1) + "行数据缺少必要字段或时间不正确，没能正确处理");
//                                    rowIsWrong = true;
//                                    break;
//                                }
//                            }
//                        }
                        String value = rowList.get(i);
//                        if (null != value && !value.isEmpty()) {
                        rowMap.put(firstLine.get(i), value);
//                        }
                    }
                    Map<String, String> rightRowMap = new HashMap<String, String>(totalCells + 2);
//                    if (rowIsWrong) {
//                        Map<String, String> wrongRowMap = new HashMap<String, String>(totalCells + 2);
//                        wrongRowMap.put("SHEET", String.valueOf(k + 1));
//                        wrongRowMap.put("LINE", String.valueOf(r + 1));
//                        wrongRowMap.putAll(rowMap);
//                        wrongDataList.add(wrongRowMap);
//                    } else {
                    rightRowMap.put("SHEET", String.valueOf(k + 1));
                    rightRowMap.put("LINE", String.valueOf(r + 1));
                    rightRowMap.putAll(rowMap);
                    rightDataList.add(rightRowMap);
//                    }
                }
            }
        }
        return result;
    }
}
