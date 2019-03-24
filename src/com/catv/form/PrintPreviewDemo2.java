package com.catv.form;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
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

class PrintPreviewDemo2 extends JFrame implements ActionListener {

//	public static void main(String[] args){
//		new PrintPreviewDemo().setVisible(true);
//	}
	
	private JTextPane mTextPane;
	private CustomController customController = new CustomController();
	
	public PrintPreviewDemo2(){
		List <CustomDto> list = customController.getReceiptPrint();
		setTitle("Printer preview demo");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		
		mTextPane = new JTextPane();
		mTextPane.setContentType("text/html");
		StringBuilder builder = new StringBuilder();
		//builder.append("<h1>Header</h1><table width=\"100%\">");
		for(int i=0;i<list.size();i++) {
		builder.append("<br/>"
				+"<br/>"
				+"<br/>"
				+"<br/>"
				+"<br/>"
				+ "<table width=\"100%\" style=\"border-collapse:separate;border-spacing:0 0;font-size:10px;width=\"100%\";\">");
			builder.append(
					"<tr >"
					+	"<td style='padding-left:150px'>"+list.get(i).getAddress()+"</td>"
					+ "</tr>"	
					+"<tr >"
					+"</tr>"
					+"<tr>"
					+	"<td style='padding-left:250px'>"+Integer.toString(list.get(i).getPost_num()).substring(0,3)
					+"-"+Integer.toString(list.get(i).getPost_num()).substring(3)+"</td>"
					+ "</tr>"
					+"<tr >"
					+"</tr>"
					+"<tr>"
					+	"<td style='padding-left:230px'>"+list.get(i).getName()+" 귀하</td>"
					+ "</tr>"	
					);
		builder.append("</table>");
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
		//페이지 크기설정 maybe 11.8 곱해줫음
		Paper paper = new Paper();
	//   paper.setSize(595, 842); //A4 용지 설정 mm * 2.83 is scaling factor to convert mm to pixels
		
		//a25.47
		paper.setSize(449.97,285.83);
		
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
