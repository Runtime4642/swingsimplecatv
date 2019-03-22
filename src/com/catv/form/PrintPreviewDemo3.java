package com.catv.form;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.cate.controller.CustomController;
import com.catv.vo.CustomVo;

class PrintPreviewDemo3 extends JFrame implements ActionListener {

//	public static void main(String[] args){
//		new PrintPreviewDemo().setVisible(true);
//	}
	
	private JTextPane mTextPane;
	private CustomController customController = new CustomController();
	
	public PrintPreviewDemo3(){
		List <CustomVo> list = customController.getAutoPrint();
		setTitle("Printer preview demo");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		
		mTextPane = new JTextPane();
		mTextPane.setContentType("text/html");
		
		//date 포맷팅
		Date now = new Date();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(now);
//		cal.add(Calendar.MONTH,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date = sdf.format(now);
		
		
		StringBuilder builder = new StringBuilder();
		builder.append("<h1 style='text-align:center'>&lt; 고 객 리 스 트 (자동이체)&gt;</h1>");
		builder.append("<h4 style='text-align:left'>인쇄일 : "+date+"</h4><hr>");
		builder.append("<table  width='100%'style=\"border-collapse:separate;border-spacing:0 0;font-size:12px;\">");
		builder.append("<tr  width='100%' style='border-bottom:1px solid black;'><td >고객No</td><td>성명</td><td>관리비</td><td>TV수</td><td>최종납입</td><td>미수금</td><td>계좌번호</td></tr>");
		for(int i=0;i<list.size();i++) {
			builder.append(
					"<tr width='100%' style='border-bottom:1px solid black'>"
					+"<td>"
					+ list.get(i).getNo()		
					+"</td>"
					+"<td>"
					+ list.get(i).getName()		
					+"</td>"
					+"<td>"
					+ list.get(i).getMouth_price()		
					+"</td>"
					+"<td>"
					+ list.get(i).getTv_count()		
					+"</td>"
					+"<td>"
					+ list.get(i).getLast_collect_date()		
					+"</td>"
					+"<td>"
					+ list.get(i).getReceive_money()		
					+"</td>"
					+"<td>"
					+ list.get(i).getAccount_num()		
					+"</td>"
					+"</tr>"
					);
		}
		builder.append("</table>");
		
		mTextPane.setText(builder.toString());
		
		JButton previewButton = new JButton("미리보기");
		previewButton.addActionListener(this);
		
		panel.add(new JScrollPane(mTextPane), BorderLayout.CENTER);
		panel.add(previewButton, BorderLayout.SOUTH);
		getContentPane().add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent event){
		JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.setSize(700, 400);
		dialog.setLayout(new BorderLayout());
		
		HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
		
		set.add(MediaSizeName.ISO_A4);
		
		
		//set.add(new MediaPrintableArea(6, 6, 6, 6, MediaPrintableArea.MM));
		set.add(OrientationRequested.PORTRAIT);
		PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);
		//   paper.setSize(595, 842); //A4 용지 설정 mm * 2.83 is scaling factor to convert mm to pixels
		Paper paper = new Paper();
		paper.setSize(682.03,792.4);
		
		//여백설정  //왼쪽, 위 , 인쇄영역 넓이 , 인쇄영역 높이
		paper.setImageableArea(10,10,paper.getWidth()-10*2,paper.getHeight()-20*2);
		pf.setPaper(paper);
        //PageFormat can be also prompted from user with PrinterJob.pageDialog()
		final PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);
		
		JButton printButton = new JButton("프린트출력");
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				preview.print();
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(printButton);
		buttonsPanel.add(preview.getControls());
		
		dialog.getContentPane().add(preview, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		dialog.setVisible(true);
	}
	
}
