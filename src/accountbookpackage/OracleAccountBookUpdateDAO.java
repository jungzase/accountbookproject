package d260202_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import d260202_01.Phonebook;

public class OracleAccountBookUpdateDAO implements AccountBookDAO {
	
	Connection conn;

	public OracleAccountBookUpdateDAO() {
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
	@Override
	public AccountBook findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Phonebook> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
