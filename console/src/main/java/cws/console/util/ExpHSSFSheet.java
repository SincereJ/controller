package cws.console.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExpHSSFSheet {

	private int index = 0;
	private boolean isFull = false;
	private HSSFSheet hssfSheet;
	
	public ExpHSSFSheet(HSSFSheet hssfSheet) {
		this.hssfSheet = hssfSheet;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public HSSFSheet getHssfSheet() {
		return hssfSheet;
	}

	public void setHssfSheet(HSSFSheet hssfSheet) {
		this.hssfSheet = hssfSheet;
	}
	
}
