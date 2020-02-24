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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Maxwel
 */
public class ProcedimentoConsulta extends javax.swing.JInternalFrame {

    Connection conecta;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ProcedimentoConsulta() throws ClassNotFoundException {
        initComponents();
        this.setLocation(300, 0);
        conecta = ConectaBd.conectabd();
        listarProcedimentos();
        listarmedicos();
        listarAnimais();
    }

    public void CadastraConsulta() {
        String sql = "insert into procedimento(cpfmedico,nomeanimal,cpfclianimal,data,descricao) values(?,?,?,?,?)";

        try {

            pst = conecta.prepareStatement(sql);

            pst.setString(1, tfCPF.getText());
            pst.setString(2, tfNome.getText());
            pst.setString(3, tfCPFDono.getText());
            String dia = tfData.getText().substring(0, 2);
            String mes = tfData.getText().substring(3, 5);
            String ano = tfData.getText().substring(6, 10);
            String startDate = dia + "-" + mes + "-" + ano;
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date date;
            try {
                date = sdf1.parse(startDate);
                java.sql.Date dataSql = new java.sql.Date(date.getTime());
                pst.setDate(4, dataSql);

            } catch (ParseException ex) {
                Logger.getLogger(CadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

            pst.setString(5, tfDescricao.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Consulta Cadastrato com sucesso!", "Consulta Cadastrato com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarProcedimentos();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Animal ou Medico não Cadastrado!");
        }

    }

    public void listarmedicos() {

        String sql = "select id,cpf,nome,crmv from funcionario where crmv is not null";
        try {
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbMedicos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void listarAnimais() {

        String sql = "select cpfcliente,nome from animal order by nome";
        try {
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbAnimal.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void listarProcedimentos() {

        String sql = "select cpfmedico,nomeanimal,cpfclianimal,data,descricao,codproc from procedimento order by codproc";
        try {
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbProcedimentos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void pesquisaAnimal() {

        String sql = "select cpfcliente,nome from animal where nome LIKE ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfPesquisaAnimal.getText() + "%");
            rs = pst.executeQuery();
            tbAnimal.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);

        }
    }

    public void pesquisaMedico() {

        String sql = "select id,cpf,nome,crmv from funcionario where nome LIKE ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfPesquisaMedico.getText() + "%");
            rs = pst.executeQuery();
            tbMedicos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);

        }
    }

    public void pesquisaProcedimento() {

        String sql = "select * from procedimento where nomeanimal LIKE ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfPesquisaConsulta.getText() + "%");
            rs = pst.executeQuery();
            tbProcedimentos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);

        }
    }

    public void mostrarItensMedicos() {

        int seleciona = tbMedicos.getSelectedRow();

        tfCPF.setText(tbMedicos.getModel().getValueAt(seleciona, 1).toString());

    }

    public void mostrarItensAnimal() {

        int seleciona = tbAnimal.getSelectedRow();

        tfCPFDono.setText(tbAnimal.getModel().getValueAt(seleciona, 0).toString());
        tfNome.setText(tbAnimal.getModel().getValueAt(seleciona, 1).toString());

    }

    public void mostrarItensProcedimentos() {

        int seleciona = tbProcedimentos.getSelectedRow();

        tfCPF.setText(tbProcedimentos.getModel().getValueAt(seleciona, 0).toString());
        tfNome.setText(tbProcedimentos.getModel().getValueAt(seleciona, 1).toString());
        tfCPFDono.setText(tbProcedimentos.getModel().getValueAt(seleciona, 2).toString());

        String data = tbProcedimentos.getModel().getValueAt(seleciona, 3).toString();
        String ano2 = data.substring(0, 4);
        String mes2 = data.substring(5, 7);
        String dia2 = data.substring(8, 10);
        String dataTela = dia2 + "/" + mes2 + "/" + ano2;
        tfData.setText(dataTela);

        tfDescricao.setText(tbProcedimentos.getModel().getValueAt(seleciona, 4).toString());
        tfID.setText(tbProcedimentos.getModel().getValueAt(seleciona, 5).toString());
    }

    public void editarProcedimento() {

        String sql = "update procedimento set cpfmedico = ?, nomeanimal = ?, cpfclianimal = ?, data = ?, descricao = ? where codproc = ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfCPF.getText());
            pst.setString(2, tfNome.getText());
            pst.setString(3, tfCPFDono.getText());
            String dia = tfData.getText().substring(0, 2);
            String mes = tfData.getText().substring(3, 5);
            String ano = tfData.getText().substring(6, 10);
            String startDate = dia + "-" + mes + "-" + ano;
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date date;
            try {
                date = sdf1.parse(startDate);
                java.sql.Date dataSql = new java.sql.Date(date.getTime());
                pst.setDate(4, dataSql);

            } catch (ParseException ex) {
                Logger.getLogger(CadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            pst.setString(5, tfDescricao.getText());
                pst.setInt(6, Integer.parseInt(tfID.getText()));

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta Atualizado com sucesso!", "Consulta Atualizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarProcedimentos();

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void deletarProcedimento() {

        String sql = "delete from procedimento where cpfclianimal = ? and codproc = ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfCPFDono.getText());
            pst.setInt(2, Integer.parseInt(tfID.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Consulta Deletado com sucesso!", "Consulta Deletado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarProcedimentos();
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
        tfID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfCPF = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfCPFDono = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfData = new javax.swing.JFormattedTextField();
        tfDescricao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedicos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAnimal = new javax.swing.JTable();
        tfPesquisaMedico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfPesquisaAnimal = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProcedimentos = new javax.swing.JTable();
        tfPesquisaConsulta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Consulta");

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

        jLabel1.setText("ID:");

        jLabel2.setText("CPFMedico:");

        try {
            tfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("NomeAnimal:");

        jLabel4.setText("CPFDono:");

        try {
            tfCPFDono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Data:");

        jLabel7.setText("Descrição:");

        try {
            tfData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        tbMedicos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMedicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMedicos);

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
        jScrollPane2.setViewportView(tbAnimal);

        tfPesquisaMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaMedicoKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ico_buscar.png"))); // NOI18N
        jLabel8.setText("Pesquisar:");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ico_buscar.png"))); // NOI18N
        jLabel9.setText("Pesquisar:");

        tfPesquisaAnimal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaAnimalKeyReleased(evt);
            }
        });

        tbProcedimentos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProcedimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProcedimentosMouseClicked(evt);
            }
        });
        tbProcedimentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbProcedimentosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbProcedimentos);

        tfPesquisaConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaConsultaKeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ico_buscar.png"))); // NOI18N
        jLabel10.setText("Pesquisar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfCPF)
                                    .addComponent(tfCPFDono, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfData, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNome)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfPesquisaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfPesquisaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfPesquisaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfCPF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfCPFDono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfDescricao))
                        .addGap(122, 122, 122))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPesquisaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tfPesquisaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(tfPesquisaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        deletarProcedimento();
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        tfNome.setText("");
        tfCPF.setText("");
        tfCPFDono.setText("");
        tfData.setText("");

        tfDescricao.setText("");
        tfID.setText("");
        tfCPF.requestFocusInWindow();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        CadastraConsulta();
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        editarProcedimento();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void tfPesquisaMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaMedicoKeyReleased
        pesquisaMedico();
    }//GEN-LAST:event_tfPesquisaMedicoKeyReleased

    private void tfPesquisaAnimalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaAnimalKeyReleased
        pesquisaAnimal();
    }//GEN-LAST:event_tfPesquisaAnimalKeyReleased

    private void tfPesquisaConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaConsultaKeyReleased
        pesquisaProcedimento();
    }//GEN-LAST:event_tfPesquisaConsultaKeyReleased

    private void tbMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMedicosMouseClicked
        mostrarItensMedicos();
    }//GEN-LAST:event_tbMedicosMouseClicked

    private void tbAnimalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnimalMouseClicked
        mostrarItensAnimal();
    }//GEN-LAST:event_tbAnimalMouseClicked

    private void tbProcedimentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedimentosKeyReleased

    }//GEN-LAST:event_tbProcedimentosKeyReleased

    private void tbProcedimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedimentosMouseClicked
        mostrarItensProcedimentos();
    }//GEN-LAST:event_tbProcedimentosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbAnimal;
    private javax.swing.JTable tbMedicos;
    private javax.swing.JTable tbProcedimentos;
    private javax.swing.JFormattedTextField tfCPF;
    private javax.swing.JFormattedTextField tfCPFDono;
    private javax.swing.JFormattedTextField tfData;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPesquisaAnimal;
    private javax.swing.JTextField tfPesquisaConsulta;
    private javax.swing.JTextField tfPesquisaMedico;
    // End of variables declaration//GEN-END:variables
}
