package com.catv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			String sql = "insert into custom values(? , ? , ? , ? , ? , ? ,   ? ,   ? ,    ? ,       ? ,   ? , 0      ,?,     ?,  ? ,   ?   , ? ,  ? ,  ?  ,current_date())";
			
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
		
	public boolean insert(CustomDto dto)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			 String sql = "select no from resudent where type=?";
			 pstmt = conn.prepareStatement(sql);
			
			 pstmt.setString(1, dto.getRes_type());
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				dto.setRes_no(rs.getInt(1));
			 }
			 
			 sql = "select no from area where name=?";
			 pstmt = conn.prepareStatement(sql);
			
			 pstmt.setString(1, dto.getArea_name());
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				dto.setArea_no(rs.getInt(1));
			 }

			 sql = "select no from collectmoneymethod where name=?";
			 pstmt = conn.prepareStatement(sql);
			
			 pstmt.setString(1, dto.getCollect_money_method_name());
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				dto.setCollect_money_method_no(rs.getInt(1));
			 }

			 sql = "select no from bank where name=?";
			 pstmt = conn.prepareStatement(sql);
			
			 pstmt.setString(1, dto.getBank_name());
			 rs = pstmt.executeQuery();
			 
			 while(rs.next())
			 {
				dto.setBank_no(rs.getInt(1));
			 }

			
			//                                번호    이름  전화1 전화2 주소   계좌명 계좌번호    월관리비   마지막납부일                                       가설일         티비수 총납부금액   거주구분   은행   수금방법   상태       메모 미수금     지역
			 sql = "insert into custom values(null , ? , ? , ? , ? , ? ,   ? ,   ? , ? ,  current_date() ,   ? , 0      ,?,     ?,  ? ,   1   , ? ,  ? ,  ?  ,current_date())";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone1());
			pstmt.setString(3, dto.getPhone2());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getAccount_name());
			pstmt.setString(6, dto.getAccount_num());
			pstmt.setInt(7, dto.getMouth_price());
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			String date = sdf.format(now);
			pstmt.setString(8, date);
			pstmt.setInt(9, dto.getTv_count());
			pstmt.setInt(10, dto.getRes_no());
			 //이름  전화1 전화2 주소   계좌명 계좌번호    월관리비   마지막납부일     가설일   티비수 총납부금액   거주구분   은행   수금방법   상태       메모 미수금     지역
			pstmt.setInt(11,dto.getBank_no());
			pstmt.setInt(12,dto.getCollect_money_method_no());
			 //  메모 미수금     지역
			pstmt.setString(13, dto.getMemo());
			pstmt.setInt(14, dto.getRecevice_money());
			pstmt.setInt(15, dto.getArea_no());
			
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
		
		
		
		return true;
		
	}
	public List<CustomVo> getList(String text,String option) {
		List<CustomVo> list = new ArrayList<CustomVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//"고객번호","이름","전화1","전화2","주소","계좌명","계좌번호","미수금","최종납입",
			if(option.equals("phone")) {
			String sql = "select no,name,phone1,phone2,address,account_name,account_num,receive_money,last_collect_day from custom where phone1 like ? or phone2 like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+text+"%");
			pstmt.setString(2, "%"+text+"%");
			}
			else {
			String sql = "select no,name,phone1,phone2,address,account_name,account_num,receive_money,last_collect_day from custom where "+option+" like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+text+"%");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomVo vo = new CustomVo();
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone1 = rs.getString(3);
				String phone2 = rs.getString(4);
				String address = rs.getString(5);
				String account_name = rs.getString(6);
				String account_num = rs.getString(7);
				int receive_money = rs.getInt(8);
				String last_collect_date = rs.getString(9);
				vo.setNo(no);
				vo.setName(name);
				vo.setPhone1(phone1);
				vo.setPhone2(phone2);
				vo.setAddress(address);
				vo.setAccount_name(account_name);
				vo.setAccount_num(account_num);
				vo.setReceive_money(receive_money);
				vo.setLast_collect_date(last_collect_date);
				list.add(vo);
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

	public CustomVo getCustom(int no) {
		
		CustomVo vo = new CustomVo();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			 conn = getConnection();
			 
			 String sql = "select * from custom where no="+no;
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				String name = rs.getString(2);
				String phone1 = rs.getString(3);
				String phone2 = rs.getString(4);
				String address = rs.getString(5);
				String account_name = rs.getString(6);
				String account_num = rs.getString(7);
				int month_price = rs.getInt(8);
				String last_collect_date = rs.getString(9);
				String install_date = rs.getString(10);
				int tv_count = rs.getInt(11);
				int res_no = rs.getInt(13);
				int bank_no =rs.getInt(14);
				int collect_money_method_no = rs.getInt(15);
				int state_no = rs.getInt(16);
				String memo = rs.getString(17);
				int receive_money = rs.getInt(18);
				int area_no = rs.getInt(19);
			 
				vo.setNo(no);
				vo.setName(name);
				vo.setPhone1(phone1);
				vo.setPhone2(phone2);
				vo.setAddress(address);
				vo.setAccount_name(account_name);
				vo.setAccount_num(account_num);
				vo.setMouth_price(month_price);
				vo.setLast_collect_date(last_collect_date);
				vo.setLast_collect_date(last_collect_date);
				vo.setInstall_date(install_date);
				vo.setTv_count(tv_count);
				vo.setRes_no(res_no);
				vo.setBank_no(bank_no);
				vo.setCollect_money_method_no(collect_money_method_no);
				vo.setState_no(state_no);
				vo.setMemo(memo);
				vo.setReceive_money(receive_money);
				vo.setArea_no(area_no);
			 }
			 
			 
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(rs !=null)
						rs.close();
					if(pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return vo;
	}

	public boolean update(CustomVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = 
					"update custom set state_no= ?, name=? ,install_date=?,res_no=?,phone1=?,phone2=?,area_no=?,address=?,memo=?,collect_money_method_no=?,bank_no=?,account_name=?,account_num=?,tv_count=?,month_price=?,last_collect_day=?  where no=?"; 
			pstmt = conn.prepareStatement(sql);
			
//state= 1, name=2 ,install_date=3,res_no=4,
//phone1=5,phone2=6,area_no=7,address=8,memo=9,
//collect_money_method_no=10,bank_no=11,account_name=12,
//account_num=13,tv_count=14,month_price=15,last_collect_day=16
			
			pstmt.setInt(1,vo.getState_no());
			pstmt.setString(2,vo.getName());
			pstmt.setString(3,vo.getInstall_date());
			pstmt.setInt(4,vo.getRes_no());
			pstmt.setString(5,vo.getPhone1());
			pstmt.setString(6,vo.getPhone2());
			pstmt.setInt(7,vo.getArea_no());
			pstmt.setString(8,vo.getAddress());
			pstmt.setString(9,vo.getMemo());
			pstmt.setInt(10,vo.getCollect_money_method_no());
			pstmt.setInt(11,vo.getBank_no());
			pstmt.setString(12,vo.getAccount_name());
			pstmt.setString(13,vo.getAccount_num());
			pstmt.setInt(14,vo.getTv_count());
			pstmt.setInt(15,vo.getMouth_price());
			pstmt.setString(16,vo.getLast_collect_date());
			pstmt.setInt(17, vo.getNo());
			
			int count = pstmt.executeUpdate();
			return result = count==1;
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	public List<CustomDto> getReceiptPrint(){
		List<CustomDto> list = new ArrayList<CustomDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql =
			"SELECT  " + 
			"    custom.no, " + 
			"    custom.name, " + 
			"    custom.address, " + 
			"    custom.month_price, " + 
			"    custom.last_collect_day, " + 
			"    custom.tv_count, " + 
			"    area.post_num, " + 
			"    custom.receive_money " + 
			"FROM " + 
			"    custom, " + 
			"    bank, " + 
			"    collectmoneymethod, " + 
			"    resudent, " + 
			"    state, " + 
			"    area " + 
			"WHERE " + 
			"    custom.bank_no = bank.no " + 
			"        AND custom.res_no = resudent.no " + 
			"        AND custom.state_no = state.no " + 
			"        AND custom.collect_money_method_no = collectmoneymethod.no " + 
			"        AND state.name=\"정상\" " + 
			"        AND custom.area_no = area.no " + 
			"        AND collectmoneymethod.name=\"지로\"";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomDto dto = new CustomDto();
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String address =rs.getString(3);
				int price = rs.getInt(4);
				String last_collect_day = rs.getString(5);
				int tv_count = rs.getInt(6);
				int post_num = rs.getInt(7);
				int receive_money = rs.getInt(8);
				
				dto.setNo(no);
				dto.setName(name);
				dto.setAddress(address);
				dto.setMouth_price(price);
				dto.setLast_collect_date(last_collect_day);
				dto.setTv_count(tv_count);
				dto.setPost_num(post_num);
				dto.setRecevice_money(receive_money);
				list.add(dto);
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

	
	//자동이체 리스트겟
	public List<CustomVo> getAutoList(){
		List<CustomVo> list = new ArrayList<CustomVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select a.no,a.name,a.month_price,a.tv_count,a.last_collect_day,a.receive_money,a.account_num from custom a,collectmoneymethod b where a.collect_money_method_no=b.no and b.name='자동이체'; ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			
				int no = rs.getInt(1);
				String name = rs.getString(2);
				int month_price = rs.getInt(3);
				int tv_count = rs.getInt(4);
				String last_collect_day = rs.getString(5);
				int receive_money = rs.getInt(6);
				String account_num = rs.getString(7);
				
				CustomVo vo = new CustomVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMouth_price(month_price);
				vo.setTv_count(tv_count);
				vo.setLast_collect_date(last_collect_day);
				vo.setReceive_money(receive_money);
				vo.setAccount_num(account_num);
				list.add(vo);
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
	
	public boolean updateRenew(List<Integer> list) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
			for(int i=0;i<list.size();i++) {
			conn = getConnection();

			String sql = 
					"update custom set receive_money_date = CURRENT_DATE() and receive_money=0 where no = ? and MONTH(CURRENT_DATE()) != MONTH(receive_money_date) and YEAR(CURRENT_DATE()) != YEAR(receive_money_date)"; 
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, list.get(i));
			 pstmt.executeUpdate();
			
		 
			}
			String sql = 
			"update custom set receive_money_date = CURRENT_DATE() and receive_money=receive_money+(200*tv_count) where MONTH(CURRENT_DATE()) != MONTH(receive_money_date) and YEAR(CURRENT_DATE()) != YEAR(receive_money_date)";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error :" + e);
			return false;
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
		
	}
}
