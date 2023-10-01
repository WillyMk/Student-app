package com.example.schoolapp.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Guardian {
    private String guardianName;
    private String guardianEmail;
    private String guardianContact;
}
