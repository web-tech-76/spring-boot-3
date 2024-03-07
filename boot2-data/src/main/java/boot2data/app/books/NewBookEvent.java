package boot2data.app.books;

import org.springframework.context.ApplicationEvent;


class NewBookEvent extends ApplicationEvent {


    public NewBookEvent(Book source) {
        super(source);
    }

    @Override
    public Book getSource() {
        return (Book) super.getSource();
    }
}
