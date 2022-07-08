package model;

import view.FereastraAplicatie;

public class Principal {

	public static void main(String[] args) {		
		DateJson date = new DateJson();
		date.citire_date_persoana();
		
		FereastraAplicatie aplicatie = FereastraAplicatie.getInstance();
		aplicatie.start_aplicatie();
	}

}
