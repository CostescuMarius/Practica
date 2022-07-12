package Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Depozit;

public class DateJson implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void scriere_date_persoana(String filename)
	{
		Depozit produse = Depozit.getInstance();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		
		 try(FileWriter fw = new FileWriter(filename)){
			gson.toJson(produse, fw);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void citire_date_persoana(String filename)
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		Depozit depozit_temp = Depozit.getInstance();
		
		try(FileReader fr = new FileReader(filename)) {
			depozit_temp.set_data(gson.fromJson(fr, Depozit.class));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}
}
