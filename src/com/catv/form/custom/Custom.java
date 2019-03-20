package com.catv.form.custom;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.cate.controller.CustomController;
import com.catv.dto.CustomDto;

public class Custom extends JFrame implements ActionListener {

	private JTable customTable;
	private JButton 신규고객추가버튼;
	private JButton 고객정보수정버튼;
	private JButton 고객해지버튼;
	private JButton 신규고객버튼;
	private JButton 기타등록버튼;
	private JButton 데이터등록버튼;
	private CustomController c = new CustomController();

	public Custom() {

		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new FlowLayout());
		신규고객추가버튼 = new JButton("신규고객추가");
		고객정보수정버튼 = new JButton("고객정보수정");
		고객해지버튼 = new JButton("고객해지");
		기타등록버튼 = new JButton("기타등록");
		데이터등록버튼 = new JButton("데이터등록");
		panel.add(신규고객추가버튼);
		panel.add(고객정보수정버튼);
		panel.add(고객해지버튼);
		// 아직 추가 안했음panel.add(기타등록버튼);
		panel.add(데이터등록버튼);
		getContentPane().add(panel);
		신규고객추가버튼.addActionListener(this);
		데이터등록버튼.addActionListener(this);
		고객정보수정버튼.addActionListener(this);
//		customButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ac) {
		Object obj = ac.getSource();
		if ((JButton) obj == 신규고객추가버튼) {
			new CustomInsert(c.insertForm()).setVisible(true);
		} else if ((JButton) obj == 기타등록버튼) {
			// new Custom().setVisible(true);
		} else if ((JButton) obj == 데이터등록버튼) {
			if (JOptionPane.showInputDialog("비밀번호입력: ").equals("mana129")) {
				// 데이터위치
				readData("/1234.txt");

			}
		} else if((JButton) obj == 고객정보수정버튼) {
			new CustomSearch().setVisible(true);
		}
	}

	public void readData(String path) {
		InputStreamReader isr = null;
		StringBuffer sb = new StringBuffer("");
		try {
			//기반스트림
			FileInputStream fis = new FileInputStream(path);
			
			//보조스트림
			isr = new InputStreamReader(fis,"MS949");
			
			int data = -1;
			while((data= isr.read()) != -1)
			{
				//System.out.print((char)data);
				sb.append((char)data);
			}
			System.out.println(sb);
			System.out.println("@@@@@");
			String [] a = sb.toString().split("\n");
			String [] b=null;
			List<CustomDto> list = new ArrayList<CustomDto>();
			for(int i=1;i<a.length;i++)
			{
				CustomDto dto = new CustomDto();
			 b = a[i].split("\t");
			 if(b[0]!=null && b[1]!=null && !b[0].equals("") && !b[1].equals("")) {
				dto.setNo(Integer.parseInt(b[0]));
				dto.setName(b[1]);
				dto.setPhone1(b[3]);
				dto.setPhone2(b[4]);
				dto.setRes_type(b[5]);
				if(b[6]==null || b[6].equals(""))
					dto.setArea_no(20);
				else
					dto.setArea_no(Integer.parseInt(b[6]));
				dto.setAddress(b[8]);
				dto.setCollect_money_method_name(b[16]);
				dto.setBank_name(b[17]);
				dto.setAccount_name(b[18]);
				dto.setAccount_num(b[19]);
				dto.setMouth_price(Integer.parseInt(b[22]));
				dto.setLast_collect_date(b[23]);
				dto.setInstall_date(b[24]);
				dto.setTv_count(Integer.parseInt(b[25]));
				dto.setState_name(b[26]);
				dto.setRecevice_money(Integer.parseInt(b[33]));
				dto.setMemo(b[34]);
				list.add(dto);
			 }
			}
			b=a[30].split("\t");
			System.out.println("고객번호:"+b[0]);
			System.out.println("고객명:"+b[1]);
			System.out.println("전화1:"+b[3]);
			System.out.println("전화2:"+b[4]);
			System.out.println("거주구분:"+b[5]);
			System.out.println("지역코드:"+b[6]);
			System.out.println("주소:"+b[8]);
			System.out.println("수금방법:"+b[16]);
			System.out.println("이체은행:"+b[17]);
			System.out.println("계좌명:"+b[18]);
			System.out.println("계좌번호:"+b[19]);
			System.out.println("월관리비:"+b[22]);
			System.out.println("최종납입:"+b[23]);
			System.out.println("가설일:"+b[24]);
			System.out.println("tv대수:"+b[25]);
			System.out.println("상태:"+b[26]);
			System.out.println("미수금:"+b[33]);
			System.out.println("메모:"+b[34]);
			System.out.println(b.length);
			
			
			System.out.println(list.size());
			c.insertDataByText(list);
//			for(int i=0;i<a.length;i++)
//			{
//			System.out.println(a[i]);
//			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Fount:"+e);
		
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException :"+e);
		} catch (IOException e) {
		
			System.out.println("Error:"+e);
		}finally
		{
			
				try {
					if(isr!=null)
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}
