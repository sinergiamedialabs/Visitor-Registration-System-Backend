package com.simelabs.vrs.service;

import com.simelabs.vrs.model.InviteesModel;
import com.simelabs.vrs.request.InviteesRequest;

public interface InviteesService {

  InviteesModel createInvitees(InviteesRequest inviteesRequest);
}
