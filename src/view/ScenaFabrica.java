package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Model.Depozit;
import Model.Produs;

import javax.swing.JLabel;

public class ScenaFabrica extends JPanel{
	private JPanel p_zona_lucru;
	private JComboBox<String> produs;
	private JLabel l_componenta1;
	private JLabel l_componenta2;
	
	String produs_selectat;
	ButoaneFabrica bf = new ButoaneFabrica(this);
	
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
		p_zona_lucru.setBackground(new Color(128, 0, 255));
		
	    String denumiri_produse[] = {"-", "paine", "suc" ,"salata", "salam"};
	    
	    produs = new JComboBox<>(denumiri_produse); 
	    produs.setBounds(250, 220, 100, 30);
	    
	    produs.setSelectedIndex(0);
	    produs_selectat = (String) produs.getSelectedItem();
	    
	    adaugare_actiune_producere_produs();
	    
	    p_zona_lucru.add(produs);
	    
	    l_componenta1 = new JLabel("");
	    l_componenta1.setFont(new Font("Times New Roman", Font.BOLD, 22));
	    l_componenta1.setBounds(600, 150, 300, 50);
	    p_zona_lucru.add(l_componenta1);
	    
	    l_componenta2 = new JLabel("");
	    l_componenta2.setFont(new Font("Times New Roman", Font.BOLD, 22));
	    l_componenta2.setBounds(600, 290, 300, 50);
	    p_zona_lucru.add(l_componenta2);
	    
		this.add(p_zona_lucru);
	}
	
	public String getProdusSelectat()
	{
		return produs_selectat;
	}
	
	private void adaugare_actiune_producere_produs()
	{
		produs.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        produs_selectat = (String) produs.getSelectedItem();
		        
		        update_produse_din_fabrica();
		    }
		});
	}
	
	public void update_produse_din_fabrica()
	{
		int cantitate1 = 0;
		int cantitate2 = 0;
		
		if(produs_selectat == "-")
		{
			l_componenta1.setText("-            - / -");
			l_componenta2.setText("-            - / -");
		}
		
		else if(produs_selectat == "paine")
		{
			for(Produs p : Depozit.getInstance().getEvidenta())
			{
				if(p.getDenumire().equals("faina"))
				{
					cantitate1 = p.getCantitatePlayer();
				}
				
				else if(p.getDenumire().equals("apa"))
				{
					cantitate2 = p.getCantitatePlayer();
				}
			}
			
			l_componenta1.setText("faina         " + cantitate1 + " / 1");
			l_componenta2.setText("apa           " + cantitate2 + " / 1");
		}
		
		else if(produs_selectat == "suc")
		{
			for(Produs p : Depozit.getInstance().getEvidenta())
			{
				if(p.getDenumire().equals("apa"))
				{
					cantitate1 = p.getCantitatePlayer();
				}
				
				else if(p.getDenumire().equals("arome"))
				{
					cantitate2 = p.getCantitatePlayer();
				}
			}
			
			l_componenta1.setText("apa            " + cantitate1 + " / 1");
			l_componenta2.setText("arome          " + cantitate2 + " / 1");
		}
		
		else if(produs_selectat == "salata")
		{
			for(Produs p : Depozit.getInstance().getEvidenta())
			{
				if(p.getDenumire().equals("rosii"))
				{
					cantitate1 = p.getCantitatePlayer();
				}
				
				else if(p.getDenumire().equals("castraveti"))
				{
					cantitate2 = p.getCantitatePlayer();
				}
			}

			l_componenta1.setText("rosii          " + cantitate1 + " / 1");
			l_componenta2.setText("castraveti     " + cantitate2 + " / 1");
		}
		
		else if(produs_selectat == "salam")
		{
			for(Produs p : Depozit.getInstance().getEvidenta())
			{
				if(p.getDenumire().equals("carne"))
				{
					cantitate1 = p.getCantitatePlayer();
				}
				
				else if(p.getDenumire().equals("condimente"))
				{
					cantitate2 = p.getCantitatePlayer();
				}
			}
			
			l_componenta1.setText("carne          " + cantitate1 + " / 1");
			l_componenta2.setText("condimente     " + cantitate2 + " / 1");
		}
		
		if(cantitate1 > 0)
		{
			l_componenta1.setForeground(Color.green);
		}
		
		else
		{
			l_componenta1.setForeground(Color.red);
		}
		
		if(cantitate2 > 0)
		{
			l_componenta2.setForeground(Color.green);
		}
		
		else
		{
			l_componenta2.setForeground(Color.red);
		}
	}
}
