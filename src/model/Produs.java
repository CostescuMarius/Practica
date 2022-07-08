package model;

public class Produs {
	private String m_denumire;
	private int m_cantitate_piata;
	private int m_cantitate_player;
	private int m_cantitate_magazin;
	private int m_pret_actual;
	private int m_pret_cumparare;
	private String m_categorie;
	
	public Produs()
	{
		
	}
	
	public Produs(String denumire, int cantitate_piata, int cantitate_player, int cantitate_magazin,
			int pret_a, int pret_c, String categorie)
	{
		this.m_denumire = denumire;
		this.m_cantitate_piata = cantitate_piata;
		this.m_cantitate_player = cantitate_player;
		this.m_cantitate_magazin = cantitate_magazin;
		this.m_pret_actual = pret_a;
		this.m_pret_cumparare = pret_c;
		this.m_categorie = categorie;
	}
	
	public String getDenumire()
	{
		return this.m_denumire;
	}
	
	public void setDenumire(String denumire)
	{
		this.m_denumire = denumire;
	}
	
	public int getCantitatePiata()
	{
		return this.m_cantitate_piata;
	}
	
	public void setCantitatePiata(int cantitate_piata)
	{
		this.m_cantitate_piata = cantitate_piata;
	}
	
	public int getCantitatePlayer()
	{
		return this.m_cantitate_player;
	}
	
	public void setCantitatePlayer(int cantitate_player)
	{
		this.m_cantitate_player = cantitate_player;
	}
	
	public int getCantitateMagazin()
	{
		return this.m_cantitate_magazin;
	}
	
	public void setCantitateMagazin(int cantitate_magazin)
	{
		this.m_cantitate_magazin = cantitate_magazin;
	}
	
	public int getPretActual()
	{
		return this.m_pret_actual;
	}
	
	public void setPretActual(int pret_a)
	{
		this.m_pret_actual = pret_a;
	}
	
	public int getPretCumparare()
	{
		return this.m_pret_cumparare;
	}
	
	public void setPretCumparere(int pret_c)
	{
		this.m_pret_cumparare = pret_c;
	}
	
	public String getCategorie()
	{
		return this.m_categorie;
	}
	
	public void setCategorie(String categorie)
	{
		this.m_categorie = categorie;
	}
	
	public String afisare_in_piata()
	{
		if(this.m_cantitate_piata > 0)
		{
			return "";
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		return "" + this.m_denumire + "    " + this.m_cantitate_piata + "   " + this.m_cantitate_player +
				"    " + this.m_pret_actual + "    " + this.m_pret_cumparare + "    ";
	}
}
