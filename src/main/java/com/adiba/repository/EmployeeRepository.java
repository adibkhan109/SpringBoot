package com.adiba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adiba.model.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
