package util;

import java.io.Serializable;

//고객
public class User implements Serializable{
	//Field
	private boolean isUser; //고객 or 스터디룸 업주
	private String id; //아이디
	private String password; //패스워드
	private String name; //이름
	private String tel; //전화번호
	
	public User(){}

	public User(boolean isUser,String id, String pwd, String name, String tel) {
		super();
		this.isUser = isUser;
		this.id = id;
		this.password = pwd;
		this.name = name;
		this.tel = tel;
	}

	// 고객인지 업주인지 구분
	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
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
	
	
	@Override
	public String toString() {
		return isUser+","+id+","+password+","+name+","+tel;
	}

}