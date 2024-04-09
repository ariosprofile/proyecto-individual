package com.files.library;

import com.files.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {
	public static void main(String[] args) {SpringApplication.run(LibraryApplication.class, args);}
}
