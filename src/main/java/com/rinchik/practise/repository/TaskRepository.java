package com.rinchik.practise.repository;

import com.rinchik.practise.model.Event;
import com.rinchik.practise.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEvent(Event event);
}
