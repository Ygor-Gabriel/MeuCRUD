package br.com.crud.appcrud.Controller;

import br.com.crud.appcrud.Models.Usuario;
import br.com.crud.appcrud.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // métodos do CRUD aqui
    // Listar todos os usuarios aqui aqui (Get)
    @GetMapping
    public List findAll(){
        return usuarioRepository.findAll();
    }
    // listar apenas um usuario buscando pelo seu ID
    @GetMapping(path = {"/{id}"})
    public Object findById(@PathVariable long id){
        return usuarioRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //Criando um novo usuario (Post)
    @PostMapping(path = {"/salvar"})
    public Usuario create(@RequestBody Usuario usuario){
        return (Usuario) usuarioRepository.save(usuario);
    }

    //para atualizar um contato existente está listado abaixo POST
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(record -> {
                    record.setLogin(usuario.getLogin());
                    record.setSenha(usuario.getSenha());
                    Usuario updated = (Usuario) usuarioRepository.save((Usuario) record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    //Removendo um usuario pelo ID (DELETE /usuarios/{id}) (Delete)
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return usuarioRepository.findById(id)
                .map(record -> {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}