package com.dogiant.cms.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {

    public static String getCellValue(HSSFCell cell, String type) {
        String result = "";
        if(StringUtils.isNotEmpty(type) && "1".equals(type)){
        	DecimalFormat formatter = new DecimalFormat("0.00");
            result = formatter.format(cell.getNumericCellValue());
        }else{
        	if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
        		DecimalFormat formatter = new DecimalFormat("########");
        		result = formatter.format(cell.getNumericCellValue());
        	} else{
        		result = cell.getStringCellValue();
        	}
        }
        return result;
    }

    /**
     * 校验导入excel有效性
     * 		1、是否是xls\xlsx后缀的有效excel文件
     * 		2、最大可导入记录
     * @return
     */
    public static ExcelVerifyResult validateExcel(String excelFileFileName, File excelFile, int maxExcelRows){
    	ExcelVerifyResult result = new ExcelVerifyResult();
		try {
			if(null == excelFileFileName || null == excelFile){
				result.setMessage("请选择有效的excel导入");
				return result;
			}
			if(!(excelFileFileName.toLowerCase().endsWith(".xls") || excelFileFileName.toLowerCase().endsWith(".xlsx"))){
				result.setMessage("请导入有效的excel文件。");
				return result;
			}
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFile));
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			//默认excel的书页（sheet）是"Sheet1"
	        HSSFSheet sheet = workbook.getSheetAt(0);
	        //该excel表的总行数
	        int totalRows = sheet.getLastRowNum();
	        if(totalRows > maxExcelRows){
	        	result.setMessage("对不起，您最多可以导入"+maxExcelRows+"条记录，请检查后重新导入。");
	        	return result;
	        }
	        result.setSheet(sheet);
		}catch (IOException e){
			result.setMessage("请导入有效的excel文件。");
			return result;
		}
    	return result;
    }

    /**
     * 验证期望下单日期有效性
     * 		数字表示星期，且为1-7，可为数字，可按照“1,2,3”方式
     * @param cell
     * @return
     */
//    public static boolean validateExpectDay(HSSFCell cell){
//    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//    		return inWeekdays((int) cell.getNumericCellValue());
//    	}else{
//    		String[] weeks = cell.getStringCellValue().split("\\,");
//    		for (String week : weeks) {
//				try {
//					int week1 = Integer.valueOf(week);
//					if(!inWeekdays(week1))
//						return false;
//				} catch (NumberFormatException e) {
//					return false;
//				}
//			}
//    		return true;
//    	}

//    	return cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC &&
//    		(cell.getNumericCellValue() == 1 || cell.getNumericCellValue()==2 ||
//    				cell.getNumericCellValue() == 3 || cell.getNumericCellValue()==4||
//    				cell.getNumericCellValue() == 5 || cell.getNumericCellValue() == 6 || cell.getNumericCellValue()==7);
//    }

    public static boolean validateReceiveType(HSSFCell cell){
    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    		String value = getCellValue(cell, null);
    		return "0".equals(value) || "1".equals(value);
    	}
    	return false;
    }

//    public static boolean inWeekdays(int week){
//    	boolean result = false;
//    	for (int a : Constant.WEEKDAYS_INT) {
//			if(week == a){
//				result = true;
//				break;
//			}
//		}
//    	return result;
//    }

    /**
     * 几点转换：
     * 		1、下单工作日需要转换为相应的星期
     * 		2、仓库需要转换为相应的城市
     *
     * @return
     */
    public static <T> HSSFWorkbook getWorkbook(List<T> list, String[] headers, String[] cellValues)throws Exception{
    	HSSFWorkbook workBook = new HSSFWorkbook();

    	HSSFSheet sheet = workBook.createSheet("Sheet1");

    	sheet.setDefaultColumnWidth(20);
    	HSSFCellStyle style = createCellStyle(workBook);
    	HSSFFont font = createFont(workBook, HSSFFont.BOLDWEIGHT_BOLD,HSSFColor.VIOLET.index, (short)12);
    	style.setFont(font);

    	//创建表头
    	HSSFRow headRow = sheet.createRow(0);
    	HSSFCell cell;

    	//生成表头
    	for (int i = 0; i < headers.length; i++) {
    		cell = headRow.createCell(i);
    		cell.setCellStyle(style);
    		cell.setCellValue(new HSSFRichTextString(headers[i]));
		}

    	int index = 0;
    	for (T t : list) {
    		index++;
    		HSSFRow row = sheet.createRow(index);
    		Class<? extends Object> clazz = t.getClass();
    		for (int i = 0; i < cellValues.length; i++) {
    			cell = row.createCell(i);
    			String key = cellValues[i];
    			Method method = clazz.getMethod(cellValues[i], new Class[]{});
    			Object value = method.invoke(t, new Object[] {});
    			
    			cell.setCellValue((String)value);
			}

		}
    	return workBook;
    }

    public static HSSFFont createFont(HSSFWorkbook workBook, short boldweight, short color, short size){
    	HSSFFont font = workBook.createFont();
    	font.setBoldweight(boldweight);
    	font.setColor(color);
    	font.setFontHeightInPoints(size);
    	return font;
    }

    public static HSSFCellStyle createCellStyle(HSSFWorkbook workBook){
    	HSSFCellStyle cellStyle = workBook.createCellStyle();
    	cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    	cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    	cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    	cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    	return cellStyle;
    }

    public static boolean isEmptySheet(HSSFSheet sheet) {
		return sheet.getPhysicalNumberOfRows() <= 1;// 表头占一行
	}

	public static boolean isEmptyRow(HSSFRow row) {
		boolean empty = true;
		if (row == null) {
			return empty;
		}
		for (Iterator<Cell> iter = row.cellIterator(); iter.hasNext();) {
			Cell cell = iter.next();
			if (cell != null && StringUtils.isNotBlank(cell.toString())) {
				empty = false;
				break;
			}
		}
		return empty;
	}

	public static boolean isBlankCell(HSSFCell cell) {
		return cell == null || StringUtils.isBlank(cell.toString());
	}

	public static boolean isNotBlankCell(HSSFCell cell) {
		return cell != null && StringUtils.isNotBlank(cell.toString());
	}

    /**
     * 判断cell的类型为Excel公式
     *
     * @param cell
     * @return
     */
    public static boolean isCellTypeFormula(HSSFCell cell) {
        return null != cell && HSSFCell.CELL_TYPE_FORMULA == cell.getCellType();
    }
    
    public static class ExcelVerifyResult{

    	private HSSFSheet sheet;
    	private String message;
    	public HSSFSheet getSheet() {
    		return sheet;
    	}
    	public void setSheet(HSSFSheet sheet) {
    		this.sheet = sheet;
    	}
    	public String getMessage() {
    		return message;
    	}
    	public void setMessage(String message) {
    		this.message = message;
    	}
    }

}