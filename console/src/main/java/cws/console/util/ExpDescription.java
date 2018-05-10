package cws.console.util;

public class ExpDescription {

	private String tableName;
	private String sheetName;
	private String expType;
	
	private String[] fileds;
	private String[] colName;
	
	
	
	public ExpDescription(String tableName, String sheetName, String expType, String[] fileds, String[] colName) {
		this.tableName = tableName;
		this.sheetName = sheetName;
		this.expType = expType;
		this.fileds = fileds;
		this.colName = colName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String[] getFileds() {
		return fileds;
	}
	public void setFileds(String[] fileds) {
		this.fileds = fileds;
	}
	public String[] getColName() {
		return colName;
	}
	public void setColName(String[] colName) {
		this.colName = colName;
	}
	
}
