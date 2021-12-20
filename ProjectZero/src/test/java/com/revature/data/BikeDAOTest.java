package com.revature.data;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.postgres.BikePostgres;

public class BikeDAOTest {
	List<Bike> bikes = new ArrayList<Bike>();
	private BikeDAO dao = new BikePostgres();
		
	@BeforeAll
	public static void initAll() {

		
		
	}
	
	
	@Test					// testing create if there are no bikes
	public void testCreateForBikes() {
		
		
		
		
	}
	@Test					// testing create if there are no bikes
	public void testCreateForNoBikes() {
		
	}
	
	
	@Test							// testing getById if there is an Id
	public void getByIdWhenIdExists() {
		int idInput = 10;
		Bike b = dao.getById((idInput));
		//Bike bike = dao.getById(idInput);
		assertEquals(idInput,b.getId());
		
		
	}
	@Test							// testing getById if there is no ID
	public void getByIdWhenIdDoesntExist() {
		int idInput = 99;
		Bike id = null;
		id = dao.getById(idInput);
		System.out.println(id);
	//	assertEquals(id.getId(), idInput);
		
	}
	
	
	@Test					//testing getall for bikes
		public  void testIfThereAreBikes() {//
		Set<Bike> bike = new HashSet<Bike>();
			bike = null;
			bike = dao.getAll();
			//System.out.println(bike);
			assertNotNull(bike);
		}
	@Test					// testing getall if there are no bikes
		public void testIfThereAreNoBikes() {
			bikes = null;
			//bikes = dao.getAll();
			System.out.println(bikes);
			assertNull(bikes);
		}
		
	@Test					// testing update if there is good bike data passed
	public void testUpdateForGoodBikeData() {
		
	}
	
	@Test					// testing update if there is not good bike data passed
	public void testUpdateForBadBikeData() {
		
	}
	
	@Test					// testing delete if there is good bike data passed
	public void testDeleteForGoodData() {
		
	}
	
	@Test					// testing update if there is good bike data passed
	public void testDeleteForBadBikeData() {
		
	}
	
	
}