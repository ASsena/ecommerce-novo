package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.CategoriaDTO;
import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.repositories.CategoriaRepository;
import com.revisao.ecommerce.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private CategoriaRepository repository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> findAll() {
        var listaProdutos = produtoRepository.findAll();
        return listaProdutos.stream().map(ProdutoDTO::new).toList();
    }

    public Page<ProdutoDTO> findpagina(Pageable pagina){
        Page<Produto> busca = produtoRepository.findAll(pagina);
        return busca.map(ProdutoDTO::new);
    }

    public ProdutoDTO insert(ProdutoDTO dto){
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());

        for(CategoriaDTO cDto : dto.getCategorias()){
            Categoria categoria = repository.getReferenceById(cDto.getId());
            entity.getCategorias().add(categoria);
        }

        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);

    }
}
