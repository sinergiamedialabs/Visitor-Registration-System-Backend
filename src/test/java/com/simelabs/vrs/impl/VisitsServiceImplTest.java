package com.simelabs.vrs.impl;

import com.simelabs.vrs.entity.VisitesEntity;
import com.simelabs.vrs.mock.EntityMocks;
import com.simelabs.vrs.mock.RequestMocks;
import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.repository.InviteesRepository;
import com.simelabs.vrs.repository.VisitesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitsServiceImplTest {

	@Mock
	private InviteesRepository inviteesRepository;

	@Mock
	private VisitesRepository visitesRepository;

	@InjectMocks
	private VisitsServiceImpl visitsService;

	@Test
	void testSaveVisits_WhenAccepted() {
		when(inviteesRepository.findById(1L)).thenReturn(Optional.of(EntityMocks.inviteesEntity()));
		when(visitesRepository.save(ArgumentMatchers.any(VisitesEntity.class))).thenReturn(EntityMocks.visitesEntity());
		VisitsModel result = visitsService.saveVisits(RequestMocks.visitsRequest_WhenAcceptedTrue());
		Assertions.assertEquals(EntityMocks.visitesEntity().getBarcode(), result.getBarcode());
	}

	@Test
	void testSaveVisits_WhenNotAccepted() {
		when(inviteesRepository.findById(1L)).thenReturn(Optional.of(EntityMocks.inviteesEntity()));
		VisitsModel result = visitsService.saveVisits(RequestMocks.visitsRequest_WhenAcceptedFalse());
		Assertions.assertNull(result);
	}

}
