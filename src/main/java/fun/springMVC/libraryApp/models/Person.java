package fun.springMVC.libraryApp.models;

import jakarta.validation.constraints.*;

public class Person {

    private int id;

    @NotBlank(message = "This field is not valid")
    @Size(min = 2, max = 15, message = "Name size must be from to 15 letters")
    private String name;

    @Positive(message = "Age should not be less than 0")
    private int age;

    @NotEmpty(message = "Email could not be empty")
    @Email(message = "Email is not valid. Check it out and try again")
    private String email;

    //Страна, Город, индекс 6 цифр
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in format: Country, City, index (6 digits)")
    private String address;


    public Person(){}

    public Person(int id, String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
