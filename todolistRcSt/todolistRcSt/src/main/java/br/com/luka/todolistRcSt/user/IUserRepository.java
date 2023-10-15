package br.com.luka.todolistRcSt.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//http://localhost:8080/h2-console

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    /*-> cria uma interface do usuario para ser 
    gerenciada pelo repositorio do jpa(bd persist de dados)
    */

    UserModel findByUsername(String username); //-> cria um metodo para verificar se os usernames sao unicos
} 
