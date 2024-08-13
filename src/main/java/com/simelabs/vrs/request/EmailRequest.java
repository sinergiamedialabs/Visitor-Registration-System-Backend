package com.simelabs.vrs.request;

import lombok.Data;

@Data
public class EmailRequest {

	private String url;

	private String from;

	private String to;

}
