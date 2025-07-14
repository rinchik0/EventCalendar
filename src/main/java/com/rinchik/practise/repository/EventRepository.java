package com.rinchik.practise.repository;

import com.rinchik.practise.model.Event;
import com.rinchik.practise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizer(User user);
}
