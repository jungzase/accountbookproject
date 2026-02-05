package d260202_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import d260202_01.Phonebook;

public class H2AccountBookDAOUpdate implements AccountBookDAO {
	
	Connection conn;

	public H2AccountBookDAOUpdate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@172.16.15.71:1521:xe", "system", "1234");
			if(conn==null) {
				System.out.println("DB연결을 다시 확인하세요.");
				System.out.println("프로그램을 종료합니다.");
			}
			System.out.println("DB연결 성공!!");
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public List findAll() {
		try {
		String sql="select * from AccountBook";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		//결과를 이용하여 리스트를 반환할 예정이므로 코드 순서는 다음과 같다.
		/*
		1)빈 리스트를 만든다.
		2)반복문을 통해 한 개의 객체를 Phonebook에 담고 담은 내용을 list에 add한다.
		3)반복문이 끝나고 나서 list를 반환한다.
		*/
		List<AccountBook> list=new ArrayList<AccountBook>(); //빈리스트 만들기
		while(rs.next()) {
			int type=rs.getInt("type");
			String amount=rs.getString("amount");
			String category=rs.getString("category");
			String date=rs.getString("date");
			AccountBook ab=new AccountBook(type, amount, category, date);
			list.add(ab);
		}
		return list;
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AccountBook findById(int id) {
		try {
			String sql="select * phonebook where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				String type=rs.getString("type");
				String amount=rs.getString("amount");
				String category=rs.getString("category");
				String date=rs.getString("date");
				AccountBook ab=new AccountBook(type,amount , category, date);
				rs.close();ps.close();
				return ab;
			}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("null")
	@Override
	public int update(AccountBook ab) {
		try {
			AccountBook new_ab=findById(ab.getId());
			if(ab.getType()!=null) new_ab.setType(new_ab.getType());
			if(ab.getAmount()!=null) new_ab.setAmount(new_ab.getAmount());
			if(ab.getCategory()!=null) new_ab.setCategory(new_ab.getCategory());
			if(ab.getDate()!=null) new_ab.setDate(new_ab.getCategory());
			
			String sql="update phonebook set type=?, amount=?, category=?, date=? where id=?";
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, (String) new_ab.getType());
			ps.setString(2, (String) new_ab.getAmount());
			ps.setString(3, (String) new_ab.getCategory());
			ps.setString(4, (String) new_ab.getDate());
			ps.setInt(5, (int) new_ab.getId());
			int result=ps.executeUpdate();
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {
				Connection ps = null;
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private AccountBook findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
