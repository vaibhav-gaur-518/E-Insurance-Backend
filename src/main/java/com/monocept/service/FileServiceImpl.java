package com.monocept.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.entity.Customer;
import com.monocept.entity.Document;
import com.monocept.entity.Status;
import com.monocept.exception.UserNotFoundException;
import com.monocept.repository.CustomerRepository;
import com.monocept.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository repository;

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Document store(int userId, MultipartFile file) throws IOException {

		List<Customer> findAll = customerRepo.findAll();

		for (Customer c : findAll) {
			if (c.getUser().getUserId() == userId) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Document FileDB = new Document(fileName, Status.INACTIVE, fileName.getBytes());

				Set<Document> documents = c.getDocuments();
				documents.add(FileDB);
				c.setDocuments(documents);

				customerRepo.save(c);
				return repository.save(FileDB);

			}
		}
		return null;
	}

	@Override
	public Set<Document> findById(int userId) {
		List<Customer> findAll = customerRepo.findAll();
		for (Customer c : findAll) {
			if (c.getUser().getUserId() == userId) {
				return c.getDocuments();
			}
		}
		return null;
	}

	@Override
	public List<Document> findAll() {
		List<Document> documents = new ArrayList<>();
		List<Customer> findAll = customerRepo.findAll();
		for (Customer c : findAll) {
			documents.addAll(c.getDocuments());
		}
		return documents;
	}

	@Override
	public Document approve(long documentId) {
		Optional<Document> findById = repository.findById(documentId);
		if (!findById.isPresent())
			throw new UserNotFoundException("Document with id " + findById.get().getDocumentId() + " not found");
		Document document = findById.get();

		Status currentStatus = document.getDocumentStatus();

		if (currentStatus == Status.ACTIVE) {
			document.setDocumentStatus(Status.INACTIVE);

		} else if (currentStatus == Status.INACTIVE) {
			document.setDocumentStatus(Status.ACTIVE);
		}
		return repository.save(document);
	}
}