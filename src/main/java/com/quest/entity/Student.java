package com.quest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name="student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String fname;
    @Column
    private String lname;
    @Column
    private Integer studentClass;
    @Column
    private String schoolName;
    @Column
    private String emailId;

    @OneToMany(/*mappedBy = "student",*/ targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name="student_id", referencedColumnName = "id")
    private List<Address> address= new ArrayList<>();
}
