package com.app.treballadors.input_obj;

import com.app.treballadors.model.Serializable;

public class canacces_obj extends Serializable {
	public boolean can;
	public canacces_obj(boolean can) {
		this.can = can;
	}
	public class acces_permes extends canacces_obj{
		public boolean primera;
		public boolean es_ilimitat;

		public acces_permes(boolean primera, boolean es_ilimitat) {
			super(true);
			this.primera = primera;
			this.es_ilimitat = es_ilimitat;
		}
	}
	public class acces_denegat extends canacces_obj{
		public String motiu;

		public acces_denegat(String motiu) {
			super(false);
			this.motiu = motiu;
		}
	}
}
