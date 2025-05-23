package com.revisao.ecommerce.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revisao.ecommerce.dto.ProdutoDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter
    private String nome;

	@Column(columnDefinition = "TEXT")
	private String descricao;
	private Double preco;
	@Column(columnDefinition = "TEXT")
	private String imgUrl;

	@ManyToMany
	@JoinTable(name = "tb_produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();

	@OneToMany(mappedBy = "id.produto", cascade = CascadeType.ALL)
	private Set<ItemDoPedido> items = new HashSet<>();

	public Produto() {

	}

	public Produto(Long id, String nome, String descricao, Double preco, String imgUrl) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imgUrl = imgUrl;
	}

	public List<Pedido> getPedido() {
		return items.stream().map(ItemDoPedido::getPedido).toList();
	}

}
