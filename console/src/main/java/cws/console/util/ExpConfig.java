package cws.console.util;

/**
* һ��sheetҳ�ܴ� 65536 ������  60000
 * һ��excel�ܴ�� 256 ��sheetҳ  �� 200
 * һ��excel ��Լ�洢  1200 W ������
 * @author Administrator
 */
public class ExpConfig {

	public static final long MAX_WORKBOOK_NUM = 100;
	//public static final long MAX_ITEM_NUM = 60000;
	public static final long MAX_ITEM_NUM = 250;
	public static final long MAX_SHEET_NUM = 200;
	public static final long DEFAULT_TOTAL = 100000;
	public static final long MAX_TOATAL = 60000 * 200;	
	
	//exp table header if
	public static final boolean IS_TABLE_HEAD = false;
	
	public static final String DEFAULT_EXT_NAME = ".xls";
	
	//exp type equal 0
		//���š���������֯��������������֯�������ֻ���(�м�4λ����)������״̬�����ʱ��
		public static final String TABLE_NAME_0 = "Ա��������";
		public static final String SHEET_NAME_0 = "sheet";
		public static final String[] FILEDS_0 = new String[] {"a","b","c","d","e","f"};	
		public static final String[] COLNAMES_0 = new String[] {"����","����","��֯","���ֻ���","����״̬","���ʱ��"};
		
		//exp type equal 1 
		//���š�������ϵͳ����ɫ
		public static final String TABLE_NAME_1 = "Ա���Ľ�ɫ����";
		public static final String SHEET_NAME_1 = "sheet";
		public static final String[] FILEDS_1 = new String[] {"a","b","c","d"};	
		public static final String[] COLNAMES_1 = new String[] {"����","����","ϵͳ","��ɫ"};
		
		//exp type equal 2
		//���š���������Ȩ���ƣ����ʱ��
		public static final String TABLE_NAME_2 = "Ա������Ȩ����";
		public static final String SHEET_NAME_2 = "sheet";
		public static final String[] FILEDS_2 = new String[] {"a","b","c","d"};	
		public static final String[] COLNAMES_2 = new String[] {"����","����","��Ȩ����","���ʱ��"};
		
		//exp type equal 3
		//���š���������������֯
		public static final String TABLE_NAME_3 = "Ա�������������";
		public static final String SHEET_NAME_3 = "sheet";
		public static final String[] FILEDS_3 = new String[] {"a","b","c","d"};	
		public static final String[] COLNAMES_3 = new String[] {"����","����","����","��֯"};
	
	
	
	
	
	
	
}
