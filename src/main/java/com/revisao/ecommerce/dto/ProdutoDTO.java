package com.revisao.ecommerce.dto;

import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.entities.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private String imgUrl;
    private Long id;

    public ProdutoDTO(){}

    private List<CategoriaDTO> categorias = new ArrayList<>();


    public ProdutoDTO(Long id, String nome, String descricao, Double preco,String imgUrl){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = 0;
        this.imgUrl = imgUrl;
    }

    public ProdutoDTO(Produto produto){
        nome = produto.getNome();
        descricao = produto.getDescricao();
        preco = produto.getPreco();
        imgUrl = produto.getImgUrl();
        id = produto.getId();
        for(Categoria cat : produto.getCategorias()){
            categorias.add(new CategoriaDTO());
        }

    }
}

