package fun.springMVC.libraryApp.models;

import javax.validation.constraints.*;

public class LibraryReader {

    private int id;

    @NotBlank(message = "Full name can NOT be empty. Full name should be in format: 'Surname Name Patronymic'")
    @Size(min = 5, max = 40, message = "Full name should be in format: 'Surname Name Patronymic'")
    //Regexp for 'Surname Name Patronymic'
    @Pattern(regexp = "[A-Z][a-z]*'*[a-z]+ [A-Z][a-z]+ [A-Z][a-z]+", message = "Incorrect name")
    private String fullName;

    @NotNull(message = "Field can NOT be empty")
    @Min(value = 1899, message = "Year must be above 1899")
    @Max(value = 2023, message = "Year should be in the past")
    private int yearOfBirth;


    public LibraryReader(){}

    public LibraryReader(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
