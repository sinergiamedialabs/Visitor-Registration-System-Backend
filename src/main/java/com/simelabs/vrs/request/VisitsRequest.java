package com.simelabs.vrs.request;

import lombok.Data;

@Data
public class VisitsRequest {

	private long invitees_id;

	private String barCode;

	private boolean accepted;

}
