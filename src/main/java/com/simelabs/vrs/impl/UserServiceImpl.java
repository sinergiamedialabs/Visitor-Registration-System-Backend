package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.repository.UserRepository;
import com.simelabs.vrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

}