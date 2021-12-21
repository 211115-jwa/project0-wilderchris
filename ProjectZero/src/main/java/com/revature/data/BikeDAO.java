package com.revature.data;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.exceptions.InvalidBikeException;


public interface BikeDAO {// interface for Bikepostgres

	public int create(Bike dataToSave) throws Exception;//
 	public Bike getById(int id);//
 	public Set<Bike> getAll();	//	
 	public int update(Bike dataToUpdate) throws InvalidBikeException;
	public int delete(Bike dataToDelete) throws InvalidBikeException;
}
