
package Sukh.Assignment3.controllers;

import Sukh.Assignment3.beans.Book;
import Sukh.Assignment3.beans.Review;
import Sukh.Assignment3.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    DatabaseAccess databaseAccess;

    @RequestMapping(path = "/review/{id}")
    public String viewBook(Model model, @PathVariable("id") Long id) {
        Book book = databaseAccess.viewBook(id);
        model.addAttribute("book", book);
        return "view-book";
    }

    @RequestMapping(path = "/add")
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "/admin/add-book";
    }

    @PostMapping(path = "/new")
    public String addNewBook(Book book) {
        databaseAccess.addNewBook(new Book(book.getTitle(), book.getAuthor()));
        return "redirect:/";
    }

    @RequestMapping(path = "/review/add/{id}")
    public String addBookReview(Model model, @PathVariable("id") Long id) {
        Book book = databaseAccess.getBookById(id);
        Review review = new Review(book);
        model.addAttribute("review", review);
        return "/user/add-review";
    }

    @PostMapping(path = "/add-review")
    public String addNewReview(Review review) {
        System.out.println(review.getBookId().getTitle());
        databaseAccess.addNewReview(review);
        return "redirect:/";
    }

}
