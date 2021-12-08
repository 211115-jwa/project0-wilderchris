package com.revature.data;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;

public class BikeDAOTest {
	List<Bike> bikes = new ArrayList<Bike>();
	private BikeDAO dao = new BikeCollection();
		
//	@BeforeAll
//	public void initAll() {
//		
//	}
	@Test
		public  void testIfThereAreBikes() {
			bikes = null;
			bikes = dao.getAll();
			assertNotNull(bikes);
		}
	@Test
		public void testIfThereAreNoBikes() {
			bikes = null;
			bikes = dao.getAll();
			assertNull(bikes);
		}
		@Test
		public void getByIdWhenIdExists() {
			int idInput = 0;
			Bike id = dao.getById(idInput);
			assertEquals(0,id.getId());
			
			
		}
		@Test
		public void getByIdWhenIdDoesntExists() {
			int idInput = 0;
			Bike id = dao.getById(idInput);
			assertNull(id.getId());
			
		}
		
		@Test
		public void testIfGetAllByBrandWhenThereAreBikes() {
			String brandIn = "";
			List<Bike> brand = dao.getAllByBrand(brandIn);
			assertEquals("",brand.get(0).getBrand());
		}
		
		@Test
		public void testIfGetByBrandWhenThereAreNoBikes() {
			String brandIn = "any";
			List<Bike> brand = dao.getAllByBrand(brandIn);
			System.out.println(brand.toString());

			assertNull(brand.get(0).getBrand());
		}
		
		@Test
		public void testIfGetAllByModelWhenThereAreBikes() {
			String modelIn = "";
			List<Bike> model = dao.getAllByModel(modelIn);
			assertEquals("",model.get(0).getModel());
		}
		
		@Test
		public void testIfGetByModelWhenThereAreNoBikes() {
			String modelIn = "any";
			List<Bike> model = dao.getAllByModel(modelIn);
			System.out.println(model.toString());

			assertNull(model.get(0).getBrand());
		}

}