package com.simelabs.vrs.controller;

import com.simelabs.vrs.impl.VisitsServiceImpl;
import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.request.VisitsRequest;
import com.simelabs.vrs.constants.ApiEndPoints;
import com.simelabs.vrs.constants.MessageCodes;
import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.response.BaseResponse;
import com.simelabs.vrs.response.ResponseUtils;
import com.simelabs.vrs.service.EventService;
import com.simelabs.vrs.service.InviteesService;
import com.simelabs.vrs.service.UserService;
import com.simelabs.vrs.service.VenueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class VisitorRegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private VenueService venueService;

	@Autowired
	private EventService eventService;

	@Autowired
	private InviteesService inviteesService;

	@Autowired
	private ResponseUtils responseUtils;

	@Autowired
	VisitsServiceImpl visitsService;

	@PostMapping(value = ApiEndPoints.SAVE_VISITS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse<VisitsModel>> saveVisits(@RequestBody VisitsRequest visitsRequest) {
		BaseResponse<VisitsModel> baseResponse;
		VisitsModel entities = visitsService.saveVisits(visitsRequest);
		if (entities != null) {
			baseResponse = responseUtils.setBaseResponse(entities, MessageCodes.API_SUCCESS_MESSAGE_CODE,
					MessageCodes.API_SUCCESS_MESSAGE, true);
		}
		else {
			baseResponse = responseUtils.setBaseResponse(null, MessageCodes.API_ERROR_MESSAGE_CODE,
					MessageCodes.NO_DATA_FOUND_MESSAGE, true);

		}
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping(value = ApiEndPoints.GET_MASTER, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse<Map<String, Object>>> getAll() {

		Map<String, Object> response = new HashMap<>();

		List<UserEntity> users = userService.getAllUsers();
		List<VenueEntity> venues = venueService.getAllVenues();
		List<EventEntity> events = eventService.getAllEvents();

		response.put("user", users);
		response.put("venue", venues);
		response.put("event", events);

		BaseResponse<Map<String, Object>> baseResponse;
		if (!response.isEmpty()) {
			baseResponse = responseUtils.setBaseResponse(response, MessageCodes.API_SUCCESS_MESSAGE_CODE,
					MessageCodes.API_SUCCESS_MESSAGE, true);
		}
		else {
			baseResponse = responseUtils.setBaseResponse(null, MessageCodes.API_ERROR_MESSAGE_CODE,
					MessageCodes.NO_DATA_FOUND_MESSAGE, false);
		}

		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping(value = ApiEndPoints.GET_INVITEES_BY_ID + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse<InviteesEntity>> getInviteeById(@PathVariable(value = "id") Long id) {

		InviteesEntity invitee = inviteesService.getInviteeById(id);
		BaseResponse<InviteesEntity> baseResponse;

		if (invitee != null) {
			baseResponse = responseUtils.setBaseResponse(invitee, MessageCodes.API_SUCCESS_MESSAGE_CODE,
					MessageCodes.API_SUCCESS_MESSAGE, true);
		}
		else {
			baseResponse = responseUtils.setBaseResponse(null, MessageCodes.API_ERROR_MESSAGE_CODE,
					MessageCodes.NO_DATA_FOUND_MESSAGE, true);
		}

		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

}
