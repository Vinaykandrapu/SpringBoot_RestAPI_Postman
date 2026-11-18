package com.vinay.studentmanagement.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vinay.studentmanagement.dto.StudentPageResponse;
import com.vinay.studentmanagement.dto.StudentRequestDTO;
import com.vinay.studentmanagement.dto.StudentResponseDTO;
import com.vinay.studentmanagement.exception.StudentNotFoundException;
import com.vinay.studentmanagement.model.Student;
import com.vinay.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}
	
	public StudentResponseDTO saveStudents(StudentRequestDTO students) {
		boolean emailFound = repository.existsByEmail(students.getEmail());
		
		if(emailFound) {
			throw new IllegalArgumentException("Email already Registered: " + students.getEmail());
		}
		
		Student studentdto = new Student(students.getName(), students.getEmail());
		Student student = repository.save(studentdto);
		return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail());
	}

	public List<StudentResponseDTO> getStudents() {
		// TODO Auto-generated method stub
		return repository.findAll().stream().map(student -> 
														new StudentResponseDTO(student.getId(), student.getName(), student.getEmail())).toList();
	}

	public StudentResponseDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student student =  repository.findById(id).orElseThrow(() ->  new StudentNotFoundException("Student not found with ID: " + id));
		
		return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail());
	}

	public StudentResponseDTO updateStudents(Long id, StudentRequestDTO student) {
		// TODO Auto-generated method stub
		Student existingStudent =  repository.findById(id).orElseThrow(() ->  new StudentNotFoundException("Student not found with ID: " + id));
		
		
		if(!existingStudent.getEmail().equalsIgnoreCase(student.getEmail()) && repository.existsByEmail(student.getEmail())) {
			throw new IllegalArgumentException("Email already Registered: " + student.getEmail());
		}
		
		existingStudent.setName(student.getName());
		existingStudent.setEmail(student.getEmail());
		
		Student studentresponse = repository.save(existingStudent);
		return new StudentResponseDTO(studentresponse.getId(), studentresponse.getName(), studentresponse.getEmail());
	}

	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
		repository.deleteById(id);
	}

//	public List<Student> getStudentByName(String name) {
//		// TODO Auto-generated method stub
//		return repository.findByNameContaining(name);
//	}

	public List<Student> getStudentByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmailContaining(email);
	}
	
	public Page<Student> StudentsPerPage(int page, int size, String sortBy, String direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));
		return repository.findAll(pageable);
	}

	public StudentPageResponse searchStudents(String search, int page, int size, String sortBy, String direction) {
		// TODO Auto-generated method stub
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));
		Page<Student> studentPage  = repository.findByNameContaining(search, pageable);
		
		StudentPageResponse pageResponse = new StudentPageResponse();
		
		List<StudentResponseDTO> students = studentPage.getContent().stream().map(student -> new StudentResponseDTO(student.getId(), student.getName(), student.getEmail())).toList();
		
		pageResponse.setStudents(students);
		//pageResponse.setListofStudents(studentPage.getContent().stream().map(student -> new StudentResponseDTO(student.getId(), student.getName(), student.getEmail())));
		pageResponse.setCurrentPage(studentPage.getNumber());
		pageResponse.setTotalItems(studentPage.getTotalElements());
		pageResponse.setTotalPages(studentPage.getTotalPages());
		pageResponse.setPageSize(studentPage.getSize());
		
		return pageResponse;
	}
	
		
}
