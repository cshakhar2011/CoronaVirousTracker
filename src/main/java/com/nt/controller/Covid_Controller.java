package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Covid_Model;
import com.nt.service.Covid_Service;

@RestController
public class Covid_Controller {

	@Autowired
	private Covid_Service  covid;
	
	@GetMapping("/covid")
	public List<Covid_Model> covid(Model model)
	
	{
		
		List<Covid_Model> fatchDataAb = this.covid.fatchDataAb();
		
		
		
		
		
		
		
		
		
		return fatchDataAb;
	}
	
	
	

   


}
