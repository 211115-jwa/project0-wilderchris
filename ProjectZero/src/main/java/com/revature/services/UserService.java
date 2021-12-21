package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.exceptions.InvalidBikeException;

public interface UserService {// interface for User Service Implementation


		public Set<Bike> viewAllBikes();
		public Bike getById(int id);
		public Set<Bike> getByModel(String model);
		public Set<Bike> getByBrand(String brand);
		public int addNewBike(Bike bikeToAdd);
		public Bike editBike(Bike b) throws InvalidBikeException;
		public int removeBike(int id) throws InvalidBikeException;
		
	
}
