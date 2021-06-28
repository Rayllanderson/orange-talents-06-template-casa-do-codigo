package br.com.zupacademy.rayllanderson.casadocodigo.book.responses;

import br.com.zupacademy.rayllanderson.casadocodigo.author.responses.AuthorResponse;
import br.com.zupacademy.rayllanderson.casadocodigo.book.model.Book;

import java.time.format.DateTimeFormatter;

public class BookDetailsResponse {

    private final AuthorResponse author;
    private final String isbn;
    private final String title;
    private final String resume;
    private final String summary;
    private final Double price;
    private final Integer pageNumbers;
    private final String publicationDate;

    public BookDetailsResponse(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.pageNumbers = book.getPageNumbers();
        this.publicationDate = book.getPublicationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.author = new AuthorResponse(book.getAuthor());
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSummary() {
        return summary;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getPageNumbers() {
        return pageNumbers;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public AuthorResponse getAuthor() {
        return author;
    }
}
