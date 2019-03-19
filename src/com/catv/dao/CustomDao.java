package com.catv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.catv.dto.CustomComboDto;
import com.catv.dto.CustomDto;
import com.catv.vo.CustomVo;

public class CustomDao extends Dao {

	public List<CustomVo> getList() {
		List<CustomVo> list = new ArrayList<CustomVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from custom,bank,collectmoneymeyhod,resudent,state where custom.bank_no=bake.no and";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

			}
			return list;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;
	}

	public CustomComboDto getComboList(){
		CustomComboDto dto = new CustomComboDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		try {
			conn = getConnection();
			
			//bank
			String sql = "select name from bank";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				String name = rs.getString(1);
				list1.add(name);
			}
			dto.setBank(list1);
			
			//수금방법
			sql = "select name from collectmoneymethod";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				String name = rs.getString(1);
				list2.add(name);
			}
			dto.setMethod(list2);
			
			//상태
			sql = "select name from state";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				String name = rs.getString(1);
				list3.add(name);
			}
			dto.setState(list3);
			
			//resudent 거주구분
			sql = "select type from resudent";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				list4.add(name);
			}
			dto.setResudent(list4);
			
			//area 거주구분
			sql = "select name from area";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				list5.add(name);
			}
			dto.setArea(list5);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return dto;
		
	}


	public boolean insertData(List<CustomDto> list) {
		int cnt=0;
		for(int m=0;m<list.size();m++) {
			cnt++;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			System.out.println("@@@@@");
			System.out.println(list.get(m).getNo());
			conn=getConnection();
			//                                            이름  전화1 전화2 주소   계좌명 계좌번호    월관리비   마지막납부일     가설일   티비수 총납부금액   거주구분   은행   수금방법   상태       메모 미수금     지역
			String sql = "insert into custom values(? , ? , ? , ? , ? , ? ,   ? ,   ? ,    ? ,       ? ,   ? , 0      ,?,     ?,  ? ,   ?   , ? ,  ? ,  ?  )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,list.get(m).getNo());
			pstmt.setString(2, list.get(m).getName());
			pstmt.setString(3, list.get(m).getPhone1());
			pstmt.setString(4, list.get(m).getPhone2());
			pstmt.setString(5, list.get(m).getAddress());
			pstmt.setString(6, list.get(m).getAccount_name());
			pstmt.setString(7, list.get(m).getAccount_num());
			pstmt.setInt(8, list.get(m).getMouth_price());
			pstmt.setString(9, list.get(m).getLast_collect_date());
			pstmt.setString(10, list.get(m).getInstall_date());
			pstmt.setInt(11, list.get(m).getTv_count());
			Integer resNo =null;
			if(list.get(m).getRes_type().equals("단독주택"))
				resNo=1;
			else if(list.get(m).getRes_type().equals("업소"))
				resNo=2;
			else if(list.get(m).getRes_type().equals("빌라"))
				resNo=3;
			else if(list.get(m).getRes_type().equals("사무실"))
				resNo=4;
			else if(list.get(m).getRes_type().equals("다방"))
				resNo=5;
			else if(list.get(m).getRes_type().equals("공장"))
				resNo=6;
			else
				resNo=1;
			pstmt.setInt(12, resNo);
			 //이름  전화1 전화2 주소   계좌명 계좌번호    월관리비   마지막납부일     가설일   티비수 총납부금액   거주구분   은행   수금방법   상태       메모 미수금     지역
			
			Integer bank = null;
			if(list.get(m).getBank_name().equals("화개농협"))
				bank=1;
			else if(list.get(m).getBank_name().equals("신협"))
				bank=2;
			else
				bank=3;
			pstmt.setInt(13,bank);
			
			Integer method = null;
			if(list.get(m).getCollect_money_method_name().equals("지로"))
				method=1;
			else if(list.get(m).getCollect_money_method_name().equals("자동이체"))
				method=2;
			else{
				method=1;
			}
			pstmt.setInt(14,method);
			
			Integer state = null;
			if(list.get(m).getState_name().equals("정상"))
				state=1;
			else if(list.get(m).getState_name().equals("해약"))
				state=2;
			else if(list.get(m).getState_name().equals("보류"))
				state=3;
			pstmt.setInt(15,state);
			
			 //  메모 미수금     지역
			pstmt.setString(16, list.get(m).getMemo());
			pstmt.setInt(17, list.get(m).getRecevice_money());
			pstmt.setInt(18, list.get(m).getArea_no());
			
			int count = pstmt.executeUpdate();
			if( count !=1)
			{
				System.out.println("insert query error");
				return false;
			}

			
		}catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(pstmt != null)
						pstmt.close();
					if(rs != null)
						rs.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		}
		
		
		return true;
	}
		


}
