package com.revisao.ecommerce.controllers;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<ProdutoDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/pagina")
	public ResponseEntity<Page<ProdutoDTO>> findPagina(Pageable pageable) {
		Page<ProdutoDTO> page = service.findpagina(pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO produtoDTO) {
		produtoDTO = service.insert(produtoDTO);
		URI uri = URI.create("/produtos/" + produtoDTO.getId()); // Para retornar o Location do novo recurso
		return ResponseEntity.created(uri).body(produtoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
		ProdutoDTO updatedProduto = service.update(id, produtoDTO);
		return ResponseEntity.ok(updatedProduto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); // Código 204 No Content
	}
}
