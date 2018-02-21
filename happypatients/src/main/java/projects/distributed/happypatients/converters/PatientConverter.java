package projects.distributed.happypatients.converters;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Row;

import projects.distributed.happypatients.springboot.model.Patient;

@Component
public class PatientConverter implements Converter<Row, Patient> {

	@Override
	public Patient convert(Row row) {
		Patient patient = new Patient();

		UUID id = row.getUUID("pid");
		patient.setId(id.toString());
		patient.setDateOfBirth(row.getTimestamp("dob"));
		patient.setPhoneNumber(row.getString("phonenumber"));
		patient.setName(row.getString("pname"));
		patient.setAddress(row.getString("address"));
		patient.setBldGroup(row.getString("bloodgrp"));
		patient.setHeight(row.getDecimal("height") != null ? row.getDecimal("height").floatValue() : 0);
		patient.setWeight(row.getDecimal("weight") != null ? row.getDecimal("weight").floatValue() : 0);
		return patient;
	}

}
