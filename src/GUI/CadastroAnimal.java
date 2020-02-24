/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ConectaBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Maxwel
 */
public class CadastroAnimal extends javax.swing.JInternalFrame {

    Connection conecta;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CadastroAnimal() throws ClassNotFoundException {
        initComponents();
        this.setLocation(300, 10);
        conecta = ConectaBd.conectabd();
         listarAnimal();
    }

    public void CadastraAnimal() {
        String sql = "insert into animal(cpfcliente,nome,familia,raca,especie) values(?,?,?,?,?)";

        try {
           
            PreparedStatement pst = conecta.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, tfCPF.getText());
            pst.setString(2, tfNome.getText());
            pst.setString(3, tfFamilia.getText());
            pst.setString(4, tfRaca.getText());
            pst.setString(5, tfEspecie.getText());

            pst.execute();
                 
            JOptionPane.showMessageDialog(null, "Animal Cadastrato com sucesso!", "Animal Cadastrato com sucesso!", JOptionPane.INFORMATION_MESSAGE);
             listarAnimal();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "CPF não Cadastrado!");
        }

    }

    public void listarAnimal() {

        String sql = "select * from animal order by nome";
        try {
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbAnimal.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void pesquisaAnimal() {

        String sql = "select * from animal where nome LIKE ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfPesquisa.getText() + "%");
            rs = pst.executeQuery();
            tbAnimal.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void mostrarItens() {

        int seleciona = tbAnimal.getSelectedRow();

        tfCPF.setText(tbAnimal.getModel().getValueAt(seleciona, 0).toString());
        tfNome.setText(tbAnimal.getModel().getValueAt(seleciona, 1).toString());
        tfFamilia.setText(tbAnimal.getModel().getValueAt(seleciona, 2).toString());
        tfRaca.setText(tbAnimal.getModel().getValueAt(seleciona, 3).toString());
        tfEspecie.setText(tbAnimal.getModel().getValueAt(seleciona, 4).toString());

    }

    public void editarAnimal() {

        String sql = "update animal set nome = ?, familia = ?, raca = ?, especie = ? where cpfcliente = ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(5, tfCPF.getText());
            pst.setString(1, tfNome.getText());
            pst.setString(2, tfFamilia.getText());
            pst.setString(3, tfRaca.getText());
            pst.setString(4, tfEspecie.getText());
            

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Animal Atualizado com sucesso!", "Animal Atualizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarAnimal();

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void deletarAnimal() {

        String sql = "delete from animal where cpfcliente = ? and nome = ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfCPF.getText());
            pst.setString(2, tfNome.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Animal Deletado com sucesso!", "Animal Deletado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarAnimal();
        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        btDeletar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btCadastrar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfFamilia = new javax.swing.JTextField();
        tfEspecie = new javax.swing.JTextField();
        tfRaca = new javax.swing.JTextField();
        tfCPF = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAnimal = new javax.swing.JTable();
        LabelPesquisa = new javax.swing.JLabel();
        tfPesquisa = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("CadastroAnimal");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Excluir.jpg"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDeletar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 44, 13, 0);
        jPanel1.add(btDeletar, gridBagConstraints);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Novo.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 26, 13, 0);
        jPanel1.add(btNovo, gridBagConstraints);

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Sair.png"))); // NOI18N
        btSair.setText("Sair");
        btSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 30, 13, 12);
        jPanel1.add(btSair, gridBagConstraints);

        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Salvar.png"))); // NOI18N
        btCadastrar.setText("Salvar");
        btCadastrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCadastrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 27, 13, 0);
        jPanel1.add(btCadastrar, gridBagConstraints);

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Alterar.png"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 39, 13, 0);
        jPanel1.add(btAlterar, gridBagConstraints);

        jLabel1.setText("CpfDono:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Família:");

        jLabel4.setText("Espécie:");

        jLabel5.setText("Raça:");

        try {
            tfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        tbAnimal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbAnimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnimalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAnimal);

        LabelPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ico_buscar.png"))); // NOI18N
        LabelPesquisa.setText("Pesquisa:");

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfRaca)
                    .addComponent(tfEspecie)
                    .addComponent(tfFamilia)
                    .addComponent(tfNome)
                    .addComponent(tfCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LabelPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisa)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfCPF, tfEspecie, tfFamilia, tfNome, tfRaca});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfFamilia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfRaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPesquisa)
                    .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfCPF, tfEspecie, tfFamilia, tfNome, tfRaca});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        deletarAnimal();
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        tfCPF.setText("");
        tfNome.setText("");
        tfEspecie.setText("");
        tfFamilia.setText("");
        tfRaca.setText("");
        tfPesquisa.setText("");
        tfCPF.requestFocusInWindow();
    }//GEN-LAST:event_btNovoActionPerformed


    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        CadastraAnimal();
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        editarAnimal();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased
        pesquisaAnimal();
    }//GEN-LAST:event_tfPesquisaKeyReleased

    private void tbAnimalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnimalMouseClicked
        mostrarItens();
    }//GEN-LAST:event_tbAnimalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelPesquisa;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbAnimal;
    private javax.swing.JFormattedTextField tfCPF;
    private javax.swing.JTextField tfEspecie;
    private javax.swing.JTextField tfFamilia;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPesquisa;
    private javax.swing.JTextField tfRaca;
    // End of variables declaration//GEN-END:variables
}
