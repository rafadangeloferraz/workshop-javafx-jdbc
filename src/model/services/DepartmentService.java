package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFactory.createDepartmentDao();//dependencia criada p chamada para ir ao BD

	public List<Department> findAll() {
		return dao.findAll();//vai buscar os dpto no BD
	}
	/*
	//MOCK - só retornar dados não oficiais
	List<Department> list = new ArrayList<Department>();
	list.add(new Department(1, "Books"));
	list.add(new Department(2, "Computers"));
	list.add(new Department(3, "Electronics"));
	return list;
	*/
	
}
