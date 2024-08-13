package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.model.EventModel;
import com.simelabs.vrs.model.InviteesModel;
import com.simelabs.vrs.model.UserModel;
import com.simelabs.vrs.model.VenueModel;
import com.simelabs.vrs.repository.EventRepository;
import com.simelabs.vrs.repository.InviteesRepository;
import com.simelabs.vrs.repository.UserRepository;
import com.simelabs.vrs.repository.VenueRepository;
import com.simelabs.vrs.request.InviteesRequest;
import com.simelabs.vrs.service.InviteesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InviteesServiceImpl implements InviteesService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    InviteesRepository inviteesRepository;

    @Override
    public InviteesModel createInvitees(InviteesRequest inviteesRequest){

        InviteesModel inviteesModel = new InviteesModel();
        InviteesEntity inviteesEntity = new InviteesEntity();

        inviteesEntity.setId(inviteesEntity.getId());

        Optional<UserEntity> userEntity = userRepository.findById(inviteesRequest.getUserId());
        inviteesEntity.setUser(userEntity.get());

        Optional<VenueEntity> venueEntity = venueRepository.findById(inviteesRequest.getVenueId());
        inviteesEntity.setVenue(venueEntity.get());

        Optional<EventEntity> eventEntity = eventRepository.findById(inviteesRequest.getEventId());
        inviteesEntity.setEvent(eventEntity.get());

        inviteesEntity.setStatus(inviteesRequest.isStatus());

        InviteesEntity inviteEntity = inviteesRepository.save(inviteesEntity);
        inviteesModel.setId(inviteEntity.getId());
        inviteesModel.setStatus(inviteEntity.getStatus());
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.get().getId());
        userModel.setFullName(userEntity.get().getFullName());
        userModel.setEmail(userEntity.get().getEmail());
        userModel.setPhoneNumber(userEntity.get().getPhoneNumber());
        inviteesModel.setUser(userModel);

        VenueModel venueModel = new VenueModel();
        venueModel.setId(venueEntity.get().getId());
        venueModel.setName(venueEntity.get().getName());
        venueModel.setAddress(venueEntity.get().getAddress());
        inviteesModel.setVenue(venueModel);

        EventModel eventModel = new EventModel();
        eventModel.setId(eventEntity.get().getId());
        eventModel.setName(eventEntity.get().getName());
        inviteesModel.setEvent(eventModel);

        return inviteesModel;

    }
}
