package com.simelabs.vrs.model;

import com.simelabs.vrs.entity.InviteesEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class VisitsModel {

    private Long id;
    private Long inviteesId;
    private String barcode;

}
