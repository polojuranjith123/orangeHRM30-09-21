package com.orangeHRM30_09_21.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xlutils {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getrowCount(String xfile,String shName) throws IOException {
		fis=new FileInputStream(xfile);
		workbook=new XSSFWorkbook(fis);
		int rowcount=workbook.getSheet(shName).getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
	}
	public static int getcellCount(String xfile,String shName ,int rowno) throws IOException {
		fis=new FileInputStream(xfile);
		workbook=new XSSFWorkbook(fis);
		int cellcount=workbook.getSheet(shName).getRow(rowno).getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
	}
	public static String  getcellData(String xfile,String shName,int rowno ,int cellno) throws IOException {
		fis=new FileInputStream(xfile);
		workbook=new XSSFWorkbook(fis);
		row=workbook.getSheet(shName).getRow(rowno);
		cell=row.getCell(cellno);
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			String celldata=formatter.formatCellValue(cell);
			return celldata;
		}catch(Exception e) {
			data ="";
			
		}
		workbook.close();
		fis.close();
		return data;
	}
	public static void setcellData(String xfile,String shName,int rowno,int cellno) throws IOException {
		fis=new FileInputStream(xfile);
		fos=new FileOutputStream(xfile);
		workbook=new XSSFWorkbook(fis);
		
		
	}
	

}
