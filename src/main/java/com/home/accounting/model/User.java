package com.home.accounting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "UserEntity")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    //    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}",
//            message = "Must be minimum 6 characters, at least one letter and one number")
    @NotBlank(message = "Must be minimum 1 character")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Account> accounts;
}
