package client.view;

import javax.swing.*;

import client.ClientController;
import server.Protocol;
import util.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//����� ȸ������
public class SignUpUserView extends JFrame implements Protocol {
	private JTextField tf_id;
	private JTextField tf_name;
	private JTextField tf_tel;
	private JPasswordField tf_pwd;
	private JButton btn_confirmId;

	private boolean confirmId = false;

	public SignUpUserView() {
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lbl_regImg = new JLabel("");
		lbl_regImg.setIcon(new ImageIcon("gui_imgs/icon_signUp_2.png"));
		lbl_regImg.setBounds(60, 62, 57, 54);
		getContentPane().add(lbl_regImg);

		JLabel lbl_reg = new JLabel("ȸ������");
		lbl_reg.setFont(new Font("���� ���", Font.BOLD, 16));
		lbl_reg.setBounds(129, 74, 82, 42);
		getContentPane().add(lbl_reg);

		JLabel lbl_regDes = new JLabel("ȸ������ ������ �Է����ּ���.");
		lbl_regDes.setFont(new Font("���� ���", Font.PLAIN, 12));
		lbl_regDes.setBounds(73, 126, 168, 15);
		getContentPane().add(lbl_regDes);

		JLabel lbl_id = new JLabel("���̵�");
		lbl_id.setFont(new Font("���� ���", Font.PLAIN, 12));
		lbl_id.setBounds(73, 186, 57, 15);
		getContentPane().add(lbl_id);

		JLabel lbl_pwd = new JLabel("��й�ȣ");
		lbl_pwd.setFont(new Font("���� ���", Font.PLAIN, 12));
		lbl_pwd.setBounds(73, 221, 57, 32);
		getContentPane().add(lbl_pwd);

		JLabel lbl_name = new JLabel("�̸�");
		lbl_name.setFont(new Font("���� ���", Font.PLAIN, 12));
		lbl_name.setBounds(73, 266, 57, 29);
		getContentPane().add(lbl_name);

		JLabel lbl_tel = new JLabel("��ȭ��ȣ");
		lbl_tel.setFont(new Font("���� ���", Font.PLAIN, 12));
		lbl_tel.setBounds(73, 305, 69, 31);
		getContentPane().add(lbl_tel);

		tf_id = new JTextField();
		tf_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				confirmId=false;
				btn_confirmId.setEnabled(!confirmId);
			}
		});
		tf_id.setBounds(154, 179, 192, 29);
		getContentPane().add(tf_id);
		tf_id.setColumns(10);

		tf_pwd = new JPasswordField();
		tf_pwd.setBounds(154, 225, 192, 29);
		getContentPane().add(tf_pwd);
		tf_pwd.setColumns(10);

		tf_name = new JTextField();
		tf_name.setBounds(154, 266, 192, 29);
		getContentPane().add(tf_name);
		tf_name.setColumns(10);

		tf_tel = new JTextField();
		tf_tel.setBounds(154, 308, 192, 29);
		getContentPane().add(tf_tel);
		tf_tel.setColumns(10);

		btn_confirmId = new JButton("");
		btn_confirmId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ���̵� �ߺ�Ȯ��
				// ���̵� ��ĭ�̸� ����
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
		btn_confirmId.setIcon(new ImageIcon("gui_imgs/btn_signUp_3.png"));
		btn_confirmId.setBounds(358, 179, 83, 29);
		getContentPane().add(btn_confirmId);

		JButton btn_submit = new JButton("");
		btn_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �Ϲ� ����� ȸ������ ����
				// ��ȿ���˻�
				if (tf_id.getText().equals("")) {// ���̵��ĭȮ��
					JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���", "���̵� �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else if (!confirmId) {// ���̵� �ߺ��˻� Ȯ��
					JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� ���ּ���", "���̵� �ߺ�Ȯ�� ����",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (tf_pwd.getText().equals("")) {// ��й�ȣ ��ĭȮ��
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���", "��й�ȣ �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else if (tf_name.getText().equals("")) {// �̸� ��ĭȮ��
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���", "����� �̸� �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else if (tf_tel.getText().equals("")) {// ��ȭ��ȣ ��ĭȮ��
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է����ּ���", "��ȭ��ȣ �Է� ����", JOptionPane.WARNING_MESSAGE);
				} else {
					// �Է��� �����͸� ������ ����
					User message = new User(true, tf_id.getText(), tf_pwd.getText(), tf_name.getText(), tf_tel.getText());
					Object result = new ClientController().send(POST, REG_USER, message);
					if(result instanceof Boolean){
						boolean check = (boolean)result;
						if (check) {
							JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.", "����� ȸ������ �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
							new LoginView();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "ȸ�������� �����Ͽ����ϴ�.", "����� ȸ������ ����", JOptionPane.WARNING_MESSAGE);
						}
					}					
				}
			}
		});
		btn_submit.setIcon(new ImageIcon("gui_imgs/btn_signUp_4.png"));
		btn_submit.setBounds(175, 441, 150, 64);
		getContentPane().add(btn_submit);

		JButton btn_back = new JButton("�ڷΰ���");
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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