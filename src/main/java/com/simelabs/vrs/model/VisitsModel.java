package com.simelabs.vrs.model;

import lombok.Data;

@Data
public class VisitsModel {

    private int id;

    private String barcode;

    private InviteesModel invitees;


}
