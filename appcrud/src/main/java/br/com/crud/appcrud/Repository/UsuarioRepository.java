package br.com.crud.appcrud.Repository;

import br.com.crud.appcrud.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
