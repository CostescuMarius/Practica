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
	private JButton b_ziua_urmatoare;
	private JButton b_continua;
	
	BufferedImage image1;
	BufferedImage image2;
	
	JFrame f_progres;
	
	public ButoaneJocNou()
	{
		aplicatie = FereastraAplicatie.getInstance();
		
		b_piata = new JButton();
		b_magazin = new JButton();
		b_ziua_urmatoare = new JButton("Ziua urmatoare");
		b_continua = new JButton("Continua");
		
		setare_butoane();
		
		adaugare_imagine();
		
		adaugare_actiune_piata();
		adaugare_actiune_magazin();
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

	    
	    Image image_scaled1 = image1.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
	    b_piata.setIcon(new ImageIcon(image_scaled1));
	    
	    b_magazin.setIcon(new ImageIcon(image2));
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
            	aplicatie.setare_scena4();
            }
        });
	}
	
	private void adaugare_actiune_ziua_urmatoare()
	{
		b_ziua_urmatoare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		adaugare_fereastra_progres();
        		aplicatie.setare_scena2_ziua_urmatoare();
            }
        });
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
	
	private void adaugare_fereastra_progres()
	{
		f_progres = new JFrame();
		f_progres.setUndecorated(true);
		f_progres.setBounds(560, 210, 400, 350);
		f_progres.setVisible(true);
    	
    	JPanel p_progres = new JPanel();
    	p_progres.setBackground(Color.black);
    	p_progres.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
    	p_progres.setLayout(null);
    	
    	int profit = 0;
    	int produse_vandute = 0;
    	for(Produs p : Depozit.getInstance().getEvidenta())
    	{
    		produse_vandute = produse_vandute + p.getCantitateMagazin();
    		
    		if(p.getCantitateMagazin() > 0)
    		{
    			profit = profit + (p.getPretActual() - p.getPretCumparare());
    		}
    	}
    	
    	JLabel l_progres1 = new JLabel("PROGRES");
    	l_progres1.setForeground(Color.white);
    	l_progres1.setFont(new Font("Times New Roman", Font.BOLD, 25));
    	l_progres1.setBounds(130, 40, 120, 50);
    	
    	JLabel l_progres2 = new JLabel();
    	l_progres2.setForeground(Color.white);
    	l_progres2.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	l_progres2.setBounds(10, 100, 300, 50);
    	if(profit > 0)
    	{
    		l_progres2.setText("Profit: " + profit);
    	}
    	else
    	{
    		l_progres2.setText("Profit: 0");
    	}
    	
    	JLabel l_progres3 = new JLabel();
    	l_progres3.setForeground(Color.white);
    	l_progres3.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	l_progres3.setBounds(10, 150, 300, 50);
    	l_progres3.setText("Produse vandute: " + produse_vandute);
    	
    	JLabel l_progres4 = new JLabel();
    	l_progres4.setForeground(Color.white);
    	l_progres4.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	l_progres4.setBounds(10, 200, 300, 50);
    	l_progres4.setText("Bani necesari: " + (1000 - Jucator.getInstance().getBani()));
    	
    	p_progres.add(l_progres1);
    	p_progres.add(l_progres2);
    	p_progres.add(l_progres3);
    	p_progres.add(l_progres4);
    	
    	p_progres.add(b_continua);
    	
    	f_progres.add(p_progres);
	}
}
