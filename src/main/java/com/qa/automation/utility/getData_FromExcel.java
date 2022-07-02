package com.qa.automation.utility;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class getData_FromExcel {

	static Xls_Reader reader;

	public static Map<String, String> globalConfigmap;
	public static Object[][] getDataFrom_excel(String sheetName, String testCaseName) {

		Hashtable<String, String> hashTable = null;
		Object[][] dataArray = null;

		int ci = 0;
		int cj = 0;
		boolean isSkipped = false;
		int RowCount = 0;
		try {
				String rootpath=System.getProperty("user.dir");
				
			reader = new Xls_Reader(rootpath+"\\testData\\testData.xlsx");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int RowCOunt = reader.getRowCount(sheetName);
		int ColumnCount = reader.getColumnCount(sheetName);
		
		RowCount = reader.getExecutableRowCount(sheetName, testCaseName); /* pass the total row count */
		dataArray = new Object[RowCount][1];
		for (int i = 1; i <= reader.getRowCount(sheetName); i++, ci++) {

			String executeStatus = reader.getCellData(sheetName, 0, i);
			String testCase = reader.getCellData(sheetName, 1, i);
			if (executeStatus.equalsIgnoreCase("End")) {
				break;
			}
			hashTable = new Hashtable<String, String>();
			for (int j = 1; j < reader.getColumnCount(sheetName); j++, cj++) {
				

				if (executeStatus.equalsIgnoreCase("Yes") && testCase.equalsIgnoreCase(testCaseName)) {

					String columName = reader.getCellData(sheetName, j, 1).toString();

					String ColumnVal = reader.getCellData(sheetName, columName, i).toString();

					hashTable.put(columName, ColumnVal);
					System.out.println(hashTable.get(columName));

				} else {
					isSkipped = true;
					ci = ci - 1;
					break;
				}
			}
			if (isSkipped) {
				System.out.println("Row is skipped");
				isSkipped = !isSkipped;
			} else {

				dataArray[ci][0] = hashTable;
			}

		}
		return (dataArray);
	}

	
	public static void getDataFromConfigFile() throws FileNotFoundException {

		reader = new Xls_Reader(System.getProperty("user.dir") + "\\config\\globalParameter.xlsx");
		globalConfigmap = new HashMap<String, String>();
		if (null != reader) {
			if (reader.isSheetExist("globalParamter_sheet")) {
				String globalParamSheetName = "globalParamter_sheet";
				int totalRowCount_globalParam = reader.getRowCount(globalParamSheetName);

				for (int row = 1; row <= totalRowCount_globalParam; row++) {
					String globalParamName = reader.getCellData(globalParamSheetName, "Name", row).toString();
					String globalParamValue = reader.getCellData(globalParamSheetName, "Value", row).toString();

					globalConfigmap.put(globalParamName, globalParamValue);
					System.out.println(globalConfigmap.get(globalParamName));
				}
			}

		}
	}
}
