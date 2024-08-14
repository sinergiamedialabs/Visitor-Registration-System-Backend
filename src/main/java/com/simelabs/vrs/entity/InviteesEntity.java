package com.simelabs.vrs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "invitees")
public class InviteesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@OneToOne
	@JoinColumn(name = "venue_id", nullable = false)
	private VenueEntity venue;

	@OneToOne
	@JoinColumn(name = "event_id", nullable = false)
	private EventEntity event;

	@Column(name = "status", nullable = false)
	private Boolean status;

}
