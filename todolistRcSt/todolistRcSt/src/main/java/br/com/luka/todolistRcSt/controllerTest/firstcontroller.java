package br.com.luka.todolistRcSt.controllerTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*controller => camada de acesso entre a requisicao 
do usuario com as outras camadas: regras negc, bd*/

/** anotacoes: requisicoes http - CRUD
 * GET -> Buscar um dado
 * POST -> Adicionar um dado
 * PUT -> Alterar um dado
 * DELETE -> Remover um dado
 * PATCH -> Alterar uma parte de um dado
 */

 //add /controllerTest ao gitignore: anotacoes
 
@RestController
@RequestMapping("/primeiraRota") // -> determina a roda de chamadas http
// http://localhost:8080/primeiraRota
public class firstcontroller {
    
    @GetMapping("")
    public String primeiramsg(){
        return "<h1>Funcionou</h1>";
    }
}
