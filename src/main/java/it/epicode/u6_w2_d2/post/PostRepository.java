package it.epicode.u6_w2_d2.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PostRepository extends JpaRepository<Post, UUID> {

    Boolean existsByTitle(String title);
}
