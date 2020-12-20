package util;

import java.io.Serializable;
import java.util.ArrayList;

// ����� ���ฮ��Ʈ ��ü, ����������� ���� ���ฮ��Ʈ, ����������� ����ڸ���Ʈ
public class ReservationList implements Serializable{

	ArrayList<Reservation> useList = new ArrayList<Reservation>();

	public ReservationList(ArrayList<Reservation> reservationList) {
		super();
		this.useList = reservationList;
	}

	public ArrayList<Reservation> getReservationList() {
		return useList;
	}

	public void setOrderList(ArrayList<Reservation> reservationList) {
		this.useList = reservationList;
	}	
}