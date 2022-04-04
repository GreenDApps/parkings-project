package io.greendapps.parkingapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordEntity {

	@JsonProperty(value = "fields")
	private FieldsEntity fields;

	public FieldsEntity getFields() {
		return fields;
	}

	public void setFields(FieldsEntity fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "RecordEntity [fields=" + fields + ", getFields()=" + getFields() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
