package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.CategoriaDTO;
import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.repositories.CategoriaRepository;
import com.revisao.ecommerce.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }
    @Transactional
    public List<ProdutoDTO> findAll() {
        var listaProdutos = produtoRepository.findAll();
        return listaProdutos.stream().map(ProdutoDTO::new).toList();
    }
    @Transactional
    public Page<ProdutoDTO> findpagina(Pageable pagina) {
        Page<Produto> busca = produtoRepository.findAll(pagina);
        return busca.map(ProdutoDTO::new);
    }
    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copyDtoToEntity(dto, entity);
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }
    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        try {
            Produto entity = produtoRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = produtoRepository.save(entity);
            return new ProdutoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
    }
    @Transactional
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategorias().clear();
        for (CategoriaDTO cDto : dto.getCategorias()) {
            Categoria categoria = categoriaRepository.getReferenceById(cDto.getId());
            entity.getCategorias().add(categoria);
        }
    }
}
