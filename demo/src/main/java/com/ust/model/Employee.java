package com.ust.model;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor; import lombok.Data; 
import lombok.NoArgsConstructor; import lombok.NonNull; 
import lombok.RequiredArgsConstructor; 
 


@Data 	
@Entity 
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue
	private Integer empId;
	private String empName;
	private String designation;
	private String dept;
	private String addrs;
	private String empCode;
}
