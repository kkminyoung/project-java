package client;

import java.io.*;
import java.util.*;

import util.RoomOwner;
import util.Session;
import util.User;

// ���� ����
public class SessionController {
	// ���� �ҷ�����
	public Object sessionLoad(){
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("session.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String session = prop.getProperty("session");
		String[] split = session.split(",");
		if(split[0].equals("true")){
			User user = new User(true, split[1], split[2], split[3], split[4]);
			Object temp=user;			
			return temp;
		}else{
			RoomOwner manager= new RoomOwner(false, split[1], split[2], split[3], split[4], split[5], split[6], split[7],Boolean.parseBoolean(split[8]));
			Object temp=manager;
			return temp;
		}		
	}
	
	// ���� ����
	public void sessionSave(Object obj){
		Properties prop = new Properties();
		if(obj instanceof RoomOwner){
			prop.setProperty("session", ((RoomOwner)obj).toString());
		}else if(obj instanceof User){
			prop.setProperty("session", ((User)obj).toString());
		}
		try {
			prop.storeToXML(new FileOutputStream("session.data"), "", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	// �޴�(��)����
	public int menuSelectOpen(){
		Properties prop = new Properties();
		String menu=null;
		try {
			prop.loadFromXML(new FileInputStream("session.data"));
			menu = prop.getProperty("selectedMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(menu);
	}
	
	
	// ������ �޴�(��) ����
	public void menuSelectSave(int menuNo){
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("session.data"));
			prop.setProperty("selectedMenu", String.valueOf(menuNo));
			prop.storeToXML(new FileOutputStream("session.data"), "", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	// ���� ����
	public void deleteSession(Session session) {
		session.setSession(null);
		Properties prop = new Properties();
		try {
			prop.storeToXML(new FileOutputStream("session.data"), "", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
