package com.catv.form;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cate.controller.CustomController;


public class InputData extends JFrame implements ActionListener{

	private JTextArea text;
	private JButton button;
	
	private CustomController controller = new CustomController();
	
	@Override 	
	public void actionPerformed(ActionEvent ac) {
		String[] s =text.getText().split(",");
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<s.length;i++)
			list.add(Integer.parseInt(s[i]));
		
		if(controller.Renew(list))
			JOptionPane.showMessageDialog(null, "입력성공");
		else
			JOptionPane.showMessageDialog(null, "입력실패");
	}
	public InputData() {
		setTitle("화면");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		text = new JTextArea();
		button = new JButton("입력");
		Font font = new Font("arian", Font.BOLD, 30);
		text.setFont(font);
		text.setSize(500,700);
		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text);
		button.setFont(font);
		panel.add(scrollbar,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		getContentPane().add(panel);
		button.addActionListener(this);
	}
}
