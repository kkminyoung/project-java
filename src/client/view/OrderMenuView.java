package client.view;

import javax.swing.table.DefaultTableModel;

import client.ClientController;
import client.SessionController;
import server.Protocol;
import server.controller.TimeHandler;
import util.RoomOwner;
import util.Room;
import util.RoomList;
import util.Reservation;
import util.Session;
import util.StudyRoomList;
import util.User;

//메뉴주문
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Font;


// �޴�(���͵��) ����
public class OrderMenuView extends JFrame implements Protocol{
	
	private JLabel lbl_order, lbl_orderTotal;
	private JTextField tf_serch;
	private JTable storeList;
	private JTable menuList;
	private JButton btn_order,btn_serch,btn_back;
	private Session session = new Session();
	private int menuType=0;
	private int totalPrice = 0;
	private int beforeSelected=-1;
	
	private DefaultTableModel dtmStore = new DefaultTableModel(new Object[][] {}, new String[] {
				"���͵�� �̸�","�ּ�","��ȭ��ȣ"
			}){  //�� ���� ���ϰ� �ϴ� �κ�
		 public boolean isCellEditable(int row, int column){
			    return false;
		 }};
	private DefaultTableModel dtmMenu = new DefaultTableModel(new Object[][] {}, new String[] {
			"�з�", "�� �̸�", "����", "����"	}){  //�� ���� ���ϰ� �ϴ� �κ�
		 public boolean isCellEditable(int row, int column){
			    return false;
		 }};
	

	
	public OrderMenuView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				session.setSession(new SessionController().sessionLoad());
				menuType = new SessionController().menuSelectOpen();
				User user = (User) session.getSession();
				String menuTypeTemp = null;
				switch (menuType) {
				case 1:	
					menuTypeTemp="1�ν�";
					break;
				case 2:					
					menuTypeTemp="2~4�ν�";
					break;
				case 3:					
					menuTypeTemp="���̳���";
					break;
				case 4:					
					menuTypeTemp="���ǽ�";
					break;
				}
				lbl_order.setText(menuTypeTemp+ " ���͵�� ����");
				
				Object result= new ClientController().send(GET, STORE_LIST, menuTypeTemp);
				if (result instanceof StudyRoomList) {
					StudyRoomList storelist = (StudyRoomList) result;
					ArrayList<RoomOwner> store = storelist.getStoreList();
					Object[][] resultList = new Object[store.size()][9];
					for (int i = 0; i < resultList.length; i++) {
						resultList[i][0] = ((RoomOwner) (store.get(i))).getName();
						resultList[i][2]=((RoomOwner)(store.get(i))).getTel();
						resultList[i][1]=((RoomOwner)(store.get(i))).getAddr();
					}
					for (int i = 0; i < resultList.length; i++) {
						dtmStore.addRow(resultList[i]);
					}
				}
			}
		});
		this.setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,70,42));
		panel.setBounds(0, 0, 794, 80);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lbl_order = new JLabel("���͵�� ����");
		lbl_order.setFont(new Font("���� ���", Font.BOLD, 24));
		lbl_order.setIcon(new ImageIcon("gui_imgs/����������������.PNG"));
		lbl_order.setBounds(86, 10, 302, 60);
		panel.add(lbl_order);
		
		JLabel title_icon = new JLabel("");
		title_icon.setIcon(new ImageIcon("gui_imgs/icon_selectCategory_1.png"));
		title_icon.setBounds(12, 10, 62, 60);
		panel.add(title_icon);
		
		JLabel lbl_logo = new JLabel("");
		lbl_logo.setIcon(new ImageIcon("gui_imgs/logo_galgeyo_2.png"));
		lbl_logo.setBounds(666, 3, 99, 73);
		panel.add(lbl_logo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0,70,42));
		panel_2.setBounds(12, 127, 379, 435);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbl_img3 = new JLabel("���͵�� �˻�");
		lbl_img3.setFont(new Font("���� ���", Font.BOLD, 15));
		
		lbl_img3.setForeground(Color.WHITE);
		lbl_img3.setBackground(new Color(255, 255, 240));
		lbl_img3.setBounds(12, 10, 147, 32);
		panel_2.add(lbl_img3);
		
		tf_serch = new JTextField();
		tf_serch.setBounds(12, 52, 303, 38);
		panel_2.add(tf_serch);
		tf_serch.setColumns(10);
		
		btn_serch = new JButton("");
		btn_serch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel resultDtm = new DefaultTableModel(new Object[][] {},
						new String[] { "���͵�� �̸�","�ּ�","��ȭ��ȣ" }){  //�� ���� ���ϰ� �ϴ� �κ�
					 public boolean isCellEditable(int row, int column){
						    return false;
					 }};
				String searchStr = tf_serch.getText();
				if (!searchStr.equals("")) {
					for (int i = 0; i < dtmStore.getRowCount(); i++) {
						if (searchStr.equals((String) dtmStore.getValueAt(i, 0))) {// ���ڿ���
							resultDtm.addRow(new Object[] { dtmStore.getValueAt(i, 0), dtmStore.getValueAt(i, 1), dtmStore.getValueAt(i, 2)});
						}
					}
					if (resultDtm.getRowCount() == 0) {
						resultDtm.addRow(new String[] { "�˻����","������", "�ϴ�" });
					}
					storeList.setModel(resultDtm);
				} else {
					storeList.setModel(dtmStore);
				}
			}
		});
		
		btn_serch.setIcon(new ImageIcon("gui_imgs/btn_menuEdit_1.png"));
		btn_serch.setBounds(327, 52, 40, 38);
		panel_2.add(btn_serch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 100, 355, 325);
		panel_2.add(scrollPane);
		
		storeList = new JTable();
		storeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		storeList.setModel(dtmStore);
		storeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = storeList.getSelectedRow();
				if(beforeSelected!=row){
					DefaultTableModel dtmclone = new DefaultTableModel(new Object[][] {}, new String[] {"�з�", "���͵���̸�", "����", "����"	}){  //�� ���� ���ϰ� �ϴ� �κ�
						 public boolean isCellEditable(int row, int column){
							    return false;
						 }};
				String storeName = (String) storeList.getValueAt(row, 0);
				Object result = new ClientController().send(GET, STORE_MENU_LIST, storeName);
				if (result instanceof RoomList) {
					RoomList menulist = (RoomList) result;
					ArrayList<Room> menu = menulist.getRoomList();
					Object[][] resultList = new Object[menu.size()][6];
					for (int i = 0; i < resultList.length; i++) {
						resultList[i][1] = ((Room) (menu.get(i))).getMenuName();
						resultList[i][0]=((Room)(menu.get(i))).getCategory();
						resultList[i][2] = ((Room) (menu.get(i))).getPrice();
						resultList[i][3]=new Integer(0);
					}
					for (int i = 0; i < resultList.length; i++) {
						dtmclone.addRow(resultList[i]);
					}
					 menuList.setModel(dtmclone);
				}
				beforeSelected = row;
				}else{
				}
			}
		});
		scrollPane.setViewportView(storeList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(new Color(0,70,42));
		panel_1.setBounds(403, 127, 379, 435);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbl_img = new JLabel("���͵�� ����");
		lbl_img.setFont(new Font("���� ���", Font.BOLD, 15));
		
		lbl_img.setForeground(Color.WHITE);
		lbl_img.setBackground(new Color(255, 255, 240));
		lbl_img.setBounds(12, 10, 121, 28);
		panel_1.add(lbl_img);
		
		btn_order = new JButton("");
		btn_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sendMessage="";
				int storeSelectedRow = storeList.getSelectedRow();
				String orderTime = new TimeHandler().getOrderTime();
				String bookingTime = (String) JOptionPane.showInputDialog(null,"�� �� �Ŀ� ���ó���?\n����ð��� �Է����ּ���.\n���� �ð� : "+orderTime+"\n�Է� �� : 10 (10�� �ķ� ����)", "����ð� ����", JOptionPane.INFORMATION_MESSAGE);
				int bookingTimeInt = Integer.parseInt(bookingTime);
				if(bookingTime.equals("")){
					JOptionPane.showMessageDialog(null,"������ ��ҵǾ����ϴ�","���� ���",JOptionPane.INFORMATION_MESSAGE);
				}else if(Integer.parseInt(bookingTime)<60){
					String receipt = "�����Ͻ� ���͵�� ����Ʈ�Դϴ�.\n���͵��                 ����        �������   ���� ����\n";
					for(int i=0;i<menuList.getRowCount();i++){
						String menuName = (String) menuList.getValueAt(i, 1);
						int menuPrice = (Integer) menuList.getValueAt(i,2);
						int quant = (Integer) menuList.getValueAt(i, 3);
						if(quant>0){
							if(i!=0){
								sendMessage+="/"+menuName+"_"+menuPrice+"_"+quant;						
							}else{
								sendMessage+=menuName+"_"+menuPrice+"_"+quant;	
							}
							receipt+=menuName+"      "+menuPrice+"                        "+quant+"            "+(menuPrice*quant)+"\n";
						}else{
							JOptionPane.showMessageDialog(null,"������ �������� �����ϴ�.","���� ����",JOptionPane.ERROR_MESSAGE);
						}
					}
					receipt+="\n�����Ͻ� ���͵�� ����Ʈ�� �� ������ "+totalPrice+" �� �Դϴ�.\n";
					receipt+="����ð� "+bookingTime+"�� ��";
					User user = (User) session.getSession();
					
					String[] hm = orderTime.split(":");
					int hour = Integer.parseInt(hm[0]);
					int min = Integer.parseInt(hm[1]);
					if((min+=bookingTimeInt)>59){
						hour = (++hour)%24;
						min-=60;
					}
					String h="";
					if(hour<10){
						h="0"+(hour%10);
					}else{
						h=String.valueOf(hour);
					}
					String m="";
					if(min<10){
						m="0"+(min%10);
					}else{
						m=String.valueOf(min);
					}
					String reservation = h+":"+m;
					
					Reservation order = new Reservation((String)storeList.getValueAt(storeSelectedRow, 0),new TimeHandler().makeOrderNo(), user.getName(), user.getTel(), reservation, sendMessage);
					Object result = new ClientController().send(POST, ORDER_MENU, order);				
					if(result instanceof Boolean){
						boolean check = (Boolean)result;
						if(check){
							JOptionPane.showMessageDialog(null,receipt,"���� �Ϸ�",JOptionPane.INFORMATION_MESSAGE);
							new UserMainView();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null,"������ �Ϸ���� ���߽��ϴ�.","���� ����",JOptionPane.INFORMATION_MESSAGE);
						}
					}					
				}else{
					JOptionPane.showMessageDialog(null,"����ð��� 60���� �ʰ��� �� �����ϴ�","���� ���",JOptionPane.WARNING_MESSAGE);
				}				
			}
		});
		btn_order.setFont(new Font("����", Font.BOLD, 14));
		btn_order.setIcon(new ImageIcon("gui_imgs/btn_orderMenu_1.png"));
		btn_order.setBounds(217, 391, 150, 34);
		panel_1.add(btn_order);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 48, 355, 333);
		panel_1.add(scrollPane_1);
		
		menuList = new JTable();
		menuList.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int row = menuList.getSelectedRow();
				if(row!=-1){
					int menuPrice = (Integer) menuList.getValueAt(row, 2);
					int menuQuantity = (Integer) menuList.getValueAt(row, 3);
					int step = -e.getWheelRotation();
					int quantity = menuQuantity+step;
					if(quantity>=0&&quantity<20){
						menuList.setValueAt(quantity, row, 3);
						if(menuQuantity<quantity){//��������
							totalPrice += menuPrice;
						}else{//��������
							totalPrice -= menuPrice;
						}
						if(totalPrice!=0){
							lbl_orderTotal.setText("�������� : "+totalPrice+" ��");
						}else{
							lbl_orderTotal.setText("");
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "���͵���� �����ϼ���", "���͵�� ���� ����", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuList.setModel(dtmMenu);
		scrollPane_1.setViewportView(menuList);
		
		lbl_orderTotal = new JLabel("");
		lbl_orderTotal.setForeground(Color.WHITE);
		lbl_orderTotal.setFont(new Font("���� ���", Font.BOLD, 15));
		lbl_orderTotal.setBounds(12, 391, 190, 34);
		panel_1.add(lbl_orderTotal);
		
		btn_back = new JButton("�ڷΰ���");
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SelectMenuView();
				dispose();
			}
		});
		btn_back.setFont(new Font("���� ���", Font.BOLD, 12));
		btn_back.setBounds(682, 90, 100, 28);
		getContentPane().add(btn_back);
		
		setVisible(true);
	}

}