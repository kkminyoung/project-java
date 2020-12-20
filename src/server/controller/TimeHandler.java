package server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// ��¥������ String �������� ��ȯ�ϱ� ����
public class TimeHandler {
	
	private Date inTime;
	private String Time;

	private SimpleDateFormat sdf;

	public TimeHandler() {
		super();
		inTime = new Date();
	}
	
	public String getTime() {
		sdf = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss", Locale.US);
		Time = sdf.format(inTime);
		return Time;
	}
	
	public String getOrderTime() {
		sdf = new SimpleDateFormat("HH:mm", Locale.US);
		Time = sdf.format(inTime);		
		return Time;
	}
	
	public String makeOrderNo(){
		sdf = new SimpleDateFormat("yyMMddHHmmss", Locale.US);
		Time = sdf.format(inTime);		
		return Time;
	}	
}