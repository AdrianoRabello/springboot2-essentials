package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.exceptions.BadRequestException;
import academy.devdojo.springboot2.exceptions.BadRequestExceptionDetail;
import academy.devdojo.springboot2.exceptions.FieldValidation;
import academy.devdojo.springboot2.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetail> badRequestException(BadRequestException br){

       return new ResponseEntity<>(
                BadRequestExceptionDetail.builder()
                        .timeStamp(LocalDateTime.now())
                        .title("Bad request teste")
                        .detail(br.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())

                        .build(), HttpStatus.BAD_REQUEST
        );


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationException> validationExceptio(MethodArgumentNotValidException methodArgumentNotValidException){

       return  new ResponseEntity<>(
                ValidationException.builder()
                        .message(methodArgumentNotValidException.getLocalizedMessage())
                        .title("Bad request. Invalid fields.")
                        .detail(methodArgumentNotValidException.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timeStamp(LocalDateTime.now())
                        .fieldErros(methodArgumentNotValidException.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(fieldError -> FieldValidation.builder()
                                .field(fieldError.getField()).message(fieldError.getDefaultMessage()).build())
                                .collect(Collectors.toList()))
                       .build(),
                HttpStatus.BAD_REQUEST
        );




    }
}
