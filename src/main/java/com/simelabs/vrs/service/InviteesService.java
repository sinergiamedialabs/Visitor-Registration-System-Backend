package com.simelabs.vrs.service;

import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.model.InviteesModel;
import com.simelabs.vrs.request.InviteesRequest;

public interface InviteesService {

	InviteesEntity getInviteeById(Long id);

  InviteesModel createInvitees(InviteesRequest inviteesRequest);
}
