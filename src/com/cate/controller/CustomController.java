package com.cate.controller;

import java.util.List;

import com.catv.dao.CustomDao;
import com.catv.dto.CustomComboDto;
import com.catv.dto.CustomDto;

public class CustomController {
	
	private CustomDao dao = new CustomDao();
	
	public CustomComboDto insertForm() {
		return dao.getComboList();
	}
	public boolean insertDataByText(List <CustomDto> list) {
		return dao.insertData(list);
	}
}
