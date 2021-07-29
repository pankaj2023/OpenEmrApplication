	package com.vfislk.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class DemoTest {

	@Test
	public void readProperties() throws IOException
	{
		FileInputStream file=new FileInputStream("src/test/resources/testdata/data.properties");
		
		Properties prop=new Properties();
		prop.load(file);
		
		String browserName=prop.getProperty("browser1", "chrome");
		System.out.println(browserName);
		
	}

	@Test
	public void excelRead() throws IOException {		
		FileInputStream file = new FileInputStream("src/test/resources/testdata/OpenEMRData.xlsx"); // location - read
		
		XSSFWorkbook book = new XSSFWorkbook(file); 
		XSSFSheet sheet = book.getSheet("addPatientTest");
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		
		int cellCount=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(cellCount);
		
		DataFormatter format = new DataFormatter();
		
		Object[][] main=new Object[rowCount-1][cellCount];
		
		for (int r = 1; r < rowCount; r++) 
		{
			XSSFRow row = sheet.getRow(r);
			for (int c = 0; c < cellCount; c++) 
			{		
				XSSFCell cell = row.getCell(c);		
				String cellValue = format.formatCellValue(cell);
				System.out.println(cellValue);
				main[r-1][c]=cellValue;
			}
		}

		book.close();
		file.close();
		
	}	
}	
