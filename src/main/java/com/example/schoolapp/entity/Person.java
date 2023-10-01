package com.example.schoolapp.entity;

import com.example.schoolapp.infrastructure.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Indexed
@Inheritance(strategy = InheritanceType.JOINED)
public class Person  extends Auditable {

    private String name;
    @Column(unique = true)
    private String email;
    private String mobile;
    private String primaryContact;
    private int age;
    private String residence;
    private int idNumber;
}
