package com.jac.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "jac_user_payment_info")
public class UserPaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "cvv_code")
    private String cvvCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}