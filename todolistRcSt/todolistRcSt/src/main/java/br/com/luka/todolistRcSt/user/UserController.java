package br.com.luka.todolistRcSt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired // -> spring gerencia todo ciclo de vida dos dados
    private IUserRepository userRepository;

    /*corpo da requisicao http*/
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        // -> corpo de resposta do status http sobre a requisicao
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }

        //importa lib de cript. senha e deve se converter para um char[]
        var passowordHash = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passowordHash); //usa o metodo pra setar a criptografia 

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);

    }
}
