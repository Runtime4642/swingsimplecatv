package com.catv.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cate.controller.CustomController;
import com.catv.form.custom.Custom;

public class Main extends JFrame implements ActionListener{
	
	private JButton printButton;
	private JButton printButton2;
	private JButton printButton3;
	private JButton customButton;
	private JButton inputButton;
	private JComboBox cb;
	private CustomController controller = new CustomController();
	@Override 	
	public void actionPerformed(ActionEvent ac) {
		
		Object obj = ac.getSource();
		if((JButton)obj == printButton) {
			new PrintPreviewDemo().setVisible(true);
		}
		else if((JButton)obj == printButton2){
			new PrintPreviewDemo2().setVisible(true);
		}
		else if((JButton)obj == printButton3){
			new PrintPreviewDemo3(cb.getSelectedItem().toString()).setVisible(true);
		}
		else if((JButton)obj == customButton){
			new Custom().setVisible(true);
		}
		else if((JButton)obj == inputButton){
			new InputData().setVisible(true);
		}
	}

	
	
	public static void main(String[] args){
		new Main().setVisible(true);
	}
	
	public Main() {
		Font font = new Font("arian", Font.BOLD, 30);
		setTitle("메인화면");
		setSize(1000, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		JPanel printPanel =new JPanel(new FlowLayout());
		JPanel printPanel2 =new JPanel(new FlowLayout());
		JPanel panel2 =new JPanel(new FlowLayout());
		
		printButton = new JButton("영수증뒷면(금액부분)출력");
		customButton = new JButton("고객관리");
		printButton2 = new JButton("영수증앞면(우편부분)출력");
		printButton3 = new JButton("자동이체출력");
		inputButton = new JButton("최종납입일갱신");
		cb= new JComboBox();
		List <String> list =controller.insertForm().getBank();
		for(int i=0;i<list.size()-1;i++)
			cb.addItem(list.get(i));
		printButton.setFont(font);
		inputButton.setFont(font);
		customButton.setFont(font);
		printButton2.setFont(font);
		printButton3.setFont(font);
		cb.setFont(font);
		
		panel2.add(customButton);
		printPanel.add(printButton);
		printPanel.add(printButton2);
		printPanel2.add(cb);
		printPanel2.add(printButton3);
		panel2.add(inputButton);
		
		panel.add(printPanel,BorderLayout.SOUTH);
		panel.add(printPanel2,BorderLayout.CENTER);
		panel.add(panel2,BorderLayout.NORTH);
		getContentPane().add(panel);
		printButton.addActionListener(this);
		printButton2.addActionListener(this);
		printButton3.addActionListener(this);
		inputButton.addActionListener(this);
		customButton.addActionListener(this);
	}
}
