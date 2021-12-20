package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.revature.beans.Bike;
import com.revature.postgres.BikePostgres;
import com.revature.utils.DAOUtilities;

//@ExtendWith(MockitoExtension.class)
public class BikeDAOTest {
	List<Bike> bikes = new ArrayList<Bike>();
	private BikeDAO bikedao = new BikePostgres();

	
	private static Set<Bike> mockAvailableBikes;

	@Mock
	DAOUtilities mockDAOUtilities;
	@Mock
	PreparedStatement mockPreparedStatement;
	@Mock
    ResultSet mockResultSet;
	@Mock
    Connection mockConn;
	
	 int userId = 50;
	
	@BeforeAll
	public static void mockAvailableBikesSetup() {
		mockAvailableBikes = new HashSet<>();

		for (int i = 1; i <= 5; i++) {
			Bike b = new Bike();
			b.setId(i);
			if (i < 3)
				b.setBrand("huffy");
			b.setColor("red");
			b.setModel("gravel");
			mockAvailableBikes.add(b);
		}
	}

	
	
	@Test // testing getById if there is an Id
	public void getByIdWhenIdExists() {
		int idInput = 1;
		Bike b = bikedao.getById((idInput));
		assertEquals(idInput, b.getId());

	}

	@Test // testing getById if there is no ID
	public void getByIdWhenIdDoesntExist() {
		int idInput = 99;
		Bike idBike = null;
		idBike = bikedao.getById(idInput);
		//assertEquals(id.getId(), idInput);
		assertEquals(idBike.getName(), "");
	}

	@Test // testing getall for bikes
	public void testIfThereAreBikes() {//
		Set<Bike> bike = new HashSet<Bike>();
		bike = null;
		bike = bikedao.getAll();
		assertFalse(bike.isEmpty());
	}

	
	@Test // testing update if there is good bike data passed
	public void testUpdateForGoodBikeData() {
		//Bike createBike = null;
		
	}
	
	
	@Test // testing update if there is not good bike data passed
	public void testUpdateForBadBikeData() {

	}

	@Test // testing delete if there is good bike data passed
	public void testDeleteForGoodData() {

	}

	@Test // testing update if there is good bike data passed
	public void testDeleteForBadBikeData() {

	}

}