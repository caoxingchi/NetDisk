package com.netdisk.test;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class t3 extends JFrame{
	
	private URL resource = t3.class.getResource("/Icon/tab.png");//映射根目录下的icon图标地址链接
	//private ImageIcon imageIcon = new ImageIcon("F:\\Test\\Java\\testJava8\\bin\\tab.jpg");//或是直接将icon图标的地址链接上
	private ImageIcon imageIcon = new ImageIcon(resource);//图标
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	public t3() {
		setTitle("选项卡面板");
		setBounds(400, 400, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);//设置选项卡标签的布局方式为滚动布局
		tabbedPane.addChangeListener(new ChangeListener() {//添加时间监听器
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				int selectedIndex = tabbedPane.getSelectedIndex();//获得被选中选项卡的索引
				String title = tabbedPane.getTitleAt(selectedIndex);//获得指定索引的选项卡标签
				System.out.println(title);
			}
		});
		
		tabbedPane.addTab("选项卡A", imageIcon, new JLabel("选项卡A"), "点击查看选项卡A");//将标签组件添加到选项卡中，并且要求有提示
		tabbedPane.addTab("选项卡B", imageIcon, new JLabel("选项卡B"), "点击查看选项卡B");
		tabbedPane.addTab("选项卡C", imageIcon, new JLabel("选项卡C"), "点击查看选项卡C");
		
		tabbedPane.setSelectedIndex(2); //设置索引为2的选项卡被选中
		tabbedPane.setEnabledAt(0, true); //设置索引为0的选项卡不可用（也就是选项卡A不可用）
		
		add(tabbedPane);
		setVisible(true);
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		t3 test = new t3();
 
	}
 
}
