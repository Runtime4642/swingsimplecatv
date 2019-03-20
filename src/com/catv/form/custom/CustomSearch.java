package com.catv.form.custom;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.cate.controller.CustomController;
import com.catv.dto.CustomComboDto;
import com.catv.vo.CustomVo;

public class CustomSearch extends JFrame  implements ActionListener {

	private JRadioButton jrbNo;
	private JRadioButton jrbName;
	private JRadioButton jrbPhone;
	private JRadioButton jrbAccountNum;
	private JRadioButton jrbAccountName;
	private JTextField searchText;
	private JTable table;
	private JButton button;
	private CustomController c= new CustomController();
	private ButtonGroup radioGroup;
	private DefaultTableModel model;
	
	public CustomSearch() {
		Font font = new Font("arian", Font.BOLD, 30);
		setTitle("메인화면");
		setSize(900, 1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		
		//검색옵션 panel
		JPanel optionPanel = new JPanel(new FlowLayout());
		optionPanel.setBorder(new TitledBorder("검색옵션"));
		jrbNo = new JRadioButton("고객번호",true);
		jrbName = new JRadioButton("이름");
		jrbPhone = new JRadioButton("전화번호");
		jrbAccountNum = new JRadioButton("계좌번호");
		jrbAccountName = new JRadioButton("계좌명");
		jrbNo.setFont(font);
		jrbName.setFont(font);
		jrbPhone.setFont(font);
		jrbAccountNum.setFont(font);
		jrbAccountName.setFont(font);
		 radioGroup = new ButtonGroup();
		radioGroup.add(jrbNo);
		radioGroup.add(jrbName);
		radioGroup.add(jrbPhone);
		radioGroup.add(jrbAccountNum);
		radioGroup.add(jrbAccountName);
		optionPanel.add(jrbNo);
		optionPanel.add(jrbName);
		optionPanel.add(jrbPhone);
		optionPanel.add(jrbAccountNum);
		optionPanel.add(jrbAccountName);
		
		//테이블
		table = new JTable();
		String colName[]= {"고객번호","이름","전화1","전화2","주소","계좌명","계좌번호","미수금","최종납입"};
		 model = new DefaultTableModel(colName,0) {
			public boolean isCellEditable(int i,int c)
			{
				return false;
			}
		};
		table.setModel(model);
		Font tableFont = new Font("arian", Font.BOLD, 16);
		table.setFont(tableFont);
		//입력 텍스트,버튼 패널
		JPanel inputPanel = new JPanel(new FlowLayout());
		searchText = new JTextField(25);
		searchText.setFont(font);
		button = new JButton("검색");
		button.setFont(font);
		inputPanel.add(searchText);
		inputPanel.add(button);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		            DefaultTableModel model= (DefaultTableModel) table.getModel();
		            new CustomModifiy(c.insertForm(),Integer.parseInt(model.getValueAt(row, 0).toString())).setVisible(true);

		        }
		    }
		});
		
		panel.add(inputPanel,BorderLayout.SOUTH);
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(optionPanel,BorderLayout.NORTH);
		getContentPane().add(panel);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ac) {
		Object obj = ac.getSource();
		String option="";
		if((JButton)obj == button) {
			if(jrbNo.isSelected()) {
				option="no";
			}
			else if(jrbName.isSelected()) {
				option="name";
			}
			else if(jrbPhone.isSelected()) {
				option="phone";
			}
			else if(jrbAccountNum.isSelected()) {
				option="account_num";
			}
			else if(jrbAccountName.isSelected()) {
				option="account_name";
			}
		List <CustomVo> list = c.getSearchList(searchText.getText(), option);
		
		 DefaultTableModel amodel;
	        amodel = (DefaultTableModel)table.getModel();
	           for(int i= amodel.getRowCount()-1; i>=0; i--) 
	        amodel.removeRow(i); //테이블삭제
		
		for(int i=0;i<list.size();i++)
		{
			//"고객번호","이름","전화1","전화2","주소","계좌명","계좌번호","미수금","최종납입"
		String row[] = new String[9];
		row[0]=Integer.toString(list.get(i).getNo());
		row[1]=list.get(i).getName();
		row[2]=list.get(i).getPhone1();
		row[3]=list.get(i).getPhone2();
		row[4]=list.get(i).getAddress();
		row[5]=list.get(i).getAccount_name();
		row[6]=list.get(i).getAccount_num();
		row[7]=Integer.toString(list.get(i).getReceive_money());
		row[8]=list.get(i).getLast_collect_date();
		model.addRow(row);
		}
		table.setModel(model);
		
		}
	}
}
