package util;

import java.io.Serializable;
import java.util.*;

//예약내역 객체
public class Reservation implements Serializable {
	//Field
	private String studyRoom;//스터디룸
	private String orderNo; // 예약번호
	private String name;  //성명
	private String tel; //예약자 전화번호
	private String time; //예약시간
	private String roomMenu; //예약한 방 이름_가격_수량/이름_가격_수량/이름_가격_수량...
	private boolean acceptOrder; //승낙여부
	
	//Constructor
	public Reservation(){}

	public Reservation(String desStore,String orderNo, String name, String tel, String reservation, String orderMenu) {
		super();
		this.studyRoom = desStore;
		this.orderNo = orderNo;
		this.name = name;
		this.tel = tel;
		this.time = reservation;
		this.roomMenu = orderMenu;
		this.acceptOrder = true;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getReservation() {
		return time;
	}

	public void setReservation(String reservation) {
		this.time = reservation;
	}

	public String getOrderMenu() {
		return roomMenu;
	}

	public void setOrderMenu(String orderMenu) {
		this.roomMenu = orderMenu;
	}

	public boolean isAcceptOrder() {
		return acceptOrder;
	}

	public void setAcceptOrder(boolean acceptOrder) {
		this.acceptOrder = acceptOrder;
	}

	public String getDesStore() {
		return studyRoom;
	}

	public void setDesStore(String desStore) {
		this.studyRoom = desStore;
	}

	@Override
	public String toString() {
		return  studyRoom + "," + orderNo + "," + name + "," + tel + "," + time
				+ "," + roomMenu + "," + acceptOrder;
	}
	
	
}