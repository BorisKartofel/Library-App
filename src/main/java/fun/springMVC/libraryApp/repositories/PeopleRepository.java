package fun.springMVC.libraryApp.repositories;

import fun.springMVC.libraryApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{

    Optional<Person> findByUsername(String username); // Auto-generated method by SpringJPA

}
