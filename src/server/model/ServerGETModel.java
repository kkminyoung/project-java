package server.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import server.DBsetting;
import util.RoomOwner;
import util.Room;
import util.RoomList;
import util.Reservation;
import util.ReservationList;
import util.StudyRoomList;

public class ServerGETModel implements DBsetting {
    // ���͵�� ������� �մԵ�
	public Object answerWaitList(Object message) {
		ArrayList<Reservation> waitinglist = new ArrayList<Reservation>();
		String data = (String) message;// ���̵�
		Properties waitingList = new Properties();
		try {
			waitingList.loadFromXML(new FileInputStream(WAITING_LIST+data+"_waitinglist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = waitingList.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = waitingList.getProperty(key);
			String[] values = value.split(",");
			Reservation order = new Reservation(values[0], values[1], values[2], values[3], values[4], values[5]);
			waitinglist.add(order);
		}
		ReservationList array = new ReservationList(waitinglist);
		return array;
	}

	// ���͵�� ������ ������ �մԵ�
	public Object preOrderList(Object message) {
		ArrayList<Reservation> orderlist = new ArrayList<Reservation>();
		String data = (String) message;// ���̵�
		Properties orderList = new Properties();
		try {
			orderList.loadFromXML(new FileInputStream(ORDER_LIST+data+"_orderlist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = orderList.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = orderList.getProperty(key);
			String[] values = value.split(",");
			Reservation order = new Reservation(values[0], values[1], values[2], values[3], values[4], values[5]);
			orderlist.add(order);
		}
		ReservationList array = new ReservationList(orderlist);
		return array;
	}

	// ���͵�� ���� ����Ʈ
	public Object roomManagementList(Object message) { //���͵�� ������ ���̵�
		String id = (String) message;
		ArrayList<Room> roomlist = new ArrayList<Room>();

		Properties roomList = new Properties();
		try {
			roomList.loadFromXML(new FileInputStream(MENU_LIST + id + "_roomlist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = roomList.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = roomList.getProperty(key);
			String[] values = value.split(",");
			Room room = new Room(values[0], values[1], values[2], Integer.parseInt(values[3]),
					Double.parseDouble(values[4]), Boolean.parseBoolean(values[5]));
			roomlist.add(room);
		}
		RoomList array = new RoomList(roomlist);
		return array;
	}

	// ��� ���� ����Ʈ
	public Object orderHistoryList(Object message) {
		ArrayList<Reservation> orderlist = new ArrayList<Reservation>();
		String data = (String) message;// ���̵�
		Properties orderList = new Properties();
		try {
			orderList.loadFromXML(new FileInputStream(ORDER_LIST+data+"_orderlist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = orderList.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = orderList.getProperty(key);
			String[] values = value.split(",");
			Reservation order = new Reservation(values[0], values[1], values[2], values[3], values[4], values[5]);
			orderlist.add(order);
		}
		ReservationList array = new ReservationList(orderlist);
		return array;
	}

	// ���͵�� ���� ����Ʈ
	public Object storeList(Object message) {
		ArrayList<RoomOwner> storelist = new ArrayList<RoomOwner>();
		String data = (String) message;
		Properties storeList = new Properties();
		try {
			storeList.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = storeList.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = storeList.getProperty(key);
			String[] values = value.split(",");
			if (!Boolean.parseBoolean(values[0])) {
				RoomOwner store = new RoomOwner(Boolean.parseBoolean(values[0]), values[1], values[2], values[3], values[4],
						values[5], values[6], values[7], Boolean.parseBoolean(values[8]));
				if (store.getType().equals(data)) {
					if (store.isOpen()) {
						storelist.add(store);
					}
				}
			}
		}
		StudyRoomList array = new StudyRoomList(storelist);
		return array;
	}

	// ���͵�볻�� �� ���� �޴� ����Ʈ
	public Object storeMenuList(Object message) {
		String data = (String) message;
		Properties storeList = new Properties();
		try {
			storeList.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = storeList.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = storeList.getProperty(key);
			String[] values = value.split(",");
			if (!Boolean.parseBoolean(values[0])) {// ���͵�� ����
				RoomOwner store = new RoomOwner(Boolean.parseBoolean(values[0]), values[1], values[2], values[3], values[4],
						values[5], values[6], values[7], Boolean.parseBoolean(values[8]));
				if (store.getName().equals(data)) {
					return roomManagementList(key);
				}
			}
		}
		return null;
	}

}