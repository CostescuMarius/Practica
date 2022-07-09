package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ScenaFabrica extends JPanel{
	private JPanel p_zona_lucru;
	private JComboBox<String> produs;

	String produs_selectat;
	ButoaneFabrica bf = new ButoaneFabrica();
	
	public ScenaFabrica()
	{
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		
		bf.adaugare_butoane(this);
		
		adaugare_zona_lucru();
	}
	
	private void adaugare_zona_lucru()
	{
		p_zona_lucru = new JPanel();
		p_zona_lucru.setLayout(null);
		p_zona_lucru.setBounds(300, 170, 950, 500);
		p_zona_lucru.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		
	    String denumiri_produse[] = {"Paine", "Salam"};
	    
	    produs = new JComboBox<>(denumiri_produse); 
	    produs.setBounds(100, 250, 100, 30);
	    
	    produs.setSelectedIndex(0);
	    produs_selectat = (String) produs.getSelectedItem();
		
	    p_zona_lucru.add(produs);
		this.add(p_zona_lucru);
	}
}
