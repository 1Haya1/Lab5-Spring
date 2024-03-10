package org.example.project.Tracker;

import org.example.project.Api.ApiResponse;
import org.example.project.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final List<Project> projects =new ArrayList<>();
    private long projectId=1;



  @PostMapping("/add")
  public ApiResponse addProject(@RequestBody Project project){
      project.setId(projectId++);
      projects.add(project);
      return new ApiResponse("project added");
  }

@GetMapping("/all")
    public List<Project> getAllProjects(){
      return projects;
}

@PutMapping("/change/{id}")
public ApiResponse changeProjectsStatus(@PathVariable long id){
      for(Project project : projects){
          if(project.getId() == id){
              project.setStatus(!project.isStatus());
              return new ApiResponse("projects changed");
          }
      }
      return new ApiResponse("project not found");
}


    @PutMapping("/update/{id}")
    public ApiResponse updateProject(@PathVariable("id") long id, @RequestBody Project updatedProject) {
        for (Project project : projects) {
            if (project.getId() == id) {
                project.setTitle(updatedProject.getTitle());
                project.setDescription(updatedProject.getDescription());
                project.setStatus(updatedProject.isStatus());
                project.setCompanyName(updatedProject.getCompanyName());
                return new ApiResponse("project updated");
            }
        }
        return new ApiResponse("project not found");
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable long id) {
        try {
            projects.removeIf(project -> project.getId() == id);
            return new ApiResponse("deleted done");
        } catch (Exception e) {
            return new ApiResponse("error in delete");
        }
    }


    @GetMapping("/search/{title}")
    public List<Project> searchByTitle(@PathVariable String title) {
        List<Project> foundProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                foundProjects.add(project);
            }
        }
        return foundProjects;
    }


    @GetMapping("/company/{companyName}")
    public List<Project> getAllProjectsByCompanyName(@PathVariable String companyName) {
        List<Project> projectsByCompany = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                projectsByCompany.add(project);
            }
        }
        return projectsByCompany;
    }
















}
