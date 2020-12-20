package client.view;

import client.ClientController;
import client.SessionController;
import server.Protocol;
import util.Room;
import util.RoomList;
import util.Reservation;
import util.ReservationList;
import util.Session;
import util.User;
import client.view.LoginView;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;

//����� ����ȭ��
public class UserMainView extends JFrame implements Protocol {
	private JTable tb_recentlyOrder;
	private JButton btn_editInfo;
	private JButton btn_startOrder;
	private JLabel lbl_userName;
	private JLabel lbl_userId;
	private Session session = new Session();
	private DefaultTableModel dtmOrder = new DefaultTableModel(new Object[][] {}, new String[] {
		"�ֹ���ȣ","���� �̸�","��ȭ��ȣ","����ð�","����"
		}){  //�� ���� ���ϰ� �ϴ� �κ�
	 public boolean isCellEditable(int row, int column){
		    return false;
	 }};
	 
	public UserMainView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				session.setSession(new SessionController().sessionLoad());
				User user = (User)session.getSession();
				lbl_userName.setText(user.getName());
				lbl_userId.setText(user.getId());
				//�ֱ��ֹ����� �ʱ�ȭ
				String message = user.getId();
				Object result = new ClientController().send(GET, ORDER_HISTORY_LIST, message);		
				if(result instanceof ReservationList){
					ReservationList orderlist = (ReservationList) result;
					ArrayList<Reservation> order = orderlist.getReservationList();
					Object[][] resultList = new Object[order.size()][6];
					for (int i = 0; i < resultList.length; i++) {
						resultList[i][0] = ((Reservation) (order.get(i))).getOrderNo();
						resultList[i][1]=((Reservation)(order.get(i))).getDesStore();
						resultList[i][2] = ((Reservation) (order.get(i))).getTel();
						resultList[i][3]=((Reservation)(order.get(i))).getReservation();
						resultList[i][4] = ((Reservation) (order.get(i))).isAcceptOrder();
					}
					for (int i = 0; i < resultList.length; i++) {
						dtmOrder.addRow(resultList[i]);
					}
					tb_recentlyOrder.setModel(dtmOrder);
				}
			}
		});
		this.setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 255));

		JPanel panel = new JPanel();
		panel.setBackground(new Color( 0, 70, 42));
		panel.setBounds(0, 0, 794, 80);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel title_text = new JLabel("����������");
		title_text.setIcon(null);
		title_text.setFont(new Font("���� ���", Font.BOLD, 24));
		title_text.setBounds(86, 10, 302, 60);
		panel.add(title_text);
		
		JLabel title_icon = new JLabel("");
		title_icon.setIcon(new ImageIcon("gui_imgs/icon_user_1.png"));
		title_icon.setBounds(12, 10, 62, 60);
		panel.add(title_icon);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 90, 772, 143);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lbl_Img1 = new JLabel("");
		lbl_Img1.setBounds(0, 0, 154, 143);
		panel_1.add(lbl_Img1);
		lbl_Img1.setIcon(new ImageIcon("gui_imgs/thumbnail_img_sample.PNG"));

		lbl_userName = new JLabel("����� �̸�");
		lbl_userName.setForeground(Color.DARK_GRAY);
		lbl_userName.setFont(new Font("���� ���", Font.BOLD, 22));
		lbl_userName.setBounds(166, 28, 270, 29);
		panel_1.add(lbl_userName);

		lbl_userId = new JLabel("����� ���̵�");
		lbl_userId.setForeground(Color.DARK_GRAY);
		lbl_userId.setFont(new Font("���� ���", Font.BOLD, 18));
		lbl_userId.setBounds(176, 67, 270, 23);
		panel_1.add(lbl_userId);

		JButton btn_logout = new JButton("�α׾ƿ�");
		btn_logout.setBackground(Color.WHITE);
		btn_logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "�α׾ƿ� �Ͻðڽ��ϱ�?", "�α׾ƿ�", JOptionPane.OK_CANCEL_OPTION);
				if (result == 0) { // ĵ���̸� 2 ���� ok�� ����
					new SessionController().deleteSession(session);
					new LoginView();
					dispose();
				}
			}
		});
		btn_logout.setFont(new Font("���� ���", Font.BOLD, 12));
		btn_logout.setBounds(672, 0, 100, 28);
		panel_1.add(btn_logout);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255,255,255));
		panel_2.setBounds(0, 243, 794, 60);
		getContentPane().add(panel_2);

		JLabel lbl_userMenu = new JLabel("����� ����");
		lbl_userMenu.setForeground(Color.WHITE);
		lbl_userMenu.setFont(new Font("���� ���", Font.PLAIN, 22));
		lbl_userMenu.setBounds(16, 6, 228, 46);
		panel_2.add(lbl_userMenu);

		// �ֹ��ϱ� ��ư ������ �޴����� ȭ������ �̵�
		btn_startOrder = new JButton("");
		btn_startOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SelectMenuView();
				dispose();
			}
		});
		btn_startOrder.setIcon(new ImageIcon("gui_imgs/btn_orderMenu_1.png"));
		btn_startOrder.setFont(new Font("����", Font.BOLD, 13));
		btn_startOrder.setBounds(642, 12, 140, 40);
		panel_2.add(btn_startOrder);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(10, 313, 772, 249);
		getContentPane().add(panel_4);

		JLabel lbl_recentlyOrder = new JLabel("�ֱ� �ֹ� ����");
		lbl_recentlyOrder.setForeground(new Color(245, 245, 245));
		lbl_recentlyOrder.setFont(new Font("���� ���", Font.BOLD, 15));
		lbl_recentlyOrder.setBounds(12, 8, 144, 27);
		panel_4.add(lbl_recentlyOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 748, 198);
		panel_4.add(scrollPane);
		
				tb_recentlyOrder = new JTable();
				scrollPane.setViewportView(tb_recentlyOrder);
				tb_recentlyOrder.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"\uC8FC\uBB38\uBC88\uD638", "\uAC00\uAC8C \uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uC608\uC57D\uC2DC\uAC04", "\uC0C1\uD0DC"
					}
				));

		setVisible(true);
	}
}