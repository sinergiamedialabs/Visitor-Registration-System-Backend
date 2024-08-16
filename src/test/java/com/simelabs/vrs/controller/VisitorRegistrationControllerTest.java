package com.simelabs.vrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simelabs.vrs.constants.ApiEndPoints;
import com.simelabs.vrs.constants.MessageCodes;
import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.impl.VisitsServiceImpl;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.mock.ModelMocks;
import com.simelabs.vrs.mock.RequestMocks;
import com.simelabs.vrs.model.InviteesModel;
import com.simelabs.vrs.model.UserModel;
import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.request.InviteesRequest;
import com.simelabs.vrs.request.UserRequest;
import com.simelabs.vrs.request.VisitsRequest;
import com.simelabs.vrs.response.BaseResponse;
import com.simelabs.vrs.response.ResponseUtils;
import com.simelabs.vrs.service.EventService;
import com.simelabs.vrs.service.InviteesService;
import com.simelabs.vrs.service.UserService;
import com.simelabs.vrs.service.VenueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(VisitorRegistrationController.class)
class VisitorRegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private VisitorRegistrationController visitorRegistrationController;

	@MockBean
	UserService userService;

	@MockBean
	VenueService venueService;

	@MockBean
	EventService eventService;

	@MockBean
	private ResponseUtils responseUtils;

	@MockBean
	InviteesService inviteesService;

	@MockBean
	VisitsServiceImpl visitsServiceImpl;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetAllSuccess() throws Exception {
		List<UserEntity> mockUsers = EntityMocks.getUserEntityList();
		List<VenueEntity> mockVenues = EntityMocks.getVenueEntityList();
		List<EventEntity> mockEvents = EntityMocks.getEventEntityList();

		Map<String, Object> mockResponseData = new HashMap<>();
		mockResponseData.put("user", mockUsers);
		mockResponseData.put("venue", mockVenues);
		mockResponseData.put("event", mockEvents);

		BaseResponse<Map<String, Object>> mockResponse = EntityMocks.getBaseResponse(mockResponseData,
				MessageCodes.API_SUCCESS_MESSAGE, MessageCodes.API_SUCCESS_MESSAGE_CODE, true);

		when(userService.getAllUsers()).thenReturn(mockUsers);
		when(venueService.getAllVenues()).thenReturn(mockVenues);
		when(eventService.getAllEvents()).thenReturn(mockEvents);
		when(responseUtils.setBaseResponse(mockResponseData, MessageCodes.API_SUCCESS_MESSAGE_CODE,
				MessageCodes.API_SUCCESS_MESSAGE, true)).thenReturn(mockResponse);

		mockMvc.perform(get(ApiEndPoints.GET_MASTER).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(true))
				.andExpect(jsonPath("$.data.user[0].fullName").value("Rahul EK"))
				.andExpect(jsonPath("$.data.venue[0].name").value("Venue 1"))
				.andExpect(jsonPath("$.data.event[0].name").value("Event 1"));
	}

	@Test
	void testAddUserSuccess() throws Exception {
		UserRequest userRequest = EntityMocks.getUserRequest();

		UserModel userModel = EntityMocks.getUserModel();

		BaseResponse<UserModel> expectedResponse = new BaseResponse<>();
		expectedResponse.setData(userModel);
		expectedResponse.setCode(MessageCodes.API_SUCCESS_MESSAGE_CODE);
		expectedResponse.setMessage(MessageCodes.API_SUCCESS_MESSAGE);

		when(userService.addUser(any(UserRequest.class))).thenReturn(userModel);
		when(responseUtils.setBaseResponse(any(UserModel.class), eq(MessageCodes.API_SUCCESS_MESSAGE_CODE),
				eq(MessageCodes.API_SUCCESS_MESSAGE), eq(true))).thenReturn(expectedResponse);

		mockMvc.perform(post(ApiEndPoints.ADD_USERS).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userRequest))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.fullName").value("Rahul EK"))
				.andExpect(jsonPath("$.data.email").value("rahukek@gmail.com"))
				.andExpect(jsonPath("$.data.phoneNumber").value("1234567890"));
	}

	@Test
	void testGetInviteeById_Success() throws Exception {
		Long inviteeId = 1L;
		InviteesEntity mockInvitee = new InviteesEntity();
		mockInvitee.setId(inviteeId);

		BaseResponse<InviteesEntity> mockResponse = new BaseResponse<>();
		mockResponse.setData(mockInvitee);
		mockResponse.setMessage(MessageCodes.API_SUCCESS_MESSAGE);
		mockResponse.setCode(MessageCodes.API_SUCCESS_MESSAGE_CODE);
		mockResponse.setStatus(true);

		when(inviteesService.getInviteeById(inviteeId)).thenReturn(mockInvitee);
		when(responseUtils.setBaseResponse(mockInvitee, MessageCodes.API_SUCCESS_MESSAGE_CODE,
				MessageCodes.API_SUCCESS_MESSAGE, true)).thenReturn(mockResponse);

		mockMvc.perform(
				get(ApiEndPoints.GET_INVITEES_BY_ID + "/{id}", inviteeId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.id").value(inviteeId))
				.andExpect(jsonPath("$.message").value(MessageCodes.API_SUCCESS_MESSAGE))
				.andExpect(jsonPath("$.code").value(MessageCodes.API_SUCCESS_MESSAGE_CODE))
				.andExpect(jsonPath("$.status").value(true));
	}

	@Test
	void testCreateInviteesSuccess() throws Exception {
		InviteesRequest inviteesRequest = RequestMocks.getInviteesRequest();

		InviteesModel inviteesModel = ModelMocks.getInviteesModel();

		when(inviteesService.createInvitees(any(InviteesRequest.class))).thenReturn(inviteesModel);
		when(responseUtils.setBaseResponse(any(), any(), any(), anyBoolean())).thenReturn(new BaseResponse<>());

		mockMvc.perform(post(ApiEndPoints.POST_INVITEES).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inviteesRequest))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.status").value(false));
	}

	@Test
	void testCreateInviteesException() throws Exception {
		InviteesRequest inviteesRequest = RequestMocks.getInviteesRequest();

		doThrow(new RuntimeException("Test Exception")).when(inviteesService)
				.createInvitees(any(InviteesRequest.class));
		when(responseUtils.setBaseResponse(any(), any(), any(), anyBoolean())).thenReturn(new BaseResponse<>());

		mockMvc.perform(post(ApiEndPoints.POST_INVITEES).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inviteesRequest))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").doesNotExist()).andExpect(jsonPath("$.status").value(false));
	}

	@Test
	void testSaveVisitsSuccess() throws Exception {
		VisitsRequest visitsRequest = RequestMocks.getVisitsRequest();

		VisitsModel visitsModel = ModelMocks.getVisitsModel();

		BaseResponse<VisitsModel> baseResponse = ModelMocks.getResponseVisitModel();

		when(visitsServiceImpl.saveVisits(any(VisitsRequest.class))).thenReturn(visitsModel);
		when(responseUtils.setBaseResponse(any(VisitsModel.class), anyString(), anyString(), anyBoolean()))
				.thenReturn(baseResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/" + ApiEndPoints.SAVE_VISITS)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(visitsRequest)))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Success"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true));

		verify(visitsServiceImpl, times(1)).saveVisits(any(VisitsRequest.class));
		verify(responseUtils, times(1)).setBaseResponse(any(VisitsModel.class), anyString(), anyString(), anyBoolean());
	}

}