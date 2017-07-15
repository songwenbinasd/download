package cn.sintang.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cn.sintang.DownLoadUtil;

/**
 * 下载swing样式
 * 
 * @author sintang
 * @date 2017-07-15
 */
public class DownUI {
	private JTextArea downTxt;
	private JLabel statusLabel;

	public void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建及设置窗口
		JFrame jframe = new JFrame("圣唐文件下载器");
		jframe.setSize(650, 400);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		jframe.add(panel);

		// 创建文字标签
		JLabel label = new JLabel("下载地址:");
		/*
		 * 这个方法定义了组件的位置。 setBounds(x, y, width, height) x 和 y 指定左上角的新位置，由 width
		 * 和 height 指定新的大小。
		 */
		label.setBounds(10, 20, 30, 25);
		panel.add(label);

		// 创建文本域
		downTxt = new JTextArea(4, 40);
		downTxt.setBounds(100, 20, 300, 25);
		downTxt.setLineWrap(true);// 设置自动换行
		downTxt.setWrapStyleWord(true);// 设置断行不断字
		panel.add(downTxt);

		// 创建下载按钮
		JButton downBtn = new JButton("下载");
		downBtn.setBounds(20, 80, 100, 25);
		downBtn.setActionCommand("download");
		downBtn.addActionListener(new ButtonClickListener());
		panel.add(downBtn);
		
		//设置状态域
		statusLabel = new JLabel("",JLabel.CENTER);
		statusLabel.setSize(250,100);
		panel.add(statusLabel);

		// 设置界面可见
		jframe.setVisible(true);
	}

    class ButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if ("download".equals(command)) {
				try {
					DownLoadUtil.download(downTxt.getText(),statusLabel);
				} catch (Exception e1) {
					statusLabel.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}

		}
	}
    
    public static void main(String[] args) {
		DownUI down = new DownUI();
		down.createAndShowGUI();
	}
}
