package com.revature.data;

import java.util.List;

import com.revature.beans.Bike;


public interface BikeDAO extends GenericDAO<Bike> {

	public List<Bike> getAllByBrand(String brand);
	public List<Bike> getAllByModel(String model);
	public List<Bike> getAllByQuery(String query, String search);

}
