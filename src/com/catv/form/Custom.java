package com.catv.form;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Custom extends JFrame {

	JTable customTable;
	
	public Custom() {
		
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new FlowLayout());
//		printButton = new JButton("프린터출력");
//		customButton = new JButton("고객관리");
//		panel.add(printButton);
//		panel.add(customButton);
		getContentPane().add(panel);
//		printButton.addActionListener(this);
//		customButton.addActionListener(this);
	}
}
