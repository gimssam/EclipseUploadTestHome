package com.example.myapp.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.myapp.hr.model.Emp;
import com.example.myapp.hr.service.IEmpService;

// Controller 등록 servlet-context.xml에 함
@Controller
public class EmpController {
	@Autowired
	IEmpService empService;
	
	// (실습 첫 코딩) http://localhost:8090/myapp/hr/count?deptid=50 => 파라미터 테스트 확인
	@GetMapping("/hr/count")
	public String empCount(@RequestParam(value = "deptid", required = false, defaultValue = "0") int deptid,
			Model model) {
		if(deptid==0) {
			model.addAttribute("count",empService.getEmpCount());
		}else {
			model.addAttribute("count",empService.getEmpCount(deptid));
		}
		return "hr/count"; // 뷰에는 /안붙이고 써서 구분을 해주는게 좋음
	}
	
	// 스프링 버전 2.5로 되어 있으면 사용 = 구지 사용할 필요는 없음
//	@GetMapping("/hr/count")
//	public ModelAndView empCount(@RequestParam(value = "deptid", required = false, defaultValue = "0") int deptid)) 
//	{
//		ModelAndView mav = new ModelAndView();
//		if(deptid==0) {
//			mav.addObject("count",empService.getEmpCount());
//		}else {
//			mav.addObject("count",empService.getEmpCount(deptid));
//		}
//		mav.setViewName("hr/count");
//	}
	
	// http://localhost:8090/myapp/hr/list
	@GetMapping("/hr/list")
	public String getAllEmps(Model model) {
		List<Emp> empList = empService.getEmpList();
		model.addAttribute("empList",empList);
		return "hr/list";
	}
	// http://localhost:8090/myapp/hr/100
	@GetMapping("/hr/{employeeId}")
	public String getEmpInfo(@PathVariable int employeeId, Model model) {
		Emp emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp",emp);
		return "hr/view";
	}
	
	
	
	
	
}
