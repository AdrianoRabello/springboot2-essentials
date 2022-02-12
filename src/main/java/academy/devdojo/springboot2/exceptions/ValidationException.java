package academy.devdojo.springboot2.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
public class ValidationException extends ExceptionDetail {


    private List<FieldValidation> fieldErros = new ArrayList<>();


    public void addFieldError(FieldValidation fieldValidation) {
        this.fieldErros.add(fieldValidation);
    }


}
