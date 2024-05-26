package com.example.ejercicio6.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Debe ingresar un texto")
    @Size(min = 3, max = 100, message = "El texto debe tener entre 3 y 100 caracteres")
    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @NotNull(message = "Debe ingresar un texto")
    @Size(min = 3, max = 100, message = "El texto debe tener entre 3 y 100 caracteres")
    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "identity", length = 45)
    private String identity;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "align", length = 45)
    private String align;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "eye", length = 45)
    private String eye;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "hair", length = 45)
    private String hair;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "sex", length = 45)
    private String sex;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "gsm", length = 45)
    private String gsm;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "alive", length = 45)
    private String alive;

    @Digits(integer = 10,fraction = 0, message = "El número debe ser entero")
    @Min(value = 0, message = "El número debe ser mayor a 0")
    @Column(name = "appearances")
    private Integer appearances;

    @Size(max = 45, message = "El texto debe ser menor a 45 caracteres")
    @Column(name = "first_appearance", length = 45)
    private String firstAppearance;

    @Digits(integer = 4,fraction = 0, message = "El número debe ser entero")
    @Min(value = 0, message = "El número debe ser mayor a 0")
    @Max(value = 2050, message = "El número debe ser menor a 2050")
    @Column(name = "year")
    private Integer year;
}
