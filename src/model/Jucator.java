package model;

public class Jucator {
	private int m_bani;
	private int m_ziua;
	
	//singleton
	private static Jucator instance = null;
	private Jucator() 
	{ 
		m_ziua = 1;
	}
	
	public static Jucator getInstance() {
    if(instance == null) {
        instance = new Jucator();
     }
     return instance;
	}
	
	public void setBani(int bani)
	{
		this.m_bani = bani;
	}
	
	public int getBani()
	{
		return this.m_bani;
	}
	
	public void setZiua(int ziua)
	{
		this.m_ziua = ziua;
	}
	
	public int getZiua()
	{
		return this.m_ziua;
	}
}
