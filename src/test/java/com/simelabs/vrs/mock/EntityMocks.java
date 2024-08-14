package com.simelabs.vrs.mock;

import com.simelabs.vrs.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class EntityMocks {

	public static UserEntity mockUserEntity() {
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setFullName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPhoneNumber("1234567890");
		return user;
	}

	public static List<UserEntity> mockUserEntityList() {
		List<UserEntity> users = new ArrayList<>();
		users.add(mockUserEntity());
		UserEntity anotherUser = new UserEntity();
		anotherUser.setId(2L);
		anotherUser.setFullName("Jane Smith");
		anotherUser.setEmail("jane.smith@example.com");
		anotherUser.setPhoneNumber("0987654321");
		users.add(anotherUser);
		return users;
	}

}
