package com.simelabs.vrs.mock;

import com.simelabs.vrs.model.InviteesModel;
import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.response.BaseResponse;

public class ModelMocks {

	public static InviteesModel getInviteesModel() {
		InviteesModel request = new InviteesModel();
		request.setId(1L);
		request.setStatus(true);
		return request;
	}

	public static VisitsModel getVisitsModel() {
		VisitsModel visitsModel = new VisitsModel();
		visitsModel.setId(1L);
		visitsModel.setBarcode("1234567");
		return visitsModel;
	}

	public static BaseResponse<VisitsModel> getResponseVisitModel() {
		BaseResponse<VisitsModel> baseResponse = new BaseResponse<>();
		baseResponse.setMessage("Success");
		baseResponse.setCode("200");
		baseResponse.setStatus(true);
		return baseResponse;
	}

}
