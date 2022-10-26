package fr.afpajulien.webappdemo.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Skill> skills;
}
