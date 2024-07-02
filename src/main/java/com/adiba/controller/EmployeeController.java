package com.adiba.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adiba.model.Employee;
import com.adiba.repository.EmployeeRepository;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	// get
	@GetMapping("/get")
	public ResponseEntity<Object> getAllEmployee(){
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeRepository.findAll());
	}

	// post
	@PostMapping("/save")
	public ResponseEntity<Object> create(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");

	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Employee employee){
		Optional<Employee> emp = employeeRepository.findById(id);
		if(emp.isPresent()) {
			Employee existingEmployee = emp.get();
			existingEmployee.setAge(employee.getAge());
			existingEmployee.setCity(employee.getCity());
			existingEmployee.setName(employee.getName());
			employeeRepository.save(existingEmployee);
			return ResponseEntity.status(HttpStatus.OK).body("Employee updated successfully" + id);
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found" + id);
		}
		
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Integer id){
		employeeRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully" + id);
		}
	}

