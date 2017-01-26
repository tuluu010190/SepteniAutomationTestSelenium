package utils;

import java.io.FileInputStream;
import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static HSSFSheet HSSExcelWSheet;
	private static HSSFWorkbook HSSExcelWBook;


	// This method is to get the path of the file 'test.xlsx' in folder resources
	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path, String SheetName) {
		try {
			File file = new File(Path);
			System.out.println("path file " + file.getAbsolutePath());
			if (file.exists() && file.isFile()) {
				// Open the Excel file
				FileInputStream ExcelFile = new FileInputStream(Path);
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				System.out.println("sheet name " + ExcelWSheet.getSheetName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * get Data using XSS
	 */
	public static String[][] getData(){
		int xRows = ExcelWSheet.getLastRowNum();
		int xCols = ExcelWSheet.getRow(0).getLastCellNum();
		int nRow = 1;
		String[][] xData = new String[xRows][xCols];

		for (int i = 0; i < xRows; i++) {
			XSSFRow row = ExcelWSheet.getRow(nRow);
			for (int j = 0; j < xCols; j++) {
				XSSFCell cell = row.getCell(j);
				if(cell==null)
					continue;
				else{
					if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC){
						String value = String.valueOf((int)Math.round(cell.getNumericCellValue()));
						if(value!=null)
							xData[i][j] = value;
					}
					else{
						String value =  cell.getStringCellValue();
						if(value!=null)
							xData[i][j] = value;
					}
				}
			}
			nRow++;
		}
		return xData;
	}

	/**
	 * set Excel File using HSS
	 */
	public static void setExcelFileHSS(String Path,String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			HSSExcelWBook = new HSSFWorkbook(ExcelFile);
			HSSExcelWSheet = HSSExcelWBook.getSheet(SheetName);
		} catch (Exception e){
			throw (e);
		}
	}

	/**
	 * get Data using HSS
	 */
	public static String[][] getDataHSS(){
		int xRows = HSSExcelWSheet.getLastRowNum();
		int xCols = HSSExcelWSheet.getRow(0).getLastCellNum();
		int nRow = 1;
		String[][] xData = new String[xRows][xCols];

		for (int i = 0; i < xRows; i++) {
			HSSFRow row = HSSExcelWSheet.getRow(nRow);
			for (int j = 0; j < xCols; j++) {
				HSSFCell cell = row.getCell(j);
				if(cell==null)
					continue;
				else{
					if (cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC){
						String value = String.valueOf((int)Math.round(cell.getNumericCellValue()));
						if(value!=null)
							xData[i][j] = value;
					}
					else{
						String value =  cell.getStringCellValue();
						if(value!=null)
							xData[i][j] = value;
					}
				}
			}
			nRow++;
		}
		return xData;
	}

	/**
	 * Read data from excel file then return the two dimension array
	 * @param filePath
	 * @param sheetName
	 */
	public static String[][] getExcelDataFromSource(String userDataFile, String userSheet) throws Exception{
		String[][] arrayData = null;
		setExcelFile(userDataFile,userSheet);
		arrayData = getData();
		return arrayData;
	}

}
