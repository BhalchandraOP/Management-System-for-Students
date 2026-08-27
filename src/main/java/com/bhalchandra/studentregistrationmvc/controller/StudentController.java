package com.bhalchandra.studentregistrationmvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.bhalchandra.studentregistrationmvc.model.Student;
import com.bhalchandra.studentregistrationmvc.repo.StudentRepo;
import com.bhalchandra.studentregistrationmvc.service.StudentService;

@Controller
public class StudentController {
	private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

   	
	@ModelAttribute
	public void commanData(ModelMap m) {
		m.addAttribute("college","TSEC");
	}
	
	@GetMapping("/login")
	public String login(Authentication authentication) {

	    if (authentication != null &&
	        authentication.isAuthenticated()) {

	        return "redirect:/";
	    }

	    return "login";
	}
	
	
	@GetMapping("/")
	public String home() {
		System.out.println("Home Page");
		return "index";
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@ModelAttribute Student s) {	
		service.addStudent(s);
		return "result";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
	@GetMapping("/showStudents")
	public String showStudents(ModelMap m) {
		
		List<Student> list= service.getAllStudents();
		
//		System.out.println("Inside Student controller!");
		
		m.addAttribute("student",list);
		
		return "student";
	}
	
	
	@GetMapping("/getStudent")
	public String getStudent(@RequestParam("sid")int id,ModelMap m) {
		
		Student student = service.getStudent(id);
		
		m.addAttribute("student", student);
		
		return "result";
	}
	
	
	@PostMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("sid") int id) {

		 service.deleteStudent(id);
		 
	    return "redirect:/showStudents";
	}
	
	
	
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute Student student, ModelMap m) {

		boolean updated = service.updateStudent(student);

		if (updated) {
		    m.addAttribute("msg", "Student updated successfully.");
		} else {
		    m.addAttribute("msg", "Student ID not found.");
		}

		m.addAttribute("student", service.getAllStudents());

		return "student";
	}

}
