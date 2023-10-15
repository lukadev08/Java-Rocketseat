package br.com.luka.todolistRcSt.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // -> gera gets e sets pelo lombok(@getter ou @setter individualmente)
@Entity(name="tb_users") // -> cria uma entidade no banco de dados
public class UserModel {

    @Id
    @GeneratedValue (generator = "UUID") // -> cria automaticamente proximos ids
    private UUID id; // -> gera um id aleatorio nao sequencial
    
    @Column(unique = true) // -> restricao para entidades unicas criadas
    private String username;
    private String name;
    private String password;
    
    @CreationTimestamp // -> quando for gerado um novo dado sera atribuido a data quando foi criada
    private LocalDateTime createdAt;
}
