package com.amir.taskmanager.repository;

import com.amir.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContainingIgnoreCase(String title);

    @Query("""
            SELECT t
            FROM Task t
            WHERE t.status = 'DONE'
            """)
    List<Task> findAllDoneTask();
}
