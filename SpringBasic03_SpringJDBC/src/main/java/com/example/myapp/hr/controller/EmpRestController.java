package com.example.myapp.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.hr.model.Emp;
import com.example.myapp.hr.service.IEmpService;

@RestController // 스프링 4버전이후로 사용가능 = 없으면 잭슨라이브러리 써서 매핑시켜야 함 => 아니면 ReponseBody로 처리
public class EmpRestController { //JSON 반환하기
	
	@Autowired
	IEmpService empService;
	// http://localhost:8090/myapp/hr2/emps
	@GetMapping("/hr2/emps") 
	public List<Emp> getEmps(){ // 리턴이 List면 배열에
		return empService.getEmpList();
	}
}
