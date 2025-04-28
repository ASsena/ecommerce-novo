package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.StatusDoPedido;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.PedidoRepository;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PedidoDTO inserir(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        copyDtoToEntity(dto, pedido);
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);
            copyDtoToEntity(dto, pedido);
            pedido = pedidoRepository.save(pedido);
            return new PedidoDTO(pedido);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }
    }

    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    private void copyDtoToEntity(PedidoDTO dto, Pedido entity) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.getClienteId());
        entity.setCliente(usuario);
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getMomento() != null) {
            entity.setMomento(dto.getMomento());
        }
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }
    @Transactional
    public PedidoDTO buscarId(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return new PedidoDTO(pedido.get());
        }
        return null;
    }
}
