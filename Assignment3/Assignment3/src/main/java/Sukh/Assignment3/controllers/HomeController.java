package Sukh.Assignment3.controllers;

import Sukh.Assignment3.beans.Book;
import Sukh.Assignment3.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    DatabaseAccess databaseAccess;

    @RequestMapping
    public String getAllBooks(Model model) {
        List<Book> list = databaseAccess.getAllBooks();
        model.addAttribute("books", list);
        return "index";
    }

    @RequestMapping(path = "/login")
    public String showLoginScreen(Model model) {
        return "login";
    }
    @RequestMapping("/register")
    public String showRegisterPage() {
        return "register";
    }


    @RequestMapping("/access-denied")
    public String accessDenied(Model model) {
        System.out.println(model);
        return "/error/permission-denied";
    }
}
