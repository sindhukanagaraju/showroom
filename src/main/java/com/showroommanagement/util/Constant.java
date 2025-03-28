package com.showroommanagement.util;

import org.springframework.stereotype.Component;

@Component
public class Constant {

    private Constant() {
    }

    public static final String CREATE = "Created Successfully";
    public static final String RETRIEVE = "Retrieved Successfully";
    public static final String UPDATE = "Updated Successfully";
    public static final String DELETE = "Deleted Successfully";
    public static final String REMOVE = "Removed Successfully";
    public static final String USER_NOT_FOUND = "user not Found";
    public static final String FOUND = "Data Found";
    public static final String NOT_FOUND = "Data Not Found";
    public static final String ID_DOES_NOT_EXIST = "ID Does not Exist";
    public static final String SIGN_UP = "signup Successfully";
    public static final String SIGN_IN = "signin Successfully";
    public static final String EXIST_MAIL = "email already exist";
}