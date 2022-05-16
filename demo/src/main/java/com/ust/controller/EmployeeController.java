package com.ust.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.model.Employee;
import com.ust.model.Message;
import com.ust.service.EmployeeService;


@RestController
@CrossOrigin(origins = "*") 
//@RequestMapping("/rest/student") 
@RequestMapping("/Employee")
public class EmployeeController {
	
	
	@Autowired
	EmployeeService empserObj;
	
//===================================================Employee module=======================================
	
	
	
	//update employee profile
//	@PutMapping("/update")
//	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp){
//		String mg;
//		boolean bobj=empserObj.isPresent(emp.getEmpId());
//		if(bobj) {
//			empserObj.updateEmployee(emp);
//			mg= "Employee Record updated  Successfully ";
//			return new ResponseEntity<String>(mg,HttpStatus.OK);
//		}else {		
//			mg= "No DataFound Based on EmpId ";
//			return new ResponseEntity<String>(mg,HttpStatus.OK);
//		}	
//	}
	
	@PutMapping("/update")  	
	public ResponseEntity<Message> updateStudent(@RequestBody Employee employee) {  	 	
		ResponseEntity<Message> resp=null; 
			try { 
				boolean exist=empserObj.isPresent(employee.getEmpId()); 
				if(exist) { 
					empserObj.updateEmployee(employee); 
					resp=new ResponseEntity<Message>(new Message("OK",employee.getEmpId()+"-Updated"),HttpStatus.OK); 
				}else { 
					resp=new ResponseEntity<Message>(new Message("OK",employee.getEmpId()+"-Not Exist"),HttpStatus.BAD_REQUEST); 
				} 
			} catch (Exception e) { 
				resp=new ResponseEntity<Message>(new Message("OK","Unable to Update"),HttpStatus.INTERNAL_SERVER_ERROR); 
				e.printStackTrace(); 
			} 
			return resp; 
	} 
	
	
	
	
	//view profile
//	@GetMapping("/view/{id}")
//	public Optional<Employee> searchEmployeeById(@PathVariable int id){
//			Optional<Employee> em=empserObj.getEmployeeById(id);
//			return em;
//	}
	
	@GetMapping("/view/{id}")  	
	public ResponseEntity<?> searchEmployeeById(@PathVariable Integer id) 
	{ 
		ResponseEntity<?> resp=null; 
		try { 
			Optional<Employee> opt=empserObj.getEmployeeById(id); 
			if(opt.isPresent())  
				resp=new 
				ResponseEntity<Employee>(opt.get(),HttpStatus.OK); 
			else 
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.BAD_REQUEST); 
		} catch (Exception e) { 
			resp=new ResponseEntity<String>("Unable to Fetch Data",HttpStatus.INTERNAL_SERVER_ERROR); 
			e.printStackTrace(); 
		} 
		return resp; 
	} 
	
	
	
	//
//============================================admin module==========================================================
	
	
	//add employee
//	@PostMapping("/add")
//	public ResponseEntity<String> addEmployee(@RequestBody Employee emp){
//		int iobj=empserObj.addEmployee(emp);
//		String mg="Employee id "+iobj+" Record inserted Successfully ";
//		return new ResponseEntity<String>(mg,HttpStatus.OK);
//	}
	

	@PostMapping("/add") 
	public ResponseEntity<Message> saveStudent(@RequestBody Employee student) 
	 	{ System.out.println(student.getEmpName());
	 	 	ResponseEntity<Message> resp=null; 
	 	 	try { 
	 	 	 	Integer id=empserObj.addEmployee(student);  	 	 	
	 	 	 	resp=new ResponseEntity<Message>(new Message("SUCCESS","Saved"),HttpStatus.OK); 
	 	 	} catch (Exception e) {  	 	 	
	 	 		resp=new ResponseEntity<Message>(new Message("FAIL","Unable to Save"),HttpStatus.OK); 
	 	 	 	e.printStackTrace(); 
	 	 	} 
	 	 	return resp; 
	 	} 

	
	
	
	//list view employee
	
//	@GetMapping("/list")
//	public List<Employee> listAllRecordsEmp(){
//		List<Employee> em=empserObj.listAllrecords();
//			return em;
//	
//	}
	
	@GetMapping("/list")  	
	public ResponseEntity<?> getAllEmployees(){  	 	
		ResponseEntity<?> resp=null; 
	try { 
		List<Employee> list=empserObj.listAllrecords(); 
		if(list!=null && !list.isEmpty()) 
			resp=new 
			ResponseEntity<List<Employee>>(list,HttpStatus.OK); 
		else 
			resp=new ResponseEntity<String>("No Data Found",HttpStatus.OK); 
	} catch (Exception e) { 
		resp=new ResponseEntity<String>("Unable to fetch Data",HttpStatus.INTERNAL_SERVER_ERROR); 
				e.printStackTrace(); 
	} 

	return resp; 
	} 

	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id){
//		String mg;
//		boolean bobj=empserObj.isPresent(id);
//		if(bobj) {
//			empserObj.deleteById(id);
//			mg="Employee  Record deleted Successfully ";
//		}
//		else {
//			mg="Employee  Record not found ";
//		}
//		
//		return new ResponseEntity<String>(mg,HttpStatus.OK);
//	}
	
	
	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<Message> deleteStudent(@PathVariable Integer id) 
	{ 
		System.out.println("welcome");  	 	
		ResponseEntity<Message> resp=null; 
		try { 
			boolean exist=empserObj.isPresent(id); 
			if(exist) {  	 	 	 	
				empserObj.deleteById(id);  	 	 	 	
				resp=new ResponseEntity<Message>(new Message("SUCCESSS",id+"-removed"),HttpStatus.OK); 
			}else { 
				resp=new ResponseEntity<Message>(new Message("FAIL",id+"-Not Exist"),HttpStatus.BAD_REQUEST); 
			} 
		} catch (Exception e) { 
			resp=new ResponseEntity<Message>(new Message("FAIL","Unable to Delete"),HttpStatus.INTERNAL_SERVER_ERROR); 
			e.printStackTrace(); 
		} 

		return resp; 
	} 
	
	
	
	//===========================================================login===============================================
	

	/*
	 * @PostMapping("/save") public ResponseEntity<?> login(@RequestBody User user)
	 * { ResponseEntity<Message> resp=null;
	 * 
	 * System.out.println(user.getUsername());
	 * 
	 * Optional<Employee> use=empserObj.searchusera(user.getUsername());
	 * if(use.get().getPassword().equals(user.getPassword())) return
	 * ResponseEntity.ok(use);
	 * 
	 * return (ResponseEntity<?>) ResponseEntity.internalServerError(); //
	 * e.printStackTrace(); } //
	 */	

	
	
	
	
}
