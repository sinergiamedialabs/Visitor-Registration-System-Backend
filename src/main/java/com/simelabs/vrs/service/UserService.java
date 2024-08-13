package com.simelabs.vrs.service;

import com.simelabs.vrs.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

	List<UserEntity> getAllUsers();

}