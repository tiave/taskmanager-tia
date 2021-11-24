package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.PriorityRepository;
import com.example.demo.domain.Task;
import com.example.demo.domain.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	private TaskRepository trepo; 

	@Autowired
	private PriorityRepository prepo; 
	
	// KIRJAUTUMISSIVU
    @RequestMapping(value= {"/", "/login"})
    public String login() {	
        return "login";
    }	
	
	// TEHTÄVÄSIVU
    @RequestMapping(value="/tasklist")
    public String taskList(Model model) {	
        model.addAttribute("tasks", trepo.findAll());
        return "tasklist";
    }
  
	// REST, KAIKKI TEHTÄVÄT
    @RequestMapping(value="/tasks")
    public @ResponseBody List<Task> taskListRest() {	
        return (List<Task>) trepo.findAll();
    }    

	// REST, TEHTÄVÄ ID:N KAUTTA
    @RequestMapping(value="/task/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) {	
    	return trepo.findById(taskId);
    }      
    
    // TEHTÄVÄN LISÄYS
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("task", new Task());
    	model.addAttribute("priorities", prepo.findAll());
        return "addtask";
    }     
    
    // TEHTÄVÄN TALLENNUS
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Task task){
        trepo.save(task);
        return "redirect:tasklist";
    }    

    // TEHTÄVÄN POISTO, VAIN ADMIN NÄKEE
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") Long id, Model model) {
    	trepo.deleteById(id);
        return "redirect:../tasklist";
    }     
    
    // TEHTÄVÄN MUOKKAAMINEN
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTask(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("task", trepo.findById(id));
    	model.addAttribute("priorities", prepo.findAll());
    	return "edittask";
    }   

}
