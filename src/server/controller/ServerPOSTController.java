package server.controller;

import server.Protocol;
import server.model.ServerPOSTModel;
import util.Packet;

public class ServerPOSTController implements Protocol {
	private ServerPOSTModel sm = new ServerPOSTModel();
	/*
	 * ServerThread���� ���� �����͸� �м��Ͽ� �𵨿� ������ �ʿ��ϸ� ���� ��ȯ���� Packet
	 */

	// sendPacket = new Packet(receivePacket.isHeader(), LOGIN_SUCCESS, new
	// TimeHandler().getTime(), null);

	public Packet POSTprocess(Packet receivePacket) {
		System.out.println("���� ��Ʈ�ѷ� POST process ����");
		byte protocol = receivePacket.getProtocol();
		Object message = receivePacket.getMessage();
		Object sendMessage = null;
		Packet tempPacket = null;
		switch (protocol) {
		case LOGIN:
			System.out.println("�α���  ó��");
			sendMessage = sm.loginCheck(message);
			tempPacket = new Packet(POST, LOGIN, new TimeHandler().getTime(), sendMessage);
			break;//
		case REG_USER:
			System.out.println("ȸ������ ó��");
			sendMessage = sm.addUser(message);
			tempPacket = new Packet(POST, REG_USER, new TimeHandler().getTime(), sendMessage);
			break;//
		case REG_ID_CHECK:
			System.out.println("���̵� �ߺ�Ȯ�� ó��");
			sendMessage = sm.idCheck(message);
			tempPacket = new Packet(POST, REG_ID_CHECK, new TimeHandler().getTime(), sendMessage);
			break;//		
		case FIND_ID:
			System.out.println("���̵� ã�� ó��");
			sendMessage = sm.findId(message);
			tempPacket = new Packet(POST, FIND_ID, new TimeHandler().getTime(), sendMessage);
			break;//
		case FIND_PW:
			System.out.println("�н����� ã�� ó��");
			sendMessage = sm.findPw(message);
			tempPacket = new Packet(POST, FIND_PW, new TimeHandler().getTime(), sendMessage);
			break;//
		case MODIFY_PASSWORD:
			System.out.println("��й�ȣ ���� ó��");
			sendMessage = sm.modifyPw(message);
			tempPacket = new Packet(POST, MODIFY_PASSWORD, new TimeHandler().getTime(), sendMessage);
			break;//
		case CHANGE_RESER_STATUS:
			System.out.println("������� ����:���������");
			sendMessage = sm.changeReserStatus(message);
			tempPacket = new Packet(POST, CHANGE_RESER_STATUS, new TimeHandler().getTime(), sendMessage);
			break;//
		case ACCEPT_ORDER:
			System.out.println("�ֹ� �³�");
			sendMessage = sm.acceptOrder(message);
			tempPacket = new Packet(POST, ACCEPT_ORDER, new TimeHandler().getTime(), sendMessage);
			break;
		case ACCEPT_CANCEL:
			System.out.println("�ֹ� ���");
			sendMessage = sm.acceptCancel(message);
			tempPacket = new Packet(POST, ACCEPT_CANCEL, new TimeHandler().getTime(), sendMessage);
			break;
		case EDIT_USER_INFO:
			System.out.println("����� ���� ���� ó��");
			sendMessage = sm.editUserInfo(message);
			tempPacket = new Packet(POST, EDIT_USER_INFO, new TimeHandler().getTime(), sendMessage);
			break;
		case ADD_MENU:
			System.out.println("���͵�� �߰� ó��");
			sendMessage = sm.addMenu(message);
			tempPacket = new Packet(POST, ADD_MENU, new TimeHandler().getTime(), sendMessage);
			break;
		case DEL_MENU:
			System.out.println("���͵�� ���� ó��");
			sendMessage = sm.deleteMenu(message);
			tempPacket = new Packet(POST, DEL_MENU, new TimeHandler().getTime(), sendMessage);
			break;
		case ORDER_MENU:
			System.out.println("���͵�� �ֹ� ó��");
			sendMessage = sm.orderMenu(message);
			tempPacket = new Packet(POST, ORDER_MENU, new TimeHandler().getTime(), sendMessage);
			break;
		}

		System.out.println("���� ��Ʈ�ѷ� ����");

		Packet sendPacket = tempPacket;
		return sendPacket;
	}
}