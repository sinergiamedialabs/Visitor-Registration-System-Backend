package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testGetAllUsers() {
		List<UserEntity> mockUserList = EntityMocks.mockUserEntityList();
		when(userRepository.findAll()).thenReturn(mockUserList);

		List<UserEntity> result = userServiceImpl.getAllUsers();

		assertEquals(2, result.size());
		assertEquals("John Doe", result.get(0).getFullName());
		assertEquals("jane.smith@example.com", result.get(1).getEmail());
	}

}
