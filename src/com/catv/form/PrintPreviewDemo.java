package com.catv.form;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

class PrintPreviewDemo extends JFrame implements ActionListener {

//	public static void main(String[] args){
//		new PrintPreviewDemo().setVisible(true);
//	}
	
	private JTextPane mTextPane;
	
	public PrintPreviewDemo(){
		
		setTitle("Printer preview demo");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		
		mTextPane = new JTextPane();
		mTextPane.setContentType("text/html");
		StringBuilder builder = new StringBuilder();
		//builder.append("<h1>Header</h1><table width=\"100%\">");
		for(int i=0;i<20;i++) {
		builder.append("<table width=\"100%\" style=\"border-collapse:separate;border-spacing:0 0;font-size:10px;width=\"100%\";\">");
			builder.append("<tr>"
					+ "<td style='width:\"35%\";text-align:center;'>등록번호</td>"
					+ "<td style='width:\"35%\";text-align:center;'>등록번호</td>"
					+ "<td style='width:\"35%\";text-align:center;'>등록번호</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center;'>성명</td>"
					+ "<td style='width:\"35%\";text-align:center;'>성명</td>"
					+ "<td style='width:\"35%\";text-align:center;'>성명</td>"
					+ "</tr>"
					+"<tr>"
					+"<td style='text-align:center;'>주소</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"15px\";'>월관리비 &nbsp &nbsp &nbsp &nbsp &nbsp TV</td>"
					+ "<td style='width:\"35%\";text-align:center;'>2014 &nbsp 10 &nbsp 01</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"15px\";'>월관리비 &nbsp &nbsp &nbsp &nbsp &nbsp TV</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center;'>2014.05-2014.10</td>"
					+ "<td style='width:\"35%\";text-align:center;'>2014 &nbsp 10 &nbsp 01</td>"
					+ "<td style='width:\"35%\";text-align:center;'>2014.05-2014.10</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>22,800 &nbsp &nbsp &nbsp &nbsp &nbsp 2,080</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>22,800 &nbsp &nbsp &nbsp &nbsp &nbsp 2,080</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>22,800 &nbsp &nbsp &nbsp &nbsp &nbsp 2,080</td>"
					+ "</tr>"
					+"<tr>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>22,800 &nbsp &nbsp &nbsp &nbsp &nbsp 2,080</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>22,800 &nbsp &nbsp &nbsp &nbsp &nbsp 2,080</td>"
					+ "<td style='width:\"35%\";text-align:center; padding-left:\"20px\";'>22,800 &nbsp &nbsp &nbsp &nbsp &nbsp 2,080</td>"
					+ "</tr>"
					);
		builder.append("</table>");
		builder.append("<br/>");
		builder.append("<br/>");
		builder.append("<br/>");
		}
		
		mTextPane.setText(builder.toString());
		
		JButton previewButton = new JButton("Preview");
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
		
		JButton printButton = new JButton("Print!");
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
