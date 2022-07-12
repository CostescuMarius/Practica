package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Jucator;

public class ScenaJocNou extends JPanel{
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private JLabel l_bani;
	private JLabel l_masina;
	private JLabel l_ziua;
	
	JLabel l_marfa = new JLabel();
	
	Jucator player;
	
	private boolean transport_in_desfasurare;
	private boolean marfa_preluata;
	
	Timer timer1;
	Timer timer2;
	
	ButoaneJocNou bjn = new ButoaneJocNou();
	
	public ScenaJocNou()
	{
		player = Jucator.getInstance();
		
		adaugare_imagini();
	    
		this.setLayout(null);
		this.setVisible(true);
		
		adaugare_masina();
		adaugare_bani();
		adaugare_zi();
		
		bjn.adaugare_butoane(this);
		
		transport_in_desfasurare = false;
		marfa_preluata = false;
	}
	
	public boolean getStareTransport()
	{
		return this.transport_in_desfasurare;
	}
	
	public boolean getStarePreluareMarfa()
	{
		return this.marfa_preluata;
	}
	
	public void setStarePreluareMarfa(boolean stare)
	{
		this.marfa_preluata = stare;
	}
	
	private void adaugare_imagini()
	{
	    try {                
	        image1 = ImageIO.read(new File("Materiale\\oras.jpg"));
	    } catch (IOException ex) {
	    }

	    try {                
	        image2 = ImageIO.read(new File("Materiale\\money.png"));
	    } catch (IOException ex) {
	    }
	    
	    try {                
	        image3 = ImageIO.read(new File("Materiale\\masina.png"));
	    } catch (IOException ex) {
	    }
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image1, 0, 0, 1560, 870, this);
		g.drawImage(image2, 1480, 10, 50, 50, this);	
	}
	
	private void adaugare_masina()
	{
		l_masina = new JLabel("");
		l_masina.setOpaque(false);
		l_masina.setBounds(50, 364, 50, 50);
		
		Image image = image3.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		l_masina.setIcon(icon);
		
		this.add(l_masina);
	}
	
	private void adaugare_bani()
	{
		l_bani = new JLabel("" + player.getBani());
		l_bani.setBounds(1430, 10, 100, 50);
		l_bani.setForeground(Color.orange);
		l_bani.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(l_bani);
	}
	
	private void adaugare_zi()
	{
		l_ziua = new JLabel("Ziua: " + player.getZiua());
		l_ziua.setBounds(20, 8, 100, 50);
		l_ziua.setForeground(Color.orange);
		l_ziua.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		this.add(l_ziua);
	}
	
	public void update_bani()
	{
		l_bani.setText("" + player.getBani());
	}
	
	public void update_zi()
	{
		l_ziua.setText("Ziua: " + player.getZiua());
	}
	
	class MiscareDus extends TimerTask {
	    public void run() 
	    {
	    	if(transport_in_desfasurare == false)
	    	{
				l_marfa.setBackground(Color.orange);
				l_marfa.setOpaque(true);
				l_marfa.setBounds(980, 760, 15, 15);
				ScenaJocNou.this.add(l_marfa);
	    	}
			
	    	transport_in_desfasurare = true;
	    	
	    	Point p = l_masina.getLocation();
	    	
	    	if(p.y < 365)
	    	{
	    		l_masina.setLocation(p.x, p.y + 1);
	    	}
	    	
	    	else if(p.x < 516)
	    	{
	    		l_masina.setLocation(p.x + 1, p.y);
	    	}
	    	
	    	else if(p.y < 770)
	    	{
	    		l_masina.setLocation(p.x, p.y + 1);
	    	}
	    	
	    	else if(p.x < 970)
	    	{
	    		l_masina.setLocation(p.x + 1, p.y);
	    	}
	    	else
	    	{
	    		marfa_preluata = true;
	    		timer1.cancel();

	    		l_marfa.setBackground(new Color(35, 177, 77));
	    	}
	    }
	}
	
	class MiscareIntors extends TimerTask {
	    public void run() 
	    {	
	    	Point p = l_masina.getLocation();
	    	
	    	if(marfa_preluata == true)
	    	{
		    	if(p.x > 565)
		    	{
		    		l_masina.setLocation(p.x - 1, p.y);
		    	}
		    	
		    	else if(p.y > 316)
		    	{
		    		l_masina.setLocation(p.x, p.y - 1);
		    	}
		    	
		    	else if(p.x > 49)
		    	{
		    		l_masina.setLocation(p.x - 1, p.y);
		    	}
		    	
		    	else
		    	{
		    		transport_in_desfasurare = false;
		    		timer2.cancel();
		    	}
	    	}
	    }
	}
	
	public void pornire_transport_dus()
	{
		timer1 = new Timer();
		timer1.schedule(new MiscareDus(), 0, 5);
	}
	
	public void pornire_transport_intors()
	{
		timer2 = new Timer();
		timer2.schedule(new MiscareIntors(), 0, 5);
	}
}
