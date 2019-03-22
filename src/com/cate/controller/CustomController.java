package com.cate.controller;

import java.util.List;

import com.catv.dao.CustomDao;
import com.catv.dto.CustomComboDto;
import com.catv.dto.CustomDto;
import com.catv.vo.CustomVo;

public class CustomController {
	
	private CustomDao dao = new CustomDao();
	
	public CustomComboDto insertForm() {
		return dao.getComboList();
	}
	public boolean insertDataByText(List <CustomDto> list) {
		return dao.insertData(list);
	}
	public boolean insertDataByDto(CustomDto dto) {
		return dao.insert(dto);
	}
	
	public List<CustomVo> getSearchList(String text,String option){
		return dao.getList(text, option);
	}
	public CustomVo getCustomByNo(int no) {
		return dao.getCustom(no);
	}
	
	public boolean modifiy(CustomVo vo)
	{
		return dao.update(vo);
	}
	
	public List<CustomDto> getReceiptPrint() {
		return dao.getReceiptPrint();
	}
	
	public List<CustomVo> getAutoPrint() {
		return dao.getAutoList();
	}
	
	public boolean Renew(List<Integer> list) {
		return dao.updateRenew(list);
	}
}
