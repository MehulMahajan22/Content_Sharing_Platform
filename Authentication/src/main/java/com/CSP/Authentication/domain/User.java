package com.CSP.Authentication.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "userSeq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeq")
    int user_id;
    @Column(unique = true)
    long contact;
    String name, password;
}
