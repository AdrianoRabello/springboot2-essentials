package academy.devdojo.springboot2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ExceptionDetail {

    protected String title;
    protected String message;
    protected String detail;
    protected int status;
    protected LocalDateTime timeStamp;
}
