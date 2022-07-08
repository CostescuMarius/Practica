package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Depozit;
import model.Produs;

public class ButoaneMagazin {
	private JButton b_inapoi;
	private JButton b_vinde;
	
	ScenaMagazin referinta;
	
	public ButoaneMagazin(ScenaMagazin m)
	{
		referinta = m;
		b_inapoi = new JButton("Inapoi");
		b_vinde = new JButton("Vinde");
		setare_butoane();
		
		adauga_actiune_iesire();
		adauga_actiune_vinde();
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
		b_inapoi.setBounds(625, 800, 350, 50);
		
		b_vinde.setOpaque(false);
		b_vinde.setContentAreaFilled(false);
		b_vinde.setBorderPainted(false);
		b_vinde.setFocusPainted(false);
		b_vinde.setForeground(Color.blue);
		b_vinde.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b_vinde.setBorder(null);
		b_vinde.setBounds(1400, 600, 80, 25);
	}
	
	public void adaugare_butoane(JPanel p)
	{
		p.add(b_inapoi);
		
		p.add(b_vinde);
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
	
	private void adauga_actiune_vinde()
	{
		b_vinde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Depozit depozit = Depozit.getInstance();
            	
            	for(Produs produs : depozit.getEvidenta())
            	{
            		if(produs.getDenumire().equals(referinta.getDenumireProdusSelectat()))
            		{
            			produs.setCantitateMagazin(produs.getCantitateMagazin() + 1);
            			produs.setCantitatePlayer(produs.getCantitatePlayer() - 1);
            		}
            	}
            	
            	referinta.update_produse_detinute();
            	referinta.update_produse_din_magazin(referinta.getRaftSelectat());
            }
        });
	}
}
