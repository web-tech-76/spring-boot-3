package ht.process.app.books;

import org.springframework.context.ApplicationEvent;

import java.util.Collection;


class BooksLoadEvent extends ApplicationEvent {


    public BooksLoadEvent(Collection<Book> source) {
        super(source);
    }


    @Override
    public Collection<Book> getSource() {
        return (Collection<Book>) super.getSource();
    }


}
