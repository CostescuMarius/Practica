package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButoanePiata {
	private JButton b_finalizare;
	
	public ButoanePiata()
	{
		b_finalizare = new JButton("Finalizare comanda");
		
		setare_butoane();
		
		adauga_actiune_iesire();
	}
	
	private void setare_butoane()
	{
		b_finalizare.setOpaque(false);
		b_finalizare.setContentAreaFilled(false);
		b_finalizare.setBorderPainted(false);
		b_finalizare.setFocusPainted(false);
		b_finalizare.setForeground(Color.orange);
		b_finalizare.setFont(new Font("Times New Roman", Font.BOLD, 30));
		b_finalizare.setBorder(null);
		b_finalizare.setBounds(625, 770, 350, 50);
	}
	
	public void adaugare_butoane(JPanel p)
	{
		p.add(b_finalizare);
	}
	
	private void adauga_actiune_iesire()
	{
		b_finalizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		FereastraAplicatie referinta = FereastraAplicatie.getInstance();
        		referinta.setare_scena2_transport();
            }
        });
	}
}
