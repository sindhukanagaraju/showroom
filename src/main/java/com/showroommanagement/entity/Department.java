package com.showroommanagement.entity;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
=======
import lombok.Data;

@Entity
@Data
>>>>>>> 1bb0e5d (first commit)
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "id")
    private Integer id;

    @Column(name = "department_name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "showroom_id")
=======

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
>>>>>>> 1bb0e5d (first commit)
    private Showroom showroom;
}
