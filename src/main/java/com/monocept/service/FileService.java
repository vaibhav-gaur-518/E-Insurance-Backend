package com.monocept.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.monocept.entity.Document;


public interface FileService {

	Document store(int userId, MultipartFile file) throws IOException;

	Set<Document> findById(int userId);

	List<Document> findAll();

	Document approve(long documentId);

}
