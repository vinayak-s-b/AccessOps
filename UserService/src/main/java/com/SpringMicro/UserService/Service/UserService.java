package com.SpringMicro.UserService.Service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SpringMicro.UserService.Controller.UserController;
import com.SpringMicro.UserService.Model.Department;
import com.SpringMicro.UserService.Model.User;
import com.SpringMicro.UserService.Model.WrapperClass;
import com.SpringMicro.UserService.Repository.UserRepository;

import ch.qos.logback.classic.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserService {
	Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		// TODO Auto-generated method stub
		logger.info("save user Service");
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		logger.info("get all user Service");
		return userRepository.findAll();
	}
	@CircuitBreaker(name = "department", fallbackMethod = "deptFallBackMethod")
	public WrapperClass getUserById(long id) {
		// TODO Auto-generated method stub
		logger.info("get by id user Service");
		User user=new User();
		user= userRepository.getUserById(id);
		
		Department department=restTemplate.getForObject("http://DEPARTMENTSERVICE/departments/get/"+user.getDepartmentid(), Department.class);
		
		
		WrapperClass wr=new WrapperClass();
		wr.setUser(user);
		wr.setDepartment(department);
		
		return wr;
	}
	public WrapperClass deptFallBackMethod(Throwable e) {
		System.out.println("dept service is down please try later");
		return 	null;
		}
	

}
