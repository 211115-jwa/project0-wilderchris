package com.revature.services;

import java.util.List;
import com.revature.beans.Bike;

public interface UserService {

	
	
		// services represent business logic - actual user activities.
		// what can a user do?
//		public Bike register(Person newUser);
//		public Person logIn(String username, String password);
		
//		public Person adoptPet(int petId, Person newOwner);
		public List<Bike> viewAllAvailableBikes();
		public Bike getById(int id);
		public List<Bike> getByModel(String model);
		public List<Bike> getByBrand(String brand);
		public void addNewBike(Bike bikeToAdd);
		
		public Bike editBike(Bike b);//use instead of updateBike
		public void removeBike(int id);
	
}
