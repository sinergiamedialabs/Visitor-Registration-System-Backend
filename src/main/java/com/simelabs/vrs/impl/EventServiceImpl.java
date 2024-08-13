package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.EventEntity;
import com.simelabs.vrs.repository.EventRepository;
import com.simelabs.vrs.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<EventEntity> getAllEvents() {
		return eventRepository.findAll();
	}

}