package com.netdisk.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.netdisk.config.GlobalConfig;
import com.netdisk.dao.UserDao;
import com.netdisk.dao.UserFileDao;
import com.netdisk.model.Message;
import com.netdisk.model.MessageType;
import com.netdisk.model.User;
import com.netdisk.model.UserFile;
import com.netdisk.utils.DateUtil;
import com.netdisk.utils.DbUtil;
import com.netdisk.utils.isWFile;

public class NetDiskMainJFrame extends JFrame {

	private JPanel contentPane;
	private JPanel DetailInfoPanel;
	private JButton upLoad;
	private User user;
	private JButton Download;
	private JButton DeleteBtn;
	private JButton NewFileBtn;
	private JLabel userName;
	private JToggleButton MyNetDiskBtn;
	private JList list;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable Maintable;
	private JTable Trantable;
	private JTable Downtable;
	private JScrollPane MainscrollPane;
	private JToggleButton transmission_listBtn;
	
	DbUtil db=new DbUtil();
	UserFileDao userFileDao=new UserFileDao();
	UserFile userFile=new UserFile();
	private JScrollPane scrollPane;
	private JList tranlist;
	DecimalFormat df = new DecimalFormat("#0.0");
	
	private File upLoadfile;
	private Object[] table_models;
	private Object[] tran_model;
	private Object[] down_model;
	private JLabel MyCapacity;
	private int UpLoadTranrow =0;
	private int DownLoadTranrow =0;
	private final JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NetDiskMainJFrame frame = new NetDiskMainJFrame(null);
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
	public NetDiskMainJFrame(User user) {
		
		this.user=user;
		
		userFile.setUserNum(user.getUserNum());
		setIconImage(Toolkit.getDefaultToolkit().getImage(NetDiskMainJFrame.class.getResource("/Icon/NetDisk.png")));
		setTitle("ITcolorsNetDisk");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 81, 157, 400);

		DetailInfoPanel = new JPanel();
		DetailInfoPanel.setBounds(5, 5, 925, 74);
		DetailInfoPanel.setBorder(new LineBorder(Color.BLACK));
		DetailInfoPanel.setLayout(null);
		
		JLabel LogoLab = new JLabel("IT网盘");
		LogoLab.setBorder(new LineBorder(Color.BLACK));
		LogoLab.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 21));
		LogoLab.setIcon(new ImageIcon(NetDiskMainJFrame.class.getResource("/Icon/NetDisk.png")));
		LogoLab.setBounds(0, 0, 159, 74);
		DetailInfoPanel.add(LogoLab);
		
		userName = new JLabel("");
		userName.setFont(new Font("宋体", Font.BOLD, 14));
		if(user!=null) {
			userName.setText("用户名："+user.getUserName()+"  "+"账号："+user.getUserNum());
		}
		userName.setBorder(new LineBorder(Color.black));
		userName.setBounds(524, 20, 377, 38);
		DetailInfoPanel.add(userName);
		
		MyNetDiskBtn = new JToggleButton("我的网盘");
		
		buttonGroup.add(MyNetDiskBtn);
		MyNetDiskBtn.setBounds(183, 19, 135, 42);
		MyNetDiskBtn.setFont(new Font("宋体", Font.BOLD, 20));
		MyNetDiskBtn.setForeground(new Color(0, 128, 128));
		MyNetDiskBtn.setBackground(new Color(135, 206, 235));
		//MyNetDiskBtn.setSelected(true);
		DetailInfoPanel.add(MyNetDiskBtn);
		
		transmission_listBtn = new JToggleButton("传输列表");
		
		buttonGroup.add(transmission_listBtn);
		transmission_listBtn.setBounds(336, 20, 135, 38);
		transmission_listBtn.setFont(new Font("宋体", Font.BOLD, 20));
		transmission_listBtn.setForeground(new Color(0, 128, 128));
		transmission_listBtn.setBackground(new Color(135, 206, 235));
		DetailInfoPanel.add(transmission_listBtn);
		
		list = new JList();
		//高度
		list.setFixedCellHeight(56);
		//文字居中
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		list.setCellRenderer(renderer);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListChangeAction(e);
			}
		});
		
		//功能块
		Set<String> functions = new LinkedHashSet<String>();
		functions.add("<html><font size=5>全部文件<p></html>");
		functions.add("<html><font size=5>图片<p></html>");
		functions.add("<html><font size=5>视频<p></html>");
		functions.add("<html><font size=5>文档<p></html>");
		functions.add("<html><font size=5>音乐<p></html>");
		functions.add("<html><font size=5>其他<p></html>");
		functions.add("<html><font size=5>回收站<p></html>");
		list.setListData(functions.toArray());
		
		tranlist = new JList();
		tranlist.setFixedCellHeight(56);
		//文字居中
		DefaultListCellRenderer renderer1 = new DefaultListCellRenderer();
		renderer1.setHorizontalAlignment(SwingConstants.CENTER);
		tranlist.setCellRenderer(renderer1);
		tranlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListChangeAction(e);
			}
		});
		
		Set<String> functions2 = new LinkedHashSet<String>();
		functions2.add("<html><font size=5>上传<p></html>");
		functions2.add("<html><font size=5>下载<p></html>");
		tranlist.setListData(functions2.toArray());
//		tranlist.setSelectedIndex(0);

		int min=0;
		int max=100;
		progressBar = new JProgressBar(min,max);
		progressBar.setStringPainted(true);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground",  new Color(115, 128, 34));
		//UIManager.put("ProgressBar.foreground", new Color(115, 128, 34));
		progressBar.setBounds(5, 511, 157, 14);
		progressBar.setValue((int)(user.getUsedCapacity()/user.getCapacity()*100));

		MyCapacity = new JLabel();
		updateC();
		MyCapacity.setBounds(5, 525, 157, 32);
		MyCapacity.setFont(new Font("宋体", Font.BOLD, 16));
		MyCapacity.setForeground(Color.CYAN);
		
		JLabel label = new JLabel("              储存容量：");
		label.setBounds(5, 484, 157, 22);
		label.setFont(new Font("华文行楷", Font.ITALIC, 14));
		
		upLoad = new JButton("上传");
		upLoad.setBounds(165, 528, 147, 25);
		upLoad.setBackground(new Color(135, 206, 235));
		upLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpLoadActionPerformed(e);
			}
		});
		upLoad.setForeground(new Color(0, 128, 128));
		upLoad.setFont(new Font("宋体", Font.BOLD, 20));
		
		Download = new JButton("下载");
		Download.setBounds(334, 528, 161, 25);
		Download.setFont(new Font("宋体", Font.BOLD, 20));
		Download.setForeground(new Color(0, 128, 128));
		Download.setBackground(new Color(135, 206, 235));
		
		DeleteBtn = new JButton("删除");
		DeleteBtn.setBounds(523, 528, 162, 25);
		DeleteBtn.setForeground(new Color(0, 128, 128));
		DeleteBtn.setFont(new Font("宋体", Font.BOLD, 20));
		DeleteBtn.setBackground(new Color(135, 206, 235));
		
		NewFileBtn = new JButton("新建文件夹");
		NewFileBtn.setBounds(720, 528, 195, 25);
		NewFileBtn.setForeground(new Color(0, 128, 128));
		NewFileBtn.setFont(new Font("宋体", Font.BOLD, 20));
		NewFileBtn.setBackground(new Color(135, 206, 235));
		
		transmission_listBtn.addChangeListener(new ToggleButtonChangeListener());
		MyNetDiskBtn.addChangeListener(new ToggleButtonChangeListener());
		
		contentPane.setLayout(null);
		contentPane.add(progressBar);
		contentPane.add(MyCapacity);
		contentPane.add(upLoad);
		contentPane.add(Download);
		contentPane.add(DeleteBtn);
		contentPane.add(NewFileBtn);
		contentPane.add(DetailInfoPanel);
		contentPane.add(scrollPane);
		contentPane.add(label);
		
		MainscrollPane = new JScrollPane();
		MainscrollPane.setBounds(165, 81, 765, 444);
		//MainPanel.add(MainscrollPane);
		contentPane.add(MainscrollPane);
		Maintable = new JTable();//全部文件
		Maintable.setRowHeight(30);
		Trantable = new JTable();//上传文件
		Trantable.setRowHeight(30);
		Downtable=new JTable();//下载文件
		Downtable.setRowHeight(30);

		Maintable.addMouseListener(new MainTableMouseListener());
		//Maintable.getColumnModel.getColumn(0).setCellRenderer(myRenderer);

		table_models = new Object[] {"\u6587\u4EF6\u540D", "\u5927\u5C0F", "\u7C7B\u578B", "\u4FEE\u6539\u65F6\u95F4"};
		Maintable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6587\u4EF6\u540D", "\u5927\u5C0F", "\u7C7B\u578B", "\u4FEE\u6539\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Maintable.getColumnModel().getColumn(0).setPreferredWidth(252);
		Maintable.getColumnModel().getColumn(1).setPreferredWidth(94);
		Maintable.getColumnModel().getColumn(2).setPreferredWidth(161);
		Maintable.getColumnModel().getColumn(3).setPreferredWidth(116);


		tran_model = new Object [] {"\u6587\u4EF6\u540D", "\u5927\u5C0F", "\u4F20\u8F93\u901F\u5EA6", "\u8fdb\u5ea6", "\u4FEE\u6539\u65F6\u95F4"};
		Trantable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6587\u4EF6\u540D", "\u5927\u5C0F", "\u4F20\u8F93\u901F\u5EA6", "\u8fdb\u5ea6", "\u4FEE\u6539\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Trantable.getColumnModel().getColumn(0).setPreferredWidth(252);
		Trantable.getColumnModel().getColumn(1).setPreferredWidth(94);
		Trantable.getColumnModel().getColumn(2).setPreferredWidth(94);
		Trantable.getColumnModel().getColumn(3).setPreferredWidth(92);
		Trantable.getColumnModel().getColumn(4).setPreferredWidth(116);
		//MainscrollPane.setViewportView(table2);
		down_model=new Object[]{"\u6587\u4EF6\u540D", "\u5927\u5C0F", "\u4F20\u8F93\u901F\u5EA6", "\u8fdb\u5ea6", "\u4FEE\u6539\u65F6\u95F4"};
		Downtable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u6587\u4EF6\u540D", "\u5927\u5C0F", "\u4F20\u8F93\u901F\u5EA6", "\u8fdb\u5ea6", "\u4FEE\u6539\u65F6\u95F4"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Downtable.getColumnModel().getColumn(0).setPreferredWidth(252);
		Downtable.getColumnModel().getColumn(1).setPreferredWidth(94);
		Downtable.getColumnModel().getColumn(2).setPreferredWidth(94);
		Downtable.getColumnModel().getColumn(3).setPreferredWidth(92);
		Downtable.getColumnModel().getColumn(4).setPreferredWidth(116);
		MyNetDiskBtn.setSelected(true);
	
	}
	/**
	 * 菜单栏改变
	 * @param e
	 */
	private void ListChangeAction(ListSelectionEvent e) {
		JList l=(JList)e.getSource();
		
		if(l.equals(list)) {
			int index=list.getSelectedIndex();
			if(index==0) {
				fillTable("");

			}else if(index==1) {
				fillTable("picture");

			}else if(index==2) {
				fillTable("video");

			}else if(index==3) {
				fillTable("document");

			}else if(index==4) {
				fillTable("audio");

			}else if(index==5) {
				fillTable("others");

			}else if(index==6) {
				fillTable("recycleBin");//回收站类型

			}
		}else if(l.equals(tranlist)){
			int index=tranlist.getSelectedIndex();
			if(index==0) {
				MainscrollPane.setViewportView(Trantable);
				System.out.println("上传");
			}
			if(index==1) {
				MainscrollPane.setViewportView(Downtable);
				System.out.println("下载");
			}
		}
	}

	/**
	 * 1.弹出文件选择器，用户选择要上传的文件
	 * 2.网盘建立短连接，（建立socket连接）
	 * 3.封装一个消息对象，消息对象中国标记好当前这个连接想要执行什么动作
	 * 4.使用socket的流开始上传数据
	 * 5.上传完毕提示结果
	 * @param e
	 */
	private void UpLoadActionPerformed(ActionEvent e) {
		//1.

		updateC();
		String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
        
		JFileChooser fc=new JFileChooser();
		int result=fc.showOpenDialog(NetDiskMainJFrame.this);
		upLoadfile = fc.getSelectedFile();
		if(result==JFileChooser.CANCEL_OPTION){
			upLoadfile=null;
		}
		if(upLoadfile==null) {
			UpLoadTranrow--;
			return;
		}
		System.out.println(upLoadfile.getAbsolutePath());
		ObjectOutputStream out = null;
		ObjectInputStream rec =null;
 		Socket client=null;
		try {
			client=new Socket(GlobalConfig.ServerIp,GlobalConfig.port);
			out=new ObjectOutputStream(client.getOutputStream());
			rec=new ObjectInputStream(client.getInputStream());
			System.out.println("连接服务器成功！");
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		//3.
		Message message=new Message();
		message.setFrom(user);
		message.setFileSize(upLoadfile.length());
		message.setFilename(upLoadfile.getName());
		message.setType(MessageType.UPLOAD);
		
		try {
			out.writeObject(message);
			out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Connection con=null;
		try {
			/**
			 * 线程上传
			 */
			con=db.getcon();
			String  fileExt=upLoadfile.getName().substring(upLoadfile.getName().lastIndexOf(".")+1,upLoadfile.getName().length());
			String fileType=isWFile.isFileType(fileExt);
			String fileSize=df.format(upLoadfile.length()/(double)1024.0);
			String updateTime=DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			userFile=new UserFile(user.getUserNum(), upLoadfile.getName(), 
					fileType,GlobalConfig.serverStoreFilePath+user.getUserName()+"\\"+upLoadfile.getName(),
					"root",Double.parseDouble(fileSize), updateTime);

			UpLoadThread upload=new UpLoadThread(upLoadfile,out,user,userFile,con,UpLoadTranrow);
			new Thread(upload).start();
			UpLoadTranrow++;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//上传完毕
		
	}
	
	//开关的切换
	class ToggleButtonChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			
			if(transmission_listBtn.isSelected()) {
				scrollPane.setViewportView(tranlist);
				tranlist.setSelectedIndex(0);
				MainscrollPane.setViewportView(Trantable);
			}
			if(MyNetDiskBtn.isSelected()) {
				scrollPane.setViewportView(list);
				MainscrollPane.setViewportView(Maintable);
				list.setSelectedIndex(0);
			}
			
		}
		
	}
	
	
	//填充表格
	public void fillTable(String type) {
		DefaultTableModel dtm=(DefaultTableModel)Maintable.getModel();
		dtm.setRowCount(0);
		Set<UserFile> userFileSet=user.getUserFileSet();
		int row=0;
		if(type.equals("")) {
			for(UserFile file:userFileSet)
			{
				if(!file.getFileType().equals("recycleBin")){
					dtm.addRow(table_models);
					dtm.setValueAt(file.getFileName(), row, 0);
					double fileSize=file.getFileSize();
					if(fileSize<1024) {
						dtm.setValueAt(df.format(fileSize)+"KB", row, 1);
					}else if(fileSize>=1024&&fileSize<1048576) {
						dtm.setValueAt(df.format(fileSize/1024.0)+"MB", row, 1);
					}else if(fileSize>=1048576) {
						dtm.setValueAt(df.format(fileSize/1024.0/1024.0)+"GB", row, 1);
					}

					dtm.setValueAt(file.getFileType(), row, 2);
					dtm.setValueAt(file.getUpdateTime(), row, 3);
					row++;
				}

			}
		}else {
			for(UserFile file:userFileSet)
			{
				if(type.equals(file.getFileType())) {
					dtm.addRow(table_models);
					dtm.setValueAt(file.getFileName(), row, 0);
					double fileSize=file.getFileSize();
					if(fileSize<1024) {
						dtm.setValueAt(df.format(fileSize)+"KB", row, 1);
					}else if(fileSize>=1024&&fileSize<1048576) {
						dtm.setValueAt(df.format(fileSize/1024.0)+"MB", row, 1);
					}else if(fileSize>=1048576) {
						dtm.setValueAt(df.format(fileSize/1024.0/1024.0)+"GB", row, 1);
					}

					dtm.setValueAt(file.getFileType(), row, 2);
					dtm.setValueAt(file.getUpdateTime(), row, 3);
					row++;
				}
				
			}
		}
		

	}
	
	/**
	 * 更新用户的储存容量
	 */
	public void updateC() {
		double UfSize=user.getUsedCapacity();
		double LfSize=user.getCapacity();
		progressBar.setValue((int)(user.getUsedCapacity()/1048576));
		if(UfSize<1024) {
			MyCapacity.setText(df.format(UfSize)+"KB / "+df.format(LfSize/1024.0/1024.0)+"GB");
		}else if(UfSize>=1024&&UfSize<1024*1024) {
			MyCapacity.setText(df.format(UfSize/1024.0)+"MB / "+df.format(LfSize/1024.0/1024.0)+"GB");
		}else if(UfSize>=1024*1024) {
			MyCapacity.setText(df.format(UfSize/1024.0/1024.0)+"GB / "+df.format(LfSize/1024.0/1024.0)+"GB");
		}
	}

	/**
	 * 上传线程
	 */
	class UpLoadThread implements Runnable{
		double UpLoadspeed=0;//上传速度
		private File upLoadfile;
		private ObjectOutputStream out;
		private Connection con;
		double precent=0;
		private  int row= UpLoadTranrow;
		private String UpdateTime=DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("##0.00");
		User user=new User();
		UserDao userDao=new UserDao();
		UserFile userFile=new UserFile();
		UserFileDao userFileDao=new UserFileDao();
		DefaultTableModel dtm = (DefaultTableModel) Trantable.getModel();
		public UpLoadThread(File upLoadfile, ObjectOutputStream out,User user,UserFile userFile,Connection con,int row) {
			super();
			this.upLoadfile = upLoadfile;
			this.out = out;
			this.user=user;
			this.userFile=userFile;
			this.con=con;
			this.row=row;
		}
		public UpLoadThread() {
			super();
		}
		@Override
		public void run() {
			try {
				FileInputStream in = new FileInputStream(upLoadfile);
				int readBit=0;

				int length = -1;
				long startTime, endTime;
				long time;
				long sT,eT;
				sT=System.nanoTime();
				long readT=0;

				dtm.addRow(tran_model);
				dtm.setValueAt(upLoadfile.getName(), row, 0);
				double fileSize = upLoadfile.length()/1024.0;
				//改变readBit和填充表单
				if (fileSize < 1024) {
					dtm.setValueAt(df.format(fileSize) + "KB", row, 1);
					readBit=1024;
				} else if (fileSize >= 1024 && fileSize < 1048576) {
					dtm.setValueAt(df.format(fileSize / 1024.0) + "MB", row, 1);
					readBit=1024;
				} else if (fileSize >= 1048576) {
					dtm.setValueAt(df.format(fileSize / 1024.0 / 1024.0) + "GB", row, 1);
					readBit=1024*100;
				}
				dtm.setValueAt(UpdateTime, row, 4);
				startTime = System.nanoTime();
				byte[] bs = new byte[readBit];
				while ((length = in.read(bs)) != -1) {
					out.write(bs, 0, length);
					out.flush();
					endTime = System.nanoTime();
					time = endTime - startTime;
					readT+=1;
					if(fileSize<1048576){
						if(readT%5000==0) {
							long AlreadyLoad=readT*readBit;
							precent=(readT*1.0)/(upLoadfile.length()/(readBit*1.0))*100;
							dtm.setValueAt(df.format(AlreadyLoad/1024.0/(time * Math.pow(10, -6)))+"MB/s", row, 2);
							dtm.setValueAt(df.format(precent)+"%", row, 3);
						}
					}else{
						if(readT%500==0) {
							long AlreadyLoad=readT*readBit;
							precent=(readT*1.0)/(upLoadfile.length()/(readBit*1.0))*100;
							dtm.setValueAt(df.format(AlreadyLoad/1024.0/(time * Math.pow(10, -6)))+"MB/s", row, 2);
							dtm.setValueAt(df.format(precent)+"%", row, 3);
							System.out.println("次数"+readT);
						}
					}
				}

				eT=System.nanoTime();
				System.out.println("所用时间为："+df.format((eT-sT)*Math.pow(10.0, -9))+"s");
				int addFile = userFileDao.AddFile(con, userFile);
				if(addFile>0) {
					System.out.println("添加成功！");
					Set UpdateFile=user.getUserFileSet();
					UpdateFile.add(userFile);		
					user.setUserFileSet(UpdateFile);
					user.setCapacity(user.getCapacity()-userFile.getFileSize());
					user.setUsedCapacity(user.getUsedCapacity()+userFile.getFileSize());
					int updateCapacity = userDao.UpdateCapacity(user, con);
					if(updateCapacity>0) {
						System.out.println("更新用户容量成功！");
						updateC();
					}else {
						System.out.println("更新用户容量失败！");
					}
				}else {
					System.out.println("添加失败！");
				}
				//更新文件列表
				fillTable("");
				precent=100;
				out.close();
				UpLoadspeed=0;
				dtm.setValueAt(df.format(UpLoadspeed)+"MB/s", row, 2);
				dtm.setValueAt(df.format(precent)+"%", row, 3);
				precent=0;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DbUtil.closeCon(con);
			}
			
		}
	}

	class MainTableMouseListener implements MouseListener{
		int row=0;
		int currentSelect_row=0;
		String SelectFileName="";
		@Override
		public void mouseClicked(MouseEvent e) {
			row=Maintable.getRowCount();
			System.out.println(row);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			currentSelect_row=Maintable.getSelectedRow();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			DefaultTableModel d=(DefaultTableModel)Maintable.getModel();
			SelectFileName=(String)d.getValueAt(currentSelect_row,0);
			System.out.println(SelectFileName);
			if(e.getClickCount()==1&&e.getButton()==3){

				JPopupMenu menu=new JPopupMenu();
				JMenuItem  download=new JMenuItem("下载");
				JMenuItem  delete=new JMenuItem("删除");
				download.setFont(new Font("华文行楷", Font.PLAIN, 14));
				delete.setFont(new Font("华文行楷", Font.PLAIN, 14));
				download.setBackground(new Color(160, 218,229));
				delete.setBackground(new Color(233, 137, 159));
				menu.add(download);
				menu.addSeparator();
				menu.add(delete);
				menu.show(Maintable, e.getX(), e.getY());


				//下载
				download.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
						try {
							UIManager.setLookAndFeel(lookAndFeel);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						Message  willDownloadMessage=new Message();
						willDownloadMessage.setFilename(SelectFileName);
						willDownloadMessage.setFrom(user);
						willDownloadMessage.setType(MessageType.DOWNLOAD);
						Connection con=null;
						ObjectOutputStream out=null;
						ObjectInputStream in=null;
						try {
							Socket  client=new Socket(GlobalConfig.ServerIp,GlobalConfig.port);
							out=new ObjectOutputStream(client.getOutputStream());
							in=new ObjectInputStream(client.getInputStream());
							out.writeObject(willDownloadMessage);//将消息对象发送给服务器
							out.flush();

							con=db.getcon();
							//客户端想要下载的消息发给服务器之后，服务器接收到肯定会处理并且会给用户发送文件数据
							//4.弹出文件选择框让用户选择文件保存位置
							/*JFileChooser  fc=new JFileChooser();
							int result=fc.showSaveDialog(NetDiskMainJFrame.this);
							if(result==JFileChooser.CANCEL_OPTION){
								//行数的改变，将会减少
								DownLoadTranrow--;
								return;
							}
							File downLoadfilePath=fc.getSelectedFile();*/
							File downLoadfilePath=new File(GlobalConfig.LocalStorePath+user.getUserName());

							System.out.println(downLoadfilePath.getAbsolutePath());
							if(!downLoadfilePath.exists()) downLoadfilePath.mkdirs();

							//5.文件路径选择完毕就可以使用IO流来读取文件数据并存入文件
							DownLoadThread downLoadThread=new DownLoadThread(downLoadfilePath,SelectFileName,in,out,con,DownLoadTranrow);
							new Thread(downLoadThread).start();

							DownLoadTranrow++;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});

				//删除 此处的逻辑就是将之前的的文件的类型改成回收站类型，修改集合里面的类型，重新填充
				//表格一次
				delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						updateC();
						int value=JOptionPane.showConfirmDialog(null,"你确定要删除 "+SelectFileName+" 文件吗?");
						if(value==JOptionPane.YES_OPTION){
							Message ToRecycleMessage=new Message();
							ToRecycleMessage.setFilename(SelectFileName);
							ToRecycleMessage.setFrom(user);
							ToRecycleMessage.setType(MessageType.DELETE);
							ObjectOutputStream out=null;
							ObjectInputStream in=null;

							try {
								Socket  client=new Socket(GlobalConfig.ServerIp,GlobalConfig.port);
								out=new ObjectOutputStream(client.getOutputStream());
								in=new ObjectInputStream(client.getInputStream());
								out.writeObject(ToRecycleMessage);//将消息对象发送给服务器
								out.flush();
								double fileSize=0;
								Set<UserFile> userFiles=user.getUserFileSet();
								for(UserFile uF:userFiles){
									if(uF.getFileName().equals(SelectFileName)){
										fileSize=uF.getFileSize();
									}
								}
								new Thread(new ToRecThead(out,in,SelectFileName,fileSize)).start();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}else{
							return;
						}
					}
				});

			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	class DownLoadThread implements Runnable{
		double DownLoadspeed=0;//上传速度
		private File downLoadfilePath;
		private ObjectInputStream in;
		private ObjectOutputStream out;
		private Connection con;
		private String SelectFileName;
		double precent=0;
		private String UpdateTime=DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
		private int row=DownLoadTranrow;
		DecimalFormat df = new DecimalFormat("##0.00");

		DefaultTableModel dtm = (DefaultTableModel) Downtable.getModel();
		public DownLoadThread(File downLoadfilePath,String  SelectFileName,ObjectInputStream in,ObjectOutputStream out, Connection con, int row) {
			this.downLoadfilePath = downLoadfilePath;
			this.in = in;
			this.con = con;
			this.row = row;
			this.out=out;
			this.SelectFileName=SelectFileName;
		}

		public DownLoadThread() {
			super();
		}

		@Override
		public void run() {
			try {
				UserFile userFile=null;
				for(UserFile userF: user.getUserFileSet() ){
					if(userF.getFileName().equals(SelectFileName)){
						userFile=userF;
						break;
					}
				}
				FileOutputStream fileOut=new FileOutputStream(new File(downLoadfilePath,SelectFileName),true);
				int readBit=0;
				double fileSize=userFile.getFileSize();
				dtm.addRow(down_model);
				dtm.setValueAt(SelectFileName, row, 0);
				//改变readBit和填充表单
				if (fileSize < 1024) {
					dtm.setValueAt(df.format(fileSize) + "KB", row, 1);
					readBit=1024;
				} else if (fileSize >= 1024 && fileSize < 1048576) {
					dtm.setValueAt(df.format(fileSize / 1024.0) + "MB", row, 1);
					readBit=1024;
				} else if (fileSize >= 1048576) {
					dtm.setValueAt(df.format(fileSize / 1024.0 / 1024.0) + "GB", row, 1);
					readBit=1024*100;
				}
				byte[] bs=new byte[readBit];
				int length=-1;
				long startTime, endTime;
				long time;
				long sT,eT;
				long readT=0;
				sT=System.nanoTime();
				dtm.setValueAt(UpdateTime, row, 4);
				startTime = System.nanoTime();
				while((length=in.read(bs))!=-1) {
					fileOut.write(bs,0,length);
					fileOut.flush();
					endTime = System.nanoTime();
					time = endTime - startTime;
					readT+=1;
					if(fileSize<1048576){
						if(readT%5000==0) {
							long AlreadyLoad=readT*readBit;
							//precent=(readT*1.0)/(upLoadfile.length()/(readBit*1.0))*100;
							precent=((readT*1.0)/((fileSize*1024.0)/(readBit*1.0)))*100;
							dtm.setValueAt(df.format(AlreadyLoad/readBit/(time * Math.pow(10, -6)))+"MB/s", row, 2);
							dtm.setValueAt(df.format(precent)+"%", row, 3);
						}
					}else{
						if(readT%500==0) {
							long AlreadyLoad=readT*readBit;
							precent=((readT*1.0)/((fileSize*1024.0)/(readBit*1.0)))*100;
							dtm.setValueAt(df.format(AlreadyLoad/readBit/(time * Math.pow(10, -6)))+"MB/s", row, 2);
							dtm.setValueAt(df.format(precent)+"%", row, 3);
							System.out.println("次数"+readT);
						}
					}
				}
				eT=System.nanoTime();
				System.out.println("所用时间为："+df.format((eT-sT)*Math.pow(10.0, -9))+"s");
				fileOut.close();
				in.close();
				precent=100;
				DownLoadspeed=0;
				dtm.setValueAt(df.format(DownLoadspeed)+"MB/s", row, 2);
				dtm.setValueAt(df.format(precent)+"%", row, 3);
				out.close();
				precent=0;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 接收消息线程
	 */
	class ToRecThead implements Runnable{


		private ObjectOutputStream out;
		private ObjectInputStream  in;
		private String SelectFileName;
		private double fileSize;

		public ToRecThead(ObjectOutputStream out, ObjectInputStream in, String selectFileName,double fileSize) {
			this.out = out;
			this.in = in;
			SelectFileName = selectFileName;
			this.fileSize=fileSize;
		}

		public ToRecThead() {
			super();
		}

		@Override
		public void run() {

			try {
				Message RecMessage=(Message) in.readObject();
				int update=RecMessage.getUpdate();
				if(update>0){
					Set<UserFile> UpdateFile=user.getUserFileSet();
					for(UserFile uF:UpdateFile){
						if(uF.getFileName().equals(SelectFileName)){
							uF.setFileType("recycleBin");//设置为回收站类型
							break;
						}
					}
				}else{
					System.out.println("删除失败！");
				}

				user.setCapacity(user.getCapacity()+fileSize);
				user.setUsedCapacity(user.getUsedCapacity()-fileSize);
				out.close();
				in.close();
				updateC();
				fillTable("");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}


		}
	}

}
