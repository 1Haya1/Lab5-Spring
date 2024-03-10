package org.example.project.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    private long id;
    private String title;
    private String description;
    private boolean status;
    private String companyName;


}
