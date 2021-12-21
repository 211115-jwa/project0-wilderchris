package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.exceptions.InvalidBikeException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	private int success = 0;
	private int fail = 1;

	@Mock
	private BikeDAO bikedao;// mocking the dao

	@InjectMocks
	private UserService userServ = new UserServiceImpl();// injecting mocks into userServ calls to dao

	private static Set<Bike> mockBikeDatabase;// mock bike DB
	private static Bike mockBike;// single mock bike

	@BeforeAll// runs 1st
	public static void mockBikeSetup() {// setup fpr mock bike
		mockBike = new Bike();
		mockBike.setName("mock");
		mockBike.setType("mountain");
		mockBike.setBrand("Huffy");
		mockBike.setSize("XXL");
		mockBike.setColor("Green");
		mockBike.setFrame("Adult");
		mockBike.setMaterial("Steel");
		mockBike.setWheelSet("24\"");
		mockBike.setModel("Gravel");
	}

	@BeforeAll
	public static void mockBikeDatabaseSetup() {// mock Database
		mockBikeDatabase = new HashSet<>();

		for (int i = 1; i <= 10; i++) {
			Bike b = new Bike();
			b.setId(i);
			if (i < 3)
				b.setBrand("huffy");
			b.setColor("red");
			b.setModel("gravel");
			mockBikeDatabase.add(b);
		}
	}

	@Test
	public void testViewAllBikes() {// tests the allBikes method

		when(bikedao.getAll()).thenReturn(mockBikeDatabase);
		Set<Bike> allBikes = userServ.viewAllBikes();
		assertFalse( allBikes.isEmpty());// all bikes  set is not Empty

	}

	@Test
	public void testGetByBrandExists() {// test brand search
		String brand = "huffy";// test search 
		boolean onlyHuffyBikes = true;// test check
		when(bikedao.getAll()).thenReturn(mockBikeDatabase);// injects mock DB when dao getAll() is called
		Set<Bike> actualBikes = userServ.getByBrand(brand);// get mock bikes back with matching brands
		for (Bike bike : actualBikes) {//
			if (!bike.getBrand().equals(brand))// if no brands match 
				onlyHuffyBikes = false;// false value 
		}
		assertTrue(onlyHuffyBikes);// assert that brand found is true
	}

	@Test
	public void testSearchByBrandDoesNotExist() {// test for non existent brand
		String brand = "qwerty";// test value
		when(bikedao.getAll()).thenReturn(mockBikeDatabase);// injects mock DB when dao getAll() is called
		Set<Bike> actualBikes = userServ.getByBrand(brand);// userserv used for search on bad brand name
		assertTrue(actualBikes.isEmpty());// nothing returned assertion
	}

	@Test
	public void testSearchByModelExists() {// model search test
		String model = "gravel";// test value
		boolean onlyGravelBikes = true;// test check
		when(bikedao.getAll()).thenReturn(mockBikeDatabase);// mock DB injected

		Set<Bike> actualBikes = userServ.getByModel(model);// search by model 
		
		for (Bike bike : actualBikes) {
			if (!bike.getModel().equals(model))// check for test model
				onlyGravelBikes = false;// false if not found
		}
		assertTrue(onlyGravelBikes);
	}

	@Test
	public void testSearchByModelDoesNotExist() {
		String model = "qwerty";
		when(bikedao.getAll()).thenReturn(mockBikeDatabase);
		Set<Bike> actualBikes = userServ.getByModel(model);
		assertTrue(actualBikes.isEmpty());
	}

	@Test
	public void testgetByIdWhenIdExists() {//test get by id
		int id = 1;// test id
		List<Bike> bike = new ArrayList<Bike>(mockBikeDatabase);// casting set to arraylist for .get() 
		when(bikedao.getById(id)).thenReturn(bike.get(id));// // inject 

		Bike b = userServ.getById(id);// test method
		assertNotNull(b);// assert bike is present

	}

	@Test
	public void testgetByIdWhenIdDoesNotExists() {// test for bad id check
		int id = 9;// must be within range of mock bike set
		List<Bike> bikes = new ArrayList<Bike>(mockBikeDatabase);// converted the HashSet to ArrList
		when(bikedao.getById(id)).thenReturn(bikes.get(id));// to be able to use .get()
		// ArrayList to get index access
		Bike bike = userServ.getById(id);// test method
		//System.out.println(bike);
		assertEquals(bike.getName(), "");// check for empty bike

	}

	@Test
	public void testInvalidBikeException() {// test for custom exception

		Throwable exception = assertThrows(InvalidBikeException.class, () -> {
			throw new InvalidBikeException("Invalid Bike Exception Thrown!!");

		});// check the message returned for Invalid Bike Exception
		assertEquals("Invalid Bike Exception Thrown!!", exception.getMessage());
	}

	@Test
	public void testaddBikeWithGoodBikeDataUsed() {// add bike test
		try {
			when(bikedao.create(mockBike)).thenReturn(success);// injects value
		} catch (Exception e) {
			e.printStackTrace();
		}
		int returnValue = (userServ.addNewBike(mockBike));// testing return vale
		assertFalse(fail == returnValue);// asserts received success return value
	}

	@Test
	public void testAddBikeWithBadBikeDataUsed() {// test add for bad bike
			int returnValue = 0;
			try {
				when(bikedao.create(mockBike)).thenReturn(fail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			returnValue = (userServ.addNewBike(mockBike));
	
		assertEquals(fail,returnValue);//asserts received fail return
	}


}
