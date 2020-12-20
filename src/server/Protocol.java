package server;

// 프로토콜 정의
public interface Protocol {
	
	public static final boolean POST = true;
	public static final boolean GET = false;
	public static final byte LOGIN = 3;
	public static final byte REG_USER = 4; 
	public static final byte REG_ID_CHECK = 5; 
	public static final byte FIND_ID = 6; 
	public static final byte FIND_PW = 7;  
	public static final byte MODIFY_PASSWORD=8; 
	public static final byte CHANGE_RESER_STATUS = 9;  
	public static final byte ACCEPT_ORDER = 10; 
	public static final byte ACCEPT_CANCEL = 11;  
	public static final byte EDIT_USER_INFO = 12; 
	public static final byte ADD_MENU = 14;  
	public static final byte DEL_MENU = 15;  
	public static final byte DEL_THIS_RECENTLY_ORDER = 17; 
	public static final byte ORDER_MENU = 18; 
	public static final byte ANSWER_WAIT_LIST = 30; 	
	public static final byte ORDER_LIST = 31; 	
	public static final byte MENU_MANAGEMENT_LIST = 32;	
	public static final byte ORDER_HISTORY_LIST = 35;	
	public static final byte STORE_LIST = 36;	
	public static final byte STORE_MENU_LIST = 37;	
	
}