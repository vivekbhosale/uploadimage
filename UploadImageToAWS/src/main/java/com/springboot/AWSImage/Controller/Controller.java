package com.springboot.AWSImage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.AWSImage.Service.Output;
import com.springboot.AWSImage.Service.Result;
import com.springboot.AWSImage.Service.UploadService;


@RestController
@RequestMapping("/file")
public class Controller {

	@Autowired
	private UploadService service;
	
	@GetMapping("/home")
	public String firstpage(){
		return "Welcome";
	}
	
	@PostMapping("/upload")
	public Result uploadImage(@RequestParam(value = "file") MultipartFile[] file) {
		return service.uploadImage(file);
		
	}
	
	@PostMapping("/getImage")
	public String getImage(@RequestBody Output output) {
		service.getImage(output);
		return "success";
	}
	
}
