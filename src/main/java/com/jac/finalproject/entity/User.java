package com.jac.finalproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "jac_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @NotBlank
    private String userName;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_time",updatable = false)
    private Date registrationTime;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserPaymentInfo userPaymentInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jac_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;


}
