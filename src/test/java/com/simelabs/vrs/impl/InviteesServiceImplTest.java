package com.simelabs.vrs.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.simelabs.vrs.model.InviteesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.entity.InviteesEntity;
import com.simelabs.vrs.entity.UserEntity;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.exception.CustomException;
import com.simelabs.vrs.exception.ResourceNotFoundException;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.repository.EventRepository;
import com.simelabs.vrs.repository.InviteesRepository;
import com.simelabs.vrs.repository.UserRepository;
import com.simelabs.vrs.repository.VenueRepository;
import com.simelabs.vrs.request.InviteesRequest;
import com.simelabs.vrs.utils.EmailUtils;

import java.util.Optional;

@MockitoSettings(strictness = Strictness.LENIENT)
class InviteesServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private VenueRepository venueRepository;

	@Mock
	private EventRepository eventRepository;

	@Mock
	private InviteesRepository inviteesRepository;

	@Mock
	private EmailUtils emailUtils;

	@InjectMocks
	private InviteesServiceImpl inviteesServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateInvitees_Success() throws CustomException {

		InviteesRequest inviteesRequest = EntityMocks.getInviteesRequest();
		UserEntity userEntity = EntityMocks.getUserEntity();
		VenueEntity venueEntity = EntityMocks.getVenueEntity();
		EventEntity eventEntity = EntityMocks.getEventEntity();
		InviteesEntity inviteesEntity = EntityMocks.getInviteesEntity();

		when(userRepository.findById(inviteesRequest.getUserId())).thenReturn(java.util.Optional.of(userEntity));
		when(venueRepository.findById(inviteesRequest.getVenueId())).thenReturn(java.util.Optional.of(venueEntity));
		when(eventRepository.findById(inviteesRequest.getEventId())).thenReturn(java.util.Optional.of(eventEntity));
		when(inviteesRepository.save(any(InviteesEntity.class))).thenReturn(inviteesEntity);

		InviteesModel result = inviteesServiceImpl.createInvitees(inviteesRequest);

		assertNotNull(result);
		assertEquals(inviteesEntity.getId(), result.getId());
		assertEquals(userEntity.getId(), result.getUser().getId());
		assertEquals(venueEntity.getId(), result.getVenue().getId());
		assertEquals(eventEntity.getId(), result.getEvent().getId());

		verify(userRepository).findById(inviteesRequest.getUserId());
		verify(venueRepository).findById(inviteesRequest.getVenueId());
		verify(eventRepository).findById(inviteesRequest.getEventId());
		verify(inviteesRepository).save(any(InviteesEntity.class));
		verify(emailUtils).sendEmail(any());
	}

	@Test
	void testCreateInvitees_UserNotFound() {

		InviteesRequest inviteesRequest = EntityMocks.getInviteesRequest();
		when(userRepository.findById(inviteesRequest.getUserId())).thenReturn(Optional.empty());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			inviteesServiceImpl.createInvitees(inviteesRequest);
		});

		assertEquals("User not found for id: " + inviteesRequest.getUserId(), thrown.getMessage());

		verify(userRepository).findById(inviteesRequest.getUserId());
		verifyNoMoreInteractions(venueRepository, eventRepository, inviteesRepository, emailUtils);
	}

	@Test
	void testCreateInvitees_VenueNotFound() {

		InviteesRequest inviteesRequest = EntityMocks.getInviteesRequest();
		UserEntity userEntity = EntityMocks.getUserEntity();

		when(userRepository.findById(inviteesRequest.getUserId())).thenReturn(java.util.Optional.of(userEntity));
		when(venueRepository.findById(inviteesRequest.getVenueId()))
				.thenThrow(new ResourceNotFoundException("Venue not found for id: " + inviteesRequest.getVenueId()));

		assertThrows(ResourceNotFoundException.class, () -> inviteesServiceImpl.createInvitees(inviteesRequest));

		verify(userRepository).findById(inviteesRequest.getUserId());
		verify(venueRepository).findById(inviteesRequest.getVenueId());
		verifyNoMoreInteractions(eventRepository, inviteesRepository, emailUtils);
	}

	@Test
	void testCreateInvitees_EventNotFound() {

		InviteesRequest inviteesRequest = EntityMocks.getInviteesRequest();
		UserEntity userEntity = EntityMocks.getUserEntity();
		VenueEntity venueEntity = EntityMocks.getVenueEntity();

		when(userRepository.findById(inviteesRequest.getUserId())).thenReturn(java.util.Optional.of(userEntity));
		when(venueRepository.findById(inviteesRequest.getVenueId())).thenReturn(java.util.Optional.of(venueEntity));
		when(eventRepository.findById(inviteesRequest.getEventId()))
				.thenThrow(new ResourceNotFoundException("Event not found for id: " + inviteesRequest.getEventId()));

		assertThrows(ResourceNotFoundException.class, () -> inviteesServiceImpl.createInvitees(inviteesRequest));

		verify(userRepository).findById(inviteesRequest.getUserId());
		verify(venueRepository).findById(inviteesRequest.getVenueId());
		verify(eventRepository).findById(inviteesRequest.getEventId());
		verifyNoMoreInteractions(inviteesRepository, emailUtils);
	}

}
