package Controller;

import view.FereastraAplicatie;

public class Principal {

	public static void main(String[] args) {			
		FereastraAplicatie aplicatie = FereastraAplicatie.getInstance();
		aplicatie.start_aplicatie();
	}

}
