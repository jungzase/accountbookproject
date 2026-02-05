package accountbookpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class H2AccountBookDAO2 implements AccountBookDAO{
	Connection conn;
	public H2AccountBookDAO2() {
		try {
			Class.forName("org.h2.Driver");
			conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			if(conn ==null) {
				System.out.println("DB연결을 다시 확인하세요.");
				System.out.println("프로그램을 종료합니다");
			}
			System.out.println("DB연결 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB연결 실패");
			
		}
		
	}
	@Override
	public int insert(AccountBook ab) {
		try {
			String sql="insert into accountbook values(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, ab.getId());
			ps.setString(2, ab.getIndate());
			ps.setString(3, ab.getType());
			ps.setString(4, ab.getCategory());
			ps.setInt(5, ab.getAmount());
			ps.setString(6, ab.getNote());
			int result=ps.executeUpdate(); //Statement객체와 차이점 sql을 여기서 실행하는 것이 아님
			ps.close();
			return result;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
		}
	}
	@Override
	public List<AccountBook> findAll() {
		try {
			String sql="select * from accountbook";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//결과를 이용하여 리스트를 반환할 예정이므로 코드 순서는 다음과 같다.
			//1. 빈 리스트를 만든다
			//2. 반복문을 통해 한 개의 객체를 Phonebook에 담고 담은 내용을 list에 add한다.
			//3. 반복문이 끝나고 나서 list를 반환한다.
			List<AccountBook> list = new ArrayList<AccountBook>();
			while(rs.next()) {
				int id=rs.getInt("id");
				String indate = rs.getString("indate");
				String type=rs.getString("type");
				String category=rs.getString("category");
				int amount=rs.getInt("amount");
				String note = rs.getString("note");
				AccountBook ab =new AccountBook(id, indate, type, category, amount, note);
				list.add(ab);
			}
			rs.close();
			ps.close();
			return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
				
			}
	}
	
	@Override
	public List<AccountBook> selectBalance() { //잔액보기
		try {
			String sql="select * from accountbook ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<AccountBook> list = new ArrayList<AccountBook>();

			while(rs.next()) {
				int id=rs.getInt("id");
				String indate = rs.getString("indate");
				String type=rs.getString("type");
				String category=rs.getString("category");
				int amount=rs.getInt("amount");
				String note = rs.getString("note");
				AccountBook ab =new AccountBook(id, indate, type, category, amount, note);
				list.add(ab);
			}
			rs.close();
			ps.close();
			return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
				
			}
		
	}
	
	@Override
	public List<AccountBook> selectByOption(String startDate, String endDate, String type, String category,
			int startAmount, int endAmount) {
		List<AccountBook> list = new ArrayList<>();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT id, indate, type, category, amount, note ");
	    sql.append("FROM accountbook ");
	    sql.append("WHERE 1=1 ");

	    List<Object> params = new ArrayList<>();

	    // 날짜 조건
	    if (startDate != null && !startDate.isBlank()) {
	        sql.append("AND indate >= ? ");
	        params.add(startDate);
	    }
	    if (endDate != null && !endDate.isBlank()) {
	        sql.append("AND indate <= ? ");
	        params.add(endDate);
	    }

	    // 타입 조건
	    if (type != null && !type.isBlank()) {
	        sql.append("AND type = ? ");
	        params.add(type);
	    }

	    // 카테고리 조건
	    if (category != null && !category.isBlank()) {
	        sql.append("AND category = ? ");
	        params.add(category);
	    }

	    // 금액 조건 (0이면 조건 미사용이라고 가정)
	    if (startAmount > 0) {
	        sql.append("AND amount >= ? ");
	        params.add(startAmount);
	    }
	    if (endAmount > 0) {
	        sql.append("AND amount <= ? ");
	        params.add(endAmount);
	    }

	    sql.append("ORDER BY indate DESC, id DESC");

	    try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            ps.setObject(i + 1, params.get(i));
	        }

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	            	AccountBook ab = new AccountBook();
	                ab.setId(rs.getInt("id"));
	                ab.setIndate(rs.getString("indate"));
	                ab.setType(rs.getString("type"));
	                ab.setCategory(rs.getString("category"));
	                ab.setAmount(rs.getInt("amount"));
	                ab.setNote(rs.getString("note"));
	                
	                list.add(ab);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	@Override
	public AccountBook findById(int id) {
		try {
			String sql="select * from accountbook where id=?";	
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String type=rs.getString("type");
				String indate=rs.getString("indate");
				String category=rs.getString("category");
				int amount = rs.getInt("amount");
				String note=rs.getString("note");
				AccountBook ab = new AccountBook(id, indate, type, category, amount, note);
				return ab;
			}
			rs.close();
			ps.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//전체보기
	@Override
	public int count() {
		try {
			String sql="select max(id) as max from accountbook";	
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				int cnt = rs.getInt("max");
				System.out.println("fff");
				return cnt;
			}
			rs.close();
			ps.close();
			return -1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	
}
