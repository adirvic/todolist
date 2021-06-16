package br.com.adirvic.task.controller;

import br.com.adirvic.task.model.ERole;
import br.com.adirvic.task.model.StatusTask;
import br.com.adirvic.task.model.Task;
import br.com.adirvic.task.securityConfig.services.UserDetailsImpl;
import br.com.adirvic.task.services.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final @NonNull
    TaskService taskService;

    @CrossOrigin
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Task> getTask() {
        UserDetailsImpl userContext = getUserContext();

        List<Task> tasks;
        if (isAdmin(userContext)) {
            tasks = taskService.findAll();
        } else {
            tasks = taskService.findAllByUserId(userContext.getId());
        }

        return tasks;
    }

    @CrossOrigin
    @GetMapping("/status/{statusTask}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Task> getTaskByStatus(@PathVariable StatusTask statusTask) {
        UserDetailsImpl userContext = getUserContext();

        List<Task> tasks;
        if (isAdmin(userContext)) {
            tasks = taskService.findAllByStatus(statusTask);
        } else {
            tasks = taskService.findAllByUserIdAndStatus(userContext.getId(),statusTask);
        }

        return tasks;
    }

    @CrossOrigin
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void insertTask(@Valid @RequestBody Task task) {
        UserDetailsImpl userContext = getUserContext();
        task.setUserId(userContext.getId());
        taskService.saveTask(task);
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Task getTaskById(@PathVariable("id") UUID id) {
        return taskService.getTask(id);
    }

    private UserDetailsImpl getUserContext() {
        return (UserDetailsImpl) getContext().getAuthentication().getPrincipal();
    }

    private SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    private boolean isAdmin(UserDetailsImpl userContext) {
        return userContext.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(ERole::valueOf)
                .anyMatch(ERole.ROLE_ADMIN::equals);
    }
}