package util;

import java.io.Serializable;
import java.util.ArrayList;

// 사용자 예약리스트 객체, 매장관리자의 승인 예약리스트, 매장관리자의 대기자리스트
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