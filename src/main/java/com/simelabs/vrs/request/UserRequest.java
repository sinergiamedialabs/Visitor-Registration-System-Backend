package com.simelabs.vrs.request;

import lombok.Data;

@Data
public class UserRequest {

	private Long id;

	private String fullName;

	private String email;

	private String phoneNumber;

}
