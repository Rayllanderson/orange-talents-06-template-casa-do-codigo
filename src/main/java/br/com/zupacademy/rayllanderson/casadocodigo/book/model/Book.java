package br.com.zupacademy.rayllanderson.casadocodigo.book.model;

import br.com.zupacademy.rayllanderson.casadocodigo.author.model.Author;
import br.com.zupacademy.rayllanderson.casadocodigo.category.model.Category;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank @Size(max = 500)
    @Column(length = 500)
    private String resume;

    @NotBlank
    private String summary;

    @NotNull @Min(20)
    private Double price;

    @NotNull @Min(100)
    private Integer pageNumbers;

    @Future @NotNull
    private LocalDate publicationDate;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @ManyToOne
    private Author author;

    @Deprecated
    public Book() {
    }

    public Book(@NotNull String isbn, @NotBlank String title, @NotBlank @Size(max = 500) String resume, @NotBlank String summary,
                @NotNull @Min(20) Double price, @NotNull @Min(100) Integer pageNumbers, @Future LocalDate publicationDate,
                @NotNull Category category,  @NotNull Author author) {
        this.isbn = isbn;
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pageNumbers = pageNumbers;
        this.publicationDate = publicationDate;
        this.category = category;
        this.author = author;
    }

    public Long getId(){
        return this.id;
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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Author getAuthor() {
        return author;
    }
}
