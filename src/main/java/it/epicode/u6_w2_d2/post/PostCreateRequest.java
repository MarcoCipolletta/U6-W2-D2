package it.epicode.u6_w2_d2.post;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String category;
    private String title;
    private String content;
    private Integer timeToRead;
}
