package cws.console.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExpLinePipe {

	private HSSFWorkbook workbook = null;
	private ArrayList<ExpHSSFSheet> sheets = null;
	private ArrayList<ExpData> dataList = null;
	private ExpDescription expDescription = null;
	
	@SuppressWarnings({ "rawtypes","unchecked"})
	public void initExpLinePipe(long total, List dataList) {
		initExpLinePipe(total);
		createNewExpData(dataList);
	}
	
	
	public void initExpLinePipe(long total) {
		if(total > 0) {			
			workbook = new HSSFWorkbook();
			sheets = new ArrayList<ExpHSSFSheet>();
			dataList = new ArrayList<ExpData>();
			
			int sheetsNumb = (int)calaSheetNum(total);
			
			createNewSheet(sheetsNumb);
		}
	}	
	
	@SuppressWarnings("rawtypes")
	public void initExpLinePipe(long total, List dataList,ExpDescription expDesc) {
		initExpLinePipe(total,dataList);
		setExpDescription(expDesc);
	}
	
	public long calaSheetNum(long total) {
		int sheetsNumb = 1;
		if(total < ExpConfig.DEFAULT_TOTAL && total > ExpConfig.MAX_ITEM_NUM) {
			sheetsNumb = (int) (total / ExpConfig.MAX_ITEM_NUM + 1);
			
		}else if(total < ExpConfig.DEFAULT_TOTAL && total <= ExpConfig.MAX_ITEM_NUM ) {
			sheetsNumb = 1;
		}
		return sheetsNumb;
	}
	
	public ExpData getNextData() {
		ExpData expData = null;
		for(int i=0;i<dataList.size();i++) {
			if(dataList.get(i).isDone() ==  false) {
				expData = dataList.get(i);
				break;
			}
		}
		return expData;
	}
	
	public ExpHSSFSheet getNextSheet() {
		ExpHSSFSheet sheet = null;
		for(int i=0;i<sheets.size();i++) {
			if(sheets.get(i).isFull() ==  false) {
				sheet = sheets.get(i);
				break;
			}
		}
		return sheet;
	}
	
	@SuppressWarnings("rawtypes")
	public void createNewExpData(List<ExpData> dList) {
		for(int i=0;i<dList.size() ;i++) {
			dataList.add(new ExpData((List)dList.get(i)));
		}
	}
	
	public void createNewSheet(int nums) {
		for(int i =0 ;i<nums ;i ++) {
			sheets.add(new ExpHSSFSheet(workbook.createSheet()));
		}
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public ArrayList<ExpHSSFSheet> getSheets() {
		return sheets;
	}

	public ArrayList<ExpData> getDataList() {
		return dataList;
	}


	public ExpDescription getExpDescription() {
		return expDescription;
	}

	public void setExpDescription(ExpDescription expDescription) {
		this.expDescription = expDescription;
	}
	
	
}
