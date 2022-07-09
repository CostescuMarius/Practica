package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButoaneFabrica {
	private JButton b_inapoi;
	
	public ButoaneFabrica()
	{
		b_inapoi = new JButton("Inapoi");

		setare_butoane();

		adauga_actiune_iesire();
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
	}
	
	public void adaugare_butoane(JPanel p)
	{
		p.add(b_inapoi);
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
}
