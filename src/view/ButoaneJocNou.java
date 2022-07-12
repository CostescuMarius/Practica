package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Depozit;
import model.Jucator;
import model.Produs;

public class ButoaneJocNou {
	FereastraAplicatie aplicatie;
	
	private JButton b_piata;
	private JButton b_magazin;
	private JButton b_fabrica;
	private JButton b_ziua_urmatoare;
	private JButton b_continua;
	
	BufferedImage image1;
	BufferedImage image2;
	BufferedImage image3;
	
	JFrame f_progres;
	
	public ButoaneJocNou()
	{
		aplicatie = FereastraAplicatie.getInstance();
		
		b_piata = new JButton();
		b_magazin = new JButton();
		b_fabrica = new JButton();
		b_ziua_urmatoare = new JButton("Ziua urmatoare");
		b_continua = new JButton("Continua");
		
		setare_butoane();
		
		adaugare_imagine();
		
		adaugare_actiune_piata();
		adaugare_actiune_magazin();
		adaugare_actiune_fabrica();
		adaugare_actiune_ziua_urmatoare();
		adaugare_actiune_continua();
	}
	
	private void setare_butoane()
	{
		b_piata.setFocusPainted(false);
		b_piata.setBorder(null);
		b_piata.setBounds(1060, 630, 300, 150);
		
		b_magazin.setFocusPainted(false);
		b_magazin.setBorder(null);
		b_magazin.setBounds(20, 120, 150, 150);
		
		b_fabrica.setFocusPainted(false);
		b_fabrica.setBorder(null);
		b_fabrica.setBounds(900, 120, 150, 150);
		
		b_ziua_urmatoare.setFocusPainted(false);
		b_ziua_urmatoare.setBorder(null);
		b_ziua_urmatoare.setBackground(new Color(35, 177, 77));
		b_ziua_urmatoare.setForeground(Color.red);
		b_ziua_urmatoare.setFont(new Font("Times New Roman", Font.BOLD, 25));
		b_ziua_urmatoare.setBounds(30, 800, 170, 50);
		
		b_continua.setFocusPainted(false);
		b_continua.setBackground(Color.black);
		b_continua.setForeground(Color.white);
		b_continua.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b_continua.setBorder(null);
		b_continua.setBounds(140, 280, 100, 30);
	}
	
	public void adaugare_butoane(JPanel p)
	{
		p.add(b_piata);
		
		p.add(b_magazin);
		
		p.add(b_fabrica);
		
		p.add(b_ziua_urmatoare);
	}
	
	private void adaugare_imagine()
	{
	    try {                
	        image1 = ImageIO.read(new File("Materiale\\piata.png"));
	    } catch (IOException ex) {
	    }
	    
	    try {                
	        image2 = ImageIO.read(new File("Materiale\\magazin.png"));
	    } catch (IOException ex) {
	    }

	    try {                
	        image3 = ImageIO.read(new File("Materiale\\fabrica.png"));
	    } catch (IOException ex) {
	    }
	    
	    Image image_scaled1 = image1.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
	    b_piata.setIcon(new ImageIcon(image_scaled1));
	    
	    b_magazin.setIcon(new ImageIcon(image2));
	    
	    b_fabrica.setIcon(new ImageIcon(image3));
	}
	
	private void adaugare_actiune_piata()
	{
		b_piata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	aplicatie.setare_scena3();
            }
        });
	}
	
	private void adaugare_actiune_magazin()
	{
		b_magazin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(aplicatie.getScena2().getStareTransport() == false)
            	{
            		aplicatie.setare_scena4();
            	}
            }
        });
	}
	
	private void adaugare_actiune_fabrica()
	{
		b_fabrica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(aplicatie.getScena2().getStareTransport() == false)
            	{
            		aplicatie.setare_scena5();
            	}
            }
        });
	}
	
	private void adaugare_actiune_ziua_urmatoare()
	{
		b_ziua_urmatoare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	adaugare_fereastra_progres_si_vanzare_produse();
        		aplicatie.setare_scena2();
            }
        });
	}
	
	Jucator referinta_player = Jucator.getInstance();
	private void adaugare_fereastra_progres_si_vanzare_produse()
	{
    	int bani_produsi = 0;
    	int bani_inainte = 0;
    	int bani_dupa = 0;
    	int produse_vandute = 0;
    	
		referinta_player.setZiua(referinta_player.getZiua() + 1);
    	
    	for(Produs p : Depozit.getInstance().getEvidenta())
    	{
			if(p.getCantitateMagazin() > 0)
			{
	    		produse_vandute = produse_vandute + p.getCantitateMagazin();
	    		
	    		bani_inainte = referinta_player.getBani();
				referinta_player.setBani(referinta_player.getBani() + p.getCantitateMagazin() * p.getPretActual());
				bani_dupa = referinta_player.getBani();
				
				p.setCantitateMagazin(0);
			}
			
			Random rand = new Random();
			int crestere = rand.nextInt(2);
			
			if(crestere == 0)
			{
				int procent = rand.nextInt(10) + 5;
				if((p.getPretActual() - (procent * p.getPretActual()) / 100) > 0)
				{
					p.setPretActual(p.getPretActual() - (procent * p.getPretActual()) / 100);
				}
			}
			else
			{
				int procent = rand.nextInt(30) + 10;
				p.setPretActual(p.getPretActual() + (procent * p.getPretActual()) / 100);
			}
			
			int cantitate = rand.nextInt(4);
			p.setCantitatePiata(cantitate);
    	}
    	
		if(referinta_player.getBani() >= 1000)
		{
			adaugare_fereastra_final_joc();
		}
    	
		else
		{
			f_progres = new JFrame();
			f_progres.setUndecorated(true);
			f_progres.setBounds(560, 210, 400, 350);
			f_progres.setVisible(true);
	    	
	    	JPanel p_progres = new JPanel();
	    	p_progres.setBackground(Color.black);
	    	p_progres.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
	    	p_progres.setLayout(null);
	    	
	    	JLabel l_progres1 = new JLabel("PROGRES");
	    	l_progres1.setForeground(Color.white);
	    	l_progres1.setFont(new Font("Times New Roman", Font.BOLD, 25));
	    	l_progres1.setBounds(130, 40, 120, 50);
	    	
	    	JLabel l_progres2 = new JLabel();
	    	l_progres2.setForeground(Color.white);
	    	l_progres2.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    	l_progres2.setBounds(10, 100, 300, 50);
	    	
	    	bani_produsi = bani_dupa - bani_inainte;
	    	l_progres2.setText("Bani produsi: " + bani_produsi);
	    	
	    	JLabel l_progres3 = new JLabel();
	    	l_progres3.setForeground(Color.white);
	    	l_progres3.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    	l_progres3.setBounds(10, 150, 300, 50);
	    	l_progres3.setText("Produse vandute: " + produse_vandute);
	    	
	    	JLabel l_progres4 = new JLabel();
	    	l_progres4.setForeground(Color.white);
	    	l_progres4.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    	l_progres4.setBounds(10, 200, 300, 50);
	    	l_progres4.setText("Bani necesari: " + (1000 - referinta_player.getBani()));
	    	
	    	p_progres.add(l_progres1);
	    	p_progres.add(l_progres2);
	    	p_progres.add(l_progres3);
	    	p_progres.add(l_progres4);
	    	
	    	p_progres.add(b_continua);
	    	
	    	f_progres.add(p_progres);
		}
	}
	
	private void adaugare_fereastra_final_joc()
	{
		JFrame f_final = new JFrame();
		f_final.setUndecorated(true);
		f_final.setBounds(560, 260, 400, 400);
		f_final.setVisible(true);
    	
    	JPanel p_final = new JPanel();
    	p_final.setBackground(Color.black);
    	p_final.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
    	p_final.setLayout(null);
    	
    	JLabel l_final = new JLabel("Ai strans cele 1000 de bancnote in " + referinta_player.getZiua() + " zile");
    	l_final.setForeground(Color.white);
    	l_final.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	l_final.setBounds(15, 100, 385, 140);
    	
    	p_final.add(l_final);
    	
    	JButton b_exit = new JButton("Exit");
    	b_exit.setFocusPainted(false);
    	b_exit.setBackground(Color.black);
    	b_exit.setForeground(Color.white);
    	b_exit.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	b_exit.setBorder(null);
    	b_exit.setBounds(145, 330, 100, 30);
    	
		b_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
    	
    	p_final.add(b_exit);
    	
    	f_final.add(p_final);
	}
	
	private void adaugare_actiune_continua()
	{
		b_continua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	f_progres.dispatchEvent(new WindowEvent(f_progres, WindowEvent.WINDOW_CLOSING));
            }
        });
	}
	
}
