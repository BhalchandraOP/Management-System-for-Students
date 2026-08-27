package com.bhalchandra.studentregistrationmvc;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bhalchandra.studentregistrationmvc.model.Student;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger LOGGER =
	        LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution (public * com.bhalchandra.studentregistrationmvc.controller.SController.showStudents())" )
	public void logBefore() {
		LOGGER.info("getStudent method get called by Aspect");
	}
	
//	@After("execution (public * com.bhalchandra.studentregistrationmvc.controller.SController.showStudents())" )
//	public void logAfter() {
//		LOGGER.info("getStudent method get executed!");
//	}
	@AfterReturning("execution (public * com.bhalchandra.studentregistrationmvc.controller.SController.showStudents())" )
	public void logAfter() {
		LOGGER.info("getStudent method get executed!");
	}
	
	@AfterThrowing("execution (public * com.bhalchandra.studentregistrationmvc.controller.SController.showStudents())" )
	public void logException() {
		LOGGER.info("Issue");
	}
}
