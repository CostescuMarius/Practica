package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.Depozit;
import model.Jucator;
import model.Produs;

public class FereastraAplicatie{
	private static FereastraAplicatie instance = null;

	public static FereastraAplicatie getInstance() {
    if(instance == null) {
       instance = new FereastraAplicatie();
    	}
    	return instance;
	}
	   
	private JFrame f;
	JFrame f_esc;
	
	Action escAction;
	private int nr_scena;
	
	CardLayout cardLayout = new CardLayout();
	
	private JPanel p = new JPanel(cardLayout);
	private ScenaJocNou scena2;
	private ScenaPiata scena3;
	private ScenaMagazin scena4;
	
	private ButoaneMeniu b = new ButoaneMeniu(this);
	
	public Jucator referinta_player;
	
	private FereastraAplicatie()
	{	
	}
	
	public void start_aplicatie()
	{
		escAction = new escAction();
		
		referinta_player = Jucator.getInstance();

		f = new JFrame();
		
		p.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escAction");
		p.getActionMap().put("escAction" , escAction);
		
		f.add(p);

		setare_fereastra();

		creare_meniu_principal();
		creare_scena2();
		creare_scena3();
		creare_scena4();
		
		setare_meniu_principal();
	}
	
	private void setare_fereastra()
	{
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setUndecorated(true);
		f.setVisible(true);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void creare_meniu_principal()
	{
		JPanel scena1 = new ScenaMeniu();
		b.adaugare_butoane_meniu(scena1);
		p.add(scena1, "scena1");
	}
	
	public void setare_meniu_principal()
	{
		nr_scena = 1;
		
		cardLayout.show(p, "scena1");
		f.repaint();
		f.revalidate();
	}
	
	private void creare_scena2()
	{
		scena2 = new ScenaJocNou();
		
		p.add(scena2, "scena2");
	}
	
	public void setare_scena2()
	{
		nr_scena = 2;
		
		scena2.update_bani();
		
		cardLayout.show(p, "scena2");
		f.repaint();
		f.revalidate();
	}
	
	public void setare_scena2_transport()
	{
		nr_scena = 2;
		
		scena2.update_bani();
		
		
		if(scena2.getStareTransport() == false)
		{
			scena2.setStarePreluareMarfa(false);
			
			scena2.pornire_transport_dus();
			scena2.pornire_transport_intors();
		}
		
		scena4.update_produse_detinute();
		
		cardLayout.show(p, "scena2");
		f.repaint();
		f.revalidate();
	}
	
	public void setare_scena2_ziua_urmatoare()
	{
		referinta_player.setZiua(referinta_player.getZiua() + 1);
		
		scena2.update_zi();
		
		for(Produs p : Depozit.getInstance().getEvidenta())
		{
			if(p.getCantitateMagazin() > 0)
			{
				referinta_player.setBani(referinta_player.getBani() + p.getCantitateMagazin() * p.getPretActual());
				p.setCantitateMagazin(0);
			}
			
			Random rand = new Random();
			int crestere = rand.nextInt(2);
			
			if(crestere == 0)
			{
				int procent = rand.nextInt(10) + 1;
				if((p.getPretActual() - (procent * p.getPretActual()) / 100) > 0)
				{
					p.setPretActual(p.getPretActual() - (procent * p.getPretActual()) / 100);
				}
			}
			else
			{
				int procent = rand.nextInt(20) + 1;
				p.setPretActual(p.getPretActual() + (procent * p.getPretActual()) / 100);
			}
			
			int cantitate = rand.nextInt(4);
			p.setCantitatePiata(cantitate);
		}
		
		scena2.update_bani();
		scena4.update_produse_detinute();
		scena4.update_produse_din_magazin(scena4.getRaftSelectat());
		scena3.update_table();
		
		cardLayout.show(p, "scena2");
		f.repaint();
		f.revalidate();
	}
	
	private void creare_scena3()
	{
		scena3 = new ScenaPiata();
		
		p.add(scena3, "scena3");
	}
	
	public void setare_scena3()
	{
		nr_scena = 3;
		
		scena3.update_bani();
		
		cardLayout.show(p, "scena3");
		f.repaint();
		f.revalidate();
	}
	
	private void creare_scena4()
	{
		scena4 = new ScenaMagazin();
		
		p.add(scena4, "scena4");
	}
	
	public void setare_scena4()
	{
		nr_scena = 4;
		
		cardLayout.show(p, "scena4");
		f.repaint();
		f.revalidate();
	}
	
	public class escAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e){
			if(nr_scena == 2 || nr_scena == 3 || nr_scena == 4)
			{
				f_esc = new JFrame();
				
				f_esc.setUndecorated(true);
				f_esc.setBounds(635, 325, 250, 250);
				f_esc.setVisible(true);
				
				JPanel p_esc = new JPanel();
				p_esc.setLayout(null);
				p_esc.setBackground(Color.black);
				p_esc.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.white));
				b.adaugare_butoane_escape(p_esc);
				
				f_esc.add(p_esc);
			}
		}
	}
}
