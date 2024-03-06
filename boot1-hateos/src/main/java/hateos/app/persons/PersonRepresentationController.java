package hateos.app.persons;


import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Slf4j
@Controller
@ResponseBody
public class PersonRepresentationController {

    private final PersonService personService;

    PersonRepresentationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/personRep/{id}")
    HttpEntity<PersonModel> getById(@PathVariable("id") @Nullable Integer id) {

        var person = this.personService.all()
                .stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElse(null);

        var personModel =
                new PersonModel(person);

        final var link1 = linkTo(methodOn(PersonController.class).all()).withRel("persons");
        final var link2 = linkTo(methodOn(PersonRepresentationController.class).getById(id)).withSelfRel();

        personModel.add(link1, link2);
        return ResponseEntity.ok(personModel);

    }


    @Data
    @RequiredArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    static class PersonsModel extends RepresentationModel<PersonsModel> {

        private final Collection<Person> persons;

    }


    @GetMapping("/allPersons")
    HttpEntity<PersonsModel> getAllPersons() {

        var all = this.personService.all();
        var personsModel = new PersonsModel(all);

        final var link1 = linkTo(methodOn(PersonRepresentationController.class)
                .getAllPersons()).withSelfRel();
        final var link2 = linkTo(
                methodOn(PersonRepresentationController.class)
                        .getById(null)).withSelfRel();

        personsModel.add(link1, link2);
        return ResponseEntity.ok(personsModel);

    }


}
