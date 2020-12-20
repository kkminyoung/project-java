package client;

import server.controller.TimeHandler;
import util.Packet;

// Ŭ���̾�Ʈ -> ���� ������ ���� control
public class ClientController {
	public Object send(boolean header, byte protocol, Object message){
		Packet result = new ClientSender().send(new Packet(header, protocol, new TimeHandler().getTime(), message));
		return result.getMessage();
	}
}
