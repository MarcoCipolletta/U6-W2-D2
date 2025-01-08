package it.epicode.u6_w2_d2.author;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorCreateRequest {
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
}
