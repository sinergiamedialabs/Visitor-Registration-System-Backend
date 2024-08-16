package com.simelabs.vrs.impl;

import com.simelabs.vrs.constants.MessageCodes;
import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.exception.CustomException;
import com.simelabs.vrs.exception.ResourceNotFoundException;
import com.simelabs.vrs.model.EventModel;
import com.simelabs.vrs.model.InviteesModel;
import com.simelabs.vrs.model.UserModel;
import com.simelabs.vrs.model.VenueModel;
import com.simelabs.vrs.repository.EventRepository;
import com.simelabs.vrs.repository.InviteesRepository;
import com.simelabs.vrs.repository.UserRepository;
import com.simelabs.vrs.repository.VenueRepository;
import com.simelabs.vrs.request.EmailRequest;
import com.simelabs.vrs.request.InviteesRequest;
import com.simelabs.vrs.service.InviteesService;
import com.simelabs.vrs.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class InviteesServiceImpl implements InviteesService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailUtils emailUtils;

	@Autowired
	VenueRepository venueRepository;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	InviteesRepository inviteesRepository;

	@Override
	public InviteesEntity getInviteeById(Long id) {
		return inviteesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(MessageCodes.NO_DATA_FOUND_MESSAGE));
	}

	@Override
	public InviteesModel createInvitees(InviteesRequest inviteesRequest) throws CustomException {

		InviteesEntity inviteesEntity = new InviteesEntity();

		UserEntity userEntity = userRepository.findById(inviteesRequest.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("User not found for id: " + inviteesRequest.getUserId()));

		VenueEntity venueEntity = venueRepository.findById(inviteesRequest.getVenueId()).orElseThrow(
				() -> new ResourceNotFoundException("Venue not found for id: " + inviteesRequest.getVenueId()));

		EventEntity eventEntity = eventRepository.findById(inviteesRequest.getEventId()).orElseThrow(
				() -> new ResourceNotFoundException("Event not found for id: " + inviteesRequest.getEventId()));

		inviteesEntity.setUser(userEntity);
		inviteesEntity.setVenue(venueEntity);
		inviteesEntity.setEvent(eventEntity);
		inviteesEntity.setStatus(inviteesRequest.isStatus());

		InviteesEntity savedEntity = inviteesRepository.save(inviteesEntity);
		emailTrigger(inviteesRequest, savedEntity, userEntity);

		return mapEntityToModel(savedEntity, userEntity, venueEntity, eventEntity);
	}

	private InviteesModel mapEntityToModel(InviteesEntity inviteEntity, UserEntity userEntity, VenueEntity venueEntity,
			EventEntity eventEntity) {
		InviteesModel inviteesModel = new InviteesModel();

		inviteesModel.setId(inviteEntity.getId());
		inviteesModel.setStatus(false);

		UserModel userModel = new UserModel();
		userModel.setId(userEntity.getId());
		userModel.setFullName(userEntity.getFullName());
		userModel.setEmail(userEntity.getEmail());
		userModel.setPhoneNumber(userEntity.getPhoneNumber());
		inviteesModel.setUser(userModel);

		VenueModel venueModel = new VenueModel();
		venueModel.setId(venueEntity.getId());
		venueModel.setName(venueEntity.getName());
		venueModel.setAddress(venueEntity.getAddress());
		inviteesModel.setVenue(venueModel);

		EventModel eventModel = new EventModel();
		eventModel.setId(eventEntity.getId());
		eventModel.setName(eventEntity.getName());
		eventModel.setEventDate(eventEntity.getEventDate());
		inviteesModel.setEvent(eventModel);

		return inviteesModel;
	}

	private void emailTrigger(InviteesRequest inviteesRequest, InviteesEntity inviteEntity, UserEntity user) {
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setTo(user.getEmail());
		emailRequest.setUrl(inviteesRequest.getUrl() + "/" + inviteEntity.getId());
		emailUtils.sendEmail(emailRequest);
	}

}
