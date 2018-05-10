package cws.console.util;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExpWriteOutPrint {

	public void writeOutPrint(ServletOutputStream out, HSSFWorkbook workbook) {
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error(e)
		}
	}
	
	public void writeOutPrint(ServletOutputStream out, HSSFWorkbook workbook,ExpDescription expDesc) {
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error(e)
		}
	}
	
}
