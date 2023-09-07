package com.monocept.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.entity.Document;
import com.monocept.entity.DocumentResponse;
import com.monocept.service.FileService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService service;
	
	@PostMapping("/upload/{userId}")
	  public void uploadFile(@PathVariable int userId ,@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	      service.store(userId,file);
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	    }
	  }
	
	@GetMapping("/allfiles")
	public List<Document> findAll() {
		List<Document> files = service.findAll();
		return files;
	}

	@GetMapping("/allfiles/{userId}")
	public Set<Document> findAll(@PathVariable int userId) {
		 
		Set<Document> files = service.findById(userId);
		return files;
	}
	
	@PutMapping("/approve/{documentId}")
	public Document approve(@PathVariable int documentId) {
		 
		return service.approve(documentId);
		
	}
}
