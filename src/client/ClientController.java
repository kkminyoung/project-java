package client;

import server.controller.TimeHandler;
import util.Packet;

// 클라이언트 -> 서버 전송을 위해 control
public class ClientController {
	public Object send(boolean header, byte protocol, Object message){
		Packet result = new ClientSender().send(new Packet(header, protocol, new TimeHandler().getTime(), message));
		return result.getMessage();
	}
}
