package server.controller;

import server.Protocol;
import server.model.ServerGETModel;
import util.Packet;

public class ServerGETController implements Protocol{
	
	private ServerGETModel sm = new ServerGETModel();
	/*
	 * ServerThread���� ���� �����͸� �м��Ͽ� �𵨿� ������ �ʿ��ϸ� ���� ��ȯ���� Packet
	 */

	// sendPacket = new Packet(receivePacket.isHeader(), LOGIN_SUCCESS, new
	// TimeHandler().getTime(), null);
	
	public Packet GETprocess(Packet receivePacket) {
		byte protocol = receivePacket.getProtocol();
		Object message = receivePacket.getMessage();
		Object sendMessage = null;
		Packet tempPacket = null;
		switch (protocol) {
		case ANSWER_WAIT_LIST:
			System.out.println("�������� ����Ʈ ��ȯ ó��");
			sendMessage = sm.answerWaitList(message);
			tempPacket = new Packet(GET, ANSWER_WAIT_LIST, new TimeHandler().getTime(), sendMessage);
			break;
		case ORDER_LIST:
			System.out.println("������ ����Ʈ ��ȯ ó��");
			sendMessage = sm.preOrderList(message);
			tempPacket = new Packet(GET, ORDER_LIST, new TimeHandler().getTime(), sendMessage);
			break;
		case MENU_MANAGEMENT_LIST:
			System.out.println("���͵�� ���� ����Ʈ ��ȯ ó��");
			sendMessage = sm.roomManagementList(message);
			tempPacket = new Packet(GET, MENU_MANAGEMENT_LIST, new TimeHandler().getTime(), sendMessage);
			break;
		case ORDER_HISTORY_LIST:
			System.out.println("����� �ֱ��ֹ����� ����Ʈ ��ȯ ó��");
			sendMessage = sm.orderHistoryList(message);
			tempPacket = new Packet(GET, ORDER_HISTORY_LIST, new TimeHandler().getTime(), sendMessage);
			break;
		case STORE_LIST:
			System.out.println("���͵�븮��Ʈ ��ȯ ó��");
			sendMessage = sm.storeList(message);
			tempPacket = new Packet(GET, STORE_LIST, new TimeHandler().getTime(), sendMessage);
			break;
		case STORE_MENU_LIST:
			System.out.println("���͵�� �� ����Ʈ ��ȯ ó��");
			sendMessage = sm.storeMenuList(message);
			tempPacket = new Packet(GET, STORE_MENU_LIST, new TimeHandler().getTime(), sendMessage);
			break;		
		}

		Packet sendPacket = tempPacket;
		return sendPacket;
	}
}