package util;

import java.io.Serializable;
import java.util.ArrayList;

// ���͵�� �� ����Ʈ
public class RoomList implements Serializable {
private ArrayList<Room> roomList;

public RoomList(ArrayList<Room> roomList) {
	super();
	this.roomList = roomList;
}

public ArrayList<Room> getRoomList() {
	return roomList;
}

public void setRoomList(ArrayList<Room> roomList) {
	this.roomList = roomList;
}
}