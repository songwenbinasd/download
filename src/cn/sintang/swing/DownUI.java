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
 * ����swing��ʽ
 * 
 * @author sintang
 * @date 2017-07-15
 */
public class DownUI {
	private JTextArea downTxt;
	private JLabel statusLabel;

	public void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ���������ô���
		JFrame jframe = new JFrame("ʥ���ļ�������");
		jframe.setSize(650, 400);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �������
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		jframe.add(panel);

		// �������ֱ�ǩ
		JLabel label = new JLabel("���ص�ַ:");
		/*
		 * ������������������λ�á� setBounds(x, y, width, height) x �� y ָ�����Ͻǵ���λ�ã��� width
		 * �� height ָ���µĴ�С��
		 */
		label.setBounds(10, 20, 30, 25);
		panel.add(label);

		// �����ı���
		downTxt = new JTextArea(4, 40);
		downTxt.setBounds(100, 20, 300, 25);
		downTxt.setLineWrap(true);// �����Զ�����
		downTxt.setWrapStyleWord(true);// ���ö��в�����
		panel.add(downTxt);

		// �������ذ�ť
		JButton downBtn = new JButton("����");
		downBtn.setBounds(20, 80, 100, 25);
		downBtn.setActionCommand("download");
		downBtn.addActionListener(new ButtonClickListener());
		panel.add(downBtn);
		
		//����״̬��
		statusLabel = new JLabel("",JLabel.CENTER);
		statusLabel.setSize(250,100);
		panel.add(statusLabel);

		// ���ý���ɼ�
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
