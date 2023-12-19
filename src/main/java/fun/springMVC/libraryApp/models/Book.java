package fun.springMVC.libraryApp.models;

import jakarta.validation.constraints.*;


public class Book {

    private int id;

    @NotBlank(message = "Title can not be empty")
    @Size(max = 40, message = "Title is too large")
    private String title;

    @NotBlank(message = "Author can not be empty")
    @Size(max = 20, message = "Author name is too large")
    private String author;

    @NotNull(message = "Field can NOT be empty")
    @Max(value = 2023, message = "Year should be in the past")
    private int year;



    public Book(){}

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
