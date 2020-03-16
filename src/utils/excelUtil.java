package utils;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtil {
	public static String[][] getDataFromExcel(String excelLocation, String sheetName) throws IOException
	{
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(excelLocation);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCol = sheet.getRow(0).getPhysicalNumberOfCells();
		String [][]tabArray = new String[totalRows][totalCol];	
		int ci = 0;
		// Store all data in an array
		for (int i = 0; i < totalRows; i++, ci++) {
			int cj = 0;
			for (int j = 0; j < totalCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getRow(i).getCell(j).toString();
			}
		}
		return (tabArray);
	}	
}
