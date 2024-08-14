package com.simelabs.vrs.service;

import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.model.UserModel;
import com.simelabs.vrs.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

	List<UserEntity> getAllUsers();

	UserModel addUser(UserRequest userRequest);

}