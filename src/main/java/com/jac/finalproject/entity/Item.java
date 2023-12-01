package com.jac.finalproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "jac_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    @NotBlank
    private String itemName;

    @Size(max = 10000)
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

}