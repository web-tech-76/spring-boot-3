package hateos.app.persons;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
class PersonModel extends RepresentationModel<PersonModel> {

    private final Person person;


}
