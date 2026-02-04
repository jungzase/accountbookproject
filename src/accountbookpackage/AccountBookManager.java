package accountbookpackage;


public class AccountBookManager {
	AccountBookDAO dao;
	private int total;
	
	public AccountBookManager() {
		dao = new OracleAccountBookDAO();
	}
	public AccountBookManager(AccountBookDAO dao) {
		this.dao=dao;
	}	
//	전체보기
// 	id , type , amount , category, date

//	(시간대보기  년, 달) (수입보기/지출보기), 잔액보기, 분류보기
//	수입 / 월급, 용돈
//	분류 / 식비, 교통비, 주거/통신비, /기타
	//추가
	public void insert(String indate, String type, String category, int amount, String note) {
		int id=dao.count()+1;
		
		dao.insert(new AccountBook(id, indate, type, category, amount, note));
		System.out.println("넣어짐?");
	}
	//전체보기
	public void select() {
		for(AccountBook ab : dao.findAll()) {
			System.out.println(ab);
		}
	}
	public void select(String startDate, String endDate, String type, String category, int startAmount, int endAmount) {
		dao.selectByOption(startDate, endDate, type, category, startAmount, endAmount);
	}
	
	// 잔액보기
	public void selectBalance() {
		int total_income=0;
		int total_expenditure=0;
		int amount=0;
		
		for(AccountBook ab : dao.selectBalance()) {
			if(ab.getType().equals("수입")) {
				total_income += ab.getAmount();
			} else if (ab.getType().equals("지출")) {
				total_expenditure += ab.getAmount();
			}
		}
		System.out.println("총 수입: " + total_income + "총 지출: " + total_expenditure + "잔액: " + (total_income-total_expenditure));
	}

	public void selectById(int id) {
		System.out.println(dao.findById(id));
	}
	public void update() {
		
	}
	public void delete() {
		
	}
	
}
