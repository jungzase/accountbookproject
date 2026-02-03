package accountbookpackage;

import java.util.List;

public interface AccountBookDAO10 {
	public int insert(AccountBook accountBook);
	public List<AccountBook> findAll();	//전체보기
	public List<AccountBook> selectBalance(); // 잔액보기
	public List<AccountBook> selectByOption(String startDate, 
			String endDate, String type, String category, int startAmount, int endAmount);
	public AccountBook findById(int id); // id 보기
	public int count();
	
}
