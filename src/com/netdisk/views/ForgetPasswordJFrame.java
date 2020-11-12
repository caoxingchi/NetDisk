package com.netdisk.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.netdisk.Email.EmailGetSH;
import com.netdisk.dao.UserDao;
import com.netdisk.model.User;
import com.netdisk.utils.DbUtil;
import com.netdisk.utils.Md5Encipher;
import com.netdisk.utils.RegexUtil;
import com.netdisk.utils.StringUtil;

public class ForgetPasswordJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JTextField EmailTxt;
	private JTextField PhoneNumTxt;
	private JPanel confirmInfoPanel;
	private JPasswordField passwordTxt;
	private JPasswordField confirmPasswordTxt;
	private JButton ValidateBtn;
	private JButton ConfirmBtn;
	private JPanel modifyPasswordPanel;

	User user=new User();
	UserDao userDao=new UserDao();
	DbUtil db=new DbUtil();
	EmailGetSH code=new EmailGetSH();//邮箱
	private JLabel label_6;
	private JTextField IdentityCodeTxt;
	private JPanel Identifypanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPasswordJFrame frame = new ForgetPasswordJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgetPasswordJFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ForgetPasswordJFrame.class.getResource("/Icon/NetDisk.png")));
		setResizable(false);
		setTitle("找回密码");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 464);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("找回密码");
		label.setFont(new Font("华文行楷", Font.BOLD, 24));
		label.setBounds(194, 10, 113, 39);
		contentPane.add(label);
		
		confirmInfoPanel = new JPanel();
		confirmInfoPanel.setBackground(new Color(173, 216, 230));
		confirmInfoPanel.setBounds(52, 55, 390, 153);
		contentPane.add(confirmInfoPanel);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/user.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("邮 箱：");
		label_2.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/Email.png")));
		
		EmailTxt = new JTextField();
		EmailTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("手机号：");
		label_3.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/phoneT.png")));
		
		PhoneNumTxt = new JTextField();
		PhoneNumTxt.setColumns(10);
		GroupLayout gl_confirmInfoPanel = new GroupLayout(confirmInfoPanel);
		gl_confirmInfoPanel.setHorizontalGroup(
			gl_confirmInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_confirmInfoPanel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_confirmInfoPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_confirmInfoPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
						.addComponent(label_3))
					.addGap(18)
					.addGroup(gl_confirmInfoPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_confirmInfoPanel.createSequentialGroup()
							.addComponent(PhoneNumTxt)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(EmailTxt)
						.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addContainerGap(80, GroupLayout.PREFERRED_SIZE))
		);
		gl_confirmInfoPanel.setVerticalGroup(
			gl_confirmInfoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_confirmInfoPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_confirmInfoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_confirmInfoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(EmailTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_confirmInfoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(PhoneNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(89))
		);
		confirmInfoPanel.setLayout(gl_confirmInfoPanel);
		
		ConfirmBtn = new JButton("确认");
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyPasswordAction(e);
			}
		});
		ConfirmBtn.setBackground(new Color(169, 169, 169));
		ConfirmBtn.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/circle_ok.png")));
		ConfirmBtn.setBounds(271, 384, 121, 33);
		contentPane.add(ConfirmBtn);
		
		ValidateBtn = new JButton("验证");
		ValidateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidateActionPerformed(e);
			}
		});
		ValidateBtn.setBackground(new Color(169, 169, 169));
		ValidateBtn.setBounds(77, 384, 125, 33);
		contentPane.add(ValidateBtn);
		
		modifyPasswordPanel = new JPanel();
		modifyPasswordPanel.setBackground(new Color(173, 216, 230));
		modifyPasswordPanel.setBounds(52, 264, 390, 110);
		modifyPasswordPanel.setVisible(false);
		contentPane.add(modifyPasswordPanel);
		
		JLabel label_4 = new JLabel("密码：");
		label_4.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/Password.png")));
		
		JLabel label_5 = new JLabel("确认密码：");
		label_5.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/confirm_password.png")));
		
		passwordTxt = new JPasswordField();
		passwordTxt.setColumns(10);
		
		confirmPasswordTxt = new JPasswordField();
		confirmPasswordTxt.setColumns(10);
		GroupLayout gl_modifyPasswordPanel = new GroupLayout(modifyPasswordPanel);
		gl_modifyPasswordPanel.setHorizontalGroup(
			gl_modifyPasswordPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyPasswordPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_modifyPasswordPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
						.addComponent(label_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_modifyPasswordPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmPasswordTxt, 189, 189, 189))
					.addGap(59))
		);
		gl_modifyPasswordPanel.setVerticalGroup(
			gl_modifyPasswordPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modifyPasswordPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_modifyPasswordPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(gl_modifyPasswordPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(confirmPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25))
		);
		modifyPasswordPanel.setLayout(gl_modifyPasswordPanel);
		
		Identifypanel = new JPanel();
		Identifypanel.setBounds(52, 218, 390, 33);
		Identifypanel.setBackground(new Color(173, 216, 230));
		contentPane.add(Identifypanel);
		Identifypanel.setVisible(false);
		Identifypanel.setLayout(null);
		
		label_6 = new JLabel("验证码：");
		label_6.setIcon(new ImageIcon(ForgetPasswordJFrame.class.getResource("/Icon/IdentityCode.png")));
		label_6.setBounds(33, 8, 69, 18);
		Identifypanel.add(label_6);
		
		IdentityCodeTxt = new JTextField();
		IdentityCodeTxt.setBounds(136, 7, 193, 21);
		Identifypanel.add(IdentityCodeTxt);
		IdentityCodeTxt.setColumns(10);
	}

	/**
	 * 修改密码
	 * @param e
	 */
	private void ModifyPasswordAction(ActionEvent e) {
		String Code=IdentityCodeTxt.getText();
		if(StringUtil.isEmpty(Code)) {
			JOptionPane.showMessageDialog(null, "验证码不能为空！");
			return;
		}
		if(StringUtil.isEmpty(passwordTxt.getText()) ||
				StringUtil.isEmpty(confirmPasswordTxt.getText())) {
			JOptionPane.showMessageDialog(null, "密码不能为空！请输入！");
			return;
		}
		Connection con=null;
		String password=new String(passwordTxt.getPassword());
		String ConfirmPassword=new String(confirmPasswordTxt.getPassword());
		if(StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(ConfirmPassword) 
				&& !password.equals(ConfirmPassword)) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
			ResetValue( "密码");
			return;
		}
		try {
			if(Code.equals(code.getCode())) {
				System.out.println("验证码正确！！");
			
			con=db.getcon();
			user.setPassword(Md5Encipher.Md5Encode(password));
			int updateUserPassword = userDao.UpdateUserPassword(user, con);
			
			if(updateUserPassword>0) {
				JOptionPane.showMessageDialog(null, "修改密码成功！");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "修改密码失败！");
				ResetValue("密码");
				return;
			}
		}else {
			JOptionPane.showMessageDialog(null, "验证码错误！");
			return;
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 验证操作
	 * @param e
	 */
	private void ValidateActionPerformed(ActionEvent e) {
		if(StringUtil.isEmpty(userNameTxt.getText()) || StringUtil.isEmpty(PhoneNumTxt.getText()) || 
				StringUtil.isEmpty(EmailTxt.getText())) {
			JOptionPane.showMessageDialog(null, "信息不能为空！");
			return ;
		}
	
		Connection con=null;
		String userName=userNameTxt.getText();
		String userTelNum=PhoneNumTxt.getText();
		String email=EmailTxt.getText();
		
		if(!RegexUtil.isEmail(email)) {
			JOptionPane.showMessageDialog(null, "邮箱格式不正确，看是否符合XXX@xx.xx");
			return;
		}
		if(!RegexUtil.isMobile(userTelNum)) {
			JOptionPane.showMessageDialog(null, "手机号码格式不正确！");
			return;
		}
		user.setUserName(userName);
		user.setUser_tel(userTelNum);
		user.setEmail(email);
		
		try {
			con = db.getcon();
			boolean isSuccessed = userDao.isSuccessed(user, con);
			boolean existUser=userDao.isExistAccount(userTelNum, con);
			if(existUser) {
				System.out.println("存在该用户"+user.getUserName());
				if(isSuccessed) {
					ValidateBtn.setEnabled(false);
					code.GetIdentifyCode(user.getEmail());
					Identifypanel.setVisible(true);
					modifyPasswordPanel.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "信息错误！请重新输入！");
					ResetValue("重置");
					return ;
				}
			}else {
				JOptionPane.showMessageDialog(null, "不存在该用户！亲！注册一个吧！");
				ResetValue("重置");
				dispose();
				new RegisterJFrame().setVisible(true);
				return;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/***
	 * 重置
	 */
	public void ResetValue(String str) {
		if("重置".equals(str)) {
			PhoneNumTxt.setText("");
			EmailTxt.setText("");
			userNameTxt.setText("");
		}
		if("密码".equals(str)) {
			passwordTxt.setText("");
			confirmPasswordTxt.setText("");
		}
	
	}
}
