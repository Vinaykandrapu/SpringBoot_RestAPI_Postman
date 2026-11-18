package com.vinay.studentmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinay.studentmanagement.dto.StudentRequestDTO;
import com.vinay.studentmanagement.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

//	public List<Student> findByNameContaining(String name);
	
	public List<Student> findByEmailContaining(String email);

	public Page<Student> findByNameContaining(String name, Pageable pageable);
	
	boolean existsByEmail(String email);
}
