package com.simelabs.vrs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "visites")
public class VisitesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "invitees_id", nullable = false)
	private InviteesEntity invitees;

	@Column(name = "barcode", nullable = false)
	private String barcode;

}
