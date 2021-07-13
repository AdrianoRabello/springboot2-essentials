package academy.devdojo.springboot2.exceptions;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadRequestExceptionDetail {

    private String title;
    private String message;
    private String detail;
    private int status;
    private LocalDateTime timeStamp;
}
