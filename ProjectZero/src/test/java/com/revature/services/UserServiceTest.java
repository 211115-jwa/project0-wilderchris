package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	
	
	
	@Mock
	private BikeDAO bikedao;
	
	
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
private static List<Bike> mockAvailableBikes;
	
	@BeforeAll
	public static void mockAvailableBikesSetup() {
		mockAvailableBikes = new ArrayList<>();
		
		for (int i=1; i<=5; i++) {
			Bike b = new Bike();
			b.setId(i);
			if (i<3)
				b.setBrand("huffy");
			    b.setColor("red");
			mockAvailableBikes.add(b);
		}
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
	
	@Test
	public void SearchAvalibleBikes() {
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
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
