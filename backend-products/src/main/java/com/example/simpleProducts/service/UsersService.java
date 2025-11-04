package com.example.simpleProducts.service;


import com.example.simpleProducts.classBox.HashManager;
import com.example.simpleProducts.classBox.Rol;
import com.example.simpleProducts.entity.UsersJPA;
import com.example.simpleProducts.repository.LogRepository;
import com.example.simpleProducts.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional(readOnly = false)//Automáticamente se encarga de rollsback y commits
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LogRepository logRepository;



    /*Operaciones CRUD
    Todo aún sin probar
    Tareas - ¿Acabadas? -   llamadas -    ¿Probadas?
    Create - Si             -   POST         -
    Read - Si               -   Get        -
    Update - Si             -           -
    Delete - Si             -           -
    Validar usuario - si    -           -

Operación	¿Acabada?	Método HTTP	Descripción
Create	Sí	POST	Crear un nuevo recurso (usuario, producto, etc.)
Read	Sí	GET	Obtener uno o varios recursos
Update	Sí	PUT o PATCH	Modificar un recurso existente (PUT reemplaza, PATCH actualiza parcialmente)
Delete	Sí	DELETE	Eliminar un recurso
Validar usuario	Sí	POST



     */

    //Post
    public ResponseEntity<?> createUserByObj(UsersJPA values) {
        if (usersRepository.findByCompanyMail(values.getCompanyMail()) != null) {
            //System.out.println("Ejecución prevista");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Es probable que el usuario ya exista, porque el correo está en uso"));
        }

        UsersJPA savedUser = usersRepository.save(values);
        return ResponseEntity.ok(savedUser);
    }

    //Post
    public ResponseEntity<UsersJPA> createUserByParams (String name, String lastNameEx, Integer ageEx, Rol rolEx, String psswrd){


        UsersJPA storeValue = new UsersJPA(name,lastNameEx,ageEx,rolEx,psswrd);
        usersRepository.save(storeValue);
        return ResponseEntity.ok(storeValue);
    }


    //Get
    public List<UsersJPA> readAllUsers(){
        /* Entregan el mismo resultado.
        ArrayList<UsersJPA> xxx = new ArrayList<>();
        xxx.add((UsersJPA) object.findAll());
        return xxx;
        */
        return List.copyOf((Collection<? extends UsersJPA>) usersRepository.findAll());
    }
    //Get
    public  List<UsersJPA> readOnlyRolUsers(Rol rolEx){
        return List.copyOf((Collection<? extends UsersJPA>)  usersRepository.findByRol(rolEx));
    }
    //Get
    public Optional<UsersJPA> readUserById(Integer id){
        return usersRepository.findById(id);
    }

    public ResponseEntity<UsersJPA> updateUser (UsersJPA values)  {

        return usersRepository.findById(values.getIdUser()).map(newUserObj -> {
            newUserObj.setFirstName(values.getFirstName());
            newUserObj.setLastName(values.getLastName());
            newUserObj.setAge(values.getAge());
            newUserObj.setRol(values.getRol());
            newUserObj.setMobile(values.getMobile());
            newUserObj.setPsswrd(values.getPsswrd());

            UsersJPA updateCase = usersRepository.save(newUserObj);
            return ResponseEntity.ok(updateCase);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Añadir borrado en cascada, eliminar usuarios de log_user
    public ResponseEntity<UsersJPA> deleteUser(Integer id) {

       int count = usersRepository.countUserLogs(id); //Revisamos si el usuario tiene registros en la entidad Log

       Optional<UsersJPA> searchUser = usersRepository.findById(id);
       UsersJPA userEliminate = searchUser.get();

       if (count >= 1){
           logRepository.deleteAllByUserId(id); //Eliminamos todos los registros de la entidad Log, que nos impedían eliminar el usuario.
           usersRepository.deleteById(id); //Eliminamos el usuario de la entindad Usuario.
           return ResponseEntity.ok(userEliminate); //Devolvemos el usuario eliminado.
       }

        return ResponseEntity.notFound().build();

    }

    public Optional<UsersJPA> verifierPassword (String mail, String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        UsersJPA foundUser =  usersRepository.findByCompanyMail(mail); //Buscamos el usuario por su correo de empresa.
        String manualPassword = HashManager.encrypt(password); //Encriptamos la contraseña.

        if (foundUser != null) {
            if (manualPassword.equals(foundUser.getPsswrd())) {
                return Optional.of(foundUser); //Si el usuario existe y la contraseña coincide lo retornamos.
            }
        }

        return Optional.empty();

    }








}
