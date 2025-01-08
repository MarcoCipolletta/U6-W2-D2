package it.epicode.u6_w2_d2.author;

import it.epicode.u6_w2_d2.exception.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorSvc authorSvc;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok(authorSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable UUID id) {
            return ResponseEntity.ok(authorSvc.findById(id));
    }


    @PostMapping
    public ResponseEntity<Author> creae(@RequestBody AuthorCreateRequest request) {
        return new ResponseEntity<>(authorSvc.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable UUID id, @RequestBody Author request) {
            return new ResponseEntity<>(authorSvc.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
            return new ResponseEntity<>(authorSvc.delete(id), HttpStatus.NO_CONTENT);
    }


}

