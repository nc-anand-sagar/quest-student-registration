package com.quest.service;

import com.quest.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    public Long createStudent(StudentDTO student);

    public StudentDTO getStudent(Long studentId);

    public List<StudentDTO> getStudents(Integer stClass,Integer startIndex,Integer offset);
}
