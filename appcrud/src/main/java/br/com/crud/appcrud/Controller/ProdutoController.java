package br.com.crud.appcrud.Controller;

import br.com.crud.appcrud.Models.Produto;
import br.com.crud.appcrud.Repository.ProdutoReposiitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/produtos"})

public class ProdutoController {

    @Autowired
    private ProdutoReposiitory produtoReposiitory;

    // métodos do CRUD aqui
    // Listar todos os produtos aqui
    @GetMapping
    public List findAll(){
        return produtoReposiitory.findAll();
    }

    // listar apenas um produto buscando pelo seu ID
    @GetMapping(path = {"/{id}"})
    public Object findById(@PathVariable long id){
        return produtoReposiitory.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //Criando um novo produto INSERT
    @PostMapping(value = "/salvar")
    public Produto create(@RequestBody Produto produto){
        return (Produto) produtoReposiitory.save(produto);
    }

    //para atualizar um contato existente está listado abaixo POST
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Produto produto) {
        return produtoReposiitory.findById(id)
                .map(record -> {
                    record.setNome(produto.getNome());
                    record.setPreco(produto.getPreco());
                    record.setQuantidade(produto.getQuantidade());
                    Produto updated = (Produto) produtoReposiitory.save((Produto) record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

        //Removendo um produto pelo ID (DELETE /produtos/{id})
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return produtoReposiitory.findById(id)
                .map(record -> {
                    produtoReposiitory.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}