package it.epicode.u6_w2_d2.post;

import it.epicode.u6_w2_d2.exception.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class PostController {
    @Autowired
    private PostSvc postSvc;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
            return ResponseEntity.ok(postSvc.findById(id));
    }


    @PostMapping
    public ResponseEntity<?> creae(@RequestBody PostCreateRequest request) {
            return new ResponseEntity<>(postSvc.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Post request) {
            return new ResponseEntity<>(postSvc.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
            return new ResponseEntity<>(postSvc.delete(id), HttpStatus.OK);
    }

}
