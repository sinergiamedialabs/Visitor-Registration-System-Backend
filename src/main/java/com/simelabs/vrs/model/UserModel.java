package com.simelabs.vrs.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserModel {

	private Long id;

	private String fullName;

	private String email;

	private String phoneNumber;

}
