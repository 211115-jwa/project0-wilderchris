package com.revature.data;

import java.util.List;

public interface GenericDAO<D> {

	 	public int create(D dataToSave) throws Exception;//
	 	public D getById(int id);//
	 	public List<D> getAll();	//	
     	public void update(D dataToUpdate);
		public void delete(D dataToDelete);
		
}
