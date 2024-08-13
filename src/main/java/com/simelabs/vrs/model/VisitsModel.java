package com.simelabs.vrs.model;

import com.simelabs.vrs.entity.InviteesEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class VisitsModel {

	private long id;

	private String barcode;

	private InviteesModel invitees;

}
