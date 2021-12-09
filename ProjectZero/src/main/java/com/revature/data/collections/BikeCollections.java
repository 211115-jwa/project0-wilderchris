package com.revature.data.collections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.DAOUtilities;
import com.revature.exceptions.InvalidBikeException;

public class BikeCollections {
	
	
	public int create(Bike dataToSave) throws Exception{// create
		Connection connection = null;
		PreparedStatement stmt = null;
		int success =  0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "INSERT INTO bike VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, dataToSave.getId());
			stmt.setString(2, dataToSave.getName());
			stmt.setString(3, dataToSave.getType());
			stmt.setString(4, dataToSave.getBrand());
			stmt.setString(5, dataToSave.getSize());
			stmt.setString(6, dataToSave.getColor());
			stmt.setString(7, dataToSave.getFrame());
			stmt.setString(8, dataToSave.getMaterial());
			stmt.setString(9, dataToSave.getWheelSet());
			stmt.setString(10, dataToSave.getModel());
			stmt.setInt(11, dataToSave.getOnHand());
			
			success = stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(success == 0) {// didnt work throw
			throw new InvalidBikeException();
		}
		
		return 1;
	}
	
//	public List<Bike> getAllByBrand(String brand){
//		List<Bike> bike = new ArrayList<>();
//		
//		return bike;
//		
//	}
	public List<Bike> getAllByQuery(String query, String search){
		
		
		List<Bike> bike = new ArrayList<>();

		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM bike WHERE " + query+ " = \'"+search+"\'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				Bike b = new Bike();
				
				b.setId(rs.getInt("id"));//
				b.setName(rs.getString("name"));//
				b.setType(rs.getString("biketype"));//
				b.setBrand(rs.getString("bikebrand"));//
				b.setSize(rs.getString("bikesize"));//
				b.setColor(rs.getString("bikecolor"));//
				b.setFrame(rs.getString("bikeframe"));//
				b.setMaterial(rs.getString("bikematerial"));//
				b.setWheelSet(rs.getString("wheelset"));
				b.setModel(rs.getString("bikemodel"));//
				b.setOnHand(rs.getInt("onhand"));	
										
				bike.add(b);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection !=null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bike;
	
		
	}
	
	
	public List<Bike> getAllByModel(String model){
		
		List<Bike> bike = new ArrayList<>();

		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM bike WHERE bikemodel = \'"+model+"\'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				Bike b = new Bike();
				
				b.setId(rs.getInt("id"));//
				b.setName(rs.getString("name"));//
				b.setType(rs.getString("biketype"));//
				b.setBrand(rs.getString("bikebrand"));//
				b.setSize(rs.getString("bikesize"));//
				b.setColor(rs.getString("bikecolor"));//
				b.setFrame(rs.getString("bikeframe"));//
				b.setMaterial(rs.getString("bikematerial"));//
				b.setWheelSet(rs.getString("wheelset"));
				b.setModel(rs.getString("bikemodel"));//
				b.setOnHand(rs.getInt("onhand"));	
										
				bike.add(b);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection !=null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bike;
	}
	public void update(Bike dataToUpdate) {//update
		//TODO
		
		
	}
	public void delete(int id) throws InvalidBikeException {// delete
		//TODO
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int success =  0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "DELETE FROM bike\r\n"
					+ "	WHERE id ="+ id;
			stmt = connection.prepareStatement(sql);
			
						
			success = stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(success == 0) {// didnt work throw
			throw new InvalidBikeException();
		}
		
		
		
		
	}
	
	
	// work on the driver side,, more testing required
	public List<Bike> getById(int id) {// getById
		
		List<Bike> bike = new ArrayList<>();

		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM bike WHERE id = "+id;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				Bike b = new Bike();
				
				b.setId(rs.getInt("id"));//
				b.setName(rs.getString("name"));//
				b.setType(rs.getString("biketype"));//
				b.setBrand(rs.getString("bikebrand"));//
				b.setSize(rs.getString("bikesize"));//
				b.setColor(rs.getString("bikecolor"));//
				b.setFrame(rs.getString("bikeframe"));//
				b.setMaterial(rs.getString("bikematerial"));//
				b.setWheelSet(rs.getString("wheelset"));
				b.setModel(rs.getString("bikemodel"));//
				b.setOnHand(rs.getInt("onhand"));	
				
				bike.add(b);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection !=null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bike;
		}
	
	
	
	public Set<Bike> getAll() {//getall
		
		Set<Bike> allBikes = new HashSet<>();
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM bike";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				Bike b = new Bike();
				
				b.setId(rs.getInt("id"));//
				b.setName(rs.getString("name"));//
				b.setType(rs.getString("biketype"));//
				b.setBrand(rs.getString("bikebrand"));//
				b.setSize(rs.getString("bikesize"));//
				b.setColor(rs.getString("bikecolor"));//
				b.setFrame(rs.getString("bikeframe"));//
				b.setMaterial(rs.getString("bikematerial"));//
				b.setWheelSet(rs.getString("wheelset"));
				b.setModel(rs.getString("bikemodel"));//
				b.setOnHand(rs.getInt("onhand"));	
				
				allBikes.add(b);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection !=null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return allBikes;
	}
}
