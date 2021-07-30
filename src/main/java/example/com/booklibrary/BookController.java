package example.com.booklibrary;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@CrossOrigin
@RestController
public class BookController {

    private List<Book> bookList = new ArrayList<>();
    private final String CODE_OK = "1";
    private final String CODE_NOK = "-1";


    @PostMapping("/books/add")
    public String postBook(@RequestBody Book book) {

        if (book.getName() != null && book.getAuthor() != null && book.getYear() > 1500) {
            bookList.add(book);
            return CODE_OK;
        } else {
            return CODE_NOK;
        }
    }

    @DeleteMapping("/books/delete")
    public String removeBook(@RequestBody Book book) {
        System.out.println("Start");
        Iterator<Book> booksIterator = bookList.iterator();

        while(booksIterator.hasNext()) {
            Book temp = booksIterator.next();
            if (book.getAuthor().equals(temp.getAuthor()) && book.getName().equals(temp.getName()) && book.getYear() == temp.getYear()) {
                booksIterator.remove();
                break;
            }
        }
        return CODE_OK;
    }

    @GetMapping("/books")
    public List<Book> getBookList() {
        return bookList;
    }

    @PostMapping("/books/search")
    public List<Book> getBook(@RequestBody Book book) {
        Iterator<Book> booksIterator = bookList.iterator();
        List<Book> books = new ArrayList<>();

        while(booksIterator.hasNext()) {
            Book temp = booksIterator.next();
            if (book.getAuthor() != null && book.getName() != null) {
                if (book.getAuthor().equals(temp.getAuthor()) && book.getName().equals(temp.getName()) && book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getName() != null) {
                if (book.getName().equals(temp.getName()) && book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getAuthor() != null) {
                if (book.getAuthor().equals(temp.getAuthor()) && book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getYear() > 1500) {
                if (book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            }

        }
        return books;
    }



}
