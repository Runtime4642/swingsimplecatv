package com.catv.form.custom;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.catv.dto.CustomComboDto;
import com.catv.dto.CustomDto;

public class CustomInsert extends JFrame implements ActionListener {

	private JButton saveButton;
	private JButton cancleButton;
	private JTextField tfName;
	private JTextField tfInstallDay;
	private JTextField tfPhone1;
	private JTextField tfAddress;
	private JTextField tftvCount;
	private JTextField tfPrice;

	@Override
	public void actionPerformed(ActionEvent ac) {
		Object obj = ac.getSource();
		if ((JButton) obj == saveButton) {

		}
//		else if((JButton)obj == customButton){
//			new Custom().setVisible(true);
//		}
	}

	public CustomInsert(CustomComboDto dto) {

		setSize(1700, 1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		Font font = new Font("arian", Font.BOLD, 30);
		// 신규 panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(new TitledBorder("신규"));
		// 신규 1층 panel
		JPanel panel1_1 = new JPanel(new FlowLayout());
		// 이름 panel
		JPanel pName = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lName = new JLabel("고객명");
		lName.setFont(font);
		tfName = new JTextField(5);
		tfName.setFont(font);
		pName.add(lName);
		pName.add(tfName);
		panel1_1.add(pName);
		// 가설일 panel
		JPanel pInstallDay = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lInstallDay = new JLabel("가설일");
		lInstallDay.setFont(font);
		tfInstallDay = new JTextField(10);
		tfInstallDay.setFont(font);
		pInstallDay.add(lInstallDay);
		pInstallDay.add(tfInstallDay);
		panel1_1.add(pInstallDay);

		// 거주구분 panel
		JPanel pResudent = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lResudent = new JLabel("거주구분");
		lResudent.setFont(font);
		String[] list = new String[dto.getResudent().size()];
		for (int i = 0; i < dto.getResudent().size(); i++) {
			list[i] = dto.getResudent().get(i);
		}
		JComboBox cbResudent = new JComboBox(list);
		cbResudent.setFont(font);
		pResudent.add(lResudent);
		pResudent.add(cbResudent);
		panel1_1.add(pResudent);
		panel1_1.setBounds(30, 30, 900, 700);
		panel1.add(panel1_1);

		// 신규 2층 panel
		JPanel panel1_2 = new JPanel(new FlowLayout());
		// 전화1 panel
		JPanel pPhone1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lPhone1 = new JLabel("전화(1)");
		lPhone1.setFont(font);
		tfPhone1 = new JTextField(10);
		tfPhone1.setFont(font);
		pPhone1.add(lPhone1);
		pPhone1.add(tfPhone1);
		panel1_2.add(pPhone1);
		// 전화2 panel
		JPanel pPhone2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lPhone2 = new JLabel("전화(2)");
		lPhone2.setFont(font);
		JTextField tfPhone2 = new JTextField(10);
		tfPhone2.setFont(font);
		pPhone2.add(lPhone2);
		pPhone2.add(tfPhone2);
		panel1_2.add(pPhone2);

		// area panel
		JPanel pArea = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lArea = new JLabel("지역");
		lArea.setFont(font);
		list = new String[dto.getArea().size()];
		for (int i = 0; i < dto.getArea().size(); i++) {
			list[i] = dto.getArea().get(i);
		}
		JComboBox cbArea = new JComboBox(list);
		cbArea.setFont(font);
		pArea.add(lArea);
		pArea.add(cbArea);

		panel1_2.add(pArea);

		panel1.add(panel1_2);

		// 신규 3층 panel
		JPanel panel1_3 = new JPanel();
		panel1_3.setLayout(new BoxLayout(panel1_3, BoxLayout.Y_AXIS));
		// 주소 panel
		JPanel pAddress = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lAddress = new JLabel("주소");
		lAddress.setFont(font);
		tfAddress = new JTextField(50);
		tfAddress.setFont(font);
		pAddress.add(lAddress);
		pAddress.add(tfAddress);
		panel1_3.add(pAddress);

		panel1.add(panel1_3);

		// 신규 4층 panel
		JPanel panel1_4 = new JPanel();
		panel1_4.setLayout(new BoxLayout(panel1_4, BoxLayout.Y_AXIS));
		// 주소 panel
		JPanel pMemo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lMemo = new JLabel("메모");
		lMemo.setFont(font);
		JTextField tfMemo = new JTextField(50);
		tfMemo.setFont(font);
		pMemo.add(lMemo);
		pMemo.add(tfMemo);
		panel1_4.add(pMemo);

		panel1.add(panel1_4);

		// 신규 5층 panel
		JPanel panel1_5 = new JPanel(new FlowLayout());
		// 수금방법
		JPanel pMethod = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lMethod = new JLabel("수금방법");
		lMethod.setFont(font);
		list = new String[dto.getMethod().size()];
		for (int i = 0; i < dto.getMethod().size(); i++) {
			list[i] = dto.getMethod().get(i);
		}
		JComboBox cbMethod = new JComboBox(list);
		cbMethod.setFont(font);
		pMethod.add(lMethod);
		pMethod.add(cbMethod);
		panel1_5.add(pMethod);
		panel1_5.setBounds(30, 30, 900, 700);
		panel1.add(panel1_5);
		// 은행
		JPanel pBank = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lBank = new JLabel("이체은행");
		lBank.setFont(font);
		list = new String[dto.getBank().size()];
		for (int i = 0; i < dto.getBank().size(); i++) {
			list[i] = dto.getBank().get(i);
		}
		JComboBox cbBank = new JComboBox(list);
		cbBank.setFont(font);
		pBank.add(lBank);
		pBank.add(cbBank);
		panel1_5.add(pBank);
		panel1_5.setBounds(30, 30, 900, 700);

		// 계좌명 panel
		JPanel pAccountName = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lAccountName = new JLabel("계좌명");
		lAccountName.setFont(font);
		JTextField tfAccountName = new JTextField(5);
		tfAccountName.setFont(font);
		pAccountName.add(lAccountName);
		pAccountName.add(tfAccountName);
		panel1_5.add(pAccountName);

		// 계좌번호 panel
		JPanel pAccountNum = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lAccountNum = new JLabel("계좌번호");
		lAccountNum.setFont(font);
		JTextField tfAccountNum = new JTextField(20);
		tfAccountNum.setFont(font);
		pAccountNum.add(lAccountNum);
		pAccountNum.add(tfAccountNum);
		panel1_5.add(pAccountNum);
		panel1.add(panel1_5);

		// 신규 6층 panel
		JPanel panel1_6 = new JPanel(new FlowLayout());

		// tv대수 panel
		JPanel ptvCount = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel ltvCount = new JLabel("tv대수");
		ltvCount.setFont(font);
		tftvCount = new JTextField(5);
		tftvCount.setFont(font);
		ptvCount.add(ltvCount);
		ptvCount.add(tftvCount);
		panel1_6.add(ptvCount);

		// 월관리비 panel
		JPanel pPrice = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel lPrice = new JLabel("월관리비");
		lPrice.setFont(font);
		tfPrice = new JTextField(5);
		tfPrice.setFont(font);
		pPrice.add(lPrice);
		pPrice.add(tfPrice);
		panel1_6.add(pPrice);

		panel1.add(panel1_6);

		// 작업 panel
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		panel2.setBorder(new TitledBorder("작업"));
		saveButton = new JButton("저장");
		cancleButton = new JButton("취소");
		panel2.add(saveButton);
		panel2.add(cancleButton);
		getContentPane().add(panel1, BorderLayout.CENTER);
		getContentPane().add(panel2, BorderLayout.SOUTH);
	}

}
