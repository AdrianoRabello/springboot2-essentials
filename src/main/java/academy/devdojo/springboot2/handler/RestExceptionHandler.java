package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.exceptions.BadRequestException;
import academy.devdojo.springboot2.exceptions.BadRequestExceptionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

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
}
