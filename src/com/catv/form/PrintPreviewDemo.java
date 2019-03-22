package com.catv.form;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.catv.dto.CustomDto;

class PrintPreviewDemo extends JFrame implements ActionListener {

//	public static void main(String[] args){
//		new PrintPreviewDemo().setVisible(true);
//	}
	
	private JTextPane mTextPane;
	private CustomController customController = new CustomController();
	
	public PrintPreviewDemo(){
		
		List <CustomDto> list = customController.getReceiptPrint();
		
		setTitle("Printer preview demo");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		
		//date 포맷팅
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
		String date = sdf.format(cal.getTime());
		

		
		
		mTextPane = new JTextPane();
		mTextPane.setContentType("text/html");
		StringBuilder builder = new StringBuilder();
		//builder.append("<h1>Header</h1><table width=\"100%\">");
		for(int i=0;i<list.size();i++) {
//			//금액 계산
//			// 밀린달수 x 200 x tv수       밀린달수 = 현재달-1 - 마지막 낸달 +1
//			int month = (Integer.parseInt(date.substring(0,4))-Integer.parseInt(list.get(i).getLast_collect_date().substring(0,4)))*12 +(Integer.parseInt(date.substring(5))-Integer.parseInt(list.get(i).getLast_collect_date().substring(5)));
//			month++;
//			int price = month * 200 * list.get(i).getTv_count() + month * list.get(i).getMouth_price();

			// 위는 아님 미수금 + 이번달 요금 으로 해야함
			int price = list.get(i).getRecevice_money() + list.get(i).getMouth_price();
			//부가세
			int price2 = price+price/10;
		builder.append("<table width=\"100%\" style=\"border-collapse:separate;border-spacing:0 0;font-size:10px;width=\"100%\";\">");
			builder.append("<tr>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getNo()+"</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getNo()+"</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getNo()+"</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getName()+"</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getName()+"</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getName()+"</td>"
					+ "</tr>"
					+"<tr>"
					+"<td colspan='3' style='text-align:left; padding-left:40px'>"+list.get(i).getAddress()+"</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"15px\";'>"+list.get(i).getMouth_price()+" &nbsp &nbsp &nbsp &nbsp &nbsp "+list.get(i).getTv_count()+"</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+date.substring(0,4)+" &nbsp "+date.substring(4)+" &nbsp 01</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"15px\";'>"+list.get(i).getMouth_price()+" &nbsp &nbsp &nbsp &nbsp &nbsp "+list.get(i).getTv_count()+"</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getLast_collect_date()+"-"+date+"</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+date.substring(0,4)+" &nbsp "+date.substring(4)+" &nbsp 31</td>"
					+ "<td style='width:\"35%\";text-align:center;'>"+list.get(i).getLast_collect_date()+"-"+date+"</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>"+price+" &nbsp &nbsp &nbsp &nbsp &nbsp "+price/10+"</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>"+price+" &nbsp &nbsp &nbsp &nbsp &nbsp "+price/10+"</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>"+price+" &nbsp &nbsp &nbsp &nbsp &nbsp "+price/10+"</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>"+price2+" &nbsp &nbsp &nbsp &nbsp &nbsp "+(price2+list.get(i).getTv_count()*200)+"</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>"+price2+" &nbsp &nbsp &nbsp &nbsp &nbsp "+(price2+list.get(i).getTv_count()*200)+"</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>"+price2+" &nbsp &nbsp &nbsp &nbsp &nbsp "+(price2+list.get(i).getTv_count()*200)+"</td>"
					+ "</tr>"
					);
		builder.append("</table>");
		builder.append("<br/>");
		builder.append("<br/>");
		builder.append("<br/>");
		}
		
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
		//페이지 크기설정
		Paper paper = new Paper();
		paper.setSize(449.97,283);
		
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
