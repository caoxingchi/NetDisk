package com.netdisk.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.netdisk.dao.UserDao;
import com.netdisk.model.User;
import com.netdisk.utils.DbUtil;
import com.netdisk.utils.Md5Encipher;
import com.netdisk.utils.StringUtil;

public class LoginJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField AcountTxt;
	private JPasswordField PasswordTxt;
	private JButton loginBtn;
	DbUtil db=new DbUtil();
	User user=new User();
	UserDao userDao=new UserDao();
	private JLabel forgetPasswordL;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJFrame frame = new LoginJFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginJFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginJFrame.class.getResource("/images_login/netDiskIco.png")));
		setResizable(false);
		setTitle("                                                            网 盘 登 录");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 413);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("手机号/账号：");
		label.setIcon(new ImageIcon(LoginJFrame.class.getResource("/Icon/userT.png")));
		label.setFont(new Font("华文行楷", Font.BOLD, 15));
		label.setBounds(144, 125, 141, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密       码：");
		label_1.setIcon(new ImageIcon(LoginJFrame.class.getResource("/Icon/Password.png")));
		label_1.setFont(new Font("华文行楷", Font.BOLD, 15));
		label_1.setBounds(144, 199, 141, 15);
		contentPane.add(label_1);
		
		AcountTxt = new JTextField();
		AcountTxt.setBounds(295, 121, 171, 21);
		contentPane.add(AcountTxt);
		AcountTxt.setColumns(10);
		
		PasswordTxt = new JPasswordField();
		PasswordTxt.setBounds(295, 195, 171, 21);
		contentPane.add(PasswordTxt);
		PasswordTxt.setColumns(10);
		
		loginBtn = new JButton("");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		loginBtn.setBackground(new Color(60, 179, 113));
		loginBtn.setIcon(new ImageIcon(LoginJFrame.class.getResource("/Icon/confirm_password.png")));
		loginBtn.setFont(new Font("华文行楷", Font.BOLD, 14));
	
		loginBtn.setBounds(144, 265, 322, 33);
		contentPane.add(loginBtn);
		
		JButton registerBtn = new JButton("注册");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterJFrame().setVisible(true);
			}
		});
		registerBtn.setBackground(new Color(60, 179, 113));
		registerBtn.setFont(new Font("华文行楷", Font.BOLD, 14));
	
		registerBtn.setBounds(479, 324, 93, 23);
		contentPane.add(registerBtn);
		
		forgetPasswordL = new JLabel("忘记密码？");
		forgetPasswordL.setFont(new Font("华文行楷", Font.PLAIN, 16));
		forgetPasswordL.setBounds(276, 359, 86, 15);
		forgetPasswordL.addMouseListener(new ClickForgetPasswordL());
		contentPane.add(forgetPasswordL);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(LoginJFrame.class.getResource("/Icon/NetDisk.png")));
		label_2.setBounds(260, 10, 75, 71);
		contentPane.add(label_2);
		
		
	}

	/*
	 * 登陆事件处理
	 */
	private void loginActionPerformed(ActionEvent e) {
		boolean isExistUser=false;
		if(StringUtil.isEmpty(AcountTxt.getText())) {
			JOptionPane.showMessageDialog(null, "账户不能为空！");
			return ;
		}
		if(StringUtil.isEmpty(PasswordTxt.getText())) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return ;
		}
		
		String account=AcountTxt.getText();
		String Password=new String(PasswordTxt.getPassword());
		Connection con=null;
		try {
			con = db.getcon();
			isExistUser=userDao.isExistAccount(account, con);
			if(isExistUser) {
				user=userDao.Login(con, account, Md5Encipher.Md5Encode(Password));
				System.out.println(user);
				if(user!=null) {
					ResetValue();
					dispose();
					System.out.println("登录成功！");
					ResetValue();
					new NetDiskMainJFrame(user).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "登录失败！");
					ResetValue();
					return;
				}
			}else {
				JOptionPane.showMessageDialog(null, "不存在该用户！亲！注册一个账号吧！");
				ResetValue();
				dispose();
				new RegisterJFrame().setVisible(true);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 重置
	 */
	public void ResetValue() {
		AcountTxt.setText("");
		PasswordTxt.setText("");
	}
	
	class ClickForgetPasswordL implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			forgetPasswordL.setFont(new Font("华文行楷", Font.PLAIN, 16));
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			new ForgetPasswordJFrame().setVisible(true);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			forgetPasswordL.setFont(new Font("华文行楷", Font.PLAIN, 17));
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			forgetPasswordL.setFont(new Font("华文行楷", Font.PLAIN, 16));
			
		}
		
	}
}
