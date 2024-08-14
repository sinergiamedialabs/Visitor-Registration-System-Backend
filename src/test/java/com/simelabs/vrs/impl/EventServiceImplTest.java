package com.simelabs.vrs.impl;

import com.simelabs.vrs.repository.EventRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
public class EventServiceImplTest {

	@InjectMocks
	private EventServiceImpl eventServiceImpl;

	@Mock
	private EventRepository eventRepository;

}
