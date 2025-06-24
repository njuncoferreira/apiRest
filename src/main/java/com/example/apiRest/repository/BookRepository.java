package com.example.apiRest.repository;

import com.example.apiRest.model.Author;
import com.example.apiRest.model.Book;
import com.example.apiRest.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{
    List<Book> findByAuthor(Author author);
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(String isbn);
    List<Book> findByTitleAndPrice(String title, BigDecimal price);
    List<Book> findByTitleOrIsbnOrderByTitle(String title, String isbn);
    List<Book> findByPublishDateBetween(LocalDate start, LocalDate end);

    @Query("select b from Book as b order by b.title")
    List<Book> listAll();

    @Query("select a from Book b join b.author a")
    List<Author> listAuthorsOfBooks();

    @Query("select distinct b.title from Book b")
    List<Book> nonRepeatedBooks();

    //Named Parameter:
    @Query("select b.genre from Book b join b.author a where a.nationality = :nationality order by b.genre")
    List<String> listAllBookGenresPerNationality(@Param("nationality") String nationality);

    //Positional Parameter:
    @Modifying
    @Transactional
    @Query("delete from Book where genre = ?1")
    void deleteByGenre(BookGenre genre);

    @Modifying
    @Transactional
    @Query("update Book b set publishDate = ?1 where b.id = ?2")
    void updatePublishDate(LocalDate newDate, UUID id);

    boolean existsByAuthor(Author author);
}
