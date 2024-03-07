package boot2adata.app.books;

import org.springframework.context.ApplicationEvent;

import java.util.Collection;


class BooksLoadEvent extends ApplicationEvent {


    public BooksLoadEvent(Collection<Object> source) {
        super(source);
    }


    @Override
    public Collection<Book> getSource() {
        return (Collection<Book>) super.getSource();
    }

}
