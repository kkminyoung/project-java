package util;

import java.io.Serializable;
import java.util.ArrayList;

// ���͵�� �� ����Ʈ
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