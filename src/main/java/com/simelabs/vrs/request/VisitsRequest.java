package com.simelabs.vrs.request;

import lombok.Data;

@Data
public class VisitsRequest {

    private int invitees_id;

    private String barCode;

    private boolean isAccepted;

}

