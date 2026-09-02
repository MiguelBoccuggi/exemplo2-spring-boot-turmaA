package br.com.uam.exemplo2.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.uam.exemplo2.model.Produto;
import repository.ProdutoRepository;

public class ProdutoController {
    private final ProdutoRepository repository;
    public ProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        Produto produtoSalvo = repository.save(produto);

        URI location = ServletUriComponentsBuilder
                               .fromCurrentRequest()
                               .path("/{id}")
                               .buildAndExpand(produtoSalvo.getId())
                               .toUri();
        return ResponseEntity.created(location).body(produtoSalvo);
    }
}
