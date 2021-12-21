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
	private static int success = 0;// variable for success
	private static int fail = 1;// fail variable
	private BikeDAO bikedao = new BikePostgres();// dao for testing
	private static Bike mockBike;// mock data
	int userId = 50;

	@BeforeAll
	public static void mockBikeSetup() {// mock bike
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

	@Test // testing getById if there is an Id
	public void getByIdWhenIdExists() {
		int idInput = 1;
		Bike b = bikedao.getById((idInput));
		assertEquals(idInput, b.getId());// confirming what is returned

	}

	@Test // testing getById if there is no ID
	public void getByIdWhenIdDoesntExist() {
		int idInput = 99;
		Bike idBike = null;
		idBike = bikedao.getById(idInput);
		assertTrue(idBike.getName().isEmpty());// check for empty test bike
	}

	@Test // testing get all for bikes
	public void testIfThereAreBikes() {//
		Set<Bike> bike = new HashSet<Bike>();
		bike = null;
		bike = bikedao.getAll();
		assertFalse(bike.isEmpty());// confirming not empty
	}

	@Test // testing update if there is good bike data passed
	@Order(1)
	public void testCreateforBike() {
		try {
			generatedId = bikedao.create(mockBike);// mock bike creation
		} catch (Exception e) {
			e.printStackTrace();
		}
		mockBike.setId(generatedId);
		assertTrue(mockBike.getId() > 0); // since create returns generatedId
		// and 0 is initial value..so if not created
	}

	@Test
	@Order(2)
	public void testUpdateBike() {// testing update
		String updatedModel;// the testing bike
		String modelUpdate = "Off-Road";// our Bike Model update
		mockBike.setModel(modelUpdate);// sent
		try {
			bikedao.update(mockBike);// mock bike update
		} catch (InvalidBikeException e) {
			e.printStackTrace();
		}
		updatedModel = bikedao.getById(generatedId).getModel();// getting updated bike
		assertEquals(modelUpdate, updatedModel);// checking updated model is same
	}

	@Test
	@Order(3)
	public void testDeleteBike() {// testing delete
		int returnValue = 0;// test value
		try {
			returnValue = bikedao.delete(mockBike);// mock bike delete
		} catch (InvalidBikeException e) {
			e.printStackTrace();
		}
		assertEquals(success, returnValue);// checking for success
	}

	@Test
	public void testDeleteWithBadBikekData() {// testing how delete
		int returnValue = 0; // handles bad data pass
		Bike badBike = new Bike();// bad bike for empty test
		;
		badBike.setId(100);// out of range id
		try {
			returnValue = bikedao.delete(badBike);
		} catch (InvalidBikeException e) {
			e.printStackTrace();
		}
		assertEquals(fail, returnValue);// checking if delete failed
	}

	@Test
	public void testUpdateBadHandling() {// testing bad data update/ need userServ to check for empty
		Bike bike = new Bike();
		bike.setModel("");
		bike.setId(100);// it will update a good id/ use non existent id
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
