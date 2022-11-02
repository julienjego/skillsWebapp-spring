package fr.afpajulien.webappdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.afpajulien.webappdemo.configuration.CustomProperties;
import fr.afpajulien.webappdemo.model.Person;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PersonRepository {

    @Autowired
    private CustomProperties props;

    public Iterable<Person> getPersons() {
        String baseApiUrl = props.getApiUrl();
        String getPersonsUrl = baseApiUrl + "/persons";

        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<Iterable<Person>> personsList = new ParameterizedTypeReference<Iterable<Person>>() {
        };
        ResponseEntity<Iterable<Person>> response = restTemplate.exchange(getPersonsUrl, HttpMethod.GET, null,
                personsList);

        log.debug("Get persons call:" + response.getStatusCode().toString());

        return response.getBody();
    }

    public Person getPerson(int id) {
        String baseApiUrl = props.getApiUrl();
        String getPersonUrl = baseApiUrl + "/person/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> response = restTemplate.exchange(
                getPersonUrl,
                HttpMethod.GET,
                null,
                Person.class);

        log.debug("Get Person call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Person createPerson(Person p) {
        String baseApiUrl = props.getApiUrl();
        String createPersonUrl = baseApiUrl + "/person";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(p);
        ResponseEntity<Person> response = restTemplate.exchange(
                createPersonUrl,
                HttpMethod.POST,
                request,
                Person.class);

        log.debug("Create Person called. Status: " + response.getStatusCode().toString());
        return response.getBody();
    }

    public Person updatePerson(Person e) {
        String baseApiUrl = props.getApiUrl();
        String updatePersonUrl = baseApiUrl + "/person/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(e);
        ResponseEntity<Person> response = restTemplate.exchange(
                updatePersonUrl,
                HttpMethod.PUT,
                request,
                Person.class);

        log.debug("Update Person call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deletePerson(int id) {
        String baseApiUrl = props.getApiUrl();
        String deletePersonUrl = baseApiUrl + "/person/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deletePersonUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Person call " + response.getStatusCode().toString());
    }

}
