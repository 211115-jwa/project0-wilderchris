package com.revature.data;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.data.collections.BikeCollections;

public class BikeDAOTest {
	Set<Bike> bikes = new HashSet<Bike>();
	private BikeCollections dao = new BikeCollections();
		
//	@BeforeAll
//	public void initAll() {
//		
//	}
	@Test
		public  void testIfThereAreBikes() {//
		Set<Bike> bike = new HashSet<Bike>();
			bike = null;
			bike = dao.getAll();
			//System.out.println(bike);
			assertNotNull(bike);
		}
	@Test
		public void testIfThereAreNoBikes() {
			bikes = null;
			//bikes = dao.getAll();
			assertNull(bikes);
		}
		@Test
		public void getByIdWhenIdExists() {
			int idInput = 10;
			List<Bike> b = dao.getById((idInput));
			//Bike bike = dao.getById(idInput);
			assertEquals(idInput,b.get(0).getId());
			
			
		}
//		@Test
//		public void getByIdWhenIdDoesntExist() {
//			int idInput = 99;
//			Bike id = dao.getById(idInput);
//			System.out.println(id.getId());
//			assertNull(id.getId());
//			
//		}
//		
//		@Test
//		public void testIfGetAllByBrandWhenThereAreBikes() {
//			String brandIn = "huffy";
//			List<Bike> brand = dao.getAllByBrand(brandIn);
//			assertEquals("huffy",brand.get(0).getBrand());
//		}
//		
//		@Test
//		public void testIfGetByBrandWhenThereAreNoBikes() {
//			String brandIn = "huffy";
//			List<Bike> brand = dao.getAllByBrand(brandIn);
//			System.out.println(brand.toString());
//
//			assertNull(brand.get(0).getBrand());
//		}
//		@Test
//		public void testIfGetAllByQueryforBrandWhenThereAreBikes() {
//			String brandIn = "huffy";
//			List<Bike> brand = dao.getAllByQuery("bikebrand",brandIn);
//			assertEquals("huffy",brand.get(0).getBrand());
//		}
//		
//		@Test
//		public void testIfGetByBrandWhenThereAreBikes() {
//			String brandIn = "huffy";
//			List<Bike> brand = dao.getAllByBrand(brandIn);
//			System.out.println(brand.toString());
//
//			assertNull(brand.get(0).getBrand());
//		}
//		@Test
//		public void testIfGetAllByModelWhenThereAreBikes() {
//			String modelIn = "gravel";
//			List<Bike> model = dao.getAllByModel(modelIn);
//			
//			System.out.println(model.toString());
//			//assertNotNull(model.get(0));
//			assertEquals("gravel",model.get(0).getModel());
//		}
//		
//		@Test
//		public void testIfGetByModelWhenThereAreNoBikes() {
//			String modelIn = "any";
//			List<Bike> model = dao.getAllByModel(modelIn);
//			System.out.println(model.toString());
//
//			assertNull(model.get(0).getBrand());
//		}

}