package com.example.bookify.service;

import com.example.bookify.dao.BookRepository;
import com.example.bookify.entities.Book;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService implements IBookService{
    private BookRepository bookRepository;



    @Override
    @Transactional
    public void SaveBook(Book b, MultipartFile photo, MultipartFile book) throws IOException {
        try{

            if(!photo.isEmpty()){
                String bookImage=saveFile(photo);
                b.setPhoto(bookImage);

            }
            if(!book.isEmpty()){
                String bookPdf=saveFile(book);
                b.setBookPdf(bookPdf);
            }
            bookRepository.save(b);
            System.out.println("book saved succ");
        }catch(Exception e){
            System.out.println("error saving book"+e);
        }
    }
    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    @Override
    public Page<Book> getBookByMc(String mc , Pageable b){

        return bookRepository.findByTitleContains(mc, b);
    }
    @Override
    public List<Book> getBookByAuth(Long idAuth){
        return bookRepository.getBooksByAuthor(idAuth);
    }
    @Override
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    @Override
    public Book getBook(Long id){
        return bookRepository.findById(id).orElse(null);
    }


    public void editBook(Long id, Book editedBook,MultipartFile photo,MultipartFile book) throws IOException{
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if(existingBookOptional.isPresent()){
            Book existingBook = existingBookOptional.get();

            existingBook.setIsbn(editedBook.getIsbn());
            existingBook.setTitle(editedBook.getTitle());
            existingBook.setPublicationYear(editedBook.getPublicationYear());
            existingBook.setGenres(editedBook.getGenres());
            existingBook.setAuthor(editedBook.getAuthor());
            if (photo!=null && !photo.isEmpty()){
                String newPhotoName=saveFile(photo);
                existingBook.setPhoto(newPhotoName);
            }
            if(book!=null && !book.isEmpty()){
                String newBookName=saveFile(book);
                existingBook.setBookPdf(newBookName);
            }
            bookRepository.save(existingBook);
        }
    }
    public String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String[] fileNameParts = originalFilename.split("\\.");
        String newFileName = fileNameParts[0] + System.currentTimeMillis() + "." + fileNameParts[1];

        // Assuming "static/files" is the directory where you want to save the PDF files
        File directory = new File("static/files");
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        String filePath = directory.getAbsolutePath();
        Path path = Paths.get(filePath, newFileName);
        Files.write(path, file.getBytes());

        return newFileName;
    }
}
