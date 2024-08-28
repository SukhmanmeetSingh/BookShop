package Sukh.Assignment3.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Review(Book bookId) {
        this.bookId = bookId;
    }

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKID")
    private Book bookId;

    public Review() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public Book getBookId() {
        return bookId;
    }
}