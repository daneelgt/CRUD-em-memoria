package br.com.danielg.dao;

import br.com.danielg.exception.EmphyStorageException;
import br.com.danielg.exception.UserNotFoundException;
import br.com.danielg.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //para simular um comportamento de um banco de dados coloquei um contador
    private long nextid = 1L;

    private final List<UserModel> models = new ArrayList<>();

    // Um metodo de save
    public UserModel save(final UserModel model){
        model.setId(nextid++);
        models.add(model);
        return model;
    }

    // Um metodo de update
    public UserModel update(final UserModel model){
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    // Um metodo de delete
    public void delete(final long id){
        var toDelete = findById(id);
        models.remove(toDelete);
    }


    // O findById disparando uma excecao que estou usando e o RuntimeException
    public UserModel findById(final long id){
        var message = String.format("Não existe usuário com o id %s cadastrado", id);
        return models.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(message));
    }


    public List<UserModel> findAll(){
        List<UserModel> result = null;
        try {
            verifyStorage();
            result = models;
        }catch (EmphyStorageException ex){
            ex.printStackTrace();
            result = new ArrayList<>();
        }
        return result;
    }

    private void verifyStorage(){
        if(models.isEmpty()) throw new EmphyStorageException("O armazenamento está vazio");
    }
}
