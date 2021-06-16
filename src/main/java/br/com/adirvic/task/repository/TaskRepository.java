package br.com.adirvic.task.repository;

import br.com.adirvic.task.model.StatusTask;
import br.com.adirvic.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByUserIdOrderByStatus(UUID userId);

    List<Task> findAllByUserIdAndStatusOrderByStatus(UUID userId, StatusTask status);

    List<Task> findAllByOrderByStatusAsc();

    List<Task> findAllByStatus(StatusTask status);

}