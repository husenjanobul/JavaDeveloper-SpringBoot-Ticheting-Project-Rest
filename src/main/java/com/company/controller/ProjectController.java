package com.company.controller;


import com.company.dto.ProjectDTO;
import com.company.dto.TaskDTO;
import com.company.dto.UserDTO;
import com.company.enums.Status;
import com.company.service.ProjectService;
import com.company.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }
    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projects",projectService.listAllProjects());
        model.addAttribute("managers",userService.listAllByRole("manager"));
//        model.addAttribute("managers",userService.findAll());

        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(ProjectDTO project){
        projectService.save(project);
        project.setProjectStatus(Status.OPEN);
        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectcode,ProjectDTO project){
        projectService.delete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
        public String completeProject(@PathVariable("projectcode") String projectcode){
        projectService.complete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectcode}")
    public String editProject(@PathVariable("projectcode") String projectcode,Model model){
        model.addAttribute("project",projectService.getByProjectCode(projectcode));
        model.addAttribute("projects",projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));
        return "/project/update";
    }

    @PostMapping("/update/{projectcode}")
    public String updateProject(@PathVariable("projectcode") String projectcode,ProjectDTO project){
        projectService.update(project);
//        project.setProjectStatus(Status.OPEN);
            return "redirect:/project/create";
    }


    @GetMapping("/manager/complete")
    public String getProjectByManager(Model model) {
        List<ProjectDTO> projects = projectService.listAllProjectDetails();
        model.addAttribute("projects", projects);
        return "/manager/project-status";
    }

    @GetMapping("/manager/complete/{projectCode}")
    public String manager_completed(@PathVariable("projectCode") String projectCode,Model model){
        projectService.complete(projectCode);
        return "redirect:/project/manager/complete";
    }

}
