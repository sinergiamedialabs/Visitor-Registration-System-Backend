package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.model.UserModel;
import com.simelabs.vrs.repository.UserRepository;
import com.simelabs.vrs.request.UserRequest;
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

	@Override
	public UserModel addUser(UserRequest userRequest) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFullName(userRequest.getFullName());
		userEntity.setEmail(userRequest.getEmail());
		userEntity.setPhoneNumber(userRequest.getPhoneNumber());

		UserEntity savedUserEntity = userRepository.save(userEntity);
		UserModel userModel = new UserModel();
		userModel.setId(savedUserEntity.getId());
		userModel.setFullName(savedUserEntity.getFullName());
		userModel.setEmail(savedUserEntity.getEmail());
		userModel.setPhoneNumber(savedUserEntity.getPhoneNumber());

		return userModel;
	}

}