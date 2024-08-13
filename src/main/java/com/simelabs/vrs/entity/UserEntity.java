package com.simelabs.vrs.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fullname", nullable = false)
	private String fullname;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phoneno")
	private String phoneNumber;

}
