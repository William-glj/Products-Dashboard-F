package com.example.simpleProducts.service;


import com.example.simpleProducts.classBox.HashManager;
import com.example.simpleProducts.classBox.Rol;
import com.example.simpleProducts.entity.UsersJPA;
import com.example.simpleProducts.repository.LogRepository;
import com.example.simpleProducts.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional(readOnly = true)//Automáticamente se encarga de rollsback y commits
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LogRepository logRepository;

    /*Operaciones CRUD
    Todo aún sin probar
    Tareas - ¿Acabadas? - ¿Probadas?
    Create - Si
    Read - Si
    Update - Si
    Delete - Si
    Validar usuario - si





     */


    public ResponseEntity<UsersJPA> createUserByObj (UsersJPA values){
        return ResponseEntity.ok(usersRepository.save(values));
    }

    public ResponseEntity<UsersJPA> createUserByParams (String name, String lastNameEx, Integer ageEx, Rol rolEx, String psswrd){

        UsersJPA storeValue = new UsersJPA(name,lastNameEx,ageEx,rolEx,psswrd);
        usersRepository.save(storeValue);
        return ResponseEntity.ok(storeValue);
    }



    public List<UsersJPA> readAllUsers(){
        /* Entregan el mismo resultado.
        ArrayList<UsersJPA> xxx = new ArrayList<>();
        xxx.add((UsersJPA) object.findAll());
        return xxx;
        */
        return List.copyOf((Collection<? extends UsersJPA>) usersRepository.findAll());
    }
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



            /*Ahora con la función/trigger en MySQL esta acción queda en desuso.
            // Solo encriptar si hay una nueva contraseña
            if (values.getPsswrd() != null && !values.getPsswrd().isEmpty()) {
                try {
                    String ciPsswrd = HashManager.encrypt(values.getPsswrd());
                    newUserObj.setPsswrd(ciPsswrd);
                } catch (Exception e) {
                    e.printStackTrace(); // mejor que solo println
                    System.out.println("Error en la clase UserService, método updateUser, al encriptar la contraseña");
                }
            }
            */
            UsersJPA updateCase = usersRepository.save(newUserObj);
            return ResponseEntity.ok(updateCase);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    //Añadir borrado en cascada, eliminar usuarios de log_user
    public ResponseEntity<UsersJPA> deleteUser(Integer id) {

       int count = usersRepository.countUserLogs(id);

       Optional<UsersJPA> searchUser = usersRepository.findById(id);
       UsersJPA userEliminate = searchUser.get();

       if (count >= 1){
           logRepository.deleteAllByUserId(id);
           usersRepository.deleteById(id);
           return ResponseEntity.ok(userEliminate);
       }

        return ResponseEntity.notFound().build();

    }


    public Optional<UsersJPA> verifierPassword (String mail, String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        UsersJPA foundUser =  usersRepository.findByCompanyMail(mail);
        String manualPassword = HashManager.encrypt(password);

        if (foundUser != null) {
            if (manualPassword.equals(foundUser.getPsswrd())) {
                return Optional.of(foundUser);
            }
        }

        return Optional.empty();

    }








}
