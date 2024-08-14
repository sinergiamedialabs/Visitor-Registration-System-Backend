package com.simelabs.vrs.mock;

import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.request.UserRequest;
import com.simelabs.vrs.response.BaseResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EntityMocks {

	public static UserEntity getUserEntity() {
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setFullName("Rahul EK");
		user.setEmail("rahukek@gmail.com");
		user.setPhoneNumber("1234567890");
		return user;
	}

	public static List<UserEntity> getUserEntityList() {
		List<UserEntity> users = new ArrayList<>();
		UserEntity user = new UserEntity();
		user.setId(2L);
		user.setFullName("Midhun K");
		user.setEmail("midhunk@example.com");
		user.setPhoneNumber("0987654321");
		users.add(user);
		return users;
	}

	public static UserRequest getUserRequest() {
		UserRequest userRequest = new UserRequest();
		userRequest.setFullName("Rahul EK");
		userRequest.setEmail("rahukek@gmail.com");
		userRequest.setPhoneNumber("1234567890");
		return userRequest;
	}

	public static List<VenueEntity> getVenueEntityList() {
		VenueEntity venue1 = new VenueEntity();
		venue1.setId(1L);
		venue1.setName("Venue 1");

		VenueEntity venue2 = new VenueEntity();
		venue2.setId(2L);
		venue2.setName("Venue 2");

		return Arrays.asList(venue1, venue2);
	}

	public static List<EventEntity> getEventEntityList() {
		EventEntity event1 = new EventEntity();
		event1.setId(1L);
		event1.setName("Event 1");

		EventEntity event2 = new EventEntity();
		event2.setId(2L);
		event2.setName("Event 2");

		return Arrays.asList(event1, event2);
	}

	public static BaseResponse<Map<String, Object>> getBaseResponse(Map<String, Object> data, String message,
			String code, boolean status) {
		BaseResponse<Map<String, Object>> baseResponse = new BaseResponse<>();
		baseResponse.setData(data);
		baseResponse.setMessage(message);
		baseResponse.setCode(code);
		baseResponse.setStatus(status);
		return baseResponse;
	}

}
