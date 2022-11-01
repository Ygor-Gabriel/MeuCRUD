package br.com.crud.appcrud.Repository;

import br.com.crud.appcrud.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoReposiitory extends JpaRepository<Produto, Long> {
}
