package br.org.com.hc.core.jsonconverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalDateSerializer extends StdConverter<LocalDate, String> {

	static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String convert(LocalDate value) {
		System.out.println("LocalDate ");
		return value.format(DATE_FORMATTER);
	}
}