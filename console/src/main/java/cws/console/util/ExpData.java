package cws.console.util;

import java.util.List;

public class ExpData {
	
	private int index = 0;
	private boolean isDone = false;
	@SuppressWarnings("rawtypes")
	private List dataList ;
	
	@SuppressWarnings("rawtypes")
	public ExpData(List dataList) {
		this.dataList = dataList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}
	
}
