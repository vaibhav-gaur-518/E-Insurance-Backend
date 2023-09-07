package com.monocept.entity;

public class DocumentResponse {
	private long documentId;
	private String documentName;
	private Status documentStatus;
	private String documentData;

	public DocumentResponse(long documentId, String documentName, Status documentStatus, String documentData) {
		super();
		this.documentId = documentId;
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

	public String getDocumentData() {
		return documentData;
	}

	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}

}
