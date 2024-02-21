package com.example.bookify.apiREST;


import com.example.bookify.entities.Book;
import com.example.bookify.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;


import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;


@CrossOrigin("*") //angular  localhost:4200
@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class ClientRestController {
    BookService bookService;
    @GetMapping("/showbooklist")
    public List<Book> getAllBooks(@RequestParam(name = "mc",defaultValue ="" ) String mc,
                                  @RequestParam(name="page",defaultValue = "0")int page,
                                  @RequestParam(name="size",defaultValue = "5")int size)
    {
        Page<Book> list=bookService.getBookByMc(mc, PageRequest.of(page,size));
            return list.getContent();

    }
    @GetMapping("/showbookdetails/{id}")
    public Book showBook(@PathVariable("id") Long id){
        return bookService.getBook(id);
    }

    @RequestMapping("/showbookpdf/{id}")
    public ResponseEntity<Resource> showBookPdf(@PathVariable("id") Long id) throws IOException {
        Book book = bookService.getBook(id);

        if (book != null) {
            String pdfName = book.getBookPdf();
            String path = "static/files/" + pdfName; // Adjust the path based on your file structure
            Resource resource = new UrlResource(Paths.get(path).toUri());
            System.out.println(pdfName);

            System.out.println(path);

            if (resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());


                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                // Handle file not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            // Handle book not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/showbookimage/{id}")
    public byte[] showBookImage(@PathVariable("id") Long id) throws IOException {

        Book book = bookService.getBook(id);
        String photoFileName = book.getPhoto();

        // Adjust the path based on your file structure
        String photoFilePath = "static/files/" + photoFileName;

        Resource resource = new UrlResource(Paths.get(photoFilePath).toUri());

        try {
            // Read the content of the file as a byte array
            return Files.readAllBytes(Path.of(resource.getURI()));
        } catch (IOException e) {
            // Handle the exception, e.g., log it or throw a custom exception
            e.printStackTrace();
            return new byte[0];
        }

    }


}
