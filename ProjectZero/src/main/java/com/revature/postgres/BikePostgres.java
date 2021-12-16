package com.revature.postgres;

import com.revature.exceptions.InvalidBikeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.utils.DAOUtilities;

public class BikePostgres implements BikeDAO {
	private DAOUtilities connUtil = DAOUtilities.getConnectionUtil();
	

	@Override
	public int create(Bike dataToSave) throws Exception {

		// TODO Auto-generated method stub
		int generatedId = 0;
		try (Connection conn = connUtil.getConnection()) {// opens the connection.. and since try w/ resources auto closes conn
			conn.setAutoCommit(false);
			
			
			String sql = "INSERT INTO bike VALUES (default,?,?,?,?,?,?,?,?,?,?)";
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql,keys);
			
			//stmt.setInt(1, dataToSave.getId());
			stmt.setString(1, dataToSave.getName());
			stmt.setString(2, dataToSave.getType());
			stmt.setString(3, dataToSave.getBrand());
			stmt.setString(4, dataToSave.getSize());
			stmt.setString(5, dataToSave.getColor());
			stmt.setString(6, dataToSave.getFrame());
			stmt.setString(7, dataToSave.getMaterial());
			stmt.setString(8, dataToSave.getWheelSet());
			stmt.setString(9, dataToSave.getModel());
			stmt.setInt(10, dataToSave.getOnHand());
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt("id");
				conn.commit();// TCL commit statement
			}else {
				conn.rollback();
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		
		
		return generatedId;
	}

	@Override
	public Bike getById(int id) {
		Bike b = new Bike();
		
		Statement stmt = null;
	
	try (Connection conn = connUtil.getConnection()) {
		
			stmt = conn.createStatement();
			String sql = "SELECT * FROM bike WHERE id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
		
				while (rs.next()) {
			
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
	
	return b;
	}

	@Override
	public List<Bike> getAll() {
		List<Bike> allBikes = new ArrayList<>();
		
		
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

	@Override
	public void update(Bike dataToUpdate) {
		// TODO Auto-generated method stub  test
		
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			//String sql = "insert into pet where id = "+dataToUpdate.getId()+" (name,species,description,age,status) "
			//+ "values (?, ?, ?, ?, ?)";
			String sql = "update bike set "
					+ "id=?,name=?,biketype=?,bikebrand=?,bikesize=?,bikecolor=?,"
					+ "bikeframe=?,bikematerial=?,wheelset=?,bikemodel,"
					+ "onhand=? where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToUpdate.getId());
			pStmt.setString(2, dataToUpdate.getName());
			pStmt.setString(3, dataToUpdate.getType());
			pStmt.setString(4, dataToUpdate.getBrand());
			pStmt.setString(5, dataToUpdate.getSize());
			pStmt.setString(6, dataToUpdate.getColor());
			pStmt.setString(7, dataToUpdate.getFrame());
			pStmt.setString(8, dataToUpdate.getMaterial());
			pStmt.setString(9, dataToUpdate.getWheelSet());
			pStmt.setString(10, dataToUpdate.getModel());
			pStmt.setInt(11, dataToUpdate.getOnHand());
			pStmt.setInt(12, dataToUpdate.getId());
			
				int rowsAffected = pStmt.executeUpdate();
			
					if (rowsAffected==1) {
							conn.commit();
						} else {
								conn.rollback();
						}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void delete(Bike dataToDelete) throws InvalidBikeException {// delete
		
		PreparedStatement stmt = null;
		int success =  0;
		
		try (Connection conn = connUtil.getConnection()) {
			
			
			String sql = "DELETE FROM bike\r\n"
					+ "	WHERE id = "+ dataToDelete.getId();
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

	
	
	
	
}
