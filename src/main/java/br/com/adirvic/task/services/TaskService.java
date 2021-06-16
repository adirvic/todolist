package br.com.adirvic.task.services;

import br.com.adirvic.task.model.StatusTask;
import br.com.adirvic.task.model.Task;
import br.com.adirvic.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllByUserId(UUID userId) {
        return taskRepository.findAllByUserIdOrderByStatus(userId);
    }

    public List<Task> findAll() {
        return taskRepository.findAllByOrderByStatusAsc();
    }

    public Task getTask(UUID id) {
        return taskRepository.getOne(id);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findAllByUserIdAndStatus(UUID userId, StatusTask statusTask) {
        return taskRepository.findAllByUserIdAndStatusOrderByStatus(userId, statusTask);
    }

    public List<Task> findAllByStatus(StatusTask statusTask) {
        return taskRepository.findAllByStatus(statusTask);
    }
}