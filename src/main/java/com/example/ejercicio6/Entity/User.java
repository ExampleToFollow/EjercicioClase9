package com.example.ejercicio6.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @EmbeddedId
    private UserId id;

    @MapsId("idrol")
    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Role rol;

    @Size(max = 45)
    @NotNull
    @Column(name = "fullname", nullable = false, length = 45)
    private String fullname;

    @Size(max = 45)
    @NotNull
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;
}
