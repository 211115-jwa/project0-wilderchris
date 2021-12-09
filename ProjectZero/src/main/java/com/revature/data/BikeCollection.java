package com.revature.data;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Bike;

public class BikeCollection implements BikeDAO {

	public int create(Bike dataToSave) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Bike getById(int id) {
		Bike bike = new Bike();
		
		return bike;
	}

	public List<Bike> getAll() {
		List<Bike> bike = new ArrayList<Bike>();
		
		return bike;
	}

	public void update(Bike dataToUpdate) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Bike dataToDelete) {
		// TODO Auto-generated method stub
		
	}

	public List<Bike> getAllByBrand(String brand) {
		// TODO Auto-generated method stub
		List<Bike> bike = new ArrayList<Bike>();
		
		return bike;
	}

	public List<Bike> getAllByModel(String model) {
		// TODO Auto-generated method stub
		List<Bike> bike = new ArrayList<Bike>();
		
		return bike;
	}

	@Override
	public List<Bike> getAllByQuery(String query, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
