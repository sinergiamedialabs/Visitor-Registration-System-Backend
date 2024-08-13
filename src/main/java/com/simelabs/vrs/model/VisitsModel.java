package com.simelabs.vrs.model;

import lombok.Data;

@Data
public class VisitsModel {

    private long id;

    private String barcode;

    private InviteesModel invitees;


}
