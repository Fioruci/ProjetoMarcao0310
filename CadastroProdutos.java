package ProjetoMarcao0310;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import java.util.ArrayList;

public class CadastroProdutos extends JFrame {
    private JLabel lblBusca;
    private JTextField txtBusca;
    private JButton btnBuscar, btnNovo, btnEditar, btnExcluir;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private static ArrayList<Produto> listaProdutos = new ArrayList<>();
    private static int proximoId = 1;
    
    // Painel de detalhes
    private JPanel painelDetalhes;
    private JLabel lblDetalhesId, lblDetalhesDescricao, lblDetalhesPreco;
    private JLabel lblDetalhesValidade, lblDetalhesCategoria, lblDetalhesFornecedor;
    private JLabel lblDetalhesEstoque, lblDetalhesCodigoBarras;
    private JTextArea txtDetalhesObservacoes;

    public CadastroProdutos() {
        // Configuração da janela
        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de busca (NORTH)
        JPanel pBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        lblBusca = new JLabel("Buscar:");
        txtBusca = new JTextField(30);
        btnBuscar = new JButton("Buscar");
        
        pBusca.add(lblBusca);
        pBusca.add(txtBusca);
        pBusca.add(btnBuscar);
        add(pBusca, BorderLayout.NORTH);

        // Painel central com tabela e detalhes
        JPanel painelCentral = new JPanel(new BorderLayout(10, 0));
        
        // Tabela de produtos (CENTER-LEFT)
        modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Descrição", "Preço", "Validade", "Estoque"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setPreferredSize(new Dimension(600, 0));
        painelCentral.add(scroll, BorderLayout.CENTER);

        // Painel de detalhes (CENTER-RIGHT)
        criarPainelDetalhes();
        painelCentral.add(painelDetalhes, BorderLayout.EAST);
        
        add(painelCentral, BorderLayout.CENTER);

        // Painel de botões (SOUTH)
        JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnNovo = new JButton("Novo");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        
        pBotoes.add(btnNovo);
        pBotoes.add(btnEditar);
        pBotoes.add(btnExcluir);
        add(pBotoes, BorderLayout.SOUTH);

        // Listener para seleção na tabela - ATUALIZA DETALHES AUTOMATICAMENTE
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    atualizarDetalhes();
                }
            }
        });

        // Adiciona listeners dos botões
        Ouvinte ov = new Ouvinte();
        btnNovo.addActionListener(ov);
        btnEditar.addActionListener(ov);
        btnExcluir.addActionListener(ov);
        btnBuscar.addActionListener(ov);

        // Carrega produtos
        carregarProdutos();
        setVisible(true);
    }

    private void criarPainelDetalhes() {
        painelDetalhes = new JPanel();
        painelDetalhes.setLayout(new BorderLayout());
        painelDetalhes.setPreferredSize(new Dimension(350, 0));
        painelDetalhes.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 10, 0, 10),
            BorderFactory.createTitledBorder("Detalhes do Produto")
        ));

        JPanel painelInfo = new JPanel(new GridLayout(9, 1, 5, 10));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Criação dos labels de detalhes
        lblDetalhesId = criarLabelDetalhe("ID: ");
        lblDetalhesDescricao = criarLabelDetalhe("Descrição: ");
        lblDetalhesPreco = criarLabelDetalhe("Preço: ");
        lblDetalhesValidade = criarLabelDetalhe("Validade: ");
        lblDetalhesCategoria = criarLabelDetalhe("Categoria: ");
        lblDetalhesFornecedor = criarLabelDetalhe("Fornecedor: ");
        lblDetalhesEstoque = criarLabelDetalhe("Estoque: ");
        lblDetalhesCodigoBarras = criarLabelDetalhe("Código de Barras: ");

        painelInfo.add(criarPainelCampo("ID:", lblDetalhesId));
        painelInfo.add(criarPainelCampo("Descrição:", lblDetalhesDescricao));
        painelInfo.add(criarPainelCampo("Preço:", lblDetalhesPreco));
        painelInfo.add(criarPainelCampo("Validade:", lblDetalhesValidade));
        painelInfo.add(criarPainelCampo("Categoria:", lblDetalhesCategoria));
        painelInfo.add(criarPainelCampo("Fornecedor:", lblDetalhesFornecedor));
        painelInfo.add(criarPainelCampo("Estoque:", lblDetalhesEstoque));
        painelInfo.add(criarPainelCampo("Cód. Barras:", lblDetalhesCodigoBarras));

        painelDetalhes.add(painelInfo, BorderLayout.NORTH);

        // Observações
        JPanel painelObs = new JPanel(new BorderLayout(5, 5));
        painelObs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblObs = new JLabel("Observações:");
        lblObs.setFont(new Font("Arial", Font.BOLD, 11));
        txtDetalhesObservacoes = new JTextArea();
        txtDetalhesObservacoes.setEditable(false);
        txtDetalhesObservacoes.setLineWrap(true);
        txtDetalhesObservacoes.setWrapStyleWord(true);
        txtDetalhesObservacoes.setBackground(new Color(245, 245, 245));
        JScrollPane scrollObs = new JScrollPane(txtDetalhesObservacoes);
        painelObs.add(lblObs, BorderLayout.NORTH);
        painelObs.add(scrollObs, BorderLayout.CENTER);

        painelDetalhes.add(painelObs, BorderLayout.CENTER);

        limparDetalhes();
    }

    private JLabel criarLabelDetalhe(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        return lbl;
    }

    private JPanel criarPainelCampo(String rotulo, JLabel valor) {
        JPanel painel = new JPanel(new BorderLayout(5, 0));
        JLabel lblRotulo = new JLabel(rotulo);
        lblRotulo.setFont(new Font("Arial", Font.BOLD, 11));
        lblRotulo.setPreferredSize(new Dimension(100, 20));
        painel.add(lblRotulo, BorderLayout.WEST);
        painel.add(valor, BorderLayout.CENTER);
        return painel;
    }

    private void atualizarDetalhes() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            limparDetalhes();
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);
        Produto produto = buscarProdutoPorId(id);

        if (produto != null) {
            lblDetalhesId.setText(String.valueOf(produto.getId()));
            lblDetalhesDescricao.setText(produto.getDescricao());
            lblDetalhesPreco.setText(String.format("R$ %.2f", produto.getPreco()));
            lblDetalhesValidade.setText(produto.getValidade());
            lblDetalhesCategoria.setText(produto.getCategoria());
            lblDetalhesFornecedor.setText(produto.getFornecedor());
            lblDetalhesEstoque.setText(String.valueOf(produto.getEstoque()));
            lblDetalhesCodigoBarras.setText(produto.getCodigoBarras());
            txtDetalhesObservacoes.setText(produto.getObservacoes());
        }
    }

    private void limparDetalhes() {
        lblDetalhesId.setText("-");
        lblDetalhesDescricao.setText("-");
        lblDetalhesPreco.setText("-");
        lblDetalhesValidade.setText("-");
        lblDetalhesCategoria.setText("-");
        lblDetalhesFornecedor.setText("-");
        lblDetalhesEstoque.setText("-");
        lblDetalhesCodigoBarras.setText("-");
        txtDetalhesObservacoes.setText("");
    }

    public void carregarProdutos() {
        modeloTabela.setRowCount(0);
        for (Produto p : listaProdutos) {
            modeloTabela.addRow(new Object[]{
                p.getId(),
                p.getDescricao(),
                String.format("R$ %.2f", p.getPreco()),
                p.getValidade(),
                p.getEstoque()
            });
        }
    }

    private Produto buscarProdutoPorId(int id) {
        for (Produto p : listaProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private class Ouvinte implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();

            if (src == btnNovo) {
                new EdicaoProduto(CadastroProdutos.this, 0);
            } else if (src == btnEditar) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    int id = (int) modeloTabela.getValueAt(linha, 0);
                    new EdicaoProduto(CadastroProdutos.this, id);
                } else {
                    JOptionPane.showMessageDialog(CadastroProdutos.this, 
                        "Selecione um produto para editar.");
                }
            } else if (src == btnExcluir) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    int confirmacao = JOptionPane.showConfirmDialog(
                        CadastroProdutos.this,
                        "Deseja realmente excluir este produto?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        int id = (int) modeloTabela.getValueAt(linha, 0);
                        excluirProduto(id);
                    }
                } else {
                    JOptionPane.showMessageDialog(CadastroProdutos.this, 
                        "Selecione um produto para excluir.");
                }
            } else if (src == btnBuscar) {
                buscarProduto();
            }
        }
    }

    private void buscarProduto() {
        String termo = txtBusca.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            carregarProdutos();
            return;
        }

        modeloTabela.setRowCount(0);
        boolean encontrou = false;
        
        for (Produto p : listaProdutos) {
            if (p.getDescricao().toLowerCase().contains(termo) || 
                p.getCodigoBarras().toLowerCase().contains(termo)) {
                modeloTabela.addRow(new Object[]{
                    p.getId(),
                    p.getDescricao(),
                    String.format("R$ %.2f", p.getPreco()),
                    p.getValidade(),
                    p.getEstoque()
                });
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.");
        }
    }

    private void excluirProduto(int id) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            listaProdutos.remove(produto);
            carregarProdutos();
            limparDetalhes();
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
        }
    }

    public static ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void incrementarProximoId() {
        proximoId++;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CadastroProdutos();
        });
    }
}