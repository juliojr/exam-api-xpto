package com.xpto.cities.payload;

public class CitiesFileResponse {
    private String message;
    private String fileType;
    private long size;

    public CitiesFileResponse( String message, String fileType, long size) {
        this.message = message;
        this.fileType = fileType;
        this.size = size;
    }
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
