package util;

import java.io.Serializable;
import java.util.ArrayList;

// 스터디룸 방 리스트
public class StudyRoomList implements Serializable {
private ArrayList<RoomOwner> studyRoomList;

public StudyRoomList(ArrayList<RoomOwner> studyRoomList) {
	super();
	this.studyRoomList = studyRoomList;
}

public ArrayList<RoomOwner> getStoreList() {
	return studyRoomList;
}

public void setStoreList(ArrayList<RoomOwner> storeList) {
	this.studyRoomList = storeList;
}
}
