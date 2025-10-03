package ProjetoMarcao0310;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EdicaoProduto extends JDialog {
    private CadastroProdutos telaPrincipal;
    private int idProduto;
    
    private JTextField campoDescricao;
    private JTextField campoPreco;
    private JTextField campoValidade;
    private JTextField campoCategoria;
    private JTextField campoFornecedor;
    private JTextField campoEstoque;
    private JTextField campoCodigoBarras;
    private JTextArea campoObservacoes;
    
    private JButton btnGravar;
    private JButton btnCancelar;

    public EdicaoProduto(CadastroProdutos parent, int id) {
        super(parent, id == 0 ? "Novo Produto" : "Editar Produto", true);
        this.telaPrincipal = parent;
        this.idProduto = id;
        
        inicializarComponentes();
        
        if (id > 0) {
            carregarDadosProduto();
        }
        
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setSize(500, 500);
        
        // Painel de campos
        JPanel painelCampos = new JPanel(new GridLayout(9, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        campoDescricao = new JTextField();
        campoPreco = new JTextField();
        campoValidade = new JTextField();
        campoCategoria = new JTextField();
        campoFornecedor = new JTextField();
        campoEstoque = new JTextField();
        campoCodigoBarras = new JTextField();
        campoObservacoes = new JTextArea(3, 20);
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);

        painelCampos.add(new JLabel("Descrição: *"));
        painelCampos.add(campoDescricao);
        
        painelCampos.add(new JLabel("Preço (R$): *"));
        painelCampos.add(campoPreco);
        
        painelCampos.add(new JLabel("Validade (dd/mm/aaaa):"));
        painelCampos.add(campoValidade);
        
        painelCampos.add(new JLabel("Categoria:"));
        painelCampos.add(campoCategoria);
        
        painelCampos.add(new JLabel("Fornecedor:"));
        painelCampos.add(campoFornecedor);
        
        painelCampos.add(new JLabel("Estoque: *"));
        painelCampos.add(campoEstoque);
        
        painelCampos.add(new JLabel("Código de Barras:"));
        painelCampos.add(campoCodigoBarras);
        
        painelCampos.add(new JLabel("Observações:"));
        painelCampos.add(new JScrollPane(campoObservacoes));
        
        JLabel lblObrigatorio = new JLabel("* Campos obrigatórios");
        lblObrigatorio.setFont(new Font("Arial", Font.ITALIC, 10));
        painelCampos.add(lblObrigatorio);

        add(painelCampos, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGravar = new JButton("Gravar");
        btnCancelar = new JButton("Cancelar");
        
        btnGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        painelBotoes.add(btnGravar);
        painelBotoes.add(btnCancelar);
        
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void carregarDadosProduto() {
        for (Produto p : CadastroProdutos.getListaProdutos()) {
            if (p.getId() == idProduto) {
                campoDescricao.setText(p.getDescricao());
                campoPreco.setText(String.valueOf(p.getPreco()));
                campoValidade.setText(p.getValidade());
                campoCategoria.setText(p.getCategoria());
                campoFornecedor.setText(p.getFornecedor());
                campoEstoque.setText(String.valueOf(p.getEstoque()));
                campoCodigoBarras.setText(p.getCodigoBarras());
                campoObservacoes.setText(p.getObservacoes());
                break;
            }
        }
    }

    private void salvarProduto() {
        // Validação dos campos obrigatórios
        if (campoDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "O campo Descrição é obrigatório!",
                "Validação", JOptionPane.WARNING_MESSAGE);
            campoDescricao.requestFocus();
            return;
        }
        
        if (campoPreco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "O campo Preço é obrigatório!",
                "Validação", JOptionPane.WARNING_MESSAGE);
            campoPreco.requestFocus();
            return;
        }
        
        if (campoEstoque.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "O campo Estoque é obrigatório!",
                "Validação", JOptionPane.WARNING_MESSAGE);
            campoEstoque.requestFocus();
            return;
        }

        try {
            double preco = Double.parseDouble(campoPreco.getText().trim());
            int estoque = Integer.parseInt(campoEstoque.getText().trim());
            
            if (idProduto == 0) {
                // Novo produto
                Produto novoProduto = new Produto(
                    CadastroProdutos.getProximoId(),
                    campoDescricao.getText().trim(),
                    preco,
                    campoValidade.getText().trim(),
                    campoCategoria.getText().trim(),
                    campoFornecedor.getText().trim(),
                    estoque,
                    campoCodigoBarras.getText().trim(),
                    campoObservacoes.getText().trim()
                );
                CadastroProdutos.getListaProdutos().add(novoProduto);
                CadastroProdutos.incrementarProximoId();
            } else {
                // Editar produto existente
                for (Produto p : CadastroProdutos.getListaProdutos()) {
                    if (p.getId() == idProduto) {
                        p.setDescricao(campoDescricao.getText().trim());
                        p.setPreco(preco);
                        p.setValidade(campoValidade.getText().trim());
                        p.setCategoria(campoCategoria.getText().trim());
                        p.setFornecedor(campoFornecedor.getText().trim());
                        p.setEstoque(estoque);
                        p.setCodigoBarras(campoCodigoBarras.getText().trim());
                        p.setObservacoes(campoObservacoes.getText().trim());
                        break;
                    }
                }
            }
            
            telaPrincipal.carregarProdutos();
            
            JOptionPane.showMessageDialog(this, 
                "Produto salvo com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Preço e Estoque devem ser números válidos!\n" +
                "Use ponto (.) para decimais no preço.",
                "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }
}