package com.xpto.cities.dto;

public class ResumeColumnDto {

	private String column;
	private Long distinctRecords;

	public ResumeColumnDto(String column, Long distinctRecords) {
		super();
		this.column = column;
		this.distinctRecords = distinctRecords;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Long getDistinctRecords() {
		return distinctRecords;
	}

	public void setDistinctRecords(Long distinctRecords) {
		this.distinctRecords = distinctRecords;
	}

}
