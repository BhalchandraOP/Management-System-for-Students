package com.bhalchandra.studentregistrationmvc.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bhalchandra.studentregistrationmvc.model.Student;
import com.bhalchandra.studentregistrationmvc.repo.StudentRepo;

@Service
public class StudentService {

	private StudentRepo repo;

	public StudentService(StudentRepo repo) {
		super();
		this.repo = repo;
	}

	public void deleteStudent(int id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
	}
	
	
	public List<Student> getAllStudents(){
		return repo.findAll();
		}
	
	
	public Student getStudent(int id) {

	    return repo.findById(id).orElse(null);
	}
	
	
	public void addStudent(Student student) {
	    repo.save(student);
	}
	
	
	public boolean updateStudent(Student student) {

	    if (repo.existsById(student.getSid())) {
	        repo.save(student);
	        return true;
	    }

	    return false;
	}
	
}
