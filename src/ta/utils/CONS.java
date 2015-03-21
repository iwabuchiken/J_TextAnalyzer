package ta.utils;

import java.util.TreeMap;

public class CONS {

	public static class Admin {
		
		public static final String format_Date = "yyyy/MM/dd HH:mm:ss.SSS";
		
	}
	
	public static class Main {
		
		public static TreeMap<String, String> tm_Hins;
		
	}
	
	public static class DB {
		
		public static String dpath_DB = "data";
		
		public static String dbName = "ta.db";
		
		public static String tname_Tokens = "tokens";
		
//		id/created_at/updated_at/
//		form/hin/hin_1/hin_2/hin_3/
//		katsu_kei/katsu_kata/
//		genkei/yomi/hatsu/
//		history_id/user_id
		
		public static final String[] sql_CreateTable_Tokens = {
			
			//name VARCHAR(30)
			"CREATE",	"TABLE",	"IF NOT EXISTS",
			CONS.DB.tname_Tokens,
//			"tokens",
			"(",
				//REF autoincrement https://www.sqlite.org/autoinc.html
				//REF also => http://www.sqlite.org/faq.html#q1
				"id INTEGER PRIMARY KEY", ",",
				"created_at VARCHAR(20)", ",",
				"updated_at VARCHAR(20)", ",",
				"form VARCHAR(20)", ",",
				"hin VARCHAR(20)", ",",
				"hin_1 VARCHAR(20)", ",",
				"hin_2 VARCHAR(20)", ",",
				"hin_3 VARCHAR(20)", ",",
				"katsu_kei VARCHAR(20)", ",",
				"katsu_kata VARCHAR(20)", ",",
				"genkei VARCHAR(20)", ",",
				"yomi VARCHAR(20)", ",",
				"hatsu VARCHAR(20)", ",",
				"history_id INTEGER", ",",
				"user_id INTEGER", ",",
				
				"cake_id INTEGER", ",",
				"remote_created_at VARCHAR(20)", ",",
				"remote_updated_at VARCHAR(20)",
				
				
//				"user_id INTEGER", ",",
			")"
			
		};
		
		///////////////////////////////////
		//
		// csv
		//
		///////////////////////////////////
		public static final String fpath_CSV_Tokens = "data/tokens.csv";
		
		public static final int numOf_CSV_HeaderLines = 1;
		
	}//public static class DB
	
}//public class CONS
