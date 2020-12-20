package util;

import java.io.Serializable;

// ����� ���� ��Ŷ ( �ʿ��� ������ ��� ��ü��Ʈ������ �����)
public class Packet implements Serializable {
	
	private boolean header; 
	private byte protocol;
	private String time;
	private Object message;

	public Packet() {
		super();
	}

	public Packet(boolean header, byte protocol, String time, Object massage) {
		super();
		this.header = header;
		this.protocol = protocol;
		this.time = time;
		this.message = massage;
	}

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	public byte getProtocol() {
		return protocol;
	}

	public void setProtocol(byte protocol) {
		this.protocol = protocol;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object massage) {
		this.message = massage;
	}

	@Override
	public String toString() {
		if (this.message instanceof String) {
			return this.header + "," + this.protocol + "," + this.time + "," + (String) this.message;
		} else {
			return this.header + "," + this.protocol + "," + this.time + "," + this.message;
		}
	}
}