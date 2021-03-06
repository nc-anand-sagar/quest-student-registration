package com.quest.repository;

import com.quest.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s JOIN s.address a WHERE s.studentClass=:stclass")
    public Page<Student> findAll(Integer stclass, Pageable pageable);
}
