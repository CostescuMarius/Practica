package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Depozit;
import Model.Jucator;
import Model.Produs;

public class ScenaPiata extends JPanel{
	private BufferedImage image1;
	private BufferedImage image2;
	ButoanePiata bp = new ButoanePiata();
	private Depozit depozit_piata = Depozit.getInstance();
	private JTable table;
	private JLabel l_bani;
	Jucator player;
	
	private JList<String> cos_cumparaturi;
	
	String[] sumar_produse = new String[100];
	String[] produse_cumparate = new String[100];
	int index = 0;
	int[] cantitate = new int[100];
	
	Vector<Vector<Produs>> data;
	
	public ScenaPiata()
	{
		player = Jucator.getInstance();
		
		this.setLayout(null);
		
		adaugare_imagini();
		adaugare_bani();
		
		adaugare_lista();
		
		adaugare_tabel();
	    adaugare_actiune_selectie_tabel();
		
		bp.adaugare_butoane(this);
	}
	
	private void adaugare_imagini()
	{
	    try {                
	        image1 = ImageIO.read(new File("Materiale\\interior_piata.png"));
	    } catch (IOException ex) {
	    }
	    
	    try {                
	        image2 = ImageIO.read(new File("Materiale\\money.png"));
	    } catch (IOException ex) {
	    }
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image1, 0, 0, 1560, 870, this);
		g.drawImage(image2, 1488, 10, 50, 50, this);
	}
	
	public JList<String> getCosCumparaturi()
	{
		return this.cos_cumparaturi;
	}
	
	private void adaugare_bani()
	{
		l_bani = new JLabel("" + player.getBani());
		l_bani.setBounds(1440, 10, 100, 50);
		l_bani.setForeground(Color.orange);
		l_bani.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		this.add(l_bani);
	}
	
	private void adaugare_lista()
	{
		cos_cumparaturi = new JList<>();
		cos_cumparaturi.setBackground(new Color(221, 221, 255));
		
		JScrollPane scrpane = new JScrollPane(cos_cumparaturi);

		scrpane.setBounds(1200, 61, 300, 700);
		scrpane.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		
		this.add(scrpane);
	}
	
	private void adaugare_tabel()
	{
		data = new Vector<>();
		
		update_data_tabel();
		
		Vector<String> nume = new Vector<>();
		nume.add("Denumire produs                           "
				+ "                           Cantitate Piata                        "
				+ "                           Cantitate Player                       "
				+ "                           Pret Actual                            " 
				+ "                           Pret Cumparere");
		
		TableModel model = new DefaultTableModel(data, nume)
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};
		
		table = new JTable(model);
		
		table.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		table.setSelectionBackground(new Color(144, 238, 144));
		table.setBackground(new Color(221, 221, 255));
		
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
	    renderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		
	    JScrollPane scrpane = new JScrollPane(table);
	    scrpane.setBounds(60, 61, 1080, 700);
	    scrpane.getViewport().setBackground(new Color(221, 221, 255));
	    scrpane.setBorder(BorderFactory.createLineBorder(Color.black, 3));
	    
		this.add(scrpane);
	}
	
	private void adaugare_actiune_selectie_tabel() {
		table.addMouseListener(new MouseAdapter() {
		    @Override    
		    public void mouseClicked(MouseEvent evt) {
		    	if ((evt.getClickCount() == 2) && (table.getSelectedRow() != -1)
		    			&& (player.getBani() - data.get(table.getSelectedRow()).get(0).getPretActual() >= 0))
		    	{
		    		player.setBani(player.getBani() - data.get(table.getSelectedRow()).get(0).getPretActual());
		    		
		    		update_bani();
			    	
		    		update_lista();

		    		Produs p_selectat = data.get(table.getSelectedRow()).get(0);
		    		
		    		for(Produs produs : depozit_piata.getEvidenta())
		    		{
		    			if(produs.getDenumire().equals(p_selectat.getDenumire()))
		    			{
		    				produs.setCantitatePlayer(produs.getCantitatePlayer() + 1);
		    				produs.setCantitatePiata(produs.getCantitatePiata() - 1);
		    				produs.setPretCumparere(produs.getPretActual());
		    			}
		    		}
		    		
		    		update_table();
		    	}
		    }
		});
	}

	public void update_table()
	{
		data.removeAllElements();
		
		update_data_tabel();

		((AbstractTableModel) table.getModel()).fireTableDataChanged();
	}
	
	private void update_data_tabel()
	{
		for(Produs produs : depozit_piata.getEvidenta())
		{
			if(produs.afisare_in_piata() != null)
			{
				Vector<Produs> coloana = new Vector<>();
				coloana.add(produs);
				data.add(coloana);
			}
		}
		
		//bubble_sort();
	}
	
	private void istoric_cumparaturi()
	{
		int gasit = -1;
		for(int i = 0; i <= index; i++)
		{
    		if(data.get(table.getSelectedRow()).get(0).getDenumire().equals(produse_cumparate[i]))
    		{
    			gasit = i;
    		}
		}
		
		if(gasit != -1)
		{
			cantitate[gasit]++;
			produse_cumparate[gasit] = data.get(table.getSelectedRow()).get(0).getDenumire();
    		sumar_produse[gasit] = "Denumire: " + produse_cumparate[gasit]
    				+ "   " + "Cantitate: " + cantitate[gasit];
		}
		else
		{
    		cantitate[index]++;
    		produse_cumparate[index] = data.get(table.getSelectedRow()).get(0).getDenumire();
    		sumar_produse[index] = "Denumire: " + produse_cumparate[index]
    				+ "   " + "Cantitate: " + cantitate[index];
		}
		index++;
	}
	
	public void curatare_lista()
	{
		Vector<String> sumar_produse_final = new Vector<>();
		cos_cumparaturi.setListData(sumar_produse_final);
	}
	
	private void update_lista()
	{
		istoric_cumparaturi();
		
		Vector<String> sumar_produse_final = new Vector<>();

		for(String produs_particular : sumar_produse)
		{
			if(produs_particular != null)
			{
				sumar_produse_final.add(produs_particular);
			}
		}
		cos_cumparaturi.setListData(sumar_produse_final);
	}
	
	public void update_bani()
	{
		l_bani.setText("" + player.getBani());
	}
}
