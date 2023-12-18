package fun.springMVC.libraryApp.DAO;

import fun.springMVC.libraryApp.models.Book;
import fun.springMVC.libraryApp.models.LibraryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class LibraryDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> indexBooks(){
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<LibraryReader> indexLibraryReaders(){
        return jdbcTemplate.query("SELECT * FROM readers", new LibraryReaderMapper());
    }

    public void saveBook(Book book){
        jdbcTemplate.update("INSERT INTO books(title, author, year) VALUES(?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void saveLibraryReader(LibraryReader libraryReader){
        jdbcTemplate.update("INSERT INTO readers(name, year) VALUES(?, ?)", libraryReader.getFullName(),
                libraryReader.getYearOfBirth());
    }

    public Optional<Book> showBook(int id){
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny();
    }

    public Optional<LibraryReader> showLibraryReader(int id){
        return jdbcTemplate.query("SELECT * FROM readers WHERE id=?",
                        new LibraryReaderMapper(), id).stream().findAny();
    }

    //Method for correct validation in ReaderValidator class
    public Optional<LibraryReader> showLibraryReader(String fullName){
        return jdbcTemplate.query("SELECT * FROM readers WHERE name=?",
                new LibraryReaderMapper(), fullName).stream().findAny();
    }

    public void appointBookToReader(int bookID, int readerID){
            jdbcTemplate.update("UPDATE books SET reader_id = ? WHERE id = ?", readerID, bookID);
        }

    public Optional<LibraryReader> showCurrentBookReaderByBookID(int id){
        return jdbcTemplate.query("SELECT readers.id AS id, name, readers.year AS year FROM readers " +
                                        "JOIN books ON readers.id = reader_id WHERE books.id = ?",
                new LibraryReaderMapper(), id).stream().findAny();
    }

    public void returnBookFromReaderToLibrary(int id){
        jdbcTemplate.update("UPDATE books SET reader_id = null WHERE id = ?", id);
    }

    public void addNewBook(Book book){
        jdbcTemplate.update("INSERT INTO books(title, author, year) VALUES(?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void deleteBook(int bookID){
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", bookID);
    }

    public void addNewReader(LibraryReader reader){
        jdbcTemplate.update("INSERT INTO readers(name, year) VALUES(?, ?)", reader.getFullName(),
                reader.getYearOfBirth());
    }

    public void deleteReader(int id){
        jdbcTemplate.update("DELETE FROM readers WHERE id = ?", id);
    }
}
