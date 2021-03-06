package client.view;

import javax.swing.*;

import client.ClientController;
import client.ClientSender;
import client.SessionController;
import server.Protocol;
import util.RoomOwner;
import util.Packet;
import util.User;

import java.awt.*;
import java.awt.event.*;



//로그인화면
public class LoginView extends JFrame implements Protocol{
	private JTextField tf_id;
	private JTextField tf_pwd;
	private JLabel lbl_regist;
	private JLabel lbl_findInfo;	
	
	public LoginView() {
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lbl_logoImg = new JLabel("");
		lbl_logoImg.setIcon(new ImageIcon("gui_imgs/mylogo.png"));
		lbl_logoImg.setBackground(Color.WHITE);
		lbl_logoImg.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_logoImg.setBounds(12, 10, 470, 348);
		getContentPane().add(lbl_logoImg);

		tf_id = new JTextField();
		tf_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_id.setToolTipText("");
		tf_id.setBounds(121, 368, 163, 36);
		getContentPane().add(tf_id);
		tf_id.setColumns(10);

		tf_pwd = new JTextField();
		tf_pwd.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_pwd.setToolTipText("");
		tf_pwd.setBounds(121, 418, 163, 35);
		getContentPane().add(tf_pwd);
		tf_pwd.setColumns(10);

		JButton btn_login = new JButton("");
		btn_login.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 로그인
				if(tf_id.getText().equals("")){
					JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.","로그인 오류",JOptionPane.WARNING_MESSAGE);
				}
				else if(tf_pwd.getText().equals("")){
					JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요.","로그인 오류",JOptionPane.WARNING_MESSAGE);
				}
				else{
					String message = tf_id.getText()+"/"+ tf_pwd.getText();
					Object result = new ClientController().send(POST, LOGIN, message);
					if(result instanceof Boolean){
						boolean check = (boolean) result;
						if(check){
							JOptionPane.showMessageDialog(null,"비밀번호가 맞지않습니다.","로그인 오류",JOptionPane.WARNING_MESSAGE); 
						}
						else{
							JOptionPane.showMessageDialog(null,"존재하지 않는 아이디입니다.","로그인 오류",JOptionPane.ERROR_MESSAGE); 
						}
					}
					else if(result instanceof RoomOwner){
						JOptionPane.showMessageDialog(null,"매장관리자 로그인 성공","알림",JOptionPane.INFORMATION_MESSAGE);
						new SessionController().sessionSave(result);
						new ManagerMainView();
						dispose();
					}
					else if(result instanceof User){					
						JOptionPane.showMessageDialog(null,"일반 사용자 로그인 성공","알림",JOptionPane.INFORMATION_MESSAGE);
						new SessionController().sessionSave(result);
						new UserMainView();
						dispose();
					}
				}
			}
		});
		btn_login.setBackground(Color.WHITE);
		btn_login.setIcon(new ImageIcon("gui_imgs/btn_login_3.png"));
		btn_login.setBounds(296, 368, 98, 85);
		getContentPane().add(btn_login);

		JLabel lbl_idImg = new JLabel("");
		lbl_idImg.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl_idImg.setIcon(new ImageIcon("gui_imgs/icon_login_1.png"));
		lbl_idImg.setBounds(90, 368, 34, 34);
		getContentPane().add(lbl_idImg);

		JLabel lbl_pwdImg = new JLabel("");
		lbl_pwdImg.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl_pwdImg.setIcon(new ImageIcon("gui_imgs/icon_login_2.png"));
		lbl_pwdImg.setBounds(90, 418, 34, 36);
		getContentPane().add(lbl_pwdImg);

		JLabel lbl_regImg = new JLabel("");
		lbl_regImg.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl_regImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUpView();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_regist.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_regist.setForeground(Color.BLACK);
			}
		});
		lbl_regImg.setIcon(new ImageIcon("gui_imgs/btn_login_1.png"));
		lbl_regImg.setBounds(90, 474, 41, 40);
		getContentPane().add(lbl_regImg);

		lbl_regist = new JLabel("회원가입");
		lbl_regist.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl_regist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUpView();
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_regist.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_regist.setForeground(Color.BLACK);
			}
		});
		lbl_regist.setBounds(131, 478, 69, 36);
		getContentPane().add(lbl_regist);


		setVisible(true);
	}
}