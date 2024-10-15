package br.edu.utfpr.andre_almeida.api_produto.controllers;

import br.edu.utfpr.andre_almeida.api_produto.models.Produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    private List<Produto> produtos;

    public ProdutoController() {
        this.produtos = new ArrayList<>();
        this.produtos.add(new Produto(1L, "IPHONE 15", 5000.0, "Smartphone", 20));
        this.produtos.add(new Produto(2L, "Airpods Pro", 2500.0, "Acessórios", 3));
        this.produtos.add(new Produto(3L, "Notebook i7", 4800.0, "Computadores", 1000));
        this.produtos.add(new Produto(4L, "Cadeira Gamer", 1500.0, "Móveis", 5));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {

        return ResponseEntity.ok(this.produtos);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> getOne(@PathVariable(name = "id") Long idProduto) {

        Produto produtoEncontrado = this.produtos.stream()
                .filter(produto -> produto.getId().equals(idProduto))
                .findFirst()
                .orElse(null);

        if (produtoEncontrado == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } else {

            return ResponseEntity.status(HttpStatus.OK).body(produtoEncontrado);

        }
    }

    @PostMapping
    public ResponseEntity<String> addOne(@RequestBody Produto produto) {

        if (produto.getDescription() == null || produto.getPrice() < 0) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Descrição ou Preço inválidos");

        } else {

            this.produtos.add(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso");
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") Long idProduto, @RequestBody Produto produto) {

        for (Produto p : this.produtos) {

            if (p.getId().equals(idProduto)) {
                p.setDescription(produto.getDescription());
                p.setQuantity(produto.getQuantity());
                p.setPrice(produto.getPrice());
                p.setCategory(produto.getCategory());
                return ResponseEntity.ok("Produto atualizado com sucesso.");
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long idProduto) {

        Produto produtoRemover = this.produtos.stream()
                .filter(p -> p.getId().equals(idProduto))
                .findFirst()
                .orElse(null);

        if (produtoRemover != null) {
            this.produtos.remove(produtoRemover);
            return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
    }

    @PostMapping(path = "/{id}/venda")
    public ResponseEntity<String> realizarVenda(@PathVariable(name = "id") Long idProduto) {

        Produto produtoEncontrado = this.produtos.stream()
                .filter(produto -> produto.getId().equals(idProduto))
                .findFirst()
                .orElse(null);

        if (produtoEncontrado == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");

        }

        if (produtoEncontrado.getQuantity() > 0) {

            produtoEncontrado.setQuantity(produtoEncontrado.getQuantity() - 1);

            return ResponseEntity.status(HttpStatus.OK).body("Produto vendido com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sem produto em estoque");
    }
}