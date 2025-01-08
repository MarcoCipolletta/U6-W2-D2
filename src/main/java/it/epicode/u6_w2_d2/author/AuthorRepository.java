package it.epicode.u6_w2_d2.author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Boolean existsByEmail(String email);

}
