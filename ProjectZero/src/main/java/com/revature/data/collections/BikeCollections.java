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

public class BikeCollections {
	
	
	
	
	
	public int create(Bike dataToSave) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success =  0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "INSERT INTO bike VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, dataToSave.getId());
			stmt.setString(2, dataToSave.getName());
			stmt.setString(3, dataToSave.getColor());
			stmt.setString(4, dataToSave.getBrand());
			stmt.setString(5, dataToSave.getModel());
			stmt.setString(6, dataToSave.getType());
			stmt.setString(7, dataToSave.getSize());
			stmt.setString(8, dataToSave.getFrame());
			stmt.setString(9, dataToSave.getMaterial());
			stmt.setString(10, dataToSave.getWheelSet());
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
		
		if(success == 0) {
			throw new Exception("Insert of bike failed: "+ dataToSave);
		}
		
		return 1;
	}
	
	
	public void update(Bike dataToUpdate) {
		//TODO
	}
	public void delete(Bike dataToDelete) {
		//TODO
	}
	
	
	// work on the driver side,, more testing required
	public List<Bike> getById(int id) {
		
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
				b.setColor(rs.getString("bikecolor"));//
				b.setBrand(rs.getString("bikebrand"));//
				b.setModel(rs.getString("bikemodel"));//
				b.setType(rs.getString("biketype"));//
				b.setMaterial(rs.getString("bikematerial"));//
				b.setFrame(rs.getString("bikeframe"));//
				b.setSize(rs.getString("bikesize"));//
				b.setWheelSet(rs.getString("wheelset"));
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
	
	public Set<Bike> getAll() {
		
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
				b.setColor(rs.getString("bikecolor"));//
				b.setBrand(rs.getString("bikebrand"));//
				b.setModel(rs.getString("bikemodel"));//
				b.setType(rs.getString("biketype"));//
				b.setMaterial(rs.getString("bikematerial"));//
				b.setFrame(rs.getString("bikeframe"));//
				b.setSize(rs.getString("bikesize"));//
				b.setWheelSet(rs.getString("wheelset"));
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
