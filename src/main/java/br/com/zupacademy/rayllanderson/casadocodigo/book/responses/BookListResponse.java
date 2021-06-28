package br.com.zupacademy.rayllanderson.casadocodigo.book.responses;

import br.com.zupacademy.rayllanderson.casadocodigo.book.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookListResponse {

    private final Long id;
    private final String title;

    public BookListResponse(Book book) {
        this.title = book.getTitle();
        this.id = book.getId();
    }

    public static List<BookListResponse> fromBookList(List<Book> allBooks) {
        return allBooks.stream().map(BookListResponse::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}