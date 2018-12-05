package com.fms.templateSys.main;

import com.fms.platform.common.BaseModel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hy.common.Help;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * 解析XLS类
 *
 * @author malong
 */
@SuppressWarnings({"deprecation", "JavaDoc", "WeakerAccess","UnusedReturnValue"})
public class POIUtil {


    /**
     * 自动匹配读取的文件
     *
     * @param file
     * @throws IOException
     */
    public void read(MultipartFile file) throws IOException {
        if (StringUtils.equals(file.getContentType(), "")) {
            readXls(file);
        }
        if (StringUtils.equals(file.getContentType(), "")) {
            readXlsx(file);
        }
    }

    /**
     * 读取某个sheetXLS文件
     *
     * @param file
     * @param sheetName 表名
     * @return Book&lt;Sheet&lt;Row&lt;Column&gt;&gt;&gt;
     * @throws IOException
     */
    public static List<List<Object>> readXlsBySheet(File file, String sheetName) throws IOException {
        InputStream is = new FileInputStream(file);
        // 取得workbook
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet hssfSheet = hssfWorkbook.getSheet(sheetName);
        List<List<Object>> sheetList = new ArrayList<>();
        // 循环行Row
        for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow == null) {
                continue;
            }
            List<Object> rowList = new ArrayList<>();
            // 循环列Cell
            for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                HSSFCell hssfCell = hssfRow.getCell(cellNum);
                if (hssfCell == null) {
                    continue;
                }
                System.out.print("    " + getValue(hssfCell));
                rowList.add(getValue(hssfCell));
            }// end cell
            sheetList.add(rowList);
            System.out.println();
        }// end row
        return sheetList;
    }

    /**
     * 读取XLS文件
     * 适合于非大量数据的Xls
     * 大量数据请单独进行处理
     *
     * @param file
     * @return Book&lt;Sheet&lt;Row&lt;Column&gt;&gt;&gt;
     * @throws IOException
     */
    public List<List<List<Object>>> readXls(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        // 取得workbook
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<List<Object>>> bookList = new ArrayList<>();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            List<List<Object>> sheetList = new ArrayList<>();
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                List<Object> rowList = new ArrayList<>();
                // 循环列Cell
                for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                    HSSFCell hssfCell = hssfRow.getCell(cellNum);
                    if (hssfCell == null) {
                        continue;
                    }
                    System.out.print("    " + getValue(hssfCell));
                    rowList.add(hssfCell);
                }// end cell
                sheetList.add(rowList);
                System.out.println();
            }// end row
            bookList.add(sheetList);
        }// end sheet
        return bookList;
    }

    /**
     * 读取xlsx文件
     *
     * @param file
     * @throws IOException
     */
    public void readXlsx(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }

            // 循环行Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }

                // 循环列Cell
                for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    XSSFCell xssfCell = xssfRow.getCell(cellNum);
                    if (xssfCell == null) {
                        continue;
                    }
                    System.out.print("   " + getValue(xssfCell));
                }
                System.out.println();
            }
        }
    }

    public static String getValue(HSSFCell hssfCell) {
        if (hssfCell == null) return "";

        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
//			System.out.println(hssfCell.getBooleanCellValue()+"{}{}");
            System.out.println(String.valueOf(hssfCell.getBooleanCellValue()) + "{}{}");
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                Date date = hssfCell.getDateCellValue();
                System.out.println(DateFormatUtils.format(date, "yyyy-MM") + "{}{}");
                return DateFormatUtils.format(date, "yyyy-MM");
            } else {
                System.out.println(hssfCell.getNumericCellValue() + "{}{}");
                return String.valueOf(hssfCell.getNumericCellValue());
            }
        } else {
            System.out.println(hssfCell.getStringCellValue() + "{}{}");
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    private String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }

    /**
     * 根据提供的名称和key，获取Bean的属性值，最终导出
     *
     * @param linkedHashMap
     * @param trList
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void export(LinkedHashMap<String, String> linkedHashMap,
                              List<? extends BaseModel> trList, OutputStream ops) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
        export("表一", linkedHashMap, trList, ops);
    }

    /**
     * 根据提供的名称和key，获取Bean的属性值，最终导出
     *
     * @param sheetName
     * @param titleMap
     * @param trList
     * @param ops
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IOException
     */
    public static void export(String sheetName, LinkedHashMap<String, String> titleMap,
                              List<? extends BaseModel> trList, OutputStream ops) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
        if (Help.isNull(titleMap) || Help.isNull(trList)) {
            return;
        }
        int rowSize = trList.size();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        Iterator<Entry<String, String>> entrySet = titleMap.entrySet().iterator();
        //标题
        int colnum = 0;
        HSSFRow row0 = sheet.createRow(0);
        while (entrySet.hasNext()) {
            Entry<String, String> entry = entrySet.next();
            HSSFCell cell = row0.createCell(colnum);
//			String title=entry.getKey();
            String title = entry.getValue();
            cell.setCellValue(title);
            colnum++;
        }
        //内容
//		System.out.println("~~~~~~~~~"+rowSize);
        for (int rownum = 1; rownum <= rowSize; rownum++) {
            BaseModel bean = trList.get(rownum - 1);
            colnum = 0;
            HSSFRow row = sheet.createRow(rownum);
            entrySet = titleMap.entrySet().iterator();
            while (entrySet.hasNext()) {
                Entry<String, String> entry = entrySet.next();
                HSSFCell cell = row.createCell(colnum);
                String key = entry.getKey();
                insertCell(bean, cell, key);
                colnum++;
            }
        }// end for row
        try {
            hssfWorkbook.write(ops);
        } finally {
            ops.flush();
        }
    }


    private static void insertCell(BaseModel bean, HSSFCell cell, String key) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        String methodName = "get" + StringUtils.capitalize(key);
        Method method = bean.getClass().getMethod(methodName);
        Object val = method.invoke(bean);
        if (val == null) cell.setCellValue("");
        if (val instanceof String) {
            String value = (String) val;
            cell.setCellValue(value);
        } else if (val instanceof Double) {
            Double value = (Double) val;
            cell.setCellValue(value);
        } else if (val instanceof Date) {
            Date value = (Date) val;
            cell.setCellValue(value);
        } else if (val instanceof Calendar) {
            Calendar value = (Calendar) val;
            cell.setCellValue(value);
        } else {
            if (val == null) val = "";
            cell.setCellValue(val.toString());
        }
    }


    /*
     * 替换日期格式
     */
    public static String rule(String rule) {
        return rule.replaceAll("/", "-");
    }

}
