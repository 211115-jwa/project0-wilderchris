package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.revature.beans.Bike;
import com.revature.exceptions.InvalidBikeException;
import com.revature.postgres.BikePostgres;

@TestMethodOrder(OrderAnnotation.class)
public class BikeDAOTest {
	private static int generatedId = 0;
	private static int success = 0;
	private static int fail = 1;
	private BikeDAO bikedao = new BikePostgres();
	private static Set<Bike> mockBikeDatabase;
	private static Bike mockBike;
	int userId = 50;

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
	public static void mockBikeDatabaseSetup() {
		mockBikeDatabase = new HashSet<>();

		for (int i = 1; i <= 5; i++) {
			Bike b = new Bike();
			b.setId(i);
			if (i < 3)
				b.setBrand("huffy");
			b.setColor("red");
			b.setModel("gravel");
			mockBikeDatabase.add(b);
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
	@Order(1)
	public void testCreateforBike() {

		try {
			generatedId = bikedao.create(mockBike);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mockBike.setId(generatedId);
		assertTrue(mockBike.getId() > 0); // since create returns generatedId
		// and 0 is initial value..so if not created
	}

	@Test // testing update if there is not good bike data passed
	@Order(2)
	public void testUpdateBike() {
		String modelUpdate = "Off-Road";
		mockBike.setModel(modelUpdate);
		try {
			bikedao.update(mockBike);
		} catch (InvalidBikeException e) {

			e.printStackTrace();
		}
		String updatedModel = bikedao.getById(generatedId).getModel();
		assertEquals(modelUpdate, updatedModel);
	}

	@Test // testing delete if there is good bike data passed
	@Order(3)
	public void testDeleteBike() {
		int returnValue = 0;
		try {
			returnValue = bikedao.delete(mockBike);
		} catch (InvalidBikeException e) {

			e.printStackTrace();
		}
		assertEquals(success, returnValue);
	}

	@Test
	public void testDeleteWithBadBikekData() {
		int returnValue = 0;
		Bike badBike = new Bike();
		;
		badBike.setId(100);
		try {
			returnValue = bikedao.delete(badBike);
		} catch (InvalidBikeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(fail, returnValue);
	}

	@Test
	public void testUpdateBadHandling() {
		Bike bike = new Bike();
		bike.setModel("");
		int returnValue = 0;
		try {
			returnValue = bikedao.update(bike);
		} catch (InvalidBikeException e) {
			e.printStackTrace();
		}
		assertEquals(fail, returnValue);
	}
}


//@Test
//public void testUpdateForBadEntryException() {
//	Bike bike = new Bike();
//	bike.setModel("");
//	
//    Exception exception = assertThrows(InvalidBikeException.class, () -> {
//       bikedao.update(bike);
//    });
//    //System.out.println();
//    String expectedMessage = "Invalid Bike Exception Thrown!!";
//    String actualMessage = exception.getMessage();
//
//    assertTrue(actualMessage.contains(expectedMessage));
//}
