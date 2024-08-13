package com.simelabs.vrs.request;

import lombok.Data;

@Data
public class InviteesRequest {

	private Long id;

	private Long userId;

	private Long venueId;

	private Long eventId;

	private String email;

	private String url;

	private boolean status;

	private boolean emailTrigger;

}
