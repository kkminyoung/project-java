package util;

import java.io.Serializable;
import java.util.ArrayList;

// 스터디룸 방 리스트
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
