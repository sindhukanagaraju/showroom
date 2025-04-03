package com.showroommanagement.dto;

<<<<<<< HEAD
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDTO {
    private final Integer statusCode;
    private final String message;
    private final Object data;
=======
public record ResponseDTO(Integer statusCode, String message, Object data) {
>>>>>>> 1bb0e5d (first commit)
}
