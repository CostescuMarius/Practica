package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Depozit;
import model.Produs;

public class ButoaneFabrica {
	private JButton b_inapoi;
	private JButton b_producere;
	
	ScenaFabrica referinta_scenaf;
	Depozit depozit = Depozit.getInstance();
	
	public ButoaneFabrica(ScenaFabrica scenaf)
	{
		referinta_scenaf = scenaf;
		
		b_inapoi = new JButton("Inapoi");
		b_producere = new JButton("Producere");

		setare_butoane();

		adauga_actiune_iesire();
		adauga_actiune_producere();
	}
	
	private void setare_butoane()
	{
		b_inapoi.setOpaque(false);
		b_inapoi.setContentAreaFilled(false);
		b_inapoi.setBorderPainted(false);
		b_inapoi.setFocusPainted(false);
		b_inapoi.setForeground(Color.blue);
		b_inapoi.setFont(new Font("Times New Roman", Font.BOLD, 30));
		b_inapoi.setBorder(null);
		b_inapoi.setBounds(600, 770, 350, 50);
		
		b_producere.setOpaque(false);
		b_producere.setContentAreaFilled(false);
		b_producere.setBorderPainted(false);
		b_producere.setFocusPainted(false);
		b_producere.setForeground(Color.blue);
		b_producere.setFont(new Font("Times New Roman", Font.BOLD, 30));
		b_producere.setBorder(null);
		b_producere.setBounds(1200, 370, 350, 50);
	}
	
	public void adaugare_butoane(JPanel p)
	{
		p.add(b_inapoi);
		
		p.add(b_producere);
	}
	
	private void adauga_actiune_iesire()
	{
		b_inapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		FereastraAplicatie referinta = FereastraAplicatie.getInstance();
        		referinta.setare_scena2();
            }
        });
	}
	
	private void adauga_actiune_producere()
	{
		b_producere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(referinta_scenaf.getProdusSelectat() == "paine")
            	{
        			Produs paine = depozit.getProdusCuDenumirea("paine");
        			Produs apa = depozit.getProdusCuDenumirea("apa");
        			Produs faina = depozit.getProdusCuDenumirea("faina");
        			
            		if(apa.getCantitatePlayer() > 0 && faina.getCantitatePlayer() > 0)
            		{
            			
            			paine.setCantitatePlayer(paine.getCantitatePlayer() + 1);
            			paine.setPretProducere(apa.getPretCumparare() + faina.getPretCumparare());
            			apa.setCantitatePlayer(apa.getCantitatePlayer() - 1);
            			faina.setCantitatePlayer(faina.getCantitatePlayer() - 1);
            		}
            	}
            	
            	else if(referinta_scenaf.getProdusSelectat() == "suc")
            	{
        			Produs suc = depozit.getProdusCuDenumirea("suc");
        			Produs apa = depozit.getProdusCuDenumirea("apa");
        			Produs arome = depozit.getProdusCuDenumirea("arome");
        			
            		if(apa.getCantitatePlayer() > 0 && arome.getCantitatePlayer() > 0)
            		{
            			suc.setCantitatePlayer(suc.getCantitatePlayer() + 1);
            			suc.setPretProducere(apa.getPretCumparare() + arome.getPretCumparare());
            			apa.setCantitatePlayer(apa.getCantitatePlayer() - 1);
            			arome.setCantitatePlayer(arome.getCantitatePlayer() - 1);
            		}
            	}
            	
            	else if(referinta_scenaf.getProdusSelectat() == "salata")
            	{
        			Produs salata = depozit.getProdusCuDenumirea("salata");
        			Produs rosii = depozit.getProdusCuDenumirea("rosii");
        			Produs castraveti = depozit.getProdusCuDenumirea("castraveti");
        			
            		if(rosii.getCantitatePlayer() > 0 && castraveti.getCantitatePlayer() > 0)
            		{
            			
            			salata.setCantitatePlayer(salata.getCantitatePlayer() + 1);
            			salata.setPretProducere(rosii.getPretCumparare() + castraveti.getPretCumparare());
            			rosii.setCantitatePlayer(rosii.getCantitatePlayer() - 1);
            			castraveti.setCantitatePlayer(castraveti.getCantitatePlayer() - 1);
            		}
            	}
            	
            	else if(referinta_scenaf.getProdusSelectat() == "salam")
            	{
        			Produs salam = depozit.getProdusCuDenumirea("salam");
        			Produs carne = depozit.getProdusCuDenumirea("carne");
        			Produs condimente = depozit.getProdusCuDenumirea("condimente");
        			
            		if(carne.getCantitatePlayer() > 0 && condimente.getCantitatePlayer() > 0)
            		{
            			
            			salam.setCantitatePlayer(salam.getCantitatePlayer() + 1);
            			salam.setPretProducere(carne.getPretCumparare() + condimente.getPretCumparare());
            			carne.setCantitatePlayer(carne.getCantitatePlayer() - 1);
            			condimente.setCantitatePlayer(condimente.getCantitatePlayer() - 1);
            		}
            	}
            	
            	referinta_scenaf.update_produse_din_fabrica();
            }
        });
	}
}
