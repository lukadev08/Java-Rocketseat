package br.com.luka.todolistRcSt.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.luka.todolistRcSt.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    /** 
     * 1- autenticar usuario e senha
     * 2- validar usuario
     * 3- validar senha
     * 4- ok
    */

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException { 
            
                var servletPath = request.getServletPath();

                if(servletPath.startsWith("/tasks/")){ //pega todos os dados da rota apos a primeira /
                    
                    //codifica para base64
                    var authorization = request.getHeader("Authorization");
                    
                    var authEncoded = authorization.substring("Basic".length()).trim(); //filtra a linha "basic etc de resposta http"
                    
                    //decodifica
                    byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                    var authString = new String(authDecode);
        
                    String[] credentials = authString.split(":");
                    String username = credentials[0];
                    String password = credentials[1];                
        
                    //valida usuario
                    var user = this.userRepository.findByUsername(username);
                    if(user == null){
                        response.sendError(401);
                    } else {
                        //verifica a igualdade entre char de senha
                        var passowrdVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                        if(passowrdVerify.verified){
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);
                    } else {
                            response.sendError(401);
                        }
                        
                    }
                } else {
                    
                    filterChain.doFilter(request, response);
                }

    }   
    
}
