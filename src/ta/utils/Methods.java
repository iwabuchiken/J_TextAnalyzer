package ta.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import ta.items.Token;

public class Methods {

	/****************************************
	 *	getMillSeconds_now()
	 * 
	 * <Caller> 
	 * 1. ButtonOnClickListener # case main_bt_start
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	/******************************
		@return format => "yyyyMMdd_HHmmss"
	 ******************************/
	public static String get_TimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.JAPAN);
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	/*******************************
	 * @param tname 
	 * @param dbName 
	 * @return
	 * number of record inserted<br>
	 * -1	=> ClassNotFoundException<br>
	 * -2	=> SQLException when initializing statement instance<br>
	 * -3	=> Exception in closing<br>
	 *******************************/
	public static int 
	insertData_from_TokensList
	(String dbName, String tname, List<Token> list_Tokens) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        String dbCon = String.format(
        			Locale.JAPAN, 
        			"jdbc:sqlite:C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\data\\%s", 
        			dbName
					);

        String sql = null;
        
        boolean res;
        
        ///////////////////////////////////
		//
		// table
		//
		///////////////////////////////////
//		String msg;
//		msg = String.format(Locale.JAPAN, "[%s : %d] list_Tokens.get(0).getRemote_created_at()=%s", Thread
//				.currentThread().getStackTrace()[1].getFileName(), Thread
//				.currentThread().getStackTrace()[1].getLineNumber(), list_Tokens.get(0).getRemote_created_at());
//
//		System.out.println(msg);
		
        
        ///////////////////////////////////
		//
		// insert
		//
		///////////////////////////////////
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection(dbCon);
			
			statement = connection.createStatement();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return -2;
			
		}

		Token t = null;
		String timeLabel = null;
		
		int count = 0;
		
		int listSize = list_Tokens.size();
		
		int one10th = listSize / 10;
		
		int count_Interim = 0;
		
		for (int i = 0; i < listSize; i++) {
//			for (int i = 0; i < list_Tokens.size(); i++) {
			
		
	        try {
	        	
//				Class.forName("org.sqlite.JDBC");
//				
//				connection = DriverManager.getConnection(dbCon);
//				
//				statement = connection.createStatement();
	
				t = list_Tokens.get(i);
//				Token t = list_Tokens.get(0);
				
				timeLabel = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());

				sql = Methods.getSQL_Insert_Tokens();
				
//				"id", "created_at", "updated_at", 			// 0,1,2
//				"form", "hin", "hin_1", "hin_2", "hin_3", 	// 3,4,5,6,7
//				
//				"katsu_kei", "katsu_kata", "genkei", "yomi", "hatsu",	// 8,9,10,11,12
//				"history_id INTEGER", "user_id INTEGER", 	// 13,14
//				
//				"cake_id INTEGER", "remote_created_at", "remote_updated_at",	// 15,16,17

				sql = String.format(
						Locale.JAPAN, 
						sql,

						timeLabel, timeLabel,
						
						t.getForm(), t.getHin(), t.getHin_1(), t.getHin_2(), t.getHin_3(),
						
						t.getKatsu_kei(), t.getKatsu_kata(), t.getGenkei(), t.getYomi(), t.getHatsu(),
						
						t.getHistory_id(), t.getUser_id(),
						
						
						t.getCake_id(),
						t.getRemote_created_at(),
						t.getRemote_updated_at()
							
				);
				
//				sql = String.format(
//						Locale.JAPAN, 
//						"INSERT INTO %s ("
//								+ "'created_at', 'updated_at'"
//								+ " , "
//								+ "'cake_id', 'remote_created_at', 'remote_updated_at'"
//								+ ")"
//								+ " "
//								+ "VALUES ("
//								+ "'%s', '%s', %d, '%s', '%s'"
//								+ ")",
//								
//								CONS.DB.tname_Tokens,
//								timeLabel,
//								timeLabel,
//								
//								t.getCake_id(),
//								t.getRemote_created_at(),
//								t.getRemote_updated_at()
//								
//						);
//				
//	//			String msg;
//				msg = String.format(Locale.JAPAN, "[%s : %d] executing sql... => %s", Thread
//						.currentThread().getStackTrace()[1].getFileName(), Thread
//						.currentThread().getStackTrace()[1].getLineNumber(), sql);
//	
//				System.out.println(msg);
				
				
				res = statement.execute(sql);
				
//	//			String msg;
//				msg = String.format(Locale.JAPAN, "[%s : %d] sql=%s => res=%s", Thread
//						.currentThread().getStackTrace()[1].getFileName(), Thread
//						.currentThread().getStackTrace()[1].getLineNumber(), sql, res);
//	
//				System.out.println(msg);
				
				///////////////////////////////////
				//
				// count
				//
				///////////////////////////////////
				count ++;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} finally {
				
//	            try {
//	//                resultSet.close();
//	//                statement.close();
//	                connection.close();
//	                
//	//                String msg;
//					msg = String.format(Locale.JAPAN, "[%s : %d] connection => closed", Thread
//							.currentThread().getStackTrace()[1].getFileName(),
//							Thread.currentThread().getStackTrace()[1]
//									.getLineNumber());
//	
//					System.out.println(msg);
//					
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
	            
	        }//try
		
	        ///////////////////////////////////
			//
			// report: interim
			//
			///////////////////////////////////
//	        count_Interim
	        
	        if (count % one10th == 0) {
				
	        	count_Interim ++;
	        	
	        	String msg;
				msg = String.format(Locale.JAPAN, "[%s : %d] %d %% done", Thread
						.currentThread().getStackTrace()[1].getFileName(),
						Thread.currentThread().getStackTrace()[1]
								.getLineNumber(), (count_Interim * 10));

				System.out.println(msg);
				
			}
	        
	        
		}//for (int i = 0; i < list_Tokens.size(); i++)

		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] count=%d / sql=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), count, sql);

		System.out.println(msg);
		
		
		///////////////////////////////////
		//
		// close
		//
		///////////////////////////////////
        try {
        	
//            resultSet.close();
            statement.close();
            connection.close();
            
//            String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] connection => closed", Thread
					.currentThread().getStackTrace()[1].getFileName(),
					Thread.currentThread().getStackTrace()[1]
							.getLineNumber());

			System.out.println(msg);
			
        } catch (Exception e) {
        	
            e.printStackTrace();
            
            return -3;
            
        }

		return count;
		
	}//insertData_from_CSV

	private static String 
	getSQL_Insert_Tokens() {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		// header
		sb.append("INSERT INTO"); sb.append(" "); sb.append(CONS.DB.tname_Tokens);
		sb.append(" ");
		
		sb.append("("); sb.append(" ");
		
		///////////////////////////////////
		//
		// column names
		//
		///////////////////////////////////
		int i = 0;
		
		for (i = 1; i < CONS.DB.colNames_Tokens_full.length - 1; i++) {
//			for (i = 0; i < CONS.DB.colNames_Tokens_full.length - 1; i++) {
			
			sb.append("'");
			sb.append(CONS.DB.colNames_Tokens_full[i]);
			sb.append("'");
			sb.append(", ");
		}
		
		sb.append("'");
		sb.append(CONS.DB.colNames_Tokens_full[i]);
		sb.append("'");
		
		sb.append(" "); sb.append(")");
		
		///////////////////////////////////
		//
		// values
		//
		///////////////////////////////////
		sb.append(" "); sb.append("VALUES"); sb.append(" ");
		sb.append("("); sb.append(" ");
		
		for (i = 1; i < CONS.DB.colNames_Tokens_full.length - 1; i++) {
			
			sb.append("'");
			sb.append("%s");
			sb.append("'");
			sb.append(", ");
		}
		
		sb.append("'");
		sb.append("%s");
		sb.append("'");
		
		sb.append(" "); sb.append(")");
		
		///////////////////////////////////
		//
		// return
		//
		///////////////////////////////////
		return sb.toString();
		
	}//getSQL_Insert_Tokens
	

	public static List<Token> 
	conv_CSV_2_Tokens
	(List<String[]> content) {
		// TODO Auto-generated method stub
		
		List<Token> list_Tokens = new ArrayList<Token>();
		
		String[] line = null;
		
		Token t = null;
		
		for (int i = 0 + CONS.DB.numOf_CSV_HeaderLines; i < content.size(); i++) {
//			for (int i = 0; i < content.size(); i++) {
			
			line = content.get(i);
			
//			0	1			2			3	4	5	6		7		8		9			10	
//			id,created_at,updated_at,form,hin,hin_1,hin_2,hin_3,katsu_kei,katsu_kata,genkei,
//			11		12	13			14
//			yomi,hatsu,history_id,user_id
			t = new Token.Builder()
					.setCake_id(Integer.parseInt(line[0]))
					.setRemote_created_at(line[1])
					.setRemote_updated_at(line[2])
					
					.setForm(line[3])
					.setHin(line[4])
					.setHin_1(line[5])
					.setHin_2(line[6])
					.setHin_3(line[7])

					.setKatsu_kei(line[8])
					.setKatsu_kata(line[9])
					
					.setGenkei(line[10])
					.setYomi(line[11])
					.setHatsu(line[12])
					
					.setHistory_id(Integer.parseInt(line[13]))
					.setUser_id(
							(line[14].equals("")) ? -1 :
								Integer.parseInt(line[14])
					)
					
					.build();
			
			list_Tokens.add(t);
			
		}
		
		return list_Tokens;
		
	}//conv_CSV_2_Tokens

	public static String
	conv_MillSec_to_TimeLabel(long millSec)
	{
		//REF http://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android answered Oct 31 '11 at 12:59
		String dateFormat = CONS.Admin.format_Date;
//		String dateFormat = "yyyy/MM/dd hh:mm:ss.SSS";
		
		DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.JAPAN);
//		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(millSec);
		
		return formatter.format(calendar.getTime());
		
	}//conv_MillSec_to_TimeLabel(long millSec)

	/******************************
		REF http://stackoverflow.com/questions/625433/how-to-convert-milliseconds-to-x-mins-x-seconds-in-java answered Mar 9 '09 at 10:01
	 ******************************/
	public static String
	conv_MillSec_to_ClockLabel(long millSec)
	{
		return String.format(
			Locale.JAPAN,
			CONS.Admin.format_Clock, 
	//		"%02d:%02d", 
			TimeUnit.MILLISECONDS.toMinutes(millSec),
			TimeUnit.MILLISECONDS.toSeconds(millSec) - 
			TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millSec))
		);
		
	}//conv_MillSec_to_ClockLabel(long millSec)

	public static List<Token> 
	getTokens_HistoryID(int hist_ID) {
//		getTokens_HistoryID() {
		// TODO Auto-generated method stub
		
		List<Token> list = new ArrayList<Token>();
		
		String sql = "SELECT * FROM tokens WHERE history_id=" + hist_ID;
//		String sql = "SELECT * FROM tokens WHERE history_id=82";

		Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        String dbCon = String.format(
        			Locale.JAPAN, 
        			"jdbc:sqlite:C:\\WORKS\\WS\\Eclipse_Luna\\J_TextAnalyzer\\data\\%s", 
        			CONS.DB.dbName
					);

        boolean res;
        
        ///////////////////////////////////
		//
		// setup: db
		//
		///////////////////////////////////
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection(dbCon);
			
			statement = connection.createStatement();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
			
		}

		///////////////////////////////////
		//
		// execute
		//
		///////////////////////////////////
		try {
			
			resultSet = statement.executeQuery(sql);
			
			String tmp = null;
			int id = -1;
			
			String msg;
			
//			String tmp = resultSet.getString("form");
//			int id = resultSet.getInt("id");
//			
//			String msg;
//			msg = String.format(Locale.JAPAN, "[%s : %d] form=%s (id=%d)", Thread
//					.currentThread().getStackTrace()[1].getFileName(), Thread
//					.currentThread().getStackTrace()[1].getLineNumber(), tmp, id);
//
//			System.out.println(msg);

			Token t = null;

			//REF next() http://www.fluffycat.com/Java/ResultSet/
			while(resultSet.next()) {

				tmp = resultSet.getString("form");
				
				id = resultSet.getInt("id");
				
				if (tmp == null) {
					
					tmp = "NULL";
					
				} else if (tmp.equals("")) {
					
					tmp = "NO CHAR";
					
				} else if (tmp.equals(" ")) {
					
					tmp = "SPACE";
					
				} else if (tmp.equals(CONS.Char.char_SPACE_WIDE)) {
//				} else if (tmp.equals("　")) {
					
					tmp = "SPACE(wide)";
					
				} else if ((int)tmp.charAt(0) == CONS.Char.ascii_LF) {
//				} else if (tmp.equals("　")) {

					//REF char 10 http://www.theasciicode.com.ar/ascii-control-characters/line-feed-ascii-code-10.html
					tmp = "LINE_FEED";
					
				} else {
			
//					id/created_at/updated_at/		// 0-2
//					form/hin/hin_1/hin_2/hin_3/		// 3-7
//					katsu_kei/katsu_kata/genkei/yomi/hatsu/	// 8-12
//					history_id/user_id				// 13-14

					//REF getInt(columnIndex) http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
					t = new Token.Builder()
					
						.setId			(resultSet.getInt(		1	))
						.setCreated_at	(resultSet.getString(	2	))
						.setUpdated_at	(resultSet.getString(	3	))
						
						.setForm		(resultSet.getString(	4	))
						.setHin			(resultSet.getString(	5	))
						.setHin_1		(resultSet.getString(	6	))
						.setHin_2		(resultSet.getString(	7	))
						.setHin_3		(resultSet.getString(	8	))
	
						.setKatsu_kei	(resultSet.getString(	9	))
						.setKatsu_kata	(resultSet.getString(	10	))
						
						.setGenkei		(resultSet.getString(	11	))
						.setYomi		(resultSet.getString(	12	))
						.setHatsu		(resultSet.getString(	13	))
						
						.setHistory_id	(resultSet.getInt(	14	))
						.setUser_id		(resultSet.getInt(	15	))
						
						.setCake_id		(resultSet.getInt(	16	))
						.setRemote_created_at	(resultSet.getString(	17	))
						.setRemote_updated_at	(resultSet.getString(	18	))
					
						.build();

					list.add(t);
					
				}

				//REF charAt http://java-tips.org/java-se-tips/java.lang/how-to-extract-ascii-codes-from-a-string.html
				//REF char to int http://stackoverflow.com/questions/7443975/get-ascii-value-at-input-word answered Sep 16 '11 at 11:27
////				String msg;
//				msg = String.format(Locale.JAPAN, 
//						"[%s : %d] form=%s (id=%d len=%d char=%d)", 
////						"[%s : %d] form=%s (id=%d len=%d char=%c)", 
//						Thread.currentThread().getStackTrace()[1].getFileName(), Thread
//						.currentThread().getStackTrace()[1].getLineNumber(), 
//						tmp, id, tmp.length(), (int)tmp.charAt(0));
//
//				System.out.println(msg);
				
			}
			
//			res = statement.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		
	}//getTokens_HistoryID

	public static int 
	add_Symbols_2_Tokens(List<Token> list_Tokens) {
		// TODO Auto-generated method stub
		
		List<String> list_Symbols = new ArrayList<String>();		
		
		Token t = null;
		
		int count = 0;
		
		for (int i = 0; i < list_Tokens.size(); i++) {
			
			t = list_Tokens.get(i);
			
//			list_Symbols.add(CONS.Main.tm_Hins.get(t.getHin()));
			
			list_Tokens.get(i).setSymbol(CONS.Main.tm_Hins.get(t.getHin()));
			
			count ++;
			
		}
		
		return count;
		
	}//conv_Tokens_2_Symbols(List<Token> list_Tokens)

	
	public static String[] 
	conv_Tokens_2_BiSentence
	(List<Token> list_Tokens) {
		// TODO Auto-generated method stub
		
		String[] biSen = new String[2];
		
		StringBuilder nl_Sen = new StringBuilder();		// Natural Language sentece:	"強盗 殺人 事件 の 裁判 員 裁判 の"
		StringBuilder sym_Sen = new StringBuilder();	// Symbol sentence:				"N N N P N N"
		StringBuilder sym_Sen_space = new StringBuilder();	// Symbol sentence with space

		Token t = null;
		
		for (int i = 0; i < list_Tokens.size(); i++) {
			
			t = list_Tokens.get(i);

			///////////////////////////////////
			//
			// natural language
			//
			///////////////////////////////////
			nl_Sen.append(t.getForm());

			///////////////////////////////////
			//
			// symbol
			//
			///////////////////////////////////
			sym_Sen_space.append(t.getSymbol());
			
			
//			for (int j = 0; j < t.getForm().length() * 2; j++) {
			for (int j = 0; j < t.getForm().length() * 2 - 1; j++) {
//				for (int j = 0; j < t.getForm().length() - 1; j++) {
				
				sym_Sen_space.append(" ");
				
			}
			
//			sym_Sen_space.append(" ");
			
			sym_Sen.append(sym_Sen_space.toString());
			
			// spacing
//			sym_Sen.append(" ");
			
			///////////////////////////////////
			//
			// clear: sym_Sen_space
			//
			///////////////////////////////////
			//REF http://stackoverflow.com/questions/5192512/how-can-i-clear-or-empty-a-stringbuilder
			sym_Sen_space.setLength(0);
			
		}
		
		///////////////////////////////////
		//
		// build
		//
		///////////////////////////////////
		biSen[0] = nl_Sen.toString();
		biSen[1] = sym_Sen.toString();
		
		return biSen;
		
	}//conv_Tokens_2_BiSentence

}
