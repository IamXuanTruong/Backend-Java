package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.Department;
import com.example.Java_Demo.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
    public Optional<Department> getDepartmentid(Long id){
        return departmentRepository.findById(id);
    }
    public Department createDepartment(Department department){
        Department department1 = new Department();
        department1.setName_department(department.getName_department());
        return departmentRepository.save(department1);
    }
    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }
}
