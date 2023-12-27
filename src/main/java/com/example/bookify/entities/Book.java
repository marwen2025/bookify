package com.example.bookify.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// classe book est persistent
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Size(min=13,max=13, message = "The ISBN field must have exactly 13 numbers. ")
    //@Digits(integer = 13, fration =0, message="The ISBN field must have exactly 13 numbers. ")
    @Column(unique = true)
    private Integer isbn;
    @Size(min = 1, max = 30)
    private String title;
    @ManyToOne
    @NotNull(message = "Author should not be null.")
    private Author author;
    @ManyToMany
    @NotNull(message = " at least one genre is required")
    private List<Genre> genres;
    private String photo;
    //@NotNull(message = "you must add a book as pdf")
    private String bookPdf;
    private int publicationYear;



}
