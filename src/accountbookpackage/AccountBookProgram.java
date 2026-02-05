package accountbookpackage;

import java.io.IOException;
import java.util.Scanner;


public class AccountBookProgram {
	AccountBookManager bm;
	Scanner scan=new Scanner(System.in);
	int id;
	String indate;		//날짜
	String type;		//수입 or 지출
	String category;	//유형(ex. 식비, 교통비, 주거/통신비, 기타)
	int amount;			//금액
	String note;		//비고
	
	public AccountBookProgram(AccountBookDAO dao) throws IOException {
		bm=new AccountBookManager(dao);
		while(true) {
			switch(menu()) {
				case 1: insert(); break;		// 추가
				case 2: views(); break;			// 전체 기록보기
				case 3: viewsBalance(); break; 	// 잔액보기
				case 4: view();break;			// 찾기
				case 5: update(); break;		// 수정
				case 6: delete(); break;		// 삭제
				case 0: System.out.println("프로그램을 종료합니다.!");
				 		System.exit(0);
			}	 	 
		}
	}
	private int menu() {
		 System.out.println("+===================+");
		 System.out.println("|   가계부 관리    |");
		 System.out.println("+===================+");
		 System.out.println("| 1.가계부 입력	|");
		 System.out.println("| 2.가계부 전체출력	|");
		 System.out.println("| 3.가계부 잔액확인	|");
		 System.out.println("| 4.가계부 찾기	|");
		 System.out.println("| 5.가계부 수정	|");
		 System.out.println("| 6.가계부 삭제	|");
		 System.out.println("| 0.가계부 종료	|");
		 System.out.println("+===================+");
		 return scan.nextInt();
	}
	private void insert() {
		System.out.println("가계부에 입력할 날짜를 입력하세요. (ex. yyyy-mm-dd)");
		indate=scan.next();
		System.out.println("가계부에 입력할 타입을 입력하세요. (ex. 수입 or 지출)");
		type=scan.next();
		System.out.println("가계부에 입력할 유형을 입력하세요. (ex. 식비, 주거/통신비, 교통비, 기타... )");
		category=scan.next();
		System.out.println("가계부에 입력할 금액을 입력하세요.");
		amount=scan.nextInt();
		System.out.println("가계부에 입력할 비고를 입력해주세요. (메가커피)");
		note=scan.next();
		bm.insert(indate, type, category, amount, note);
	}
	private void views() {
		bm.select();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void viewsBalance() {
		bm.selectBalance();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void view() {
		int viewMode = 0;
		String startDate = null;
		String endDate = null;
		int startAmount = 0;
		int endAmount = 0;
		while(true) {
			System.out.println("1.날짜설정, 2.타입설정, 3.유형설정, 4.금액설정, 0. 찾기종료");
			viewMode = 0;
			try {
			    viewMode = Integer.parseInt(scan.next()); 
			} catch (NumberFormatException e) {
			    System.out.println("메뉴 번호는 숫자로 입력하세요.");
			    return;
			}
			switch (viewMode) {
			    case 1:
			        System.out.println("시작 날짜를 입력해주세요 (yyyy-mm-dd)");
			        startDate = scan.next();
			        System.out.println("끝 날짜를 입력해주세요 (yyyy-mm-dd)");
			        endDate = scan.next();
			        break;

			    case 2:
			        System.out.println("수입, 지출중에 입력해주세요.(종합시 enter / 미구현)");
			        type = scan.next();
			        break;

			    case 3:
			        System.out.println("유형을 입력해주세요.(ex. 식비, 교통비, 주거/통신비)");
			        category = scan.next();
			        break;

			    case 4:
			        System.out.println("시작금액을 입력해주세요.");
			        startAmount = scan.nextInt();
			        System.out.println("끝금액을 입력해주세요.");
			        endAmount = scan.nextInt();
			        break;
			    case 0: return; 

			    default:
			        System.out.println("잘못된 메뉴 번호입니다.");
			}
			
			bm.select(startDate, endDate, type, category, startAmount, endAmount);
			
		}
	}
	private void update() {
		bm.update();
		
	}
	private void delete() {
		bm.delete();
		
	}

	

	

	

	
	
}
