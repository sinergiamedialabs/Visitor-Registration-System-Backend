package com.simelabs.vrs.model;

import lombok.Data;

import java.sql.Date;

@Data
public class EventModel {

	private Long id;

	private String name;

	private Date eventDate;

}
