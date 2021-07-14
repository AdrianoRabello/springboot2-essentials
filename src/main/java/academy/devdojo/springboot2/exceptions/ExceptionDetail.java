package academy.devdojo.springboot2.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetail {

    protected String title;
    protected String message;
    protected String detail;
    protected int status;
    protected LocalDateTime timeStamp;
}
