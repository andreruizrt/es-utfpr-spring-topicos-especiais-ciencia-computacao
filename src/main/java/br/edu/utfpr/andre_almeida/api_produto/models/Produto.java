package br.edu.utfpr.andre_almeida.api_produto.models;

public class Produto {
    private Long id;
    private String description;
    private Double price;
    private String category;
    private Integer quantity;

    public Produto(Long id, String description, Double price, String category, Integer quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
