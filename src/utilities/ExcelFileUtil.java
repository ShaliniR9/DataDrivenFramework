package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//create excel path
	public ExcelFileUtil(String ExcelPath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(ExcelPath);
		wb = new XSSFWorkbook(fi);
	}
	//method for count no of rows in sheet
	public int rowCount(String SheetName)
	{
		return wb.getSheet(SheetName).getLastRowNum();
	}
	//method for reading cell data
	public String getCellData(String SheetName, int row, int coloum)
	{
		String data = "";
		if(wb.getSheet(SheetName).getRow(row).getCell(coloum).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int) wb.getSheet(SheetName).getRow(row).getCell(coloum).getNumericCellValue();
			data =String.valueOf(celldata);
		}
		else
		{
			data =wb.getSheet(SheetName).getRow(row).getCell(coloum).getStringCellValue();
		}
		return data;
	}
	//method for write results
	public void setCellData(String sheetName,int row,int coloum,String status, String WriteExcel ) throws Throwable
	{
		//get sheet from workbook
		XSSFSheet ws = wb.getSheet(sheetName);
		//get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		//create cell
		XSSFCell cell = rowNum.createCell(coloum);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(coloum).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(coloum).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(coloum).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
	}
	public static void main(String[] args) throws Throwable{
		ExcelFileUtil x1 = new ExcelFileUtil("C:\\Users\\smile\\OneDrive\\Documents\\OneDrive\\Documents\\Sample.xlsx");
		//count no of rows
		int rc =x1.rowCount("Empdata");
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String fname =x1.getCellData("Empdata", i, 0);
			String mname =x1.getCellData("Empdata", i, 1);
			String lname =x1.getCellData("Empdata", i, 2);
			String eid =x1.getCellData("Empdata", i, 3);
			System.out.println(fname+"   "+mname+"   "+"   "+lname+"   "+eid);
			x1.setCellData("Empdata", i, 4, "Fail", "C:\\Users\\smile\\OneDrive\\Documents\\OneDrive\\Documents\\Results.xlsx");
		}

	}






}

