package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.VenueEntity;
import com.simelabs.vrs.repository.VenueRepository;
import com.simelabs.vrs.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepository venueRepository;

	@Override
	public List<VenueEntity> getAllVenues() {
		return venueRepository.findAll();
	}

}