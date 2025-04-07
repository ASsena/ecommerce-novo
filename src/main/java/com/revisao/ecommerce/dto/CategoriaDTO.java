package com.revisao.ecommerce.dto;

import com.revisao.ecommerce.entities.Categoria;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria entity) {
        id = entity.getId();
        nome = entity.getNome();
    }

    public CategoriaDTO() {
    }
}
