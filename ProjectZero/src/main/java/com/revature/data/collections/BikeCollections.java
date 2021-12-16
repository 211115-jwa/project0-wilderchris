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
import com.revature.exceptions.InvalidBikeException;
import com.revature.utils.DAOUtilities;

public class BikeCollections {
	private DAOUtilities connUtil = DAOUtilities.getConnectionUtil();
	
	public int create(Bike dataToSave) throws Exception{// create
		
		PreparedStatement stmt = null;
		int success =  0;
		
		try (Connection conn = connUtil.getConnection()) {
			//connection = DAOUtilities.getConnection();
			
			String sql = "INSERT INTO bike VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			
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
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(success == 0) {// didnt work throw
			throw new InvalidBikeException();
		}
		
		return 1;
	}
	

	
	
	
	
	
//	public void update(Bike dataToUpdate) {//update
//		
//		
//			try (Connection conn = connUtil.getConnection()) {
//				conn.setAutoCommit(false);
//				
//				String sql = "update pet set "
//						+ "name=?,species=?,description=?,age=?,status=?"
//						+ "where id=?";
//				PreparedStatement pStmt = conn.prepareStatement(sql);
//				pStmt.setString(1, dataToUpdate.getName());
//				pStmt.setString(2, dataToUpdate.getSpecies());
//				pStmt.setString(3, dataToUpdate.getDescription());
//				pStmt.setInt(4, dataToUpdate.getAge());
//				pStmt.setString(5, dataToUpdate.getStatus());
//				pStmt.setInt(6, dataToUpdate.getId());
//				
//				int rowsAffected = pStmt.executeUpdate();
//				
//				if (rowsAffected==1) {
//					conn.commit();
//				} else {
//					conn.rollback();
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//			
//		
		
		
		
//		//TODO
//						
//			//Connection connection = null;
//			PreparedStatement stmt = null;
//			
//			
//			try (Connection conn = connUtil.getConnection()) {
//				//connection = DAOUtilities.getConnection();
//				
//				String sql = "INSERT INTO pet WHERE id ="+dataToUpdate.getId()+" VALUES (?,?,?,?,?,?,?,?,?)";
//				stmt = conn.prepareStatement(sql);
//				
//				stmt.setInt(1, dataToUpdate.getId());
//				stmt.setString(2, dataToUpdate.getName());
//				stmt.setString(3, dataToUpdate.getType());
//				stmt.setString(4, dataToUpdate.getBrand());
//				stmt.setString(5, dataToUpdate.getSize());
//				stmt.setString(6, dataToUpdate.getColor());
//				stmt.setString(7, dataToUpdate.getFrame());
//				stmt.setString(8, dataToUpdate.getMaterial());
//				stmt.setString(9, dataToUpdate.getWheelSet());
//				stmt.setString(10, dataToUpdate.getModel());
//				stmt.setInt(11, dataToUpdate.getOnHand());
//				
//				stmt.executeUpdate();
//				
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					if(stmt != null)
//						stmt.close();
//					
//				}catch(SQLException e) {
//					e.printStackTrace();
//				}
//				
//			}
			
			
		
		
		
		
		
		
		
		
		
		
		
	
	public void delete(int id) throws InvalidBikeException {// delete
		
		PreparedStatement stmt = null;
		int success =  0;
		
		try (Connection conn = connUtil.getConnection()) {
			
			
			String sql = "DELETE FROM bike\r\n"
					+ "	WHERE id ="+ id;
			stmt = conn.prepareStatement(sql);
			
						
			success = stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(success == 0) {
			throw new InvalidBikeException();
		}
		
		
		
		
	}
	
	
	// work on the driver side,, more testing required
	public List<Bike> getById(int id) {// getById
			List<Bike> bike = new ArrayList<>();
		
			Statement stmt = null;
		
		try (Connection conn = connUtil.getConnection()) {
			
			stmt = conn.createStatement();
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
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bike;
		}
	
	
	
	public Set<Bike> getAll() {//getall
		Set<Bike> allBikes = new HashSet<>();
		
		
		try (Connection conn = connUtil.getConnection()) {
			
			Statement stmt = conn.createStatement();
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
			
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		
		}
		return allBikes;
	}

	}

