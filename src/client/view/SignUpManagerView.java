package client.view;

import javax.swing.*;

import client.ClientController;
import server.Protocol;
import util.RoomOwner;
import util.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//������ ȸ������
public class SignUpManagerView extends JFrame implements Protocol {

	private JTextField tf_id;
	private JPasswordField tf_pwd;
	private JTextField tf_storeName;
	private JTextField tf_ownNo;
	private JTextField tf_addr;
	private JTextField tf_tel;
	private JButton btn_confirmId;

	private boolean confirmId = false;

	public SignUpManagerView() {
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lbl_regImg = new JLabel("");
		lbl_regImg.setIcon(new ImageIcon("gui_imgs/icon_signUp_2.png"));
		lbl_regImg.setBounds(50, 62, 57, 53);
		getContentPane().add(lbl_regImg);

		JLabel lbl_reg = new JLabel("ȸ������");
		lbl_reg.setFont(new Font("���� ���", Font.BOLD, 16));
		lbl_reg.setBounds(119, 62, 112, 53);
		getContentPane().add(lbl_reg);

		JLabel lbl_regDes = new JLabel("ȸ������ ������ �Է����ּ���.");
		lbl_regDes.setBounds(85, 125, 200, 30);
		getContentPane().add(lbl_regDes);

		JLabel lbl_id = new JLabel("���̵�");
		lbl_id.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_id.setBounds(50, 179, 100, 28);
		getContentPane().add(lbl_id);

		JLabel lbl_pwd = new JLabel("��й�ȣ");
		lbl_pwd.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_pwd.setBounds(50, 219, 100, 28);
		getContentPane().add(lbl_pwd);

		JLabel lbl_storeName = new JLabel("���͵�� ��");
		lbl_storeName.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_storeName.setBounds(50, 259, 100, 28);
		getContentPane().add(lbl_storeName);
		
		JLabel lbl_ownNo = new JLabel("����� ��ȣ");
		lbl_ownNo.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_ownNo.setBounds(50, 299, 100, 28);
		getContentPane().add(lbl_ownNo);


		JLabel lbl_addr = new JLabel("�ּ�");
		lbl_addr.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_addr.setBounds(50, 339, 100, 28);
		getContentPane().add(lbl_addr);

		JLabel lbl_tel = new JLabel("��ȭ��ȣ");
		lbl_tel.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_tel.setBounds(50, 379, 100, 28);
		getContentPane().add(lbl_tel);

		JLabel lbl_type = new JLabel("���͵�� �뵵");
		lbl_type.setFont(new Font("���� ���", Font.BOLD, 13));
		lbl_type.setBounds(50, 419, 100, 28);
		getContentPane().add(lbl_type);

		tf_id = new JTextField();
		tf_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				confirmId=false;
				btn_confirmId.setEnabled(!confirmId);
			}
		});
		tf_id.setBounds(160, 179, 206, 28);
		getContentPane().add(tf_id);
		tf_id.setColumns(10);

		tf_pwd = new JPasswordField();
		tf_pwd.setBounds(160, 219, 206, 28);
		getContentPane().add(tf_pwd);
		tf_pwd.setColumns(10);

		tf_storeName = new JTextField();
		tf_storeName.setBounds(160, 259, 206, 28);
		getContentPane().add(tf_storeName);
		tf_storeName.setColumns(10);

		tf_ownNo = new JTextField();
		tf_ownNo.setBounds(160, 299, 206, 28);
		getContentPane().add(tf_ownNo);
		tf_ownNo.setColumns(10);

		tf_addr = new JTextField();
		tf_addr.setBounds(160, 339, 207, 28);
		getContentPane().add(tf_addr);
		tf_addr.setColumns(10);

		tf_tel = new JTextField();
		tf_tel.setBounds(160, 379, 207, 28);
		getContentPane().add(tf_tel);
		tf_tel.setColumns(10);

		btn_confirmId = new JButton("");
		btn_confirmId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �Է��� �����͸� ������ ����
					if (tf_id.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���", "���̵� �Է� ����", JOptionPane.WARNING_MESSAGE);
					} else {
						// ������ ������ ���Ͽ��� ���̵� ã��
						String message = tf_id.getText();
						Object result = new ClientController().send(POST, REG_ID_CHECK, message);
						if(result instanceof Boolean){
							boolean check = (boolean)result;
							if(check){// ���̵� ������
								JOptionPane.showMessageDialog(null, "�Է��Ͻ� ���̵� �����մϴ�.", "���̵� �ߺ�", JOptionPane.WARNING_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null, "����� �� �ִ� ���̵��Դϴ�.", "���̵� ��� ����", JOptionPane.INFORMATION_MESSAGE);
								confirmId = true;
								btn_confirmId.setEnabled(!confirmId);
							}
						}
					}
			}
		});

		JComboBox cb_type = new JComboBox();
		cb_type.setModel(new DefaultComboBoxModel(new String[] { "1�ν�", "2~4�ν�", "���̳���", "���ǽ�" }));
		cb_type.setBounds(160, 424, 206, 28);
		getContentPane().add(cb_type);
		btn_confirmId.setIcon(new ImageIcon("gui_imgs/btn_signUp_3.png"));
		btn_confirmId.setBounds(378, 179, 80, 28);
		getContentPane().add(btn_confirmId);

		JButton btn_submit = new JButton("");
		btn_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ���� ������ ȸ������ ����
				if (tf_id.getText().equals("")) {// ���̵��ĭȮ��
					JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���", "���̵� �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else if (!confirmId) {// ���̵� �ߺ��˻� Ȯ��
					JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� ���ּ���", "���̵� �ߺ�Ȯ�� ����",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (tf_pwd.getText().equals("")) {// ��й�ȣ ��ĭȮ��
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���", "��й�ȣ �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else if (tf_storeName.getText().equals("")) {// ���� �̸� ��ĭȮ��
					JOptionPane.showMessageDialog(null, "���� �̸��� �Է����ּ���", "���� �̸� �Է� ����",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (tf_tel.getText().equals("")) {// ����ڹ�ȣ ��ĭȮ��
					JOptionPane.showMessageDialog(null, "����� ��ȣ�� �Է����ּ���", "����� ��ȣ �Է� ����",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (tf_tel.getText().equals("")) {// �ּ� ��ĭȮ��
					JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���", "�ּ� �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else if (tf_tel.getText().equals("")) {// ��ȭ��ȣ ��ĭȮ��
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է����ּ���", "��ȭ��ȣ �Է� ����", JOptionPane.WARNING_MESSAGE);
				}else{
					RoomOwner message = new RoomOwner(false, tf_id.getText(), tf_pwd.getText(), tf_storeName.getText(),tf_tel.getText(),tf_ownNo.getText(), tf_addr.getText(),(String) cb_type.getSelectedItem());
					Object result = new ClientController().send(POST, REG_USER, message);
					if(result instanceof Boolean){
						boolean check = (boolean)result;
						if (check) {
							JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.", "����� ȸ������ �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
							new LoginView();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "ȸ�������� �����Ͽ����ϴ�.", "����� ȸ������ ����", JOptionPane.INFORMATION_MESSAGE);
						}
					}		
				}
			}
		});
		btn_submit.setIcon(new ImageIcon("gui_imgs/btn_signUp_4.png"));
		btn_submit.setBounds(174, 476, 151, 64);
		getContentPane().add(btn_submit);

		JButton btn_back = new JButton("�ڷΰ���");
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ȸ������ ���� ȭ������ �̵�
				new SignUpView();
				dispose();
			}
		});
		btn_back.setBounds(382, 10, 100, 25);
		getContentPane().add(btn_back);

		this.setVisible(true);
	}
}
