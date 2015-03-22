package ta.utils;

import java.util.TreeMap;

public class CONS {

	public static class Admin {
		
		public static final String format_Date = "yyyy/MM/dd HH:mm:ss.SSS";

		public static final String format_Clock = "%02d:%02d";

		
	}
	
	public static class Char {
	
		public static final int ascii_LF = 10;
		
//		public static final int ascii_SPACE_WIDE = (int)"　".charAt(0);
		public static final String char_SPACE_WIDE = "　";
		
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
		
		public static final String[] colNames_Tokens_full = {

			"id", "created_at", "updated_at", 			// 0,1,2
			"form", "hin", "hin_1", "hin_2", "hin_3", 	// 3,4,5,6,7
			
			"katsu_kei", "katsu_kata", "genkei", "yomi", "hatsu",	// 8,9,10,11,12
			"history_id", "user_id", 	// 13,14
			
			"cake_id", "remote_created_at", "remote_updated_at",	// 15,16,17

		};
		
		public static final String[] colTypes_Tokens_full = {

			"INTEGER PRIMARY KEY", "VARCHAR(20)", "VARCHAR(20)", 
			"VARCHAR(20)", "VARCHAR(20)", "VARCHAR(20)", "VARCHAR(20)", "VARCHAR(20)",
			
			"VARCHAR(20)", "VARCHAR(20)", "VARCHAR(20)", "VARCHAR(20)", "VARCHAR(20)",
			
			"INTEGER", "INTEGER", 
			
			"INTEGER", "VARCHAR(20)", "VARCHAR(20)",

		};
		
		public static final String[] sql_CreateTable_Tokens = {
			
			//name VARCHAR(30)
			"CREATE",	"TABLE",	"IF NOT EXISTS",
			CONS.DB.tname_Tokens,
//			"tokens",
			"(",
				//REF autoincrement https://www.sqlite.org/autoinc.html
				//REF also => http://www.sqlite.org/faq.html#q1
				"id INTEGER PRIMARY KEY", ",",	// 0
				"created_at VARCHAR(20)", ",",
				"updated_at VARCHAR(20)", ",",	// 2
				
				"form VARCHAR(20)", ",",
				"hin VARCHAR(20)", ",",
				"hin_1 VARCHAR(20)", ",",
				"hin_2 VARCHAR(20)", ",",
				"hin_3 VARCHAR(20)", ",",		// 7
				
				"katsu_kei VARCHAR(20)", ",",
				"katsu_kata VARCHAR(20)", ",",
				"genkei VARCHAR(20)", ",",
				"yomi VARCHAR(20)", ",",
				"hatsu VARCHAR(20)", ",",
				"history_id INTEGER", ",",
				"user_id INTEGER", ",",			// 14
				
				"cake_id INTEGER", ",",
				"remote_created_at VARCHAR(20)", ",",
				"remote_updated_at VARCHAR(20)",	// 17
				
				
//				"user_id INTEGER", ",",
			")"
			
		};

		public static final String[] sql_Insert_Tokens = {
			
			//name VARCHAR(30)
			"INSERT",	"INTO", CONS.DB.tname_Tokens,
//			"tokens",
			"(",
			
				"'", CONS.DB.colNames_Tokens_full[0], "'",
				"'", CONS.DB.colNames_Tokens_full[1], "'",
				"'", CONS.DB.colNames_Tokens_full[2], "'",
				"'", CONS.DB.colNames_Tokens_full[3], "'",
				"'", CONS.DB.colNames_Tokens_full[4], "'",
				"'", CONS.DB.colNames_Tokens_full[5], "'",
				
				"'", CONS.DB.colNames_Tokens_full[6], "'",
				"'", CONS.DB.colNames_Tokens_full[7], "'",
				"'", CONS.DB.colNames_Tokens_full[8], "'",
				"'", CONS.DB.colNames_Tokens_full[9], "'",
				"'", CONS.DB.colNames_Tokens_full[10], "'",
				
				"'", CONS.DB.colNames_Tokens_full[11], "'",
				"'", CONS.DB.colNames_Tokens_full[12], "'",
				"'", CONS.DB.colNames_Tokens_full[13], "'",
				"'", CONS.DB.colNames_Tokens_full[14], "'",
				"'", CONS.DB.colNames_Tokens_full[15], "'",
				"'", CONS.DB.colNames_Tokens_full[16], "'",
				"'", CONS.DB.colNames_Tokens_full[17], "'",
				
			")",
			"VALUES",
			"(",
				"'", "%s", "'",
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
