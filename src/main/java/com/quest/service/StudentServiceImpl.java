package com.quest.service;

import com.quest.constant.AppConstants;
import com.quest.dto.AddressDTO;
import com.quest.dto.StudentDTO;
import com.quest.entity.Address;
import com.quest.entity.Student;
import com.quest.repository.AddressRepository;
import com.quest.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private static final int TENTH_CLASS=10;
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Long createStudent(StudentDTO student) {
        logger.info("StudentServiceImpl-createStudent() method invoked");
        com.quest.entity.Student st = new com.quest.entity.Student();
        st.setFname(student.getFirstname());
        st.setLname(student.getLastname());;
        st.setSchoolName(student.getSchoolName());
        st.setStudentClass(student.getStudentClass());

        List<Address> address = new ArrayList<>();

        st.getAddress().addAll(getAddressModelFromAddressDTO(student.getAddress()));
        Long id = studentRepo.save(st).getId();
        if(st.getStudentClass().equals(TENTH_CLASS)){
            logger.info("Student with 10th class registered.Sending message to kafk topic");
            kafkaTemplate.send(AppConstants.TOPIC_NAME,"Student is registered in 10th Class. Please connect with: "+student.getEmailId());
        }
        logger.info("StudentServiceImpl-createStudent() method completed");
        return id;
    }


    public StudentDTO getStudent(Long studentId) {
        logger.info("StudentServiceImpl-getStudent() method invoked");
        Student student = studentRepo.getById(studentId);
        return student != null ? getStudentDTOFromStudent(student) : null;

    }

    public List<StudentDTO> getStudents(Integer stClass,Integer startIndex,Integer offset) {
        logger.info("StudentServiceImpl-getStudents() method invoked");
        Pageable pageable = PageRequest.of(startIndex, offset);
        Page<Student> students = studentRepo.findAll(stClass,  pageable);
        List<StudentDTO> studentDTOS = students.getContent().stream().map(student -> getStudentDTOFromStudent(student)).collect(Collectors.toList());
        return studentDTOS;
    }

    private StudentDTO getStudentDTOFromStudent(Student student) {
        StudentDTO st = new StudentDTO();
        List<AddressDTO> addressDTOFromAddress = getAddressDTOFromAddress(student.getAddress());
        st.setAddress(addressDTOFromAddress);
        st.setStudentClass(student.getStudentClass());
        st.setFirstname(student.getFname());
        st.setLastname(student.getFname());
        st.setSchoolName(student.getSchoolName());
        return st;
    }

    private List<AddressDTO> getAddressDTOFromAddress(List<Address> address) {
        List<AddressDTO> addressDTO = address
                .stream()
                .map(obj -> new AddressDTO(obj.getPin(), obj.getCity(), obj.getState(), obj.getCountry(), obj.getFlatNumber(), obj.getAddressLine1(), obj.getAddressLine2()))
                .collect(Collectors.toList());

        return addressDTO;
    }
    private List<Address> getAddressModelFromAddressDTO(List<AddressDTO> address) {
        List<Address> addressDTO = address
                .stream()
                .map(obj -> new Address(obj.getPin(), obj.getCity(), obj.getState(), obj.getCountry(), obj.getFlatNumber(), obj.getAddressLine1(), obj.getAddressLine2()))
                .collect(Collectors.toList());

        return addressDTO;
    }
}
