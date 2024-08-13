package com.simelabs.vrs.model;

import lombok.Data;

@Data
public class InviteesModel {

    private Long id;
    private UserModel user;
    private VenueModel venue;
    private EventModel event;
    private boolean status;

}
