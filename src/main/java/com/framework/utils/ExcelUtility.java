package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static int getRowCount(String Path, String SheetName) throws IOException {
		
		fi = new FileInputStream(Path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(SheetName);
		int rowcount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public static String getCellData(String Path, String SheetName, int RowNum, int CellNum) throws IOException {
		fi = new FileInputStream(Path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(SheetName);
		row = sheet.getRow(RowNum);
		cell = row.getCell(CellNum);
		
		String data;
		try {
		DataFormatter formatter = new DataFormatter();
		data = formatter.formatCellValue(cell);
		} catch(Exception e)
		{
		 System.out.println(e);
		 data = "";
		}
		
		return data ;
	}
	
	public static void setCellData(String Path, String SheetName, int RowNum, int CellNum, String data) throws IOException {
		fi = new FileInputStream(Path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(SheetName);
		row = sheet.getRow(RowNum);
		cell = row.createCell(CellNum);
		cell.setCellValue(data);
		
		 fo = new FileOutputStream(Path);
		 wb.write(fo);
		 wb.close();
		 fi.close();
		 fo.close();
		 
	}
	
	
	

}
