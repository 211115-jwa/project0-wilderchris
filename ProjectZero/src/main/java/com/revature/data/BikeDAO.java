package com.revature.data;

import java.util.List;

import com.revature.beans.Bike;
import com.revature.exceptions.InvalidBikeException;


public interface BikeDAO {

	public int create(Bike dataToSave) throws Exception;//
 	public Bike getById(int id);//
 	public List<Bike> getAll();	//	
 	public void update(Bike dataToUpdate);
	public void delete(Bike dataToDelete) throws InvalidBikeException;
}
