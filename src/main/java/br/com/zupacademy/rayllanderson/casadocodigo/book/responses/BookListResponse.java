package br.com.zupacademy.rayllanderson.casadocodigo.book.responses;

import br.com.zupacademy.rayllanderson.casadocodigo.book.model.Book;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BookListResponse {

    private final String isbn;
    private final String title;
    private final String resume;
    private final String summary;
    private final Double price;
    private final Integer pageNumbers;
    private final String publicationDate;

    public BookListResponse(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.pageNumbers = book.getPageNumbers();
        this.publicationDate = book.getPublicationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static List<BookListResponse> fromBookList(List<Book> allBooks) {
        return allBooks.stream().map(BookListResponse::new).collect(Collectors.toList());
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
}
