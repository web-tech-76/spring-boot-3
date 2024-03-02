package hateos.app.persons;

import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController implements ApplicationListener<PersonLoadEvent> {

    private List<Person> personList = new LinkedList<>();


    @Override
    public void onApplicationEvent(PersonLoadEvent event) {
        this.personList = (event.getSource());
    }

    @GetMapping("/persons")
    @ResponseBody
    public Collection<Person> all() {
        return this.personList;
    }


    @GetMapping("/persons.do")
    public String allTh(@RequestParam Integer id, Model model) {
        model.addAttribute("person", this.personList.stream().filter(person -> person.id() == id).findFirst().get());
        return "person";
    }

    @GetMapping("/persons/{id}")
    @ResponseBody
    public HttpEntity<Person> byId(@PathVariable Integer id) {
        return ResponseEntity.ok(this
                .personList
                .stream()
                .filter(person -> person.id() == id)
                .findFirst()
                .get());
    }


    @GetMapping("/persons.th")
    public ModelAndView allTh() {
        return new ModelAndView("persons-th", Map.of("persons", this.personList));
    }

    @GetMapping("/persons.mu")
    public ModelAndView allMu() {
        return new ModelAndView("persons-mu", Map.of("persons", this.personList));
    }

}
