package com.simelabs.vrs.mock;

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

}
