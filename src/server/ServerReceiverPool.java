package server;

import java.util.*;

// client list ����
public class ServerReceiverPool {
	
	public static ArrayList<ServerReceiver> clientList = new ArrayList<ServerReceiver>();

	public static void add(ServerReceiver thread) {
		clientList.add(thread);
		System.out.println("Ŭ���̾�Ʈ ����");
	}

	public static void remove(ServerReceiver thread) {
		clientList.remove(thread);
		System.out.println("Ŭ���̾�Ʈ ��������");
	}
}