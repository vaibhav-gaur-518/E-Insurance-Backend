package com.monocept.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id")
	private long documentId;

	@Column(name = "document_name")
	private String documentName;

	@Column(name = "document_status")
	private Status documentStatus;

	@Lob
	@Column(name = "document_data")
	private byte[] documentData;

	public Document() {

	}

	public Document(long documentId, String documentName, Status documentStatus, byte[] documentData) {
		super();
		this.documentId = documentId;
		this.documentName = documentName;
		this.documentStatus = documentStatus;
		this.documentData = documentData;
	}

	public Document(String documentName, Status documentStatus, byte[] documentData) {
		super();
		this.documentName = documentName;
		this.documentStatus = documentStatus;
		this.documentData = documentData;
	}

	public long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Status getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(Status documentStatus) {
		this.documentStatus = documentStatus;
	}

	public byte[] getDocumentData() {
		return documentData;
	}

	public void setDocumentData(byte[] documentData) {
		this.documentData = documentData;
	}
}