package util;

import java.io.Serializable;

//스터디룸 주인
public class RoomOwner extends User implements Serializable{

	private String ownNo;
	private String addr;
	private String type;
	private boolean isOpen;
	
	public RoomOwner(){}

	public RoomOwner(boolean isUser, String id, String pwd, String name, String tel, String ownNo, String addr, String type) {
		super(isUser, id, pwd, name, tel);
		this.ownNo = ownNo;
		this.addr = addr;
		this.type = type;
		this.isOpen = false;
	}

	public RoomOwner(boolean isUser, String id, String pwd, String name, String tel, String ownNo, String addr, String type, boolean isOpen) {
		super(isUser, id, pwd, name, tel);
		this.ownNo = ownNo;
		this.addr = addr;
		this.type = type;
		this.isOpen = isOpen;
	}

	public String getOwnNo() {
		return ownNo;
	}

	public void setOwnNo(String ownNo) {
		this.ownNo = ownNo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+","+ownNo+","+addr+","+type+","+isOpen;
	}
}
