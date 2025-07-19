package com.rinchik.practise.repository;

import com.rinchik.practise.model.Event;
import com.rinchik.practise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizer(User user);
}
