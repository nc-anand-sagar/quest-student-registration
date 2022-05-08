package com.quest.controller;

import com.quest.dto.StudentDTO;
import com.quest.service.IStudentService;
import com.quest.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    IStudentService studentService;

    @PostMapping("/registerStudent")
    public ResponseEntity<Long> createStudent(@RequestBody StudentDTO student) {
        logger.info("StudentController-createStudent() method invoked");
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) {
        logger.info("StudentController-getStudent() method invoked");
        StudentDTO studentDTO = studentService.getStudent(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }
    }

    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getStudents(@RequestParam("class") Integer stClass, @RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        logger.info("StudentController-getStudents() method invoked");
        return new ResponseEntity<>(studentService.getStudents(stClass, pageNumber, pageSize), HttpStatus.OK);
    }
}
