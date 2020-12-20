package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import server.Protocol;
import server.ServerIP;
import util.Packet;

// ���� ���� �� ����
public class ClientSender implements Protocol, ServerIP {

	public Packet send(Packet sendPacket) {
		Socket socket = null;
		ObjectOutputStream oos = null;

		try {
			socket = new Socket(IP, 6000);
			System.out.println("���� ����");
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("��Ŷ�� ������ ���� : " + sendPacket.toString());
			oos.flush();
			oos.writeObject(sendPacket);
			oos.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ObjectInputStream ois = null;
		Packet receicvePacket = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			receicvePacket = (Packet) ois.readObject();

			System.out.println("�����κ��� ���� ��Ŷ : " + receicvePacket.toString());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return receicvePacket;
	}

}