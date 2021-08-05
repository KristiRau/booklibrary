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
    public List<Book> postBook(@RequestBody Book book) {
        int id = 1;
        if (!bookList.isEmpty()) {
            Book lastBook = bookList.get(bookList.size() - 1);
            int lastId = lastBook.getId();
            id = lastId + 1;
        }

        if (book.getName() != null && book.getAuthor() != null && book.getYear() > 1500) {
            book.setId(id);
            bookList.add(book);
        }
            return bookList;
    }

    @DeleteMapping("/books/delete")
    public List<Book> removeBook(@RequestBody Book book) {
        Iterator<Book> booksIterator = bookList.iterator();

        while(booksIterator.hasNext()) {
            Book temp = booksIterator.next();
            if (book.getId() == temp.getId()) {
                booksIterator.remove();
                break;
            }
        }
        return bookList;
    }

    @PostMapping("/books/update")
    public List<Book> updateBook(@RequestBody Book book) {
        Iterator<Book> booksIterator = bookList.iterator();

        while(booksIterator.hasNext()) {
            Book temp = booksIterator.next();

            if (book.getId() == temp.getId()) {

                if (book.getName() != null && book.getAuthor() != null && book.getYear() > 1500) {
                    temp.setName(book.getName());
                    temp.setAuthor(book.getAuthor());
                    temp.setYear(book.getYear());
                    break;
                }
            }
        }
        return bookList;
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
            if (book.getAuthor() != null && book.getName() != null && book.getYear() > 1500) {
                if (book.getAuthor().equals(temp.getAuthor()) && book.getName().equals(temp.getName()) && book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getName() != null && book.getYear() > 1500) {
                if (book.getName().equals(temp.getName()) && book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getAuthor() != null && book.getYear() > 1500) {
                if (book.getAuthor().equals(temp.getAuthor()) && book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getAuthor() != null && book.getName() != null) {
                if (book.getAuthor().equals(temp.getAuthor()) && book.getName().equals(temp.getName())) {
                    books.add(temp);
                }
            } else if (book.getYear() > 1500) {
                if (book.getYear() == temp.getYear()) {
                    books.add(temp);
                }
            } else if (book.getAuthor() != null) {
                if (book.getAuthor().equals(temp.getAuthor())) {
                    books.add(temp);
                }
            } else if (book.getName() != null) {
                if (book.getName().equals(temp.getName())) {
                    books.add(temp);
                }
            }

        }
        return books;
    }




}


