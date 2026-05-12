package br.com.danielg.validator;

import br.com.danielg.dao.UserDAO;
import br.com.danielg.exception.ValidatorException;
import br.com.danielg.model.UserModel;

public class UserValidator {
    private UserValidator(){
    }

    public static void verifyModel(final UserModel model) throws ValidatorException{

        if(stringIsBlank(model.getName()))
            throw new ValidatorException("Informe um nome válido");
        if(model.getName().length() <= 2)
            throw new ValidatorException("O nome deve ter mais que 2 caractér");
        if(stringIsBlank(model.getEmail()))
            throw new ValidatorException("Informe um e-mail válido");
        if(!model.getEmail().contains("@") || !model.getEmail().contains("."))
            throw new ValidatorException("E-mail inválido");

    }

    private static boolean stringIsBlank(final String value){
        return value == null || value.isBlank();
    }
}
