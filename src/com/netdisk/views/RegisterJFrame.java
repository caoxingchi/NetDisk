package com.netdisk.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.eltima.components.ui.DatePicker;
import com.netdisk.dao.UserDao;
import com.netdisk.model.User;
import com.netdisk.utils.DbUtil;
import com.netdisk.utils.Md5Encipher;
import com.netdisk.utils.RegexUtil;
import com.netdisk.utils.StringUtil;

public class RegisterJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JTextField PhoneNumTxt;
	private JPasswordField passwordTxt;
	private JPasswordField confirmPasswordTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField emailTxt;
	
	public boolean isleap;
	private JComboBox years;
	private JRadioButton maleRdb;
	private JRadioButton femaleRdb;
	private JButton resetBtn;
	User user=new User();
	DbUtil db=new DbUtil();
	UserDao userDao=new UserDao();
	private DatePicker datePick;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterJFrame frame = new RegisterJFrame();
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
	public RegisterJFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterJFrame.class.getResource("/images_register/cloud.png")));
		Font font=new Font("华文楷体", Font.BOLD, 14);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("                                                                       注册界面");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/user.png")));
		label.setFont(font);
		label.setBounds(39, 69, 88, 21);
		contentPane.add(label);
		
		userNameTxt = new JTextField();
		userNameTxt.setBounds(164, 69, 172, 21);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("手机号：");
		label_1.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/phoneT.png")));
		label_1.setFont(font);
		label_1.setBounds(39, 120, 88, 21);
		contentPane.add(label_1);
		
		PhoneNumTxt = new JTextField();
		PhoneNumTxt.setBounds(164, 120, 172, 21);
		contentPane.add(PhoneNumTxt);
		PhoneNumTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("密  码：");
		label_2.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/Password.png")));
		label_2.setFont(font);
		label_2.setBounds(39, 176, 88, 18);
		contentPane.add(label_2);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(164, 176, 172, 21);
		contentPane.add(passwordTxt);
		passwordTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("确认密码:");
		label_3.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/confirm_password.png")));
		label_3.setFont(font);
		label_3.setBounds(39, 236, 101, 21);
		contentPane.add(label_3);
		
		confirmPasswordTxt = new JPasswordField();
		confirmPasswordTxt.setBounds(164, 236, 172, 21);
		contentPane.add(confirmPasswordTxt);
		confirmPasswordTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("性别：");
		label_4.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/sexCho.png")));
		label_4.setFont(font);
		label_4.setBounds(395, 69, 73, 21);
		contentPane.add(label_4);
		
		maleRdb = new JRadioButton("男");
		maleRdb.setBackground(new Color(173,216,230));
		maleRdb.setFont(font);
		maleRdb.setSelected(true);
		buttonGroup.add(maleRdb);
		maleRdb.setBounds(474, 68, 54, 23);
		contentPane.add(maleRdb);
		
		femaleRdb = new JRadioButton("女");
		femaleRdb.setBackground(new Color(173,216,230));
		femaleRdb.setFont(font);
		//37,83,179
		buttonGroup.add(femaleRdb);
		femaleRdb.setBounds(530, 68, 62, 23);
		contentPane.add(femaleRdb);
		
		JLabel lblNewLabel = new JLabel("邮箱：");
		lblNewLabel.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/email_16.png")));
		lblNewLabel.setFont(font);
		lblNewLabel.setBounds(395, 123, 73, 21);
		contentPane.add(lblNewLabel);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(474, 120, 177, 21);
		contentPane.add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("生日：");
		label_5.setIcon(new ImageIcon(RegisterJFrame.class.getResource("/Icon/dateCho.png")));
		label_5.setFont(font);
		label_5.setBounds(395, 179, 73, 15);
		contentPane.add(label_5);
		
		years = new JComboBox();
		 // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();   
        // 字体
        Font fontT = new Font("华文楷体", Font.BOLD, 14);
        Dimension dimension = new Dimension(177, 21);
		datePick = new DatePicker(date, DefaultFormat, fontT, dimension);
		
		datePick.fd.setEditable(false);;
	/*	System.out.println(datePick.getText());*/
		datePick.setBounds(474, 176, 177, 21);
		contentPane.add(datePick);
/*		System.out.println(datePick.fd.getText());*/
		JButton registerBtn = new JButton("注册");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterAction(e);
			}
		});
		registerBtn.setBackground(new Color(189, 183, 107));
		registerBtn.setFont(font);
		registerBtn.setBounds(414, 235, 114, 32);
		contentPane.add(registerBtn);
		
		resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetValue("重置");
			}
		});
		resetBtn.setBackground(new Color(189, 183, 107));
		resetBtn.setFont(new Font("华文楷体", Font.BOLD, 14));
		resetBtn.setBounds(538, 235, 113, 32);
		contentPane.add(resetBtn);
		
		JButton BackToLoginBtn = new JButton("返回登录");
		BackToLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginJFrame().setVisible(true);
			}
		});
		BackToLoginBtn.setBackground(new Color(189, 183, 107));
		BackToLoginBtn.setFont(new Font("华文楷体", Font.BOLD, 14));
		BackToLoginBtn.setBounds(414, 277, 237, 34);
		contentPane.add(BackToLoginBtn);
				
		/*String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			e.printStackTrace();
		} */
	}

	/**
	 * 注册事件
	 * @param e
	 */
	private void RegisterAction(ActionEvent e) {
		Connection con=null;
		boolean existTel=false;
		boolean existUserNum=false;
		boolean existUserTel=false;
		String username=userNameTxt.getText();
		String tel_num=PhoneNumTxt.getText();
		String Password=null;
		String ConfirmPassword=null;
		String email=emailTxt.getText();
		String userNum=StringUtil.GetUserNun();
		try {
			Password = Md5Encipher.Md5Encode(new String(passwordTxt.getPassword()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			ConfirmPassword = Md5Encipher.Md5Encode(new String(confirmPasswordTxt.getPassword()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "用户名、电话号码、密码、邮箱不能为空！");
			return ;
		}
		
		if(StringUtil.isEmpty(tel_num)) {
			JOptionPane.showMessageDialog(null, "用户名、电话号码、密码、邮箱不能为空！");
			return ;
		}
		
		if(StringUtil.isEmpty(Password) || StringUtil.isEmpty(ConfirmPassword)) {
			JOptionPane.showMessageDialog(null, "用户名、电话号码、密码、邮箱不能为空！");
			return ;
		}
		
		if(StringUtil.isNotEmpty(Password) && StringUtil.isNotEmpty(ConfirmPassword) && !Password.equals(ConfirmPassword)) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
			ResetValue( "密码");
			return;
		}
		
		if(StringUtil.isEmpty(emailTxt.getText())) {
			JOptionPane.showMessageDialog(null, "用户名、电话号码、密码、邮箱不能为空！");
			return ;
		}
		
		if(!RegexUtil.isEmail(email)) {
			ResetValue("邮箱");
			JOptionPane.showMessageDialog(null, "邮箱格式不正确，看是否符合XXX@xx.xx");
			return;
		}
		if(!RegexUtil.isMobile(tel_num)) {
			ResetValue("手机号");
			JOptionPane.showMessageDialog(null, "手机号码格式不正确！");
			return;
		}
		user.setUserName(username);
		user.setUser_tel(tel_num);
		user.setPassword(Password);
		user.setEmail(email);
		user.setUserNum(userNum);
		user.setBirth(datePick.fd.getText());
		if(maleRdb.isSelected()) {
			user.setGender("男");
		}
		if(femaleRdb.isSelected()) {
			user.setGender("女");
		}
		try {
			con = db.getcon();
			existTel = userDao.isExistAccount(user.getUser_tel(),con);
			existUserNum=userDao.isExistAccount(user.getUserNum(),con);
			existUserTel=userDao.isExistAccount(user.getUser_tel(), con);
			if(existTel | existUserNum | existUserTel) {
				JOptionPane.showMessageDialog(null, "亲！该用户已被注册，换一个用户名吧！");
				ResetValue("重置");
				return;
			}else {
				int success=userDao.Register(con, user);
				if(success==1) {
					JOptionPane.showMessageDialog(null, "恭喜 "+username+" 注册成功！");
					JOptionPane.showMessageDialog(null, "您的账号为："+user.getUserNum());
					ResetValue("重置");
					dispose();
					new LoginJFrame().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "注册失败！");
					return;
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 重置
	 * @param str
	 */
	private void ResetValue(String str) {
		if("手机号".equals(str.trim())){
			PhoneNumTxt.setText("");
		}
		if("邮箱".equals(str.trim())) {
			emailTxt.setText("");
		}
		if("重置".equals(str.trim())) {
			userNameTxt.setText("");
			PhoneNumTxt.setText("");
			passwordTxt.setText("");
			confirmPasswordTxt.setText("");
			emailTxt.setText("");
			if(!"男".equals(user.getGender())) {
				maleRdb.setSelected(true);
			}
			
		}
		if("密码".equals(str.trim())) {
			passwordTxt.setText("");
			confirmPasswordTxt.setText("");
		}
	}
}
