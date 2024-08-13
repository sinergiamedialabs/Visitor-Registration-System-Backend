package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.*;
import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.repository.InviteesRepository;
import com.simelabs.vrs.repository.VisitesRepository;
import com.simelabs.vrs.request.VisitsRequest;
import com.simelabs.vrs.service.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitsServiceImpl implements VisitsService {

    @Autowired
    InviteesRepository inviteesRepository;

    @Autowired
    VisitesRepository visitesRepository;

    @Override
    public VisitsModel saveVisits(VisitsRequest request) {
        InviteesEntity inviteesEntity = inviteesRepository.findById(request.getInvitees_id())
                .orElseThrow(() -> new IllegalArgumentException("Invitees not found"));

        if (request.isAccepted() == true) {
            VisitesEntity visitesEntity = new VisitesEntity();
            visitesEntity.setInvitees(inviteesEntity);
            visitesEntity.setBarcode(request.getBarCode());

            VisitesEntity savedVisit = visitesRepository.save(visitesEntity);

            inviteesEntity.setStatus(true);
            inviteesRepository.save(inviteesEntity);

            return mapToVisitsResponse(savedVisit);
        } else {

            inviteesEntity.setStatus(true);
            inviteesRepository.save(inviteesEntity);

            VisitsModel response = new VisitsModel();
            response.setBarcode("Invitee status updated but visit not saved");
            return response;
        }
    }

    private VisitsModel mapToVisitsResponse(VisitesEntity visitesEntity) {
        VisitsModel visitsModel = new VisitsModel();

        visitsModel.setId(visitesEntity.getId());
        visitsModel.setBarcode(visitesEntity.getBarcode());

        return visitsModel;
    }
}


