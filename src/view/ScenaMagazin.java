package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Depozit;
import model.Produs;

public class ScenaMagazin extends JPanel{
	private JComboBox<String> raft;
	private JList<String> produse_detinute;
	private JList<String> produse_magazin;
	private JLabel l_raft;
	private JLabel l_depozit;
	
	Depozit depozit;
	ButoaneMagazin bm = new ButoaneMagazin(this);
	String raft_selectat;
	
	public ScenaMagazin()
	{
		depozit = Depozit.getInstance();
		
		this.setBackground(Color.orange);
		this.setLayout(null);
		
		bm.adaugare_butoane(this);
		
		adaugare_denumiri_sectiuni();
		adaugare_selectare_raft();

		adaugare_produse_din_magazin();
		
		adaugare_produse_detinute();
	}
	
	private void adaugare_denumiri_sectiuni()
	{
		l_raft = new JLabel("Alegeti raftul: ");
		l_raft.setFont(new Font("Times New Roman", Font.BOLD, 22));
		l_raft.setBounds(60, 45, 150, 50);
		
		l_depozit = new JLabel("Produse din depozit: ");
		l_depozit.setFont(new Font("Times New Roman", Font.BOLD, 22));
		l_depozit.setBounds(60, 500, 250, 50);
		
		this.add(l_raft);
		this.add(l_depozit);
	}
	
	private void adaugare_selectare_raft()
	{
	    String denumiri_rafturi[] = {"Toate", "Bauturi", "Fructe", "Legume", "Alimente", "Materii prime"};
	    
	    raft = new JComboBox<>(denumiri_rafturi); 
	    raft.setBounds(205, 55, 100, 30);
	    
	    raft.setSelectedIndex(0);
	    raft_selectat = (String) raft.getSelectedItem();
	    
	    adaugare_actiune_selectare_raft();
	    
	    this.add(raft);
	}
	
	private void adaugare_actiune_selectare_raft()
	{
		raft.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        raft_selectat = (String) raft.getSelectedItem();
		        
		        update_produse_din_magazin(raft_selectat);
		    }
		});
	}
	
	public String getRaftSelectat()
	{
		return this.raft_selectat;
	}
	
	private void adaugare_produse_din_magazin()
	{
		produse_magazin = new JList<>();
		
		JScrollPane scrp = new JScrollPane(produse_magazin);
		scrp.setBounds(50, 100, 1450, 400);
		
		this.add(scrp);
	}
	
	public void update_produse_din_magazin(String categorie)
	{
		Vector<String> produse_magazin_categorie = new Vector<>();
		
		if(categorie.equals("Toate"))
		{
			for(Produs produs : depozit.getEvidenta())
			{
				if(produs.getCantitateMagazin() > 0)
				{
					String produs_final = "Denumire: " + produs.getDenumire() + "     Cantitate: " + produs.getCantitateMagazin();
					produse_magazin_categorie.add(produs_final);
				}
			}
		}
		else
		{
			for(Produs produs : depozit.getEvidenta())
			{
				if(produs.getCantitateMagazin() > 0 && produs.getCategorie().equals(categorie))
				{
					String produs_final = "Denumire: " + produs.getDenumire() + "     Cantitate: " + produs.getCantitateMagazin();
					produse_magazin_categorie.add(produs_final);
				}
			}
		}
		
		Collections.sort(produse_magazin_categorie);
		
		produse_magazin.setListData(produse_magazin_categorie);
	}
	
	private void adaugare_produse_detinute()
	{
		produse_detinute = new JList<>();
		
		JScrollPane scrp = new JScrollPane(produse_detinute);
		scrp.setBounds(50, 550, 1230, 250);
		
		this.add(scrp);
	}
	
	public String getDenumireProdusSelectat()
	{
		String produs_detinut = produse_detinute.getSelectedValue();
		
		if(produs_detinut != null)
		{
			String[] parts = produs_detinut.split("   ");
			return parts[1];
		}
		
		return null;
	}
	
	public void update_produse_detinute()
	{
		Vector<String> produse_detinute_final = new Vector<>();
		
		for(Produs produs : depozit.getEvidenta())
		{
			if(produs.getCantitatePlayer() > 0)
			{
				String produs_final = "Denumire:   " + produs.getDenumire() + "   Cantitate:   " + produs.getCantitatePlayer() +
						"   Pret Producere:   " + produs.getPretProducere() + "   Pret Cumparare:   " + produs.getPretCumparare() + 
						"   Pret Actual:   " + produs.getPretActual();
				produse_detinute_final.add(produs_final);
			}
		}
		
		Collections.sort(produse_detinute_final);
		
		produse_detinute.setListData(produse_detinute_final);
	}
	

}
