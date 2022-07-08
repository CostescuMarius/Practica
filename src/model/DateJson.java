package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DateJson implements Serializable{
	final String FileName = "src//date.json";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void scriere_date_persoana()
	{
		Depozit produse = Depozit.getInstance();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		
		 try(FileWriter fw = new FileWriter(FileName)){
			gson.toJson(produse, fw);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void citire_date_persoana()
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		Depozit depozit_temp = Depozit.getInstance();
		
		try(FileReader fr = new FileReader(FileName)) {
			depozit_temp.set_data(gson.fromJson(fr, Depozit.class));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}
}
