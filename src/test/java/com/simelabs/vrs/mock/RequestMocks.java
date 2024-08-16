package com.simelabs.vrs.mock;

import com.simelabs.vrs.request.InviteesRequest;
import com.simelabs.vrs.request.VisitsRequest;

public class RequestMocks {

	public static VisitsRequest visitsRequest_WhenAcceptedTrue() {
		VisitsRequest request = new VisitsRequest();
		request.setInvitees_id(1L);
		request.setBarCode("123456");
		request.setAccepted(true);
		return request;
	}

	public static VisitsRequest visitsRequest_WhenAcceptedFalse() {
		VisitsRequest request = new VisitsRequest();
		request.setInvitees_id(1L);
		request.setBarCode("123456");
		request.setAccepted(false);
		return request;
	}

	public static InviteesRequest getInviteesRequest() {
		InviteesRequest request = new InviteesRequest();
		request.setId(1L);
		request.setUserId(1L);
		request.setVenueId(1L);
		request.setEventId(1L);
		request.setEmail("simelabs@gmail.com");
		request.setUrl("www.simelabs.com");
		request.setStatus(true);
		request.setEmailTrigger(true);
		return request;
	}

	public static VisitsRequest getVisitsRequest() {
		VisitsRequest visitsRequest = new VisitsRequest();
		visitsRequest.setInvitees_id(1L);
		visitsRequest.setBarCode("1234567");
		visitsRequest.setAccepted(true);
		return visitsRequest;
	}

}
