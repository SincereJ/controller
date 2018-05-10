package cws.console.util;

public class ExpDesFactory {
	
	
	public static ExpDescription getDEscription(String tableName, String sheetName, String expType) {
		String[] fileds = null;
		String[] colNames = null;
		
		if(expType.equals("0")) {
			fileds = ExpConfig.FILEDS_0;
			colNames = ExpConfig.COLNAMES_0;
		}else if(expType.equals("1")) {
			fileds = ExpConfig.FILEDS_1;
			colNames = ExpConfig.COLNAMES_1;
		}else if(expType.equals("2")) {
			fileds = ExpConfig.FILEDS_2;
			colNames = ExpConfig.COLNAMES_2;
		}else if(expType.equals("3")) {
			fileds = ExpConfig.FILEDS_3;
			colNames = ExpConfig.COLNAMES_3;
		}
		
		return new ExpDescription(tableName,sheetName,expType,fileds,colNames);
		
	}
	
	public static ExpDescription getDEscription(String expType) {
		String[] fileds = null;
		String[] colNames = null;
		String tableName = "";
		String sheetName = "";
		
		if(expType.equals("0")) {
			tableName = ExpConfig.TABLE_NAME_0;
			sheetName = ExpConfig.SHEET_NAME_0;
			fileds = ExpConfig.FILEDS_0;
			colNames = ExpConfig.COLNAMES_0;
		}else if(expType.equals("1")) {
			tableName = ExpConfig.TABLE_NAME_1;
			sheetName = ExpConfig.SHEET_NAME_1;
			fileds = ExpConfig.FILEDS_1;
			colNames = ExpConfig.COLNAMES_1;
		}else if(expType.equals("2")) {
			tableName = ExpConfig.TABLE_NAME_2;
			sheetName = ExpConfig.SHEET_NAME_2;
			fileds = ExpConfig.FILEDS_2;
			colNames = ExpConfig.COLNAMES_2;
		}else if(expType.equals("3")) {
			tableName = ExpConfig.TABLE_NAME_3;
			sheetName = ExpConfig.SHEET_NAME_3;
			fileds = ExpConfig.FILEDS_3;
			colNames = ExpConfig.COLNAMES_3;
		}
		
		return new ExpDescription(tableName,sheetName,expType,fileds,colNames);
		
	}

}
