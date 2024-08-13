package com.simelabs.vrs.service;

import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.request.VisitsRequest;

public interface VisitsService {

	VisitsModel saveVisits(VisitsRequest request);

}
