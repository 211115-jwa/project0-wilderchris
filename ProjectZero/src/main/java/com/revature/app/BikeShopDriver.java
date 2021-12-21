package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.beans.Bike;
import com.revature.exceptions.InvalidBikeException;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BikeShopDriver {

	public static void main(String[] args) {

		UserService us = new UserServiceImpl();
		Javalin app = Javalin.create();

		app.start();

		app.routes(() -> {

			path("/bikes", () -> { // get end point for queries and getAll
				get(ctx -> {
					String modelSearch = ctx.queryParam("model"); // sets var for model and
					String brandSearch = ctx.queryParam("brand"); // brand if the brand or model key is used
					// as /bikes?model= or /bikes?brand= search by brand if/bikes?brand=
					if (brandSearch != null && !"".equals(brandSearch)) {
						Set<Bike> bike = us.getByBrand(brandSearch);
						if (bike.isEmpty()) // error check for no matches
							ctx.result("No Brands By That Name!!");
						else
							ctx.json(bike);
					} else if (modelSearch != null && !"".equals(modelSearch)) {
						Set<Bike> bike = us.getByModel(modelSearch);
						if (bike.isEmpty()) // error check for no matches
							ctx.result("No Models By That Name!!");
						else
							ctx.json(bike);
					} else { // get all bikes if no params are sent
						ctx.json(us.viewAllBikes());
					}
				});
				post(ctx -> { // post end point for adding new bicycle
					Bike b = ctx.bodyAsClass(Bike.class); // using ctx.bodyAsClass to get the user
					if (b != null) { // input and set it to a bike object
						int success = us.addNewBike(b); // calls add new bike to add the bike entered
						if (success != 0) {
							b.setId(success);
							ctx.json(b); // output of added bike b
							ctx.status(HttpStatus.CREATED_201); // created 201 sent
						} else {
							ctx.status(HttpStatus.BAD_REQUEST_400); // sent bad req. 400 and throw Invalid Entry
							throw new InvalidBikeException("Invalid Entry"); // with custom exception
						}
					}
				});
				path("/delete/{id}", () -> { // delete working and has error catch for invalid id
												// that responds to user with message
					put(ctx -> {
						// put in postman.. path http://localhost:8080/bikes/delete:ID
						try {
							int id = Integer.parseInt(ctx.pathParam("id"));
							int success = us.removeBike(id); // added a return for removeBikes
							if (success == 0) { // that checks the delete from postgres succes
								ctx.status(HttpStatus.OK_200); // and checks for existing bike with id
								ctx.result("Bike with id = " + id + " has been deleted.");// if all is good executes
							} else {
								ctx.status(HttpStatus.BAD_REQUEST_400); // with a return that is not positive
								ctx.result("Bike with id = " + id + " Does not exist!");// responds with message
							}
						} catch (NumberFormatException e) {// number not entered and expected int or long
							ctx.status(400); // for id
							ctx.result("Bike needs to be a numerical value");// and message
						}
					});

				});
				path("/{id}", () -> { // get by ID
					get(ctx -> {
						try {
							int id = Integer.parseInt(ctx.pathParam("id"));// get path param and parse
							Bike b = us.getById(id); // string to integer and then get bike and assign it
							if (b == null || b.getName() == "") { // checking for empty params
								ctx.status(404);
								ctx.result("Please Enter an id to search");
							} else
								ctx.json(b); // send bike result

						} catch (NumberFormatException e) { // no number check
							ctx.status(400);
							ctx.result("Bike needs to be a numerical value");
						}
					});
					put(ctx -> {// update
						try {
							int id = Integer.parseInt(ctx.pathParam("id")); // num format exception
							
							Bike bikeToUpdate = ctx.bodyAsClass(Bike.class);

							if (bikeToUpdate != null && bikeToUpdate.getId() == id) {
								bikeToUpdate = us.editBike(bikeToUpdate);
								Bike b = us.getById(id);
								if (bikeToUpdate != null && b.getName() != "")
									ctx.json(bikeToUpdate);
								else {
									ctx.status(404);
									ctx.result("Error bike does not exist or is empty");
								}
							} else {
								ctx.status(HttpCode.CONFLICT);
								ctx.result("Error no data or id's do not match");
							}
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Bike ID must be numeric!");
						}
					});
				});
			});
		});
	}
}
