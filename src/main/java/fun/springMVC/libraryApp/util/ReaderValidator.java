package fun.springMVC.libraryApp.util;

import fun.springMVC.libraryApp.DAO.LibraryDAO;
import fun.springMVC.libraryApp.models.LibraryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReaderValidator implements Validator {
    private final LibraryDAO libraryDAO;
    @Autowired
    public ReaderValidator(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LibraryReader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LibraryReader libraryReader = (LibraryReader) target;

        if(libraryDAO.showLibraryReader(libraryReader.getFullName()).isPresent()){
            errors.rejectValue("fullName", "", "This name has already been taken");
        }
    }
}
