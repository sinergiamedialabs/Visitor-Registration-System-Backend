package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.model.UserModel;
import com.simelabs.vrs.repository.UserRepository;
import com.simelabs.vrs.request.UserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Test
	void testGetAllUsers() {
		List<UserEntity> mockUserList = EntityMocks.getUserEntityList();
		when(userRepository.findAll()).thenReturn(mockUserList);

		List<UserEntity> result = userServiceImpl.getAllUsers();

		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals("Rahul EK", result.get(0).getFullName());
		Assertions.assertEquals("midhunk@example.com", result.get(1).getEmail());
	}

	@Test
	void testAddUser() {
		UserRequest userRequest = EntityMocks.getUserRequest();
		UserEntity userEntity = EntityMocks.getUserEntity();
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

		UserModel result = userServiceImpl.addUser(userRequest);

		Assertions.assertNotNull(result);
		Assertions.assertEquals("Rahul EK", result.getFullName());
		Assertions.assertEquals("rahukek@gmail.com", result.getEmail());
		Assertions.assertEquals("1234567890", result.getPhoneNumber());
	}

}
