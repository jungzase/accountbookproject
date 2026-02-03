package accountbookpackage;

import java.util.Date;
//5명 모여서 설계(기능)
//기능을 가지고 메뉴를 작성(문서로 작성하면서 각각의 기능의 입력값과 출려값을 상세히 명세)
//인터페이스를 이용하여 각자 맡은 코드를 분배후 작성, 나눠서 작성한 것을 다시 결합(인터페이스 상속)
//팀장의 역할은 해당 모든 프로그램을 합쳐서 조율
//Oracel, h2에 대해 DAO 처리
public class AccountBook {
	private int id; 		//아이디
	private String indate;	//날짜
	private String type;	//수입, 지출
	private String category;//분류
	private int amount;		//금액
	private String note;
	
	
//	private java.util.Date date;		//사용날짜 or java.sql.Date
	
	public AccountBook(int id, String indate,String type, String category, int amount, String note) {
		this.id = id;
		this.indate = indate;
		this.type = type;
		this.category = category;
		this.amount = amount;
		this.note = note;
		
	}


	public AccountBook() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIndate() {
		return indate;
	}


	public void setIndate(String indate) {
		this.indate = indate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "AccountBook [id=" + id + ", indate=" + indate + ", type=" + type + ", category=" + category
				+ ", amount=" + amount + ", note=" + note + "]";
	}


	
	
	
}
