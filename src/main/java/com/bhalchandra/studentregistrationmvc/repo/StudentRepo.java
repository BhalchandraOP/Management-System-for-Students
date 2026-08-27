package com.bhalchandra.studentregistrationmvc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhalchandra.studentregistrationmvc.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>{

}
