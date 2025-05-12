package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", schema = "public")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private int no;
    private String city;
    private String country;

    private String description; // optional
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    @JsonManagedReference
    private Customer customer;


    // Bu sınıf Customer ile ilişki kurmayacak (tek yönlü ilişki Customer'dan)
}
