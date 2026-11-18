package com.vinay.studentmanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.studentmanagement.dto.StudentPageResponse;
import com.vinay.studentmanagement.dto.StudentRequestDTO;
import com.vinay.studentmanagement.dto.StudentResponseDTO;
import com.vinay.studentmanagement.model.Student;
import com.vinay.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentManagement")
public class StudentController {

	StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}
	
	@PostMapping("/addStudents")
	public StudentResponseDTO saveStudents(@Valid @RequestBody StudentRequestDTO students) {
		return service.saveStudents(students);
	}
	
	@GetMapping("/getStudents")
	public List<StudentResponseDTO> getStudents() {
		return service.getStudents();
	}
	
	@GetMapping("getStudents/{id}")
	public StudentResponseDTO getStudentById(@PathVariable Long id) {
		return service.getStudentById(id);
	}
	
	@PutMapping("/updateStudents/{id}")
	public StudentResponseDTO updateStudents(@PathVariable long id, @RequestBody StudentRequestDTO student) {
		return service.updateStudents(id, student);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		service.deleteStudent(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
//	@GetMapping("/searchByName")
//	public List<Student> getStudentByName(@RequestParam String name) {
//		return service.getStudentByName(name);
//	}
	
	@GetMapping("/searchByEmail")
	public List<Student> getStudentByEmail(@RequestParam String email) {
		return service.getStudentByEmail(email);
	}
	
	@GetMapping("/studentPerPage")
	public Page<Student> studentsPerPage(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "id") String sortBy, String direction) {
		if(page < 0) {
			throw new IllegalArgumentException("Page number can't be Negative");
		}
		
		if(size <= 0) {
			throw new IllegalArgumentException("Page size number must be greater than 0");
		}
		return service.StudentsPerPage(page, size, sortBy, direction);
	}
	
	@GetMapping("/searchStudents")
	public StudentPageResponse searchStudents(@RequestParam String search, @RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") String direction) {
		if(page < 0) {
			throw new IllegalArgumentException("Page number can't be Negative");
		}
		
		if(size <= 0) {
			throw new IllegalArgumentException("Page size number must be greater than 0");
		}
		
		if(size > 100) {
			throw new IllegalArgumentException("Page size number Larger");
		}
		
		if(!"ASC".equalsIgnoreCase(direction) && !"DESC".equalsIgnoreCase(direction)) {
			throw new IllegalArgumentException("Enter valid direction..");
		}
		
		List<String> allowedSortFields = Arrays.asList("id", "name", "email");
		
		boolean validSortColumn = allowedSortFields.stream().anyMatch(field -> field.equalsIgnoreCase(sortBy));
		
		if(!validSortColumn) {
			throw new IllegalArgumentException("Enter valid column present in model class");
		}
		
		return service.searchStudents(search, page, size, sortBy, direction);
	}
	
}	


