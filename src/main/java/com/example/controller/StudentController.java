package com.example.controller;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ZXingHelper.ZXingHelper;
import com.example.models.Student;
import com.example.repository.StudentRepository;

@Controller
public class StudentController 
{
	@Autowired
	private StudentRepository studentRepo;
	
	@RequestMapping({"","/home"})
	public String home(Student student, Model model) {
		model.addAttribute("data", studentRepo.findAll());
		return "index";
	}
	
	@RequestMapping(value = "/barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception
	{
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 200, 50));
		outputStream.flush();
		outputStream.close();
	}
	
}
