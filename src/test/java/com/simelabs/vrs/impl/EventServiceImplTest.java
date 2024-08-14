package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class EventServiceImplTest {

	@InjectMocks
	private EventServiceImpl eventServiceImpl;

	@Mock
	private EventRepository eventRepository;

	@Test
	public void testGetAllEvents() {
		List<EventEntity> eventEntities = EntityMocks.getEventEntityList();
		when(eventRepository.findAll()).thenReturn(eventEntities);

		List<EventEntity> result = eventServiceImpl.getAllEvents();

		assertEquals(eventEntities.size(), result.size());
		assertEquals(eventEntities, result);
	}

}
