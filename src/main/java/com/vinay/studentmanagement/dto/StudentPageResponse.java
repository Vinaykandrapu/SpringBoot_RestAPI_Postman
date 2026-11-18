package com.vinay.studentmanagement.dto;

import java.util.List;

public class StudentPageResponse {

	private List<StudentResponseDTO> students;
	
	private int currentPage = 0;
	
	private long totalItems = 0;
	
	private int totalPages = 0;
	
	private int pageSize = 0;

	public List<StudentResponseDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentResponseDTO> students) {
		this.students = students;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public StudentPageResponse(List<StudentResponseDTO> students, int currentPage, long totalItems, int totalPages,
			int pageSize) {
		super();
		this.students = students;
		this.currentPage = currentPage;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
	}

	public StudentPageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
