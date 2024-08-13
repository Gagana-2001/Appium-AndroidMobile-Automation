package appium.com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {

	public Map<String, String> getData(String filePath) throws IOException {
		Map<String, String> dataMap = new HashMap<String, String>();

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cellIterator = firstRow.iterator();

		int colIndex = 0;
		Map<String, Integer> headerMap = new HashMap<>();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			headerMap.put(cell.getStringCellValue().trim(), colIndex);
			colIndex++;
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row currentRow = sheet.getRow(i);
			if (currentRow != null) {
				for (String key : headerMap.keySet()) {
					Cell cell = currentRow.getCell(headerMap.get(key));
					String cellValue = "";
					if (cell != null) {
						if (cell.getCellType() == CellType.STRING) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == CellType.NUMERIC) {
							cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
						}
					}
					dataMap.put(key, cellValue);
				}
			}
		}

		workBook.close();
		fis.close();
//		for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}

		return dataMap;
	}
}