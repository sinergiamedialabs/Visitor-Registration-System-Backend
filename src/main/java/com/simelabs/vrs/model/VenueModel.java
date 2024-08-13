package com.simelabs.vrs.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class VenueModel {

    private Long id;
    private String name;
    private String address;

}
