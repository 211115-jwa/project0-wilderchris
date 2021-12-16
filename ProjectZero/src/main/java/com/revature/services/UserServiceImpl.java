package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.exceptions.InvalidBikeException;
import com.revature.postgres.BikePostgres;

public class UserServiceImpl implements UserService {
	private List<Bike> allBikes = new ArrayList<Bike>();
	private BikeDAO dao = new BikePostgres();
		
@Override
	public List<Bike> getByBrand(String brand){
		List<Bike> b = new ArrayList<Bike>();
		allBikes = dao.getAll();
		for(int i = 0;i < allBikes.size();i++) {
			if( allBikes.get(i).getBrand().equals(brand)) {
				b.add(allBikes.get(i));
			}
		}
		return b;
	}

@Override
	public List<Bike> getByModel(String model){
	
	List<Bike> b = new ArrayList<Bike>();
	allBikes = dao.getAll();
	for(int i = 0;i < allBikes.size();i++) {
		
		if( allBikes.get(i).getModel().equals(model)) {
			b.add(allBikes.get(i));
			
		}
		
	}
	
	return b;


}

	@Override
	public List<Bike> viewAllAvailableBikes() {
		allBikes = dao.getAll();
		return allBikes;
	}
	
	public Set<Bike> getByQuery(String query, String search){		
		Set<Bike> b = new HashSet<>();
//
//		
return b;
		
	}

	@Override
	public void addNewBike(Bike bikeToAdd) {
		try {
			dao.create(bikeToAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Bike getById(int id) {
		Bike b = new Bike();
		
		b = dao.getById(id);
		return b;
	}


	@Override
	public Bike editBike(Bike b) {
		dao.update(b);
		
		return b;
	}


	@Override
	public void removeBike(int id) {
		Bike b = dao.getById(id);
		try {
			dao.delete(b);
		} catch (InvalidBikeException e) {
			e.printStackTrace();
		}
		
		
	}
}
