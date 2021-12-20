package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.exceptions.InvalidBikeException;
import com.revature.postgres.BikePostgres;

public class UserServiceImpl implements UserService {
	private Set<Bike> allBikes = new HashSet<Bike>();
	private BikeDAO dao = new BikePostgres();

	@Override
	public Set<Bike> getByBrand(String brand) {// search by brand using
		Set<Bike> bikes = new HashSet<Bike>(); // the getALl DAO
		allBikes = dao.getAll(); // and then iterate throught eh set
		for (Iterator<Bike> it = allBikes.iterator(); it.hasNext();) {// until it finds
			// the brand
			Bike b = it.next(); // and then we
			if (b.getBrand().equals(brand)) { // add it to the bike collection
				bikes.add(b);
			}
		}
		return bikes; // and return all bikes set match brand search
	}

	@Override
	public Set<Bike> getByModel(String model) {
		// model search works similar to
		Set<Bike> bikes = new HashSet<Bike>(); // brands
		allBikes = dao.getAll(); // uses the getall and
		for (Iterator<Bike> it = allBikes.iterator(); it.hasNext();) {// then iterates through
			Bike b = it.next(); // the set allBikes
			if (b.getModel().equals(model)) { // b gets each bike
				bikes.add(b); // checks for model and
			} // then added to bike
		}
		return bikes; // set and returned
	}

	@Override
	public Set<Bike> viewAllBikes() {// gets all bikes from the get all dao
		allBikes = dao.getAll();
		return allBikes;
	}

	@Override
	public int addNewBike(Bike bikeToAdd) {// adds bike
		int success = 0;
		if (bikeToAdd == null){
			success = 1;
		}else {
			try { // uses the dao create
				success = dao.create(bikeToAdd);
			} catch (Exception e) { // checks for exception
				success = 1;
				e.printStackTrace();
			}
		}
			
		return success;
	}

	@Override
	public Bike getById(int id) { // get by id uses the dao get by id and
		Bike b = new Bike();

		b = dao.getById(id);
		return b; // returns the bike that matches
	}

	@Override								
	public Bike editBike(Bike b) throws InvalidBikeException {// edit bike 
		
		dao.update(b);							// uses the update dao
		return b;								// returns bike after update
	}

	@Override
	public int removeBike(int id) throws InvalidBikeException {// delete
		int success = 0;
		Bike b = dao.getById(id);
		if (b.getName() == "") { // removed exception, wont throw since test for vaild id
			success = 1; // since I test for the issue and return success
			// throw new InvalidBikeException("Bike Does not exist");
		}
		if (success == 0) {
			success = dao.delete(b); // if the id is a valid id then delete
		}
		return success;// return success
	}
}
