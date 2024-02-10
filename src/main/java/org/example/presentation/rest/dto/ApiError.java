package org.example.presentation.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {
  HttpStatus statusCode;
  int errorCode;
  String message;
}
