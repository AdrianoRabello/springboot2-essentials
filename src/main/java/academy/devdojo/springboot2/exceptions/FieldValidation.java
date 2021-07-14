package academy.devdojo.springboot2.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldValidation {

    private String field;
    private String message;
}
