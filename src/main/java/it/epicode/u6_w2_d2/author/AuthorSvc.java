package it.epicode.u6_w2_d2.author;


import it.epicode.u6_w2_d2.exception.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorSvc {
    private final AuthorRepository authorRepo;

    public List<Author> findAll() { return authorRepo.findAll(); }
    public Author findById(UUID id) {
        Author author = authorRepo.findById(id).orElse(null);

        if (author == null) {
            throw new EntityNotFoundException("Author with id " + id + " not found");
        }

        return author;
    }

    public Author create(AuthorCreateRequest request) {
        if (authorRepo.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("Author with email " + request.getEmail() + " already exists");
        }
        Author  a = new Author();
        BeanUtils.copyProperties(request, a);
        a.setAvatar("https://ui-avatars.com/api/?name=" + request.getName() + "+" + request.getSurname());
        return authorRepo.save(a);
    }

    public Author update(UUID id, Author modifiedAuthor) {
        Author a = findById(id);
        if (a.getEmail() != modifiedAuthor.getEmail() && authorRepo.existsByEmail(modifiedAuthor.getEmail())) {
            throw new AlreadyExistsException("Author with email " + modifiedAuthor.getEmail() + " already exists");
        }
        BeanUtils.copyProperties(modifiedAuthor,a);
        return authorRepo.save(a);
    }

    public String delete(UUID id) {
        Author a = findById(id);
        authorRepo.delete(a);
        return "Author with id " + id + " deleted";
    }

}
