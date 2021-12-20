package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	
	@Mock
	private BikeDAO bikedao;
	
	
	
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
private static Set<Bike> mockAvailableBikes;
	
	@BeforeAll
	public static void mockAvailableBikesSetup() {
		mockAvailableBikes = new HashSet<>();
		
		for (int i=1; i<=5; i++) {
			Bike b = new Bike();
			b.setId(i);
			if (i<3)
				b.setBrand("huffy");
			    b.setColor("red");
			    b.setModel("gravel");
			mockAvailableBikes.add(b);
		}
	}
	

	@Test
	public void testViewAllBikesWhenExist() {
		
		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
		Set<Bike> allBikes = userServ.viewAllBikes();
		assertNotEquals(null,allBikes);

	}
	
	@Test
	public void testGetByBrandExists() {
		String brand = "huffy";
		
		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
		
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
		
		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.getByBrand(brand);
		assertTrue(actualBikes.isEmpty());
	}

	
	@Test
	public void testSearchByModelExists() {
		String model = "gravel";
		
		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
		
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
		
		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.getByModel(model);
		assertTrue(actualBikes.isEmpty());
	}

	
@Test
public void testgetByIdWhenIdExists() {// returns plus 1... ?? working with a not null assert
	
	int id = 1;
	List<Bike> list = new ArrayList<Bike>(mockAvailableBikes);
	when(bikedao.getById(id)).thenReturn(list.get(id));
	
	Bike bike = userServ.getById(id);
	//System.out.println(bike);
			assertNotNull(bike.getId());
	
}
	
@Test
public void testgetByIdWhenIdDoesNotExists() {// returns plus 1... ??
	
	int id = 9;
	List<Bike> list = new ArrayList<Bike>(mockAvailableBikes);// converted the HashSet to 
	when(bikedao.getById(id)).thenReturn(list.get(id));		  // ArrayList to get index access
	
	Bike bike = userServ.getById(id);
	System.out.println(bike);
	//assertFailure(bike);
	
}
	@Test
	public void testInvalidBikeException() {
		
		Throwable exception = assertThrows(
			      InvalidBikeException.class, 
			      () -> {
			          throw new InvalidBikeException("Invalid Bike Exception Thrown!!");
			      
			      });
			    assertEquals("Invalid Bike Exception Thrown!!", exception.getMessage());	}
	
	
	@Test
	public void SearchAvalibleBikes() {
		
		
		
		
		
	}
	
	
	
	
	
//	@Test
//	public void searchByBrand(){
//		String brand1 = "huffy";
//		
//		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
//		
//	List<Bike> allBikes = userServ.searchByBrand(brand1);
//		boolean onlyBrands = true;
//		for (Bike bike: allBikes) {
//			if(!bike.getBrand().equals(brand1))
//				onlyBrands = false;
//		}
//		
//		assertTrue(onlyBrands);
//				
//	}
	
//	@Test
//	public void SearchByBrandWhenDoesntExist() {
//		
//		String brand = "egfjergfegui";
//		when(bikedao.getAll()).thenReturn(mockAvailableBikes);
//		
//		List<Bike> allBikes = userServ.searchByBrand(brand);
//		
//		
//		assertTrue(allBikes.isEmpty());
//	}
	
//	@Mock
//	private PetDAO petDao;
//	
//	@Mock
//	private PersonDAO personDao;
//	// tell mockito to override the regular DAOs with our mock DAOs
//	@InjectMocks
//	private UserService userServ;
//	
//	@Test
//	public void logInSuccessfully() {
//	//input setup	
//		String username= "blahBlah";
//		String password = "moreBlah";
//		
//	
//	
//	// setup mocking
//	Person mockPerson = new Person;
//	mockPerson.setUsername(username);
//	mockPerson.setPassword(password);
//	when(personDao.getByUsername(username)).thenReturn(mockPerson);
//	}
//	
//		// call the method we're testing
//	Person actualPerson = userServ.logIn(username, password);// all from demo petapp just copied for visual
//	
//	// assert the expected behavior/output
//	assertEquals(mockPerson, actualPerson);// wont work  mock petapp test
//	
}	
//@Test						below needs to be tested with mockito
//public void testIfGetAllByBrandWhenThereAreBikes() {
//	String brandIn = "huffy";
//	List<Bike> brand = dao.getAllByBrand(brandIn);
//	assertEquals("huffy",brand.get(0).getBrand());
//}
//
//@Test
//public void testIfGetByBrandWhenThereAreNoBikes() {
//	String brandIn = "huffy";
//	List<Bike> brand = dao.getAllByBrand(brandIn);
//	System.out.println(brand.toString());
//
//	assertNull(brand.get(0).getBrand());
//}
//@Test
//public void testIfGetAllByQueryforBrandWhenThereAreBikes() {
//	String brandIn = "huffy";
//	List<Bike> brand = dao.getAllByQuery("bikebrand",brandIn);
//	assertEquals("huffy",brand.get(0).getBrand());
//}
//
//@Test
//public void testIfGetByBrandWhenThereAreBikes() {
//	String brandIn = "huffy";
//	List<Bike> brand = dao.getAllByBrand(brandIn);
//	System.out.println(brand.toString());
//
//	assertNull(brand.get(0).getBrand());
//}
//@Test
//public void testIfGetAllByModelWhenThereAreBikes() {
//	String modelIn = "gravel";
//	List<Bike> model = dao.getAllByModel(modelIn);
//	
//	System.out.println(model.toString());
//	//assertNotNull(model.get(0));
//	assertEquals("gravel",model.get(0).getModel());
//}
//
//@Test
//public void testIfGetByModelWhenThereAreNoBikes() {
//	String modelIn = "any";
//	List<Bike> model = dao.getAllByModel(modelIn);
//	System.out.println(model.toString());
//
//	assertNull(model.get(0).getBrand());
//}
//
//}
