package com.ust.service;

import java.util.List;
import java.util.Optional;

import com.ust.model.Employee;


public interface EmployeeService {
	
	public void updateEmployee(Employee e);
	public boolean isPresent(int id);
	public Optional<Employee> getEmployeeById(int id);	
	public Integer addEmployee(Employee e);	
	public void deleteById(int id);
	public List<Employee> listAllrecords();
	public Optional<Employee> searchuser(Integer username);
	
	
}
