package br.com.crud.appcrud.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Produtos")
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long Id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Preco")
    private String  preco;
    @Column(name = "Quantidade")
    private String quantidade;

}
