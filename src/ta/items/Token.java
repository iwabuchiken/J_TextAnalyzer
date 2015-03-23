package ta.items;


public class Token {


	int		id;
	int		cake_id;
	String	created_at;
	String	updated_at;
	String	form;
	String	hin;
	String	hin_1;
	String	hin_2;
	String	hin_3;
	String	katsu_kei;
	String	katsu_kata;
	String	genkei;
	String	yomi;
	String	hatsu;
	int		history_id;
	int		user_id;
	
	String remote_created_at;
	String remote_updated_at;
	
	String symbol;
	
	public Token(Builder builder) {
		// TODO Auto-generated constructor stub

		this.id			= builder.id;
		this.created_at	= builder.created_at;
		this.updated_at	= builder.updated_at;
		this.form		= builder.form;
		this.hin		= builder.hin;
		this.hin_1		= builder.hin_1;
		this.hin_2		= builder.hin_2;
		this.hin_3		= builder.hin_3;
		this.katsu_kei	= builder.katsu_kei;
		this.katsu_kata	= builder.katsu_kata;
		this.genkei		= builder.genkei;
		this.yomi		= builder.yomi;
		this.hatsu		= builder.hatsu;
		this.history_id	= builder.history_id;
		this.user_id	= builder.user_id;
		
		this.cake_id	= builder.cake_id;
		this.remote_created_at	= builder.remote_created_at;
		this.remote_updated_at	= builder.remote_updated_at;
		
		this.symbol		= builder.symbol;
	}

	


	public String getSymbol() {
		return symbol;
	}




	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}




	public int getId() {
		return id;
	}



	public int getCake_id() {
		return cake_id;
	}



	public String getCreated_at() {
		return created_at;
	}



	public String getUpdated_at() {
		return updated_at;
	}



	public String getForm() {
		return form;
	}



	public String getHin() {
		return hin;
	}



	public String getHin_1() {
		return hin_1;
	}



	public String getHin_2() {
		return hin_2;
	}



	public String getHin_3() {
		return hin_3;
	}



	public String getKatsu_kei() {
		return katsu_kei;
	}



	public String getKatsu_kata() {
		return katsu_kata;
	}



	public String getGenkei() {
		return genkei;
	}



	public String getYomi() {
		return yomi;
	}



	public String getHatsu() {
		return hatsu;
	}



	public int getHistory_id() {
		return history_id;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setCake_id(int cake_id) {
		this.cake_id = cake_id;
	}



	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}



	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}



	public void setForm(String form) {
		this.form = form;
	}



	public void setHin(String hin) {
		this.hin = hin;
	}



	public void setHin_1(String hin_1) {
		this.hin_1 = hin_1;
	}



	public void setHin_2(String hin_2) {
		this.hin_2 = hin_2;
	}



	public void setHin_3(String hin_3) {
		this.hin_3 = hin_3;
	}



	public void setKatsu_kei(String katsu_kei) {
		this.katsu_kei = katsu_kei;
	}



	public void setKatsu_kata(String katsu_kata) {
		this.katsu_kata = katsu_kata;
	}



	public void setGenkei(String genkei) {
		this.genkei = genkei;
	}



	public void setYomi(String yomi) {
		this.yomi = yomi;
	}



	public void setHatsu(String hatsu) {
		this.hatsu = hatsu;
	}



	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getRemote_created_at() {
		return remote_created_at;
	}



	public String getRemote_updated_at() {
		return remote_updated_at;
	}



	public void setRemote_created_at(String remote_created_at) {
		this.remote_created_at = remote_created_at;
	}



	public void setRemote_updated_at(String remote_updated_at) {
		this.remote_updated_at = remote_updated_at;
	}
	
	public static class Builder {

		String symbol;
		int		id;
		int		cake_id;
		String	created_at;
		String	updated_at;
		String	form;
		String	hin;
		String	hin_1;
		String	hin_2;
		String	hin_3;
		String	katsu_kei;
		String	katsu_kata;
		String	genkei;
		String	yomi;
		String	hatsu;
		int		history_id;
		int		user_id;

		String remote_created_at;
		String remote_updated_at;

		public Token build() {
			
			return new Token(this);
			
		}
		
		
		
		public Builder setSymbol(String symbol) {
			this.symbol = symbol; return this;
		}



		public Builder setId(int id) {
			this.id = id; return this;
		}
		public Builder setCake_id(int cake_id) {
			this.cake_id = cake_id; return this;
		}
		public Builder setCreated_at(String created_at) {
			this.created_at = created_at; return this;
		}
		public Builder setUpdated_at(String updated_at) {
			this.updated_at = updated_at; return this;
		}
		public Builder setForm(String form) {
			this.form = form; return this;
		}
		public Builder setHin(String hin) {
			this.hin = hin; return this;
		}
		public Builder setHin_1(String hin_1) {
			this.hin_1 = hin_1; return this;
		}
		public Builder setHin_2(String hin_2) {
			this.hin_2 = hin_2; return this;
		}
		public Builder setHin_3(String hin_3) {
			this.hin_3 = hin_3; return this;
		}
		public Builder setKatsu_kei(String katsu_kei) {
			this.katsu_kei = katsu_kei; return this;
		}
		public Builder setKatsu_kata(String katsu_kata) {
			this.katsu_kata = katsu_kata; return this;
		}
		public Builder setGenkei(String genkei) {
			this.genkei = genkei; return this;
		}
		public Builder setYomi(String yomi) {
			this.yomi = yomi; return this;
		}
		public Builder setHatsu(String hatsu) {
			this.hatsu = hatsu; return this;
		}
		public Builder setHistory_id(int history_id) {
			this.history_id = history_id; return this;
		}
		public Builder setUser_id(int user_id) {
			this.user_id = user_id; return this;
		}

		public Builder setRemote_created_at(String remote_created_at) {
			this.remote_created_at = remote_created_at; return this;
		}

		public Builder setRemote_updated_at(String remote_updated_at) {
			this.remote_updated_at = remote_updated_at; return this;
		}

		
	}//public static class Builder



}
