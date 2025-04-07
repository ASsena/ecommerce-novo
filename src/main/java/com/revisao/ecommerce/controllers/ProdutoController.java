package com.revisao.ecommerce.controllers;

import java.util.List;

import com.revisao.ecommerce.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.services.ProdutoService;

@RestController
@RequestMapping
public class ProdutoController {


	private ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@GetMapping
	public List<ProdutoDTO> findAll(){
		return service.findAll();
	}

	@GetMapping("/pagina")
	public Page<ProdutoDTO> findpagina(Pageable pagina){
		return service.findpagina(pagina);
	}

	@PostMapping("/produto")
	public ResponseEntity<ProdutoDTO> insert( @RequestBody  ProdutoDTO produtoDTO){
		produtoDTO = service.insert(produtoDTO);
		System.out.println(produtoDTO.getPreco());
		return ResponseEntity.ok(produtoDTO);
	}
}
