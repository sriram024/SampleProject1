package com.ust.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.model.Employee;
import com.ust.repository.EmployeeRepo;



@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepo edao;
	
	@Override
	public boolean isPresent(int id) {
		return edao.existsById(id);
	}


	@Override
	public void updateEmployee(Employee e) {
		edao.save(e);
	}
	
	@Override
	public Optional<Employee> getEmployeeById(int id) {
		return edao.findById(id);
	}

	//====================================================
	
	@Override
	public Integer addEmployee(Employee e) {
		edao.save(e);
		return e.getEmpId();
	}

	@Override
	public List<Employee> listAllrecords() {
		// TODO Auto-generated method stub
		return edao.findAll();
	}
	
	@Override
	public void deleteById(int id) {
		edao.deleteById(id);
		
	}
	
	//=========================================================
	
	@Override
	public Optional<Employee> searchuser(Integer username) {
		return edao.findById(username);
//		return null;
	}

	
}
