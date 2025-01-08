package it.epicode.u6_w2_d2.post;

import it.epicode.u6_w2_d2.post.Post;
import it.epicode.u6_w2_d2.post.PostCreateRequest;
import it.epicode.u6_w2_d2.exception.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostSvc {
    private final PostRepository postRepo;

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public Post findById(UUID id) {
        Post post = postRepo.findById(id).orElse(null);

        if (post == null) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        }

        return post;

    }

    public Post create(PostCreateRequest request) {
        if (postRepo.existsByTitle(request.getTitle())) {
            throw new AlreadyExistsException("Post with email '" + request.getTitle() + "' already exists");
        }
        Post a = new Post();
        BeanUtils.copyProperties(request, a);
        a.setCover("https://picsum.photos/" + (int) (Math.random() * 500) + "/" + (int) (Math.random() * 500));
        return postRepo.save(a);
    }

    public Post update(UUID id, Post modifiedPost) {
        Post a = findById(id);
        if (!a.getTitle().equals(modifiedPost.getTitle()) && postRepo.existsByTitle(modifiedPost.getTitle())) {
            throw new AlreadyExistsException("Post with email " + modifiedPost.getTitle() + " already exists");
        }
        BeanUtils.copyProperties(modifiedPost, a);
        return postRepo.save(a);
    }

    public String delete(UUID id) {
        Post a = findById(id);
        postRepo.delete(a);
        return "Post with id " + id + " deleted";
    }
}
