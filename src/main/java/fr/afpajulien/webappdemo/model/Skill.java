package fr.afpajulien.webappdemo.model;

import lombok.Data;

@Data
public class Skill {
    private Long id;
    private String skillName;
    private Long personId;
}
