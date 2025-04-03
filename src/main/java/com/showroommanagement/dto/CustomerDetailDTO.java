package com.showroommanagement.dto;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailDTO {
    private String Name;
    private String userEmail;
    private String customerAddress;
    private String showroomName;
=======
import lombok.*;

@Data
//@AllArgsConstructor
public class CustomerDetailDTO {
    private String Name;
    private String customerAddress;
    private String showroomName;


>>>>>>> 1bb0e5d (first commit)
}
