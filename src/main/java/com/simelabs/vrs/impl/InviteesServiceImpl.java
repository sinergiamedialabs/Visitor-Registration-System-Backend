package com.simelabs.vrs.impl;

import com.simelabs.vrs.constants.MessageCodes;
import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.repository.InviteesRepository;
import com.simelabs.vrs.service.InviteesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteesServiceImpl implements InviteesService {

	@Autowired
	private InviteesRepository inviteesRepository;

	@Override
	public InviteesEntity getInviteeById(Long id) {
		return inviteesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(MessageCodes.NO_DATA_FOUND_MESSAGE));
	}

}
