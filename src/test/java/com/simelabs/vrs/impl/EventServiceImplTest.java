package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.repository.EventRepository;
import org.junit.jupiter.api.Test;
import com.simelabs.vrs.repository.VenueRepository;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.Mockito.when;

import java.util.Arrays;

@MockitoSettings(strictness = Strictness.LENIENT)
class EventServiceImplTest {

	@InjectMocks
	private EventServiceImpl eventServiceImpl;

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private VenueServiceImpl venueService;

	@Mock
	VenueRepository venueRepository;

	@Test
	void testGetAllEvents() {
		List<EventEntity> eventEntities = EntityMocks.getEventEntityList();
		when(eventRepository.findAll()).thenReturn(eventEntities);

		List<EventEntity> result = eventServiceImpl.getAllEvents();

		Assertions.assertEquals(eventEntities.size(), result.size());
		Assertions.assertEquals(eventEntities, result);
	}

	@Test
	void testGetAllVenues() {
		List<VenueEntity> mockVenues = Arrays.asList(EntityMocks.venueEntity1(), EntityMocks.venueEntity2());
		when(venueRepository.findAll()).thenReturn(mockVenues);
		List<VenueEntity> result = venueService.getAllVenues();
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals("Venue 1", result.get(0).getName());
		Assertions.assertEquals("Venue 2", result.get(1).getName());
	}

}
