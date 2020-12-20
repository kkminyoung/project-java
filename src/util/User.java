package util;

import java.io.Serializable;

//��
public class User implements Serializable{
	//Field
	private boolean isUser; //�� or ���͵�� ����
	private String id; //���̵�
	private String password; //�н�����
	private String name; //�̸�
	private String tel; //��ȭ��ȣ
	
	public User(){}

	public User(boolean isUser,String id, String pwd, String name, String tel) {
		super();
		this.isUser = isUser;
		this.id = id;
		this.password = pwd;
		this.name = name;
		this.tel = tel;
	}

	// ������ �������� ����
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