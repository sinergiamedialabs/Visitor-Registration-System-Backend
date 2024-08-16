package com.simelabs.vrs;

import com.simelabs.vrs.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
		"VRS_MAIL_USERNAME=test-username", "VRS_MAIL_PASSWORD=test-password" })
class VisitorRegistrationSystemApplicationTests {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	VenueRepository venueRepository;

	@MockBean
	EventRepository eventRepository;

	@MockBean
	VisitesRepository visitesRepository;

	@MockBean
	InviteesRepository inviteesRepository;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}