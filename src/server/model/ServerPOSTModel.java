package server.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import server.DBsetting;
import util.RoomOwner;
import util.Reservation;
import util.User;

public class ServerPOSTModel implements DBsetting {

	// �α���
	public Object loginCheck(Object message) {
		System.out.println("���� �� loginCheck ����");

		Object sendMessage = null;
		// message : �α��� �� �ʿ��� ���̵�/��й�ȣ
		
		String[] data = null;
		if (message instanceof String) {
			data = ((String) message).split("/");
		}
		String receiveId = data[0];
		String receivePw = data[1];

		System.out.println("���� �޽��� ���� : " + message);

		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Entry<Object, Object>> iter = UserTable.entrySet().iterator();
		while (iter.hasNext()) {
			// key�� id
			// value�� ��ü
			Entry<Object, Object> entry = iter.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			String[] values = value.split(",");
			boolean isUser = Boolean.parseBoolean(values[0]);
			String id = values[1];
			String pwd = values[2];
			String name = values[3];
			String tel = values[4];

			if (receiveId.equals(key)) {// ���̵� ��ġ
				if (receivePw.equals(pwd)) {// ���̵�, �н����� ��ġ
					if (isUser) {// �����
						sendMessage = new User(isUser, id, pwd, name, tel);
						break;
					} else {// ���������
						String ownNo = values[5];
						String addr = values[6];
						String type = values[7];
						boolean isOpen = Boolean.parseBoolean(values[8]);
						sendMessage = new RoomOwner(isUser, id, pwd, name, tel, ownNo, addr, type, isOpen);
						break;
					}
				} else {
					sendMessage = true;
					break;
				}
			} else {
				sendMessage = false;
			}
		}
		if (sendMessage == null) {
			sendMessage = false;
		}
		System.out.println("�α��� ó�� ��� : " + sendMessage);

		return sendMessage;
	}

	// ���̵� �ߺ�Ȯ��
	public Object idCheck(Object message) {

		System.out.println("���� �� idCheck ����");

		Object sendMessage = null;
		// message : �α��� �� �ʿ��� ���̵�/��й�ȣ
		String data = ((String) message);

		System.out.println("���� �޽��� ���� : " + data);

		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator iter = UserTable.keySet().iterator();
		while (iter.hasNext()) {
			// key�� id
			String key = (String) iter.next();
			if (key.equals(data)) {// ���̵� ��ġ
				sendMessage = true;
				break;
			} else {
				sendMessage = false;
			}
		}
		if (sendMessage == null) {
			sendMessage = false;
		}
		System.out.println("���̵� �ߺ�Ȯ�� ó�� ��� : " + sendMessage);

		return sendMessage;
	}

	// ȸ������
	public Object addUser(Object message) {
		System.out.println("���� �� addUser ����");
		User user = null;
		RoomOwner manager = null;
		Object sendMessage = null;
		// message : �α��� �� �ʿ��� ���̵�/��й�ȣ

		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (message instanceof RoomOwner) {
			System.out.println("���� �޽��� ���� : " + ((RoomOwner) message).toString());
			manager = (RoomOwner) message;
			UserTable.setProperty(manager.getId(), manager.toString());
			Properties menulist = new Properties();
			Properties orderList = new Properties();
			Properties waitinList = new Properties();
			Properties bookingList = new Properties();
			try {
				menulist.storeToXML(new FileOutputStream(MENU_LIST + manager.getId() + "_menulist.data"), "new file",
						"UTF-8");
				orderList.storeToXML(new FileOutputStream(ORDER_LIST + manager.getId() + "_orderlist.data"), "new file",
						"UTF-8");
				waitinList.storeToXML(new FileOutputStream(WAITING_LIST + manager.getId() + "_waitinglist.data"),
						"new file", "UTF-8");
				System.out.println("Manager�� ���� �߰�");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (message instanceof User) {
			System.out.println("���� �޽��� ���� : " + ((User) message).toString());
			user = (User) message;
			UserTable.setProperty(user.getId(), user.toString());
			Properties favoriteList = new Properties();
			Properties orderList = new Properties();

			try {
				orderList.storeToXML(new FileOutputStream(ORDER_LIST + user.getId() + "_orderlist.data"), "new file",
						"UTF-8");
				System.out.println("User�� ���� �߰�");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			UserTable.storeToXML(new FileOutputStream(USER_TABLE), "add user", "UTF-8");
			sendMessage = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (sendMessage == null) {
			sendMessage = false;
		}
		System.out.println("ȸ������ ó�� ��� : " + sendMessage);

		return sendMessage;
	}

	//
	public Object changeReserStatus(Object message) {

		Object sendMessage = null;
		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (message instanceof RoomOwner) {
			RoomOwner manager = (RoomOwner) message;
			boolean Switch = manager.isOpen();
			manager.setOpen(!Switch);
			UserTable.remove(manager.getId());
			UserTable.setProperty(manager.getId(), manager.toString());
			sendMessage = manager.isOpen();
		}
		try {
			UserTable.storeToXML(new FileOutputStream(USER_TABLE), "changeReserStatus", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sendMessage;
	}

	// ���̵� ã��
	public Object findId(Object message) {
		Object sendMessage = null;
		// message : ���̵� ã�� �� �ʿ��� �̸�/��ȭ��ȣ
		String[] data = null;
		if (message instanceof String) {
			data = ((String) message).split(",");
		}
		String receiveName = data[0];
		String receiveTel = data[1];

		System.out.println("���� �޽��� ���� : " + message);

		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Entry<Object, Object>> iter = UserTable.entrySet().iterator();
		while (iter.hasNext()) {
			// key�� id
			// value�� ��ü
			Entry<Object, Object> entry = iter.next();
			String value = (String) entry.getValue();
			String[] values = value.split(",");
			String id = values[1];
			String name = values[3];
			String tel = values[4];

			if (receiveName.equals(name)) {// �̸� ��ġ
				if (receiveTel.equals(tel)) {// �̸�,��ȭ��ȣ ��ġ
					sendMessage = id;
					break;
				} else {
					sendMessage = null;
				}
			} else {
				sendMessage = null;
			}
		}
		System.out.println("���̵� ã�� ó�� ��� : " + sendMessage);

		return sendMessage;
	}

	// �н����� ã��
	public Object findPw(Object message) {
		Object sendMessage = null;
		// message : ��й�ȣ ���� �� �ʿ��� ���̵�/��й�ȣ
		String[] data = null;
		if (message instanceof String) {
			data = ((String) message).split(",");
		}
		String receiveId = data[0];
		String receiveTel = data[1];

		System.out.println("���� �޽��� ���� : " + message);

		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Entry<Object, Object>> iter = UserTable.entrySet().iterator();
		while (iter.hasNext()) {
			// value�� ��ü
			Entry<Object, Object> entry = iter.next();
			String value = (String) entry.getValue();
			String[] values = value.split(",");
			String id = values[1];
			String tel = values[4];

			if (receiveId.equals(id)) {// ���̵� ��ġ
				if (receiveTel.equals(tel)) {// ���̵�,��ȭ��ȣ ��ġ
					sendMessage = true;
					break;
				} else {
					sendMessage = false;
				}
			} else {
				sendMessage = false;
			}
		}
		System.out.println("��й�ȣ ã�� ó�� ��� : " + sendMessage);
		return sendMessage;
	}

	public Object modifyPw(Object message) {
		Object sendMessage = null;
		Object temp = null;
		// message : ��й�ȣ ã�� �� �ʿ��� ���̵�/��ȭ��ȣ
		String[] data = null;
		if (message instanceof String) {
			data = ((String) message).split("/");
		}
		String receiveId = data[0];
		String receivePw = data[1];
		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties UserTableClone = (Properties) UserTable.clone();
		Iterator<Entry<Object, Object>> iter = UserTableClone.entrySet().iterator();
		while (iter.hasNext()) {
			// value�� ��ü
			Entry<Object, Object> entry = iter.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			String[] values = value.split(",");
			boolean isUser = Boolean.parseBoolean(values[0]);
			String id = values[1];
			String pwd = values[2];
			String name = values[3];
			String tel = values[4];
			String ownNo = null;
			String addr = null;
			String type = null;
			boolean isOpen = false;
			if (receiveId.equals(id)) {// ���̵� ��ġ
				UserTable.remove(key);
				if (isUser) {// �����
					temp = new User(isUser, id, receivePw, name, tel);
					UserTable.setProperty(key, ((User) temp).toString());
				} else {// ���������
					ownNo = values[5];
					addr = values[6];
					type = values[7];
					isOpen = Boolean.getBoolean(values[8]);
					temp = new RoomOwner(isUser, id, receivePw, name, tel, ownNo, addr, type, isOpen);
					UserTable.setProperty(key, ((RoomOwner) temp).toString());
				}
				try {
					UserTable.storeToXML(new FileOutputStream(USER_TABLE), "modify password", "UTF-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				sendMessage = true;
				break;
			} else {
				sendMessage = false;
			}
		}

		System.out.println("��й�ȣ ���� ó�� ��� : " + sendMessage);
		return sendMessage;
	}

	public Object editUserInfo(Object message) {
		Object sendMessage = null;
		System.out.println("���� �޽��� ���� : " + message.toString());
		Properties UserTable = new Properties();
		try {
			UserTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties UserTableClone = (Properties) UserTable.clone();
		Iterator<Entry<Object, Object>> iter = UserTableClone.entrySet().iterator();
		while (iter.hasNext()) {
			// value�� ��ü
			Entry<Object, Object> entry = iter.next();
			String key = (String) entry.getKey();
			if (message instanceof RoomOwner) {// ��ü������
				if (key.equals(((RoomOwner) message).getId())) {
					UserTable.remove(key);
					UserTable.setProperty(key, ((RoomOwner) message).toString());
					sendMessage = true;
					break;
				} else {
					sendMessage = false;
				}
			} else if (message instanceof User) {
				if (key.equals(((User) message).getId())) {
					UserTable.remove(key);
					UserTable.setProperty(key, ((User) message).toString());
					sendMessage = true;
					break;
				} else {
					sendMessage = false;
				}
			} else {
				System.out.println("���� �޽��� ���� : " + message);
			}
		}
		try {
			UserTable.storeToXML(new FileOutputStream(USER_TABLE), "modify user info", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (sendMessage == null) {
			sendMessage = false;
		}
		return sendMessage;
	}

	public Object addMenu(Object message) {
		Object sendMessage = true;
		boolean check = false;
		String[] data = null;
		if (message instanceof String) {
			data = ((String) message).split("/");
		}
		String receiveId = data[0];
		String receiveMenu = data[1];
		String[] menu = receiveMenu.split(",");
		Properties menuList = new Properties();

		try {
			menuList.loadFromXML(new FileInputStream(MENU_LIST + receiveId + "_menulist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Iterator<Entry<Object, Object>> iter = menuList.entrySet().iterator();
		while (iter.hasNext()) {
			// value�� ��ü
			Entry<Object, Object> entry = iter.next();
			String key = (String) entry.getKey();
			if (key.equals(menu[0])) {
				check = true;
				break;
			}
		}
		if (check) {
			sendMessage = false;
		} else {
			sendMessage = true;
			menuList.setProperty(menu[0], receiveMenu);
		}
		try {
			menuList.storeToXML(new FileOutputStream(MENU_LIST + receiveId + "_menulist.data"), "menu add", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sendMessage;
	}

	public Object acceptOrder(Object message) {
		String[] data = ((String)message).split(",");
		String orderNo = data[0];
		String desStoreId = data[1];
		String userName = null;
		Properties storeWait = new Properties();
		Properties storeOrder = new Properties();
		String userId = null;
		try {
			storeWait.loadFromXML(new FileInputStream(WAITING_LIST + desStoreId + "_waitinglist.data"));
			storeOrder.loadFromXML(new FileInputStream(ORDER_LIST + desStoreId + "_orderlist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		storeOrder.setProperty(orderNo, storeWait.getProperty(orderNo));
		storeWait.remove(orderNo);
		try {
			storeWait.storeToXML(new FileOutputStream(WAITING_LIST + desStoreId + "_waitinglist.data"),
					"move order", "UTF-8");
			storeOrder.storeToXML(new FileOutputStream(ORDER_LIST + desStoreId + "_orderlist.data"), "move order",
					"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Object acceptCancel(Object message) {
		String[] data = ((String)message).split(",");
		String orderNo = data[0];
		String desStoreId = data[1];
		String userName = null;
		String userId = null;
		Properties storeWait = new Properties();
		try {
			storeWait.loadFromXML(new FileInputStream(WAITING_LIST + desStoreId + "_waitinglist.data"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Iterator<Object> iter = storeWait.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = storeWait.getProperty(key);
			String[] values = value.split(",");	
			if(desStoreId.equals(key)){
				userName = values[2];
				break;
			}
		}
		Properties userTable = new Properties();
		try {
			userTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter2 = userTable.keySet().iterator();
		while (iter2.hasNext()) {
			String key = (String) iter2.next();
			String value = userTable.getProperty(key);
			String[] values = value.split(",");
			if (Boolean.parseBoolean(values[0])) {
				if (values[2].equals(userName)) {
					userId = values[1];
					break;
				}
			}
		}
		System.out.println(userId);
		if(userId!=null){
	
			Properties userOrder = new Properties();
			try {
				userOrder.loadFromXML(new FileInputStream(ORDER_LIST + userId + "_orderlist.data"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] oderdata=(storeWait.getProperty(orderNo)).split(",");
			Reservation order = new Reservation(oderdata[0], oderdata[1], oderdata[2], oderdata[3], oderdata[4], oderdata[5]);
			order.setAcceptOrder(!Boolean.parseBoolean(oderdata[6]));
			userOrder.setProperty(orderNo,order.toString());
			storeWait.remove(orderNo);		
			try {
				storeWait.storeToXML(new FileOutputStream(WAITING_LIST + desStoreId + "_waitinglist.data"),
						"move order", "UTF-8");
				userOrder.storeToXML(new FileOutputStream(ORDER_LIST + userId + "_orderlist.data"), "move order",
						"UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return true;//
	}

	public Object deleteMenu(Object message) {
		Object sendMessage = true;
		String[] data = ((String) message).split(",");
		String receiveMenuNo = data[0];
		String receiveId = data[1];
		Properties menuList = new Properties();
		try {
			menuList.loadFromXML(new FileInputStream(MENU_LIST + receiveId + "_menulist.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		menuList.remove(receiveMenuNo);
		try {

			menuList.storeToXML(new FileOutputStream(MENU_LIST + receiveId + "_menulist.data"), "delete menu", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sendMessage;
	}

	public Object orderMenu(Object message) {
		Reservation order = (Reservation) message;
		String[] data = order.toString().split(",");
		String desStore = data[0];
		String desStoreId = null;
		String userName = data[2];
		String userId = null;
		// data[5]=�ֹ��� �޴�����Ʈ
		Properties userTable = new Properties();
		try {
			userTable.loadFromXML(new FileInputStream(USER_TABLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Object> iter = userTable.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = userTable.getProperty(key);
			String[] values = value.split(",");
			if (!Boolean.parseBoolean(values[0])) {// ���������
				RoomOwner store = new RoomOwner(Boolean.parseBoolean(values[0]), values[1], values[2], values[3], values[4],
						values[5], values[6], values[7], Boolean.parseBoolean(values[8]));
				if (store.getName().equals(desStore)) {
					desStoreId = key;
				}
			} else {// �Ϲ� �����
				User user = new User(Boolean.parseBoolean(values[0]), values[1], values[2], values[3], values[4]);
				if (user.getName().equals(userName)) {
					userId = key;
				}
			}
		}
			if (desStoreId != null && userId != null) {
				Properties storeOrder = new Properties();
				Properties userOrder = new Properties();
				try {
					storeOrder.loadFromXML(new FileInputStream(WAITING_LIST + desStoreId + "_waitinglist.data"));
					userOrder.loadFromXML(new FileInputStream(ORDER_LIST + userId + "_orderlist.data"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				storeOrder.setProperty(order.getOrderNo(), order.toString());
				userOrder.setProperty(order.getOrderNo(), order.toString());

				try {
					storeOrder.storeToXML(new FileOutputStream(WAITING_LIST + desStoreId + "_waitinglist.data"),
							"add order", "UTF-8");
					userOrder.storeToXML(new FileOutputStream(ORDER_LIST + userId + "_orderlist.data"), "add order",
							"UTF-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
		return false;
	}
}