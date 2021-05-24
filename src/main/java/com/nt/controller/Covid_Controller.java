package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nt.entity.Covid_Model;
import com.nt.service.Covid_Service;

@Controller
public class Covid_Controller {

	@Autowired
	private Covid_Service covid;




	@GetMapping("/covid")
	public String covid(Model model)

	{ 
		List<Covid_Model> fatchDataAb = this.covid.fatchDataAb();

		

		System.out.println(fatchDataAb);
		model.addAttribute("fatch", fatchDataAb);
		
		
		return "home";
	}

}
