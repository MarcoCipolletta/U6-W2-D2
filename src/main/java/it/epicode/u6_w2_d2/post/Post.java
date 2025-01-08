package it.epicode.u6_w2_d2.post;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private Integer timeToRead;


}
