package cws.console.util;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExpFactory {

	private ExpLinePipe expLinePipe = new ExpLinePipe();
	private ExpUtil expUtil = new ExpUtil();
	private ExpWriteOutPrint writeOutPrint = new ExpWriteOutPrint();
	
	private void writeOutPrint(ServletOutputStream out, HSSFWorkbook workbook) {
		writeOutPrint.writeOutPrint(out, workbook);
	}
	
	private void writeOutPrint(ServletOutputStream out, HSSFWorkbook workbook, ExpDescription expDesc) {
		writeOutPrint.writeOutPrint(out, workbook,expDesc);
	}
	
	@SuppressWarnings("rawtypes")
	private void doExpAwared(long total,List dataList) {
		expLinePipe.initExpLinePipe(total,dataList);
	}
	
	private void doExpAwaredSingle(long total) {
		expLinePipe.initExpLinePipe(total);
	}
	
	@SuppressWarnings("rawtypes")
	private void doExpAwaredDesc(long total,List dataList, ExpDescription expDesc) {
		expLinePipe.initExpLinePipe(total,dataList,expDesc);
	}
	
	
	/**
	 * resultSet 
	 * @param out
	 * @param rs
	 * @param sheetName
	 * @param fields
	 * @param colName
	 * @param total
	 */
	public void doExport(ServletOutputStream out, ResultSet rs, String sheetName, String[] fields, String[] colName,long total) {
		doExpAwaredSingle(total);
		HSSFWorkbook workbook = doExport(expLinePipe,out,rs,sheetName,fields,colName);
		writeOutPrint(out, workbook);
	}
	
	private HSSFWorkbook doExport(ExpLinePipe expLinePipe,ServletOutputStream out, ResultSet rs, String sheetName, String[] fields, String[] colName) {
		return expUtil.export(expLinePipe,out, rs, sheetName, fields, colName);
	}
	
	/**
	 * datalist exp
	 * @param out
	 * @param dataList
	 * @param sheetName
	 * @param fields
	 * @param colName
	 * @param total
	 */
	@SuppressWarnings("rawtypes")
	public void doExport(ServletOutputStream out,List dataList, String sheetName, String[] fields, String[] colName,long total) {
		doExpAwared(total,dataList);
		HSSFWorkbook workbook = doExport(expLinePipe,sheetName,fields,colName);
		writeOutPrint(out, workbook);
	}
	
	private HSSFWorkbook doExport(ExpLinePipe expLinePipe, String sheetName, String[] fields, String[] colName) {
		return expUtil.export(expLinePipe, sheetName, fields, colName);
	}
	
	/**
	 * expDecription
	 * @param out
	 * @param dataList
	 * @param expDescription
	 */
	@SuppressWarnings("rawtypes")
	public void doExport(ServletOutputStream out,List dataList, ExpDescription expDesc,long total) {
		doExpAwaredDesc(total, dataList, expDesc);
		HSSFWorkbook workbook = doExport(expLinePipe,expDesc);
		writeOutPrint(out, workbook,expDesc);
	}
	
	public HSSFWorkbook doExport(ExpLinePipe expLinePipe, ExpDescription expDesc) {
		return expUtil.export(expLinePipe, expDesc);
	}
}
