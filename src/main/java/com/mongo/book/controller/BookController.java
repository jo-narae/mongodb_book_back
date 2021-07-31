package com.mongo.book.controller;

import com.mongo.book.domain.Book;
import com.mongo.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping
    public void saveBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") String id, @RequestBody Book book) throws NoSuchElementException {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
    }
}
