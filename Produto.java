package ProjetoMarcao0310;
public class Produto {
    private int id;
    private String descricao;
    private double preco;
    private String validade;
    private String categoria;
    private String fornecedor;
    private int estoque;
    private String codigoBarras;
    private String observacoes;

    public Produto(int id, String descricao, double preco, String validade, 
                   String categoria, String fornecedor, int estoque, 
                   String codigoBarras, String observacoes) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.validade = validade;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.estoque = estoque;
        this.codigoBarras = codigoBarras;
        this.observacoes = observacoes;
    }

    // Getters
    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public String getValidade() { return validade; }
    public String getCategoria() { return categoria; }
    public String getFornecedor() { return fornecedor; }
    public int getEstoque() { return estoque; }
    public String getCodigoBarras() { return codigoBarras; }
    public String getObservacoes() { return observacoes; }

    // Setters
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setValidade(String validade) { this.validade = validade; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}