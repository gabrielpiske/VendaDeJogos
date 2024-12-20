/*
    Tela principal do sistema onde praticamento toda a lógica e redirecionamento de telas é feito.
    Nesta tela pode-se salvar algum jogo que o usuario queira, editar o mesmo e também excluir.
    Além disso é através dessa tela que o usuário adiciona algum jogo ao carrinho e consegue fazer o acesso do mesmo.
 */

package view;

import dao.CarrinhoDao;
import dao.JogoDao;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.Jogo;
import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Carrinho;
import view.TelaCarrinho;

/**
 *
 * @author gabriel_piske
 */
public class TelaSistema extends javax.swing.JFrame {

    private CarrinhoDao carrinhoDao = new CarrinhoDao(); // DAO do carrinho
    private TelaCarrinho telaCarrinho;

    public static int idUsuario;
    private boolean isEditMode = false;
    private int jogoEditandoId = -1;

    /**
     * Creates new form TelaSistema
     */
    public TelaSistema() {
        initComponents();
        //setSize(600, 400);
        setTitle("Tela Principal - Venda de Jogos");
        txtImagem.setEnabled(false);
        imageCart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jblVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        configurarTabela();
        configurarInputClassificacao();
        carregarJogos();
        aplicarMascaraData(txtDataLancamento);
    }

    public TelaSistema(TelaCarrinho telaCarrinho) {
        this.telaCarrinho = telaCarrinho;
        initComponents();
    }

    private void carregarJogos() {
        DefaultTableModel model = (DefaultTableModel) jtblJogos.getModel();
        model.setRowCount(0);
        jtblJogos.setDefaultEditor(Object.class, null);

        JogoDao jogoDao = new JogoDao();
        jogoDao.listJogos(model);
    }

    private void configurarTabela() {
        jtblJogos.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();

                if (value instanceof ImageIcon) {
                    ImageIcon icon = (ImageIcon) value;

                    // Redimensiona a imagem
                    int width = 100;
                    int height = 100;

                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(newImg));

                    table.setRowHeight(row, height);
                }

                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });
    }

    private void configurarInputClassificacao() {
        txtClassificacao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c >= '1' && c <= '5') {
                    txtClassificacao.setText(String.valueOf(c));
                    e.consume();
                } else {
                    e.consume();
                }
            }
        });
    }

    public static void aplicarMascaraData(JTextField txtDataLancamento) {
        txtDataLancamento.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            String currentText = txtDataLancamento.getText();

            if (currentText.length() >= 10) {
                e.consume();
                return;
            }

            // Verificar se o caractere digitado é um número
            if (Character.isDigit(c)) {
                if (currentText.length() == 2 || currentText.length() == 5) {
                    txtDataLancamento.setText(currentText + "/" + c);
                } else {
                    txtDataLancamento.setText(currentText + c);
                }
                e.consume();
            } else {
                e.consume();
            }
        }
    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblJogos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaDesc = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClassificacao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDataLancamento = new javax.swing.JTextField();
        txtImagem = new javax.swing.JTextField();
        btnArquivo = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        imageCart = new javax.swing.JLabel();
        btnAddCarrinho = new javax.swing.JButton();
        jblVoltar = new javax.swing.JLabel();
        jbtnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");

        jtblJogos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Descriçao", "Preço", "Lançamento", "Classificação", "Imagem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblJogos);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("Venda de Jogos");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Descrição");

        jtaDesc.setColumns(20);
        jtaDesc.setRows(5);
        jScrollPane2.setViewportView(jtaDesc);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Preço");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Classificação Indicativa (1 a 5)");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Data Lançamento");

        btnArquivo.setText("Arquivo");
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });

        btnSave.setText("Salvar Jogo");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        imageCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Image/cart.png"))); // NOI18N
        imageCart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imageCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageCartMouseClicked(evt);
            }
        });

        btnAddCarrinho.setText("Adicionar ao Carrinho");
        btnAddCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarrinhoActionPerformed(evt);
            }
        });

        jblVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Image/setaLogin.png"))); // NOI18N
        jblVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblVoltarMouseClicked(evt);
            }
        });

        jbtnEdit.setText("Editar");
        jbtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                    .addComponent(jbtnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                    .addComponent(txtImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtClassificacao))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jblVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(158, 158, 158)
                        .addComponent(imageCart, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(imageCart, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jblVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCarrinho))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Procurar Imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem", "jpg", "png");

        fileChooser.setFileFilter(filter);
        int retorno = fileChooser.showOpenDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            txtImagem.setText(file.getPath());
        }
    }//GEN-LAST:event_btnArquivoActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txtNome.getText().isBlank() || jtaDesc.getText().isBlank() || txtPreco.getText().isBlank()
                || txtDataLancamento.getText().isBlank() || txtClassificacao.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Preencha todas as informações");
            return;
        } else {
            Jogo jogo = new Jogo();
            jogo.setNome(txtNome.getText());
            jogo.setDescricao(jtaDesc.getText());
            jogo.setPreco(Double.parseDouble(txtPreco.getText()));
            jogo.setDataLancamento(txtDataLancamento.getText());
            jogo.setClassificacaoIndicativa(txtClassificacao.getText());

            try {
                File file = new File(txtImagem.getText());
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = baos.toByteArray();
                jogo.setImagem(imageBytes);

                JogoDao jogoDao = new JogoDao();
                if (isEditMode) {
                    jogo.setIdJogo(jogoEditandoId);
                    jogoDao.updateJogo(jogo);
                    JOptionPane.showMessageDialog(this, "Jogo atualizado com sucesso!");
                } else {
                    jogoDao.cadJogo(jogo);
                    JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso!");
                }
                carregarJogos();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao processar a imagem: " + e.getMessage());
            }
        }
        limparCampos();
        isEditMode = false;
        jogoEditandoId = -1;

        btnSave.setText("Salvar Jogo");
        btnAddCarrinho.setText("Adicionar ao Carrinho");
        btnAddCarrinho.setEnabled(true);
        btnArquivo.setEnabled(true);
        jbtnEdit.setText("Editar");
        jbtnEdit.setEnabled(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void imageCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageCartMouseClicked
        TelaCarrinho telaCarrinho = new TelaCarrinho();
        telaCarrinho.setVisible(true);
        dispose();
    }//GEN-LAST:event_imageCartMouseClicked

    private void btnAddCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarrinhoActionPerformed
        int selectedRow = jtblJogos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo na tabela para continuar!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jtblJogos.getModel();
        int jogoId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

        if (btnAddCarrinho.getText().equals("Excluir")) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o jogo?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    JogoDao jogoDao = new JogoDao();
                    jogoDao.deleteById(jogoId);
                    JOptionPane.showMessageDialog(this, "Jogo excluído com sucesso!");
                    carregarJogos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o jogo: " + ex.getMessage());
                }
            }
            btnAddCarrinho.setText("Adicionar ao Carrinho");
        } else {
            List<Integer> listaJogos = new ArrayList<>();
            listaJogos.add(jogoId);

            List<Double> valorTotal = new ArrayList<>();
            valorTotal.add(Double.parseDouble(model.getValueAt(selectedRow, 3).toString()));

            CarrinhoDao carrinhoDao = new CarrinhoDao();
            Carrinho carrinho = new Carrinho(idUsuario, listaJogos, valorTotal);
            carrinhoDao.addJogoAoCarrinho(carrinho);

            JOptionPane.showMessageDialog(this, "Jogo adicionado ao carrinho!");
        }
    }//GEN-LAST:event_btnAddCarrinhoActionPerformed

    private void jblVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblVoltarMouseClicked
        idUsuario = 0;
        TelaLogin login = new TelaLogin();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jblVoltarMouseClicked

    private void jbtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditActionPerformed
        if (isEditMode) {
            limparCampos();
            isEditMode = false;
            jogoEditandoId = -1;
            btnSave.setText("Salvar Jogo");
            jbtnEdit.setText("Editar");
            btnAddCarrinho.setText("Adicionar ao Carrinho");
            btnAddCarrinho.setEnabled(true);
            btnArquivo.setEnabled(true);
        } else {
            int selectedRow = jtblJogos.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um jogo para editar!");
                return;
            }

            DefaultTableModel model = (DefaultTableModel) jtblJogos.getModel();
            jogoEditandoId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

            // Puxa os dados para os campos
            txtNome.setText(model.getValueAt(selectedRow, 1).toString());
            jtaDesc.setText(model.getValueAt(selectedRow, 2).toString());
            txtPreco.setText(model.getValueAt(selectedRow, 3).toString());
            txtDataLancamento.setText(model.getValueAt(selectedRow, 4).toString());
            txtClassificacao.setText(model.getValueAt(selectedRow, 5).toString());
            txtImagem.setText("");

            // Modo de edição
            isEditMode = true;
            btnSave.setText("Atualizar Jogo");
            jbtnEdit.setText("Cancelar");
            btnAddCarrinho.setText("Excluir");
            btnAddCarrinho.setEnabled(true);
            btnArquivo.setEnabled(true);
        }
    }//GEN-LAST:event_jbtnEditActionPerformed

    private void limparCampos() {
        txtClassificacao.setText("");
        txtDataLancamento.setText("");
        txtImagem.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        jtaDesc.setText("");
    }

    private byte[] imageToByteArray(Image image) {
        try {
            // Criação de um BufferedImage a partir da Image
            BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB
            );

            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();

            // Converte BufferedImage para byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCarrinho;
    private javax.swing.JButton btnArquivo;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel imageCart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jblVoltar;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JTextArea jtaDesc;
    private javax.swing.JTable jtblJogos;
    private javax.swing.JTextField txtClassificacao;
    private javax.swing.JTextField txtDataLancamento;
    private javax.swing.JTextField txtImagem;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
