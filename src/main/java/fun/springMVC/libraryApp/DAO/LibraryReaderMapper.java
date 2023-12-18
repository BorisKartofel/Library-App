package fun.springMVC.libraryApp.DAO;


import fun.springMVC.libraryApp.models.LibraryReader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryReaderMapper implements RowMapper<LibraryReader> {
    @Override
    public LibraryReader mapRow(ResultSet rs, int rowNum) throws SQLException {
        LibraryReader reader = new LibraryReader();

        reader.setId(rs.getInt("id"));
        reader.setFullName(rs.getString("name"));
        reader.setYearOfBirth(rs.getInt("year"));

        return reader;
    }
}
