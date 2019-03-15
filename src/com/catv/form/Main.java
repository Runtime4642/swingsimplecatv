package com.catv.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener{
	
	private JButton printButton;
	private JButton customButton;
	@Override	
	public void actionPerformed(ActionEvent ac) {
		Object obj = ac.getSource();
		if((JButton)obj == printButton) {
			new PrintPreviewDemo().setVisible(true);
		}
		else if((JButton)obj == customButton){
			new Custom().setVisible(true);
		}
	}

	
	
	public static void main(String[] args){
		new Main().setVisible(true);
	}
	
	public Main() {
		
		setTitle("메인화면");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new FlowLayout());
		printButton = new JButton("프린터출력");
		customButton = new JButton("고객관리");
		panel.add(printButton);
		panel.add(customButton);
		getContentPane().add(panel);
		printButton.addActionListener(this);
		customButton.addActionListener(this);
	}
}
