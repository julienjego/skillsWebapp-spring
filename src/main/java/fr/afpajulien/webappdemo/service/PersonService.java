package fr.afpajulien.webappdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpajulien.webappdemo.model.Person;
import fr.afpajulien.webappdemo.repository.PersonRepository;
import lombok.Data;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonRepository pr;

    public Iterable<Person> getPersons() {
        return pr.getPersons();
    }

    public Person getPerson(int id) {
        return pr.getPerson(id);
    }

    public Person addPerson(Person person) {
        Person currentPerson;
        if (person.getId() == null) {
            currentPerson = pr.createPerson(person);
        } else {
            currentPerson = pr.updatePerson(person);
        }

        return currentPerson;
    }

    public void deletePerson(int id) {
        pr.deletePerson(id);
    }

}
