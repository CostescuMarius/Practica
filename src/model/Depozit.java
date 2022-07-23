package Model;

import java.util.Vector;

public class Depozit {
	
	private static Depozit instance = null;
	private Depozit() { }
	public static Depozit getInstance() {
    if(instance == null) {
        instance = new Depozit();
     }
     return instance;
	}
	
	Vector<Produs> evidenta = new Vector<>();
	
	public void set_data(Depozit depozit_temp)
	{
		this.evidenta = depozit_temp.evidenta;
	}
	
	public void add_element(Produs produs)
	{
		evidenta.add(produs);
	}
	
	public void remove_element(Produs produs)
	{
		evidenta.remove(produs);
	}
	
	public void afisare()
	{
		for (Produs p : evidenta) {
		    System.out.println(p);
		}
	}
	
	public Vector<Produs> getEvidenta()
	{
		return evidenta;
	}
	
	public Produs getProdusCuDenumirea(String denumire)
	{
		for(Produs p : evidenta)
		{
			if(p.getDenumire().equals(denumire))
			{
				return p;
			}
		}
		
		return null;
	}
	
	public void setEvidenta(Vector<Produs> produse)
	{
		evidenta = produse;
	}
}
