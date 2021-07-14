package academy.devdojo.springboot2.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
public class ValidationException extends ExceptionDetail{


    private List<FieldValidation> fieldErros = new ArrayList<>();


    public void addFieldError(FieldValidation fieldValidation){
        this.fieldErros.add(fieldValidation);
    }


}
