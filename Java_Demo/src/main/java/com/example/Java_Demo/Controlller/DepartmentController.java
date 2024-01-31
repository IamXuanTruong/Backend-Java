package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Models.Department;
import com.example.Java_Demo.Repository.DepartmentRepository;
import com.example.Java_Demo.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<List<Department>>getAllDepartment(){
        List<Department> departments= departmentService.getAllDepartment();
        return new  ResponseEntity<>(departments, HttpStatus.OK);

    }
    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestBody Department department){
        departmentService.createDepartment(department);
        return ResponseEntity.ok().body("+1");
    }
    @GetMapping("/detail/{id}")
    public Department getDepartmentid(@PathVariable Long id){
        return departmentRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        if (departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return ResponseEntity.ok("ok");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("eerr");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Department>updateDepartment(@RequestBody Department department,@PathVariable Long id){
        Department department1 = departmentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Department not found"));
        department1.setName_department(department.getName_department());
        Department updateDepartment = departmentRepository.save(department1);
        return ResponseEntity.ok(updateDepartment);
    }
}
