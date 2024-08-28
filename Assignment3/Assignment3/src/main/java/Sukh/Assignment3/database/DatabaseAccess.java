
package Sukh.Assignment3.database;

import Sukh.Assignment3.beans.Book;
import Sukh.Assignment3.beans.Review;
import Sukh.Assignment3.exceptions.ResourceNotFoundException;
import Sukh.Assignment3.repositories.BookRepository;
import Sukh.Assignment3.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseAccess {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public List<Book> getAllBooks() {
        System.out.println("getAllBooks");
        List<Book> result = (List<Book>) bookRepository.findAll();
        if (!result.isEmpty()) {
            return result;
        } else {
            return new ArrayList<Book>();
        }
    }

    public Book viewBook(Long id) throws ResourceNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new ResourceNotFoundException("Record not exists for given id");
        }
    }

    public Book getBookById(Long id) throws ResourceNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new ResourceNotFoundException("Record not exists for given id");
        }
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void addNewReview(Review review) {
        reviewRepository.save(review);
    }
}
