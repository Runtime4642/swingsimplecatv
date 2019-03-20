package com.catv.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.catv.form.custom.Custom;

public class Main extends JFrame implements ActionListener{
	
	private JButton printButton;
	private JButton printButton2;
	private JButton customButton;
	@Override 	
	public void actionPerformed(ActionEvent ac) {
		Object obj = ac.getSource();
		if((JButton)obj == printButton) {
			new PrintPreviewDemo().setVisible(true);
		}
		else if((JButton)obj == printButton2){
			new PrintPreviewDemo2().setVisible(true);
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
		printButton = new JButton("영수증뒷면(금액부분)출력");
		customButton = new JButton("고객관리");
		printButton2 = new JButton("영수증앞면(우편부분)출력");
		panel.add(customButton);
		panel.add(printButton);
		panel.add(printButton2);
		getContentPane().add(panel);
		printButton.addActionListener(this);
		printButton2.addActionListener(this);
		customButton.addActionListener(this);
	}
}
