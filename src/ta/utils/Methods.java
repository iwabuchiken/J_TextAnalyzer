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
		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] list_Tokens.get(0).getRemote_created_at()=%s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), list_Tokens.get(0).getRemote_created_at());

		System.out.println(msg);
		
        
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
		
		for (int i = 0; i < list_Tokens.size(); i++) {
			
		
	        try {
	        	
//				Class.forName("org.sqlite.JDBC");
//				
//				connection = DriverManager.getConnection(dbCon);
//				
//				statement = connection.createStatement();
	
				t = list_Tokens.get(i);
//				Token t = list_Tokens.get(0);
				
				timeLabel = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
				
				sql = String.format(
							Locale.JAPAN, 
							"INSERT INTO %s ("
								+ "'created_at', 'updated_at'"
								+ " , "
								+ "'cake_id', 'remote_created_at', 'remote_updated_at'"
							+ ")"
							+ " "
							+ "VALUES ("
								+ "'%s', '%s', %d, '%s', '%s'"
							+ ")",
							
							CONS.DB.tname_Tokens,
							timeLabel,
							timeLabel,
							
							t.getCake_id(),
							t.getRemote_created_at(),
							t.getRemote_updated_at()
							
				);
				
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
		
		}//for (int i = 0; i < list_Tokens.size(); i++)

		///////////////////////////////////
		//
		// close
		//
		///////////////////////////////////
        try {
        	
//            resultSet.close();
            statement.close();
            connection.close();
            
//                String msg;
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

}
