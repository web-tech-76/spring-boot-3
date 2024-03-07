package boot2adata.app.books;

import org.springframework.context.ApplicationEvent;


public class NewBookEvent extends ApplicationEvent {


    public NewBookEvent(Book source) {
        super(source);
    }

    @Override
    public Book getSource() {
        return (Book) super.getSource();
    }
}
