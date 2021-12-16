package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

import static io.javalin.apibuilder.ApiBuilder.*;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.beans.Bike;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BikeShopDriver {

	public static void main(String[] args) {
		
		UserService us = new UserServiceImpl();
		Javalin app = Javalin.create();
		
		app.start();
		
		app.routes(() -> {
			
			path("/bikes", () -> {                                    //        switch collections to postgresql
			
				get(ctx -> {// fix for getall to see if it works
				String modelSearch = ctx.queryParam("model");
				String brandSearch = ctx.queryParam("brand");
				
																	// search by brand
					if (brandSearch != null && !"".equals(brandSearch)) {
						
						ctx.json(us.getByBrand((brandSearch)));	
																					// search by model 
					} else if (modelSearch != null && !"".equals(modelSearch)) {
						
						ctx.json(us.getByModel((modelSearch)));	
																														
									
					}	else {					// get all bikes
						// recheck test and refactor
						ctx.json(us.viewAllAvailableBikes());
					}	
											
				});
				
					post(ctx -> {
						//
						// add new bike			create
							Bike b = ctx.bodyAsClass(Bike.class);	
							if (b !=null) {
								us.addNewBike(b);
								ctx.status(HttpStatus.CREATED_201);
							} else {
								ctx.status(HttpStatus.BAD_REQUEST_400);
							}

							
																
					});
										
				path("/delete/{id}", () -> {//delete 
					
					put(ctx -> {
						//put in postman..   path  http://localhost:8080/bikes/delete:ID
						String id = ctx.pathParam("id");
						
						us.removeBike(Integer.parseInt(id));
						ctx.result("Deleted bike with " + id + "for an id successful");
					});
							
				});
				
				
				path("/{id}", () -> {// get by ID
					
					get(ctx -> {
//						
						try {
							int id = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Bike b = us.getById(id);
							if (b != null)
								ctx.json(b);
							else
								ctx.status(404);
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Bike needs to be a numerical value");
						}

					});
					
					
					
					
					put(ctx -> {//update
						try {
							int bikeId = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Bike b = ctx.bodyAsClass(Bike.class);
							if (b != null && b.getId() == bikeId) {
								
								b = us.editBike(b);
								if (b != null)
									ctx.json(b);
								else
									ctx.status(404);
							} else {
								// conflict: the id doesn't match the id of the pet sent
								ctx.status(HttpCode.CONFLICT);
							}
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Pet ID must be a numeric value");
						}

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