package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

// ����
public class MyServer implements DBsetting {
	public void run() {
		/*
		 * Server Socket ���� Client ���� ��� > Socket ���� Server Thread�� Start
		 * Ŭ���̾�Ʈ�� ������ ������ �޴� �κ�
		 */
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(6000);
			System.out.println("���� ���� ����");
			/*���ʽ����*/
			
			Properties firstRun = new Properties();
			try {
				firstRun.loadFromXML(new FileInputStream(USER_TABLE));		
				System.out.println("usertable ������ ���������� �ε��Ͽ����ϴ�.");
			} catch (FileNotFoundException e) {
				System.out.println("usertable ������ ã���� �����ϴ�. usertable ������ ���� �����մϴ�.");
				firstRun.storeToXML(new FileOutputStream(USER_TABLE), "","UTF-8");
				new MyServer().run();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			while (true) {
				Socket socket = serverSocket.accept();
				if (socket != null) {
					ServerReceiver serverThread = new ServerReceiver(socket);
					Thread thread = new Thread(serverThread);
					thread.start();
					ServerReceiverPool.add(serverThread);
				}
			}
		} catch (IOException e) {
		}
	}
}
