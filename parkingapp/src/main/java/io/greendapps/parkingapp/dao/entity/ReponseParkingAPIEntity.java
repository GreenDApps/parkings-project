package io.greendapps.parkingapp.dao.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReponseParkingAPIEntity {

	@JsonProperty(value = "records")
	public List<RecordEntity> records;

	public List<RecordEntity> getRecords() {
		return records;
	}

	public void setRecords(List<RecordEntity> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "ReponseParkingAPIEntity [records=" + records + ", getRecords()=" + getRecords() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
