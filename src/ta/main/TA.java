package ta.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import ta.items.Token;
import ta.utils.CONS;
import ta.utils.Methods;
import au.com.bytecode.opencsv.CSVReader;

public class TA {

	private static String JDBC_CONNECTION_URL = 
            "jdbc:oracle:thin:SCOTT/TIGER@localhost:1500:MyDB";
	
	public static void 
	main(String[] args) {
		// TODO Auto-generated method stub

		String msg;
		
		///////////////////////////////////
		//
		// init: vars
		//
		///////////////////////////////////
		init_Vars();

		///////////////////////////////////
		//
		// setup: database: sqlite3
		//
		///////////////////////////////////
		setup_DB_Sqlite3();

		///////////////////////////////////
		//
		// get: CSV file content
		//
		///////////////////////////////////
		List<String[]> con = get_CSVContent();
		
		if (con == null) {
			
//			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] content => null", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber());

			System.out.println(msg);
			
			return;
			
		} else {//if (con == null)
			
//			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] con.size()=%d", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), con.size());

			System.out.println(msg);
			
			
		}//if (con == null)

		
		///////////////////////////////////
		//
		// conv: content to a list of Token instances
		//
		///////////////////////////////////
		List<Token> list_Tokens = Methods.conv_CSV_2_Tokens(con);
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] list_Tokens.size()=%d", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), list_Tokens.size());

		System.out.println(msg);
		
		///////////////////////////////////
		//
		// show: tokens
		//
		///////////////////////////////////
		show_Tokens();
		
		///////////////////////////////////
		//
		// insert: db
		//
		///////////////////////////////////
//		insertData(list_Tokens);
		
		///////////////////////////////////
		//
		// get: header
		//
		///////////////////////////////////
		get_Header(con);
		
		///////////////////////////////////
		//
		// get: hin names(unique)
		//
		///////////////////////////////////
		Set<String> uniqueHins = get_HinNames(con);
		
		///////////////////////////////////
		//
		// close method
		//
		///////////////////////////////////
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] done", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber());

		System.out.println(msg);
		
		msg = String.format(Locale.JAPAN, "[%s : %d] msg", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber());

		System.out.println(msg);

	}//main(String[] args)

	
	private static Set<String> 
	get_HinNames(List<String[]> con) {
		// TODO Auto-generated method stub
		
		String msg;
		
//		0	1			2		3	
//		id,created_at,updated_at,form
//		4	5		6	7		8		9			10		11	12		13			14
//		hin,hin_1,hin_2,hin_3,katsu_kei,katsu_kata,genkei,yomi,hatsu,history_id,user_id
		
		List<String> list_Hins = new ArrayList<String>();
		
		String hin = null;
		
		int offset = 1;
		
		for (int i = 0 + offset; i < con.size(); i++) {
//			for (int i = 0; i < con.size(); i++) {
			
			hin = con.get(i)[4];
			
			list_Hins.add(hin);
			
		}
		
		///////////////////////////////////
		//
		// conv: hin names => unique
		//
		///////////////////////////////////
		//REF http://stackoverflow.com/questions/13429119/get-unique-values-from-arraylist-in-java answered Nov 17 '12 at 9:11
		Set<String> uniqueHins = new HashSet<String>(list_Hins);
		
		//report
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] uniqueHins.size=%d", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), uniqueHins.size());

		System.out.println(msg);
		
		Object[] uHins = uniqueHins.toArray();
		
		//report
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] uHins.length=%d", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), uHins.length);

		System.out.println(msg);
		
		String tmp_s = StringUtils.join(uHins, ",");
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] tmp=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), tmp_s);

		System.out.println(msg);

		return uniqueHins;
		
	}//get_HinNames(List<String[]> con)
	


	private static void 
	show_Tokens() {
		// TODO Auto-generated method stub
		
		int hist_ID = 63;
//		int hist_ID = 82;
		
		List<Token> list_Chosen = Methods.getTokens_HistoryID(hist_ID);
		
		if (list_Chosen == null) {
			
			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] token list => null", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber());

			System.out.println(msg);
		
			return;
			
		} else {//if (list_Chosen == null)
			
			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] token list size => %d", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), list_Chosen.size());

			System.out.println(msg);
			
			
		}//if (list_Chosen == null)
		
		///////////////////////////////////
		//
		// show: sentence
		//
		///////////////////////////////////
		List<String> list_Forms = new ArrayList<String>();
		
		for (int i = 0; i < list_Chosen.size(); i++) {
			
			list_Forms.add(list_Chosen.get(i).getForm());
			
		}
		
		Object[] strings = (Object[]) list_Forms.toArray();
//		String[] strings = (String[]) list_Forms.toArray();
		
		String sen = StringUtils.join(strings, " ");
//		String sen = StringUtils.join(strings, "");
		
		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] sen=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), sen);

		System.out.println(msg);
		
		///////////////////////////////////
		//
		// symbols
		//
		///////////////////////////////////
		Methods.add_Symbols_2_Tokens(list_Chosen);
//		List<String> list_Symbols = Methods.add_Symbols_2_Tokens(list_Chosen);

		String[] biSentence = Methods.conv_Tokens_2_BiSentence(list_Chosen);
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] sen.1=%s\nsen.2=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), 
				biSentence[0], biSentence[1]);

		System.out.println(msg);
		
		
//		Token t = list_Chosen.get(15);
////		Token t = list_Chosen.get(10);
//		
////		String msg;
//		msg = String.format(Locale.JAPAN, "[%s : %d] t.getSymbol()=%s / form=%s / hin=%s", Thread
//				.currentThread().getStackTrace()[1].getFileName(), Thread
//				.currentThread().getStackTrace()[1].getLineNumber(), t.getSymbol(), t.getForm(), t.getHin());
//
//		System.out.println(msg);
		
		
////		String msg;
//		msg = String.format(Locale.JAPAN, "[%s : %d] list_Symbols.size()=%d", Thread
//				.currentThread().getStackTrace()[1].getFileName(), Thread
//				.currentThread().getStackTrace()[1].getLineNumber(), list_Symbols.size());
//
//		System.out.println(msg);
//		
//		strings = (Object[]) list_Symbols.toArray();
////		String[] strings = (String[]) list_Forms.toArray();
//		
//		sen = StringUtils.join(strings, " ");
//		
////		String msg;
//		msg = String.format(Locale.JAPAN, "[%s : %d] sen=%s", Thread
//				.currentThread().getStackTrace()[1].getFileName(), Thread
//				.currentThread().getStackTrace()[1].getLineNumber(), sen);
//		
//		System.out.println(msg);
		
	}//show_Tokens


	private static void 
	get_Header(List<String[]> con) {
		// TODO Auto-generated method stub
	
		String msg;
		
		String[] hdr = con.get(0);
		
		// report
		String hdr_Str = StringUtils.join(hdr, ",");
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] header=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), hdr_Str);

		System.out.println(msg);

	}
	


	private static void 
	insertData(List<Token> list_Tokens) {
		// TODO Auto-generated method stub

		String msg;
		
		long start = Methods.getMillSeconds_now();
		
		msg = String.format(Locale.JAPAN, "[%s : %d] Start: insert data: %s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), 
				Methods.conv_MillSec_to_TimeLabel(start));

		System.out.println(msg);
		
		
		int res_i = Methods.insertData_from_TokensList(
						CONS.DB.dbName,
						CONS.DB.tname_Tokens,
						list_Tokens);
		
		long end = Methods.getMillSeconds_now();
		
		msg = String.format(Locale.JAPAN, "[%s : %d] End: insert data: %s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), 
				Methods.conv_MillSec_to_TimeLabel(end));
		
		System.out.println(msg);

//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] time=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), 
				Methods.conv_MillSec_to_ClockLabel(end - start));

		System.out.println(msg);

	}//insertData


	private static void 
	setup_DBFile() {
		// TODO Auto-generated method stub
		
		//REF http://www.linglom.com/programming/java/how-to-run-command-line-or-execute-external-application-from-java/
		Runtime rt = Runtime.getRuntime();
		
		String cmd = null;
		
		String sqlite_path = "C:\\WORKS\\Programs\\sqlite_shell\\sqlite3.exe";
		
		String dfile = "C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\abc_%s.db";
		
		cmd = String.format(Locale.JAPAN, "%s %s", sqlite_path, dfile,
//				cmd = String.format(Locale.JAPAN, "cmd /c %s %s", sqlite_path, dfile,
//				cmd = String.format(Locale.JAPAN, "cmd /c %s abc_%s.db", sqlite_path,
//				cmd = String.format(Locale.JAPAN, "cmd /c sqlite3 abc_%s.db", 
//				cmd = String.format(Locale.JAPAN, "sqlite3 abc_%s.db", 		//=> java.io.IOException: Cannot run program "sqlite3": CreateProcess error=2, w肳ꂽt@
//				cmd = String.format(Locale.JAPAN, "cmd /c sqlite3 abc_%s.db", 
//				cmd = String.format(Locale.JAPAN, "cmd /c echo abc > abc_%s.txt", 
//				cmd = String.format(Locale.JAPAN, "echo abc > abc_%s.txt", 
				Methods.get_TimeLabel(Methods.getMillSeconds_now()));
		
//		cmd = "cmd /c echo hi";

        //Process pr = rt.exec("cmd /c dir");
        try {
        	
			Process pr = rt.exec(cmd);
//			Process pr = rt.exec("c:\\helloworld.exe");
			
			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] cmd done => %s", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), cmd);

			System.out.println(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//setup_DBFile
	


	private static void 
	setup_DB_Sqlite3() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

//        String dbCon = "jdbc:sqlite:C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\data\\ta.db";
        
        String dbCon = String.format(
        			Locale.JAPAN, 
//        			"jdbc:sqlite:C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\data\\temp.db" 
        			"jdbc:sqlite:C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\data\\%s", 
        			CONS.DB.dbName
//        			CONS.DB.dbName + "2"
					);

        String sql = null;
        
        boolean res;
        
        ///////////////////////////////////
		//
		// table
		//
		///////////////////////////////////
		
        try {
        	
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager
                    .getConnection(dbCon);
//			.getConnection("jdbc:sqlite:C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\data\\EMPLOYEE.db");
//			.getConnection("jdbc:sqlite:C:\\SQLite\\EMPLOYEE.db");
			
			statement = connection.createStatement();
			
//			sql = "CREATE TABLE scientists (name VARCHAR(30));";
			sql = StringUtils.join(CONS.DB.sql_CreateTable_Tokens, " ");
//			sql = "CREATE TABLE IF N-OT EXISTS scientists (name VARCHAR(30));";
			
			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] executing sql... => %s", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), sql);

			System.out.println(msg);
			
			
			res = statement.execute(sql);
			
//			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] sql=%s => res=%s", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), sql, res);

			System.out.println(msg);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
            try {
//                resultSet.close();
//                statement.close();
                connection.close();
                
                String msg;
				msg = String.format(Locale.JAPAN, "[%s : %d] connection => closed", Thread
						.currentThread().getStackTrace()[1].getFileName(),
						Thread.currentThread().getStackTrace()[1]
								.getLineNumber());

				System.out.println(msg);
				
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }//try
		
        String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] setup: Sqlite3 => done", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber());

		System.out.println(msg);
		
	}//setup_DB_Sqlite3


	private static void 
	init_Vars() {
		// TODO Auto-generated method stub
		
		///////////////////////////////////
		//
		// hin name map
		//
		///////////////////////////////////
		CONS.Main.tm_Hins = new TreeMap<String, String>();
		
//		接続詞,助動詞,名詞,形容詞,接頭詞,連体詞,感動詞,動詞,助詞,副詞,記号	
//		接続詞,助動詞,名詞,形容詞,接頭詞,連体詞,感動詞,動詞,助詞,副詞,記号
//		,,,,,,,,
		//REF http://javarevisited.blogspot.jp/2011/12/treemap-java-tutorial-example-program.html
		CONS.Main.tm_Hins.put("接続詞", "C");		// Connective
		CONS.Main.tm_Hins.put("助動詞", "A");		// Auxiliary
		CONS.Main.tm_Hins.put("名詞", "N");
		
		CONS.Main.tm_Hins.put("形容詞", "J");		// adJective
		CONS.Main.tm_Hins.put("接頭詞", "FP");		// PreFix
		CONS.Main.tm_Hins.put("連体詞", "T");		// renTai-shi
		
		CONS.Main.tm_Hins.put("感動詞", "E");		// Exclamation
		CONS.Main.tm_Hins.put("動詞", "V");
		CONS.Main.tm_Hins.put("助詞", "P");		// Particle
		
		CONS.Main.tm_Hins.put("副詞", "D");		// aDverb
		CONS.Main.tm_Hins.put("記号", "S");		// Symbol
		
		
		
		
	}//init_Vars

	private static List<String[]> 
	get_CSVContent() {
		// TODO Auto-generated method stub
	
//		String fname = "data/tokens2.csv";
		String fname = CONS.DB.fpath_CSV_Tokens;
//		String fname = "data/tokens.csv";
		
		CSVReader cr = null;
		
		List<String[]> content = null;
		
		try {
			
			cr = new CSVReader(new FileReader(fname));
			
			content = cr.readAll();
//			List content = cr.readAll();
			
			String[] con1 = content.get(0);
			
			String msg;
			
			if (con1 == null) {
				
				msg = "con1 => null";
				
			} else {//if (con1 == null)
				
//				msg = String.format(Locale.JAPAN, "[%s : %d] content[0].length=%d", Thread
//						.currentThread().getStackTrace()[1].getFileName(), Thread
//						.currentThread().getStackTrace()[1].getLineNumber(), content.get(0).length);
				
				msg = StringUtils.join(con1, "/");
				
			}//if (con1 == null)
			
			System.out.println(msg);
			
			cr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///////////////////////////////////
		//
		// return
		//
		///////////////////////////////////
		return content;
		
	}//get_CSVContent

	private static void 
	read_CSV() {
		// TODO Auto-generated method stub
		
//		String fname = "data/tokens2.csv";
		String fname = "data/tokens.csv";
		
		CSVReader cr = null;
		
		try {
			
			cr = new CSVReader(new FileReader(fname));
			
			List<String[]> content = cr.readAll();
//			List content = cr.readAll();
			
			String[] con1 = content.get(0);
			
			String msg;
			
			if (con1 == null) {
				
				msg = "con1 => null";
				
			} else {//if (con1 == null)
				
//				msg = String.format(Locale.JAPAN, "[%s : %d] content[0].length=%d", Thread
//						.currentThread().getStackTrace()[1].getFileName(), Thread
//						.currentThread().getStackTrace()[1].getLineNumber(), content.get(0).length);
				
				msg = StringUtils.join(con1, "/");
				
			}//if (con1 == null)
			
			System.out.println(msg);
			
			cr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(JDBC_CONNECTION_URL);
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return connection;
    }
	
}
