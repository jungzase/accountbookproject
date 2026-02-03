package accountbookpackage;

import java.io.IOException;



public class AccountBookMain {

	public static void main(String[] args) throws IOException {
		new AccountBookProgram(new OracleAccountBookDAO());
//		new AccountBookProgram(new H2AccountBookDAO());
		
		

	}

}
