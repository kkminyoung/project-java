package util;

import java.io.Serializable;
import java.util.*;

//���೻�� ��ü
public class Reservation implements Serializable {
	//Field
	private String studyRoom;//���͵��
	private String orderNo; // �����ȣ
	private String name;  //����
	private String tel; //������ ��ȭ��ȣ
	private String time; //����ð�
	private String roomMenu; //������ �� �̸�_����_����/�̸�_����_����/�̸�_����_����...
	private boolean acceptOrder; //�³�����
	
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