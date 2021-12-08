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
					
							
					
					
					if (brandSearch != null && !"".equals(brandSearch)) {
						
						
						
						ctx.result("GET to /bikes?brand=" + brandSearch + " successful");
					} else if (modelSearch != null && !"".equals(modelSearch)) {
						
						
						
						ctx.result("GET to /bikes?model=" + modelSearch + " successful");
					} else {
						//all working for the moment, recheck test and refactor
						BikeCollections bike = new BikeCollections();
						ctx.json(bike.getAll());
						
						
					}	
						
								
				});
				
					post(ctx -> {
						
//						
						ctx.result("POST to /bikes successful");
				
					});
										
				path("/create/{id}{name}{color}{brand}{model}{type}{size}{frame}{material}{wheelset}{onHand}", () -> {
				
					put(ctx -> {
						
						String id = ctx.pathParam("id");
//						
//						
						
						
						ctx.result("PUT to /bikes/purchase/" + id + " successful");
					});
							
				});
				
				//working
				path("/{id}", () -> {
					
					get(ctx -> {
						String id = ctx.pathParam("id");
						BikeCollections bike = new BikeCollections();
						
						//working, testing needed
						ctx.json(bike.getById(Integer.parseInt(id)));
					});
					
					put(ctx -> {
						//String id = ctx.pathParam("id");
						BikeCollections bike = new BikeCollections();
						
						Bike b = new Bike();
						b.setId(Integer.parseInt(ctx.pathParam("id")));
						b.setName(ctx.pathParam("name"));
						b.setColor(ctx.pathParam("color"));
						b.setBrand(ctx.pathParam("brand"));
						b.setModel(ctx.pathParam("model"));
						b.setType(ctx.pathParam("type"));
						b.setSize(ctx.pathParam("size"));
						b.setFrame(ctx.pathParam("frame"));
						b.setMaterial(ctx.pathParam("material"));
						b.setWheelSet(ctx.pathParam("wheelset"));
						b.setOnHand(Integer.parseInt(ctx.pathParam("onHand")));
																	
						int success = (bike.create(b));
						
						if(success == 1)
						ctx.result("PUT to /bikess/" + b + " successful");
					});
				
				
				});
			});
		});
		// TODO Auto-generated method stub
		
//		ArrayList<String> bikes = new ArrayList<String>();
//		ArrayList<Double> grams = new ArrayList<Double>();
//		
//		bikes.add(0,"Huffy");
//		bikes.add(1,"Golden Cycles");
//		bikes.add(2,"All-City Cycles");
//		bikes.add(3,"Bakcou");
//		bikes.add(4,"Bianchi");
//		bikes.add(5,"Brompton");
//		bikes.add(6,"Buzz");
//		bikes.add(7,"Co-op Cycles");
//		bikes.add(8,"Devinci");
//		bikes.add(9,"Diamondback");
//		bikes.add(10,"Electra");
//		
//		grams.add(0,(double) 59);
//		grams.add(1,(double) 64);
//		grams.add(2,(double) 33);
//		grams.add(3,(double) 56);
//		grams.add(4,(double) 48);
//		grams.add(5,(double) 41);
//		grams.add(6,(double) 59);
//		grams.add(7,(double) 61);
//		grams.add(8,(double) 181);
//		grams.add(9,(double) 29);
//		grams.add(10,(double) 204);
		
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