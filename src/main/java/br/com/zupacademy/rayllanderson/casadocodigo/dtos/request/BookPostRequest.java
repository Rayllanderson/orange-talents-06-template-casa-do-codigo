package br.com.zupacademy.rayllanderson.casadocodigo.dtos.request;

import br.com.zupacademy.rayllanderson.casadocodigo.entities.Author;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Book;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Category;
import br.com.zupacademy.rayllanderson.casadocodigo.validators.annotations.Exists;
import br.com.zupacademy.rayllanderson.casadocodigo.validators.annotations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class BookPostRequest {

    @NotNull
    @UniqueValue(domainClass = Book.class, field = "isbn")
    private String isbn;

    @NotBlank
    @UniqueValue(domainClass = Book.class, field = "title")
    private String title;

    @NotBlank @Size(max = 500)
    private String resume;

    @NotBlank
    private String summary;

    @NotNull @Min(20)
    private Double price;

    @NotNull @Min(100)
    private Integer pageNumbers;

    @Future @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate publicationDate;

    @NotNull
    @Exists(domainClass = Author.class, field = "id")
    private Long authorId;

    @NotNull
    @Exists(domainClass = Category.class, field = "id")
    private Long categoryId;

    @JsonCreator
    public BookPostRequest(@NotNull String isbn, @NotBlank String title, @NotBlank @Size(max = 500) String resume, @NotBlank String summary,
                           @NotNull @Min(20) Double price, @NotNull @Min(100) Integer pageNumbers, @Future @NotNull LocalDate publicationDate,
                           @NotNull Long categoryId, @NotNull Long authorId) {
        this.isbn = isbn;
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pageNumbers = pageNumbers;
        this.publicationDate = publicationDate;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    public Book toBook(EntityManager manager){
        @NotNull Author author = manager.find(Author.class, authorId);
        @NotNull Category category = manager.find(Category.class, categoryId);
        return new Book(isbn, title, resume, summary, price, pageNumbers, publicationDate, category, author);
    }
}
