package com.example.bookify.apiREST;


import com.example.bookify.entities.Book;
import com.example.bookify.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*") //angular  localhost:4200
@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class ClientRestController {
    BookService bookService;
    @GetMapping("/showBooks")
    public List<Book> getAllBooks(@RequestParam(name = "mc",defaultValue ="" ) String mc,
                                  @RequestParam(name="page",defaultValue = "0")int page,
                                  @RequestParam(name="size",defaultValue = "5")int size)
    {
        Page<Book> list=bookService.getBookByMc(mc, PageRequest.of(page,size));
            return list.getContent();

    }

    @GetMapping("/book/{id}")
    public Book showBook(@PathVariable("id") Long id){
        return bookService.getBook(id);
    }


}
