package fun.springMVC.libraryApp.controllers;

import fun.springMVC.libraryApp.DAO.LibraryDAO;
import fun.springMVC.libraryApp.models.Book;
import fun.springMVC.libraryApp.models.LibraryReader;
import fun.springMVC.libraryApp.util.ReaderValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/library")
public class LibraryController {
    private final LibraryDAO libraryDAO;
    private final ReaderValidator readerValidator;
    @Autowired
    public LibraryController(LibraryDAO libraryDAO, ReaderValidator readerValidator) {
        this.libraryDAO = libraryDAO;
        this.readerValidator = readerValidator;
    }

    @GetMapping()
    public String startingPage(){
        return "library/startingPage";
    }

    @GetMapping("/books")
    public String bookPage(Model model){
        model.addAttribute("allBooks", libraryDAO.indexBooks());

        return "library/booksInteractions";
    }

    @GetMapping("/books/all")
    public String booksList(Model model){
        model.addAttribute("allBooks", libraryDAO.indexBooks());

        return "library/bookList";
    }

    @GetMapping("/books/{bookID}")
    public String showCertainBook(Model model, @PathVariable("bookID") int id, @ModelAttribute("reader") LibraryReader reader){
        model.addAttribute("book", libraryDAO.showBook(id));
        model.addAttribute("readers", libraryDAO.indexLibraryReaders());
        model.addAttribute("bookReader", libraryDAO.showCurrentBookReaderByBookID(id));

        return "library/certainBook";
    }

    @PatchMapping("/books/{bookID}/appoint")
    public String appointBookToReader(@PathVariable("bookID") int bookID, @ModelAttribute("reader") LibraryReader reader){
        libraryDAO.appointBookToReader(bookID, reader.getId());

        return "redirect:/library/books/" + bookID;
    }

    @PatchMapping("/books/{bookID}/return")
    public String returnBookToLibrary(@PathVariable("bookID") int bookID){
        libraryDAO.returnBookFromReaderToLibrary(bookID);

        return "redirect:/library/books/" + bookID;
    }

    @GetMapping("/books/create")
    public String createNewBook(Model model) {
        model.addAttribute("newBook", new Book());

        return "library/newBook";
    }

    @PostMapping("/books/new")
    public String addNewBookToTheLibrary(@ModelAttribute("newBook") @Valid Book book, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "library/newBook";
        }

        libraryDAO.addNewBook(book);
        return "redirect:/library/books/all";
    }

    @DeleteMapping("/books/{bookID}/delete")
    public String deleteBookFromLibrary(@PathVariable("bookID") int bookID){
        libraryDAO.deleteBook(bookID);
        return "redirect:/library/books/all";
    }

    @GetMapping("/readers")
    public String readerPage(Model model){
        model.addAttribute("allReaders", libraryDAO.indexLibraryReaders());

        return "library/readersList";
    }

    @GetMapping("/readers/{id}")
    public String showCertainReader(Model model, @PathVariable("id") int id){
        model.addAttribute("reader", libraryDAO.showLibraryReader(id));

        return "library/certainReader";
    }

    @GetMapping("/readers/create")
    public String createNewLibraryReader(Model model){
        model.addAttribute("newReader", new LibraryReader());

        return "library/createNewReader";
    }

    @PostMapping("/readers/new")
    public String addNewLibraryReader(@ModelAttribute("newReader") @Valid LibraryReader newReader,
                                      BindingResult bindingResult){

        readerValidator.validate(newReader, bindingResult);
        if(bindingResult.hasErrors()){
            return "library/createNewReader";
        }

        libraryDAO.addNewReader(newReader);
        return "redirect:/library/readers";
    }

    @DeleteMapping("/readers/{readerID}/delete")
    public String deleteReader(@PathVariable("readerID") int id){

        libraryDAO.deleteReader(id);
        return "redirect:/library/readers";
    }




    //TODO: Borrow book and Return book


}
