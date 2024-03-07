package ht.process.app.books;

import org.springframework.data.annotation.Id;

record Book(@Id int id, String name, String author, double price) {
}
