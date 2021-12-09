package com.revature.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.beans.Bike;
import com.revature.data.collections.BikeCollections;

public class BikeShopDriver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create();
		app.start();
		
		app.routes(() -> {
			
			path("/bikes", () -> {
			
				get(ctx -> {
				String modelSearch = ctx.queryParam("model");
				String brandSearch = ctx.queryParam("brand");
				String idSearch = ctx.queryParam("id");
																															// search by brand
					if (brandSearch != null && !"".equals(brandSearch)) {
						BikeCollections dao = new BikeCollections();
						//working, testing needed
						ctx.json(dao.getAllByQuery("bikebrand",(brandSearch)));	
																					// search by model
					} else if (modelSearch != null && !"".equals(modelSearch)) {
						BikeCollections dao = new BikeCollections();
						//working, testing needed
						ctx.json(dao.getAllByQuery("bikemodel",(modelSearch)));	
						
																																
					}else if(idSearch != null && !"".equals(idSearch)) {		//search by id
						BikeCollections dao = new BikeCollections();
						ctx.json(dao.getAllByQuery("id",(idSearch)));			//working, testing needed
																				//    get by id
					}	else {													// get all bikes
						BikeCollections dao = new BikeCollections();			//all working for the moment, recheck test and refactor
						ctx.json(dao.getAll());
					}	
											
				});
				
					post(ctx -> {
						//
						BikeCollections dao = new BikeCollections();
							Bike b = new Bike();
							b.setId(Integer.parseInt(ctx.queryParam("id")));
							b.setName(ctx.queryParam("name"));
							b.setColor(ctx.queryParam("color"));
							b.setBrand(ctx.queryParam("brand"));
							b.setModel(ctx.queryParam("model"));
							b.setType(ctx.queryParam("type"));
							b.setSize(ctx.queryParam("size"));
							b.setFrame(ctx.queryParam("frame"));
							b.setMaterial(ctx.queryParam("material"));
							b.setWheelSet(ctx.queryParam("wheelset"));
							b.setOnHand(Integer.parseInt(ctx.queryParam("onHand")));
																	
						int success = (dao.create(b));
						
						if(success == 1)						
						ctx.result("POST to /bikes successful");
				
					});
										
				path("/bikes/delete/{id}", () -> {//delete working   test
				
					put(ctx -> {
						//put in postman..   path  http://localhost:8080/bikes/delete:ID
						String id = ctx.pathParam("id");
						BikeCollections dao = new BikeCollections();
						dao.delete(Integer.parseInt(id));
						ctx.result("Deleted bike with " + id + "for an id successful");
					});
							
				});
				
				
				path("/{id}", () -> {
					
					get(ctx -> {////moved to query
//						String id = ctx.pathParam("id");
//						BikeCollections dao = new BikeCollections();
//						//working, testing needed
//						ctx.json(dao.getById(Integer.parseInt(id)));
					});
					
					
					
					
					put(ctx -> {
						String id = ctx.pathParam("id");
						
						
						
						ctx.result("PUT to /bikess/" + id + " successful");
					});
				
				
				});
			});
		});
		
		
	}

}
//ALTER TABLE bikes
//ADD	bikeid integer primary key,
//ADD	"name" varchar(80),
//ADD	biketype varchar(80),
//ADD	bikebrand varchar(80),
//ADD	bikesize varchar (3),
//ADD	bikecolor varchar (80),
//ADD	bikeframe varchar (80),
//ADD	framematerial varchar (80),
//ADD	wheelset varchar (80),
//)



//Technical Requirements
//Data must be stored and retrieved from a PostgreSQL database (local or AWS).
//Data access in Java will be performed using JDBC DAOs.
//HTTP handling in Java will be done using Javalin.
//Service layers must be fully unit tested using JUnit and Mockito.
//DAOs are fully unit tested.
//Postman test suites are created to test all endpoints.



//Functional Requirements
//As a user, I can view all bicycles.
//GET /bicycles
//As a user, I can add a new bicycle.
//POST /bicycles
//As a user, I can update a bicycle.
//PUT /bicycles/{id}
//As a user, I can view bicycles by ID.
//GET /bicycles/{id}
//As a user, I can search bicycles by brand/model.
//GET /bicycles?brand=
//GET /bicycles?model=