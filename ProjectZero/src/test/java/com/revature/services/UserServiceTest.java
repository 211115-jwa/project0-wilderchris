package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
	private BikeDAO bikedao;

	@InjectMocks
	private UserService userServ = new UserServiceImpl();

	private static Set<Bike> mockBikeDatabase;
	private static Bike mockBike;

	@BeforeAll
	public static void mockBikeSetup() {
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
	public static void mockAvailableBikesSetup() {
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
	public void testViewAllBikes() {

		when(bikedao.getAll()).thenReturn(mockBikeDatabase);
		Set<Bike> allBikes = userServ.viewAllBikes();
		assertNotEquals(null, allBikes);

	}

	@Test
	public void testGetByBrandExists() {
		String brand = "huffy";

		when(bikedao.getAll()).thenReturn(mockBikeDatabase);

		Set<Bike> actualBikes = userServ.getByBrand(brand);
		boolean onlyHuffyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getBrand().equals(brand))
				onlyHuffyBikes = false;
		}

		assertTrue(onlyHuffyBikes);
	}

	@Test
	public void testSearchByBrandDoesNotExist() {
		String brand = "qwerty";

		when(bikedao.getAll()).thenReturn(mockBikeDatabase);

		Set<Bike> actualBikes = userServ.getByBrand(brand);
		assertTrue(actualBikes.isEmpty());
	}

	@Test
	public void testSearchByModelExists() {
		String model = "gravel";

		when(bikedao.getAll()).thenReturn(mockBikeDatabase);

		Set<Bike> actualBikes = userServ.getByModel(model);
		boolean onlyGravelBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getModel().equals(model))
				onlyGravelBikes = false;
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
	public void testgetByIdWhenIdExists() {// working with a not null assert

		int id = 1;
		List<Bike> bike = new ArrayList<Bike>(mockBikeDatabase);
		when(bikedao.getById(id)).thenReturn(bike.get(id));

		Bike b = userServ.getById(id);
		assertNotNull(b);

	}

	@Test
	public void testgetByIdWhenIdDoesNotExists() {// returns plus 1... ??
		int id = 9;

		List<Bike> bikes = new ArrayList<Bike>(mockBikeDatabase);// converted the HashSet to
		when(bikedao.getById(id)).thenReturn(bikes.get(id));
		// ArrayList to get index access
		Bike bike = userServ.getById(id);
		//System.out.println(bike);
		assertEquals(bike.getName(), "");

	}

	@Test
	public void testInvalidBikeException() {

		Throwable exception = assertThrows(InvalidBikeException.class, () -> {
			throw new InvalidBikeException("Invalid Bike Exception Thrown!!");

		});
		assertEquals("Invalid Bike Exception Thrown!!", exception.getMessage());
	}

	@Test
	public void testaddBikeWithGoodBikeDataUsed() {
		try {
			when(bikedao.create(mockBike)).thenReturn(success);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int returnValue = (userServ.addNewBike(mockBike));
		assertFalse(fail == returnValue);
	}

	@Test
	public void testAddBikeWithBadBikeDataUsed() {
			int returnValue = 0;
			try {
				when(bikedao.create(mockBike)).thenReturn(fail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			returnValue = (userServ.addNewBike(mockBike));
	
		assertEquals(fail,returnValue);
	}
		
	@Test
	public void testRemoveBikeWhenIdExists() {
		//int id=0;
		//int returnValue = 0;
	//	returnValue = userServ.removeBike(id);
		
	
	
	}

	@Test
	public void testRemoveBikeWhenIdDoesNotExist() {

	}

}
