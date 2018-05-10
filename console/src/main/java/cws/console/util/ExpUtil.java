package cws.console.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

public class ExpUtil {

	
	private boolean hasTableHeader() {
		return ExpConfig.IS_TABLE_HEAD;
	}
	
	
	/**
	 * �������д洢��ͷ��Ϣ
	 * @param sheet
	 */
	public void setSheetTitle(HSSFSheet sheet, int filedLength) {
		HSSFRow rowtitle = sheet.createRow((short) 0);// ��ӱ�ͷ
		HSSFCell celltitle = rowtitle.createCell((short) 0);
		celltitle.setEncoding(HSSFCell.ENCODING_UTF_16);// �����������
		celltitle.setCellValue((String) "���");//���ñ�ͷ����

		rowtitle = sheet.createRow((short) 1);// ����һ�����ڴ�ű�ͷ����
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (filedLength - 1)));// �ϲ���һ��
		//rowtitle = sheet.getRow(0);// ��õ�һ�е�����
		//celltitle = rowtitle.getCell((short) 0);// ��õ�һ�е�һ����Ԫ�������
	}
	
	/**
	 * 
	 * @param sheet
	 * @param filedLength
	 * @param tableName
	 */
	public void setSheetTitle(HSSFSheet sheet, int filedLength,String tableName) {
		HSSFRow rowtitle = sheet.createRow((short) 0);// ��ӱ�ͷ
		HSSFCell celltitle = rowtitle.createCell((short) 0);
		celltitle.setEncoding(HSSFCell.ENCODING_UTF_16);// �����������
		celltitle.setCellValue((String) tableName);//���ñ�ͷ����

		//rowtitle = sheet.createRow((short) 1);// ����һ�����ڴ�ű�ͷ����
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (filedLength - 1)));// �ϲ���һ��
	}
	
	/**
	 * ��ͷ��Ϣ
	 * @param sheet
	 * @param colName
	 */
	public void setFirstRowTitle(HSSFSheet sheet, String[] colName,int j) {
		//int j = j;// ռ�õ�һ�У�����Ǹ����Ƿ�����ͷȷ���ģ�û������Ϊ0,������Ϊ1��
		HSSFRow rowfield = sheet.createRow((short) j);// ����һ�����ڴ����ͷ����
		for (int i = 0; i < colName.length; i++)// ���ݻ�ȡ������ͷ���ݣ�ѭ��������ͷ������
		{
			HSSFCell cellfield = rowfield.createCell((short) i);
			cellfield.setEncoding(HSSFCell.ENCODING_UTF_16);
			cellfield.setCellValue(colName[i]);
			// setFieldStyle(wb, cellfield);//������ͷ��ʽ
			int xx = cellfield.getStringCellValue().length();
			sheet.setColumnWidth((short) i, (short) (600 * xx));
		}
	}
	
	/**
	 * ������Ϣ
	 * @param sheet
	 * @param rs
	 * @param fields
	 * @param j
	 * @throws SQLException
	 */
	public void setSheetBody(HSSFSheet sheet,ResultSet rs,String[] fields, int j) {
		int count = 0;
		try {
			while (rs.next()) {
				HSSFRow rowbody = sheet.createRow((short) (count + j + 1));
				for (int i = 0; i < fields.length; i++) {
					HSSFCell cellbody = rowbody.createCell((short) i);
					cellbody.setEncoding(HSSFCell.ENCODING_UTF_16);
					cellbody.setCellValue(rs.getString(fields[i]));
				}
				count++;
			}
		} catch (SQLException e) {
			//if(logger){
			//  logger.error("" + e);
			//}
			//e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param sheet
	 * @param dataList
	 * @param fields
	 * @param j
	 */
	@SuppressWarnings("rawtypes")
	public void setSheetBody(HSSFSheet sheet,List dataList,String[] fields, int j) {
		int count = 0;
		Iterator itera = dataList.iterator();
		while (itera.hasNext()) {
			HSSFRow rowbody = sheet.createRow((short) (count + j + 1));
			HashMap map = (HashMap) itera.next();
			for (int i = 0; i < fields.length; i++) {				
				HSSFCell cellbody = rowbody.createCell((short) i);
				cellbody.setEncoding(HSSFCell.ENCODING_UTF_16);
				cellbody.setCellValue(map.get(fields[i]).toString());
			}
			count++;
		}
	}
	
	/**
	 * @param out
	 * @param rs
	 * @param sheetName
	 * @param fields
	 * @param colName
	 * @return
	 */
	public HSSFWorkbook export(ExpLinePipe expLinePipe,ServletOutputStream out, ResultSet rs, String sheetName, String[] fields, String[] colName) {

		HSSFWorkbook workbook = expLinePipe.getWorkbook();
					
		Iterator<ExpHSSFSheet> itera = expLinePipe.getSheets().iterator();
		while(itera.hasNext()) {
			int j = 1; // ռ�õ�һ�У�����Ǹ����Ƿ�����ͷȷ���ģ�û������Ϊ0,������Ϊ1��
			ExpHSSFSheet expHSSFSheet = expLinePipe.getNextSheet();
			HSSFSheet sheet = expHSSFSheet.getHssfSheet();
			
			workbook.setSheetName(expHSSFSheet.getIndex(), (String) sheetName + "-"+ expHSSFSheet.getIndex(), (short) 1);
			
			setSheetTitle(sheet,fields.length);
			setFirstRowTitle(sheet,colName,j);
			setSheetBody(sheet, rs, fields, j);
		}

		return workbook;
	}
	
	
	public void afterExport(ExpHSSFSheet expHSSFSheet, ExpData expData) {
		expHSSFSheet.setFull(true);
		expData.setDone(true);
	}
	
	/**
	 * 
	 * @param expLinePipe
	 * @param out
	 * @param rs
	 * @param sheetName
	 * @param fields
	 * @param colName
	 * @return
	 */
	public HSSFWorkbook export(ExpLinePipe expLinePipe,String sheetName, String[] fields, String[] colName) {

		HSSFWorkbook workbook = expLinePipe.getWorkbook();
		
		Iterator<ExpHSSFSheet> itera = expLinePipe.getSheets().iterator();
		while(itera.hasNext()) {
			int j = 1; // ռ�õ�һ�У�����Ǹ����Ƿ�����ͷȷ���ģ�û������Ϊ0,������Ϊ1��
			
			//if(hasTableHeader()) {
				
			//}
			
			ExpHSSFSheet expHSSFSheet = expLinePipe.getNextSheet();			
			if(expHSSFSheet == null) {
				break;
			}
			HSSFSheet sheet = expHSSFSheet.getHssfSheet();
			ExpData expData = expLinePipe.getNextData();
			
			workbook.setSheetName(expHSSFSheet.getIndex(), (String) sheetName + "-"+ expHSSFSheet.getIndex(), (short) 1);
			
			setSheetTitle(sheet,fields.length);
			setFirstRowTitle(sheet,colName,j);
			setSheetBody(sheet, expData.getDataList(), fields, j);
			
			afterExport(expHSSFSheet,expData);
		}

		return workbook;

	}

	/**
	 * expDescription
	 * @param expLinePipe
	 * @param sheetName
	 * @param fields
	 * @param colName
	 * @return
	 */
	public HSSFWorkbook export(ExpLinePipe expLinePipe, ExpDescription expDesc) {

		HSSFWorkbook workbook = expLinePipe.getWorkbook();
		
		Iterator<ExpHSSFSheet> itera = expLinePipe.getSheets().iterator();
		while(itera.hasNext()) {
			int j = 0; // ռ�õ�һ�У�����Ǹ����Ƿ�����ͷȷ���ģ�û������Ϊ0,������Ϊ1��
			
			if(hasTableHeader()) {
				j =1;
			}
			
			ExpHSSFSheet expHSSFSheet = expLinePipe.getNextSheet();			
			if(expHSSFSheet == null) {
				break;
			}
			HSSFSheet sheet = expHSSFSheet.getHssfSheet();
			ExpData expData = expLinePipe.getNextData();
			
			workbook.setSheetName(expHSSFSheet.getIndex(), (String) expDesc.getSheetName() + "-"+ expHSSFSheet.getIndex(), (short) 1);
			
			
			if(hasTableHeader()) {
				setSheetTitle(sheet,expDesc.getFileds().length,expDesc.getTableName());
			}
			
			setFirstRowTitle(sheet,expDesc.getColName(),j);
			setSheetBody(sheet, expData.getDataList(), expDesc.getFileds(), j);
			
			afterExport(expHSSFSheet,expData);
		}

		return workbook;

	}
	
	@SuppressWarnings("unused")
	private void setFieldStyle(HSSFWorkbook wb, HSSFCell row) {
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		;
		font.setFontName("����");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		cellStyle.setFont(font);

		row.setCellStyle(cellStyle);
	}

}
