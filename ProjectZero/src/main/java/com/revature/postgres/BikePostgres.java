package com.revature.postgres;

import com.revature.exceptions.InvalidBikeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.BikeDAO;
import com.revature.utils.DAOUtilities;

public class BikePostgres implements BikeDAO {

	private DAOUtilities connUtil = DAOUtilities.getConnectionUtil();

	@Override
	public int create(Bike dataToSave) throws Exception {// create dao for database bike addition
		int generatedId = 0;
		
		try (Connection conn = connUtil.getConnection()) {// opens the connection.. and since try w/ resources auto
															// closes conn
			conn.setAutoCommit(false);

			String sql = "insert into bicycle " 
			+ "(id,name,type,brand,size,color,frame,material,wheelset,model) "
					+ "values (default,?,?,?,?,?,?,?,?,?)";
			String[] keys = { "id" };

			PreparedStatement pStmt = conn.prepareStatement(sql, keys);

			pStmt.setString(1, dataToSave.getName());
			pStmt.setString(2, dataToSave.getType());
			pStmt.setString(3, dataToSave.getBrand());
			pStmt.setString(4, dataToSave.getSize());
			pStmt.setString(5, dataToSave.getColor());
			pStmt.setString(6, dataToSave.getFrame());
			pStmt.setString(7, dataToSave.getMaterial());
			pStmt.setString(8, dataToSave.getWheelSet());
			pStmt.setString(9, dataToSave.getModel());

			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt("id");
				conn.commit();
				
			} else {
				conn.rollback();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}

	@Override
	public Bike getById(int id) {							// get by id searches the database for matching id
		
		Bike bike = new Bike();
		try (Connection conn = connUtil.getConnection()) {
		
			String sql = "SELECT * FROM bicycle WHERE id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {

				bike.setId(rs.getInt("id"));//
				bike.setName(rs.getString("name"));//
				bike.setType(rs.getString("type"));//
				bike.setBrand(rs.getString("brand"));//
				bike.setSize(rs.getString("size"));//
				bike.setColor(rs.getString("color"));//
				bike.setFrame(rs.getString("frame"));//
				bike.setMaterial(rs.getString("material"));//
				bike.setWheelSet(rs.getString("wheelset"));
				bike.setModel(rs.getString("model"));//

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return bike;
	}

	@Override
	public Set<Bike> getAll() {			// get all gets all
		// TODO Auto-generated method stub
		Set<Bike> allBikes = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {

			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM bicycle";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Bike b = new Bike();
				b.setId(rs.getInt("id"));//
				b.setName(rs.getString("name"));//
				b.setType(rs.getString("type"));//
				b.setBrand(rs.getString("brand"));//
				b.setSize(rs.getString("size"));//
				b.setColor(rs.getString("color"));//
				b.setFrame(rs.getString("frame"));//
				b.setMaterial(rs.getString("material"));//
				b.setWheelSet(rs.getString("wheelset"));
				b.setModel(rs.getString("model"));//

				allBikes.add(b);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return allBikes;
	}

	@Override
	public void update(Bike dataToUpdate) throws InvalidBikeException { // update an existing bike in database

		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "update bicycle set " 
					+ "name=?,type=?,brand=?,size=?,color=?,"
					+ "frame=?,material=?,wheelset=?,model=?" + " where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, dataToUpdate.getName());
			pStmt.setString(2, dataToUpdate.getType());
			pStmt.setString(3, dataToUpdate.getBrand());
			pStmt.setString(4, dataToUpdate.getSize());
			pStmt.setString(5, dataToUpdate.getColor());
			pStmt.setString(6, dataToUpdate.getFrame());
			pStmt.setString(7, dataToUpdate.getMaterial());
			pStmt.setString(8, dataToUpdate.getWheelSet());
			pStmt.setString(9, dataToUpdate.getModel());
			pStmt.setInt(10, dataToUpdate.getId());

			int rowsAffected = pStmt.executeUpdate();

			if (rowsAffected == 1) {
				conn.commit();
			} else {
				conn.rollback();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}

	}

	@Override
	public int delete(Bike dataToDelete) throws InvalidBikeException {// delete from database
		int success = 0;

		try (Connection conn = connUtil.getConnection()) {

			conn.setAutoCommit(false);

			String sql = "delete from bicycle " + "where id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, dataToDelete.getId());
			int rowsAffected = pStmt.executeUpdate();

			if (rowsAffected == 1 && dataToDelete.getName() !="") {
				sql = "delete from bicycle where id=?";
				PreparedStatement pStmt2 = conn.prepareStatement(sql);
				pStmt2.setInt(1, dataToDelete.getId());
				rowsAffected = pStmt2.executeUpdate();

				if (rowsAffected <= 1) {
					conn.commit();
					success= 0;
				} else {
					conn.rollback();
					success = 1; //fail value
				}
			} else {
				conn.rollback();
				success = 1; //fail value
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;// added success variable to check for info to pass to user
	}

}
