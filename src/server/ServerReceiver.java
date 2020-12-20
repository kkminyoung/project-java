package server;

import java.io.*;
import java.net.*;

import server.controller.ServerGETController;
import server.controller.ServerPOSTController;
import util.Packet;

// Thread Ŭ���� Socket�� �޾� �׿� ����� Ŭ���̾�Ʈ�� ������ ��Ŷ�� �м��Ͽ� �۾��� �����Ѵ�. Server�� ��Ŷ�� �޴ºκ�
public class ServerReceiver implements Runnable, Protocol {

	private Socket socket;
	private ServerPOSTController postCon = new ServerPOSTController();
	private ServerGETController getCon = new ServerGETController();
	
	public ServerReceiver(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Packet receivePacket = null;
	
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			receivePacket = (Packet) ois.readObject();

			System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��Ŷ : " + receivePacket.toString());
			
			Packet sendPacket = null;
			if (receivePacket.isHeader()) {// true�� POST�۾� ó��
				
				sendPacket = postCon.POSTprocess(receivePacket);				
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("Ŭ���̾�Ʈ�� ���� ��Ŷ : "+sendPacket.toString());
					oos.flush();
					oos.writeObject(sendPacket);
					oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {// false�� GET�۾� ó��
				System.out.println(receivePacket.toString());
				sendPacket = getCon.GETprocess(receivePacket);				
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("Ŭ���̾�Ʈ�� ���� ��Ŷ : "+sendPacket.toString());
					oos.flush();
					oos.writeObject(sendPacket);
					oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			ServerReceiverPool.remove(this);
			if(socket!=null)
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}