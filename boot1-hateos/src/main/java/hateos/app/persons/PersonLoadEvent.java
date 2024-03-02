package hateos.app.persons;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class PersonLoadEvent extends ApplicationEvent {


    public PersonLoadEvent(List<Person> source) {
        super(source);
    }

    @Override
    public List<Person> getSource() {
        return (List<Person>) super.getSource();
    }
}
