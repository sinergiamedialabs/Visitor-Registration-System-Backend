package com.simelabs.vrs.response;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

	public <T> BaseResponse<T> setBaseResponse(T returnObject, String statusCode, String responseMessage,
			boolean status) {

		BaseResponse<T> baseResponse = new BaseResponse<>();
		baseResponse.setData(returnObject);
		baseResponse.setCode(statusCode);
		baseResponse.setMessage(responseMessage);
		baseResponse.setStatus(status);
		return baseResponse;

	}

}
