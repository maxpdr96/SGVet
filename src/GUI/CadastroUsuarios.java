/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ConectaBd;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Maxwel
 */
public class CadastroUsuarios extends javax.swing.JInternalFrame {

    Connection conecta;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CadastroUsuarios() throws ClassNotFoundException {
        initComponents();
        this.setLocation(300, 10);
        conecta = ConectaBd.conectabd();
        listarusuarios();
    }

    private MaskFormatter setMascara(String mascara) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(mascara);
        } catch (java.text.ParseException ex) {
        }
        return mask;

    }

    public void CadastraUsuario() {
        String sql = "insert into cliente(cpf,rg,nome,datanasc,sexo,fone1,fone2,email,estado,cidade,endereco,bairro,numero,complemento,cep) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                     java.util.Date date;
        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfCPF.getText());
            pst.setString(2, tfRG.getText());
            pst.setString(3, tfNome.getText());
           
            if ("Masculino".equals(cbSexo.getSelectedItem())) {
                pst.setString(5, "Masculino");
            } else if ("Feminino".equals(cbSexo.getSelectedItem())) {
                pst.setString(5, "Feminino");
            }

            pst.setString(6, tfFone1.getText());
            pst.setString(7, tfFone2.getText());
            pst.setString(8, tfEmail.getText());
            if ("AC".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "AC");

            } else if ("AL".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "AL");

            } else if ("AM".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "AM");

            } else if ("AP".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "AP");

            } else if ("BA".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "BA");

            } else if ("CE".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "CE");

            } else if ("DF".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "DF");

            } else if ("ES".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "ES");

            } else if ("GO".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "GO");

            } else if ("MA".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "MA");

            } else if ("MG".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "MG");

            } else if ("MS".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "MS");

            } else if ("MT".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "MT");

            } else if ("PA".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "PA");

            } else if ("PB".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "PB");

            } else if ("PE".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "PE");

            } else if ("PI".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "PI");

            } else if ("PR".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "PR");

            } else if ("RJ".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "RJ");

            } else if ("RN".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "RN");

            } else if ("RO".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "RO");

            } else if ("RR".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "RR");

            } else if ("RS".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "RS");

            } else if ("SC".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "SC");

            } else if ("SE".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "SE");

            } else if ("SP".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "SP");

            } else if ("TO".equals(cbEstado.getSelectedItem())) {
                pst.setString(9, "TO");
            }

            pst.setString(10, tfCidade.getText());
            pst.setString(11, tfEndereco.getText());
            pst.setString(12, tfBairro.getText());
            pst.setString(13, tfNumero.getText());
            pst.setString(14, tfComplemento.getText());
            pst.setString(15, tfCEP.getText());

            String dia = tfDataNasc.getText().substring(0, 2);
            String mes = tfDataNasc.getText().substring(3, 5);
            String ano = tfDataNasc.getText().substring(6, 10);
            String startDate = dia + "-" + mes + "-" + ano;
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
           
            try {
                date = sdf1.parse(startDate);
                java.sql.Date dataSql = new java.sql.Date(date.getTime());
                pst.setDate(4, dataSql);

            } catch (ParseException ex) {
                Logger.getLogger(CadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            pst.execute();

            JOptionPane.showMessageDialog(null, "Usuario Cadastrato com sucesso!", "Usuario Cadastrato com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarusuarios();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }

    }

    public void listarusuarios() {

        String sql = "select * from cliente order by nome";
        try {
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tbCliente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void pesquisarusuarios() {

        String sql = "select * from cliente where nome LIKE ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tbCliente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void mostrarItens() {

        int seleciona = tbCliente.getSelectedRow();

        tfCPF.setText(tbCliente.getModel().getValueAt(seleciona, 0).toString());

        tfRG.setText(tbCliente.getModel().getValueAt(seleciona, 1).toString());
        tfNome.setText(tbCliente.getModel().getValueAt(seleciona, 2).toString());
        
        String data = tbCliente.getModel().getValueAt(seleciona, 3).toString();
        String ano2 = data.substring(0, 4);
        String mes2 = data.substring(5, 7);
        String dia2 = data.substring(8, 10);
        String dataTela = dia2 + "/" + mes2 + "/" + ano2;
        tfDataNasc.setText(dataTela);
         
        if ("Masculino".equals(tbCliente.getModel().getValueAt(seleciona, 4).toString())) {
            cbSexo.setSelectedItem("Masculino");
        } else {
            cbSexo.setSelectedItem("Feminino");
        }
        tfFone1.setText(tbCliente.getModel().getValueAt(seleciona, 5).toString());
        tfFone2.setText(tbCliente.getModel().getValueAt(seleciona, 6).toString());
        tfEmail.setText(tbCliente.getModel().getValueAt(seleciona, 7).toString());

        if (null != tbCliente.getModel().getValueAt(seleciona, 8).toString()) {
            switch (tbCliente.getModel().getValueAt(seleciona, 8).toString()) {
                case "AC":
                    cbEstado.setSelectedItem("AC");
                    break;
                case "AL":
                    cbEstado.setSelectedItem("AL");
                    break;
                case "AM":
                    cbEstado.setSelectedItem("AM");
                    break;
                case "AP":
                    cbEstado.setSelectedItem("AP");
                    break;
                case "BA":
                    cbEstado.setSelectedItem("BA");
                    break;
                case "CE":
                    cbEstado.setSelectedItem("CE");
                    break;
                case "DF":
                    cbEstado.setSelectedItem("DF");
                    break;
                case "ES":
                    cbEstado.setSelectedItem("ES");
                    break;
                case "GO":
                    cbEstado.setSelectedItem("GO");
                    break;
                case "MA":
                    cbEstado.setSelectedItem("MA");
                    break;
                case "MG":
                    cbEstado.setSelectedItem("MG");
                    break;
                case "MS":
                    cbEstado.setSelectedItem("MS");
                    break;
                case "MT":
                    cbEstado.setSelectedItem("MT");
                    break;
                case "PA":
                    cbEstado.setSelectedItem("PA");
                    break;
                case "PB":
                    cbEstado.setSelectedItem("PB");
                    break;
                case "PE":
                    cbEstado.setSelectedItem("PE");
                    break;
                case "PI":
                    cbEstado.setSelectedItem("PI");
                    break;
                case "PR":
                    cbEstado.setSelectedItem("PR");
                    break;
                case "RJ":
                    cbEstado.setSelectedItem("RJ");
                    break;
                case "RN":
                    cbEstado.setSelectedItem("RN");
                    break;
                case "RO":
                    cbEstado.setSelectedItem("RO");
                    break;
                case "RR":
                    cbEstado.setSelectedItem("RR");
                    break;
                case "RS":
                    cbEstado.setSelectedItem("RS");
                    break;
                case "SC":
                    cbEstado.setSelectedItem("SC");
                    break;
                case "SE":
                    cbEstado.setSelectedItem("SE");
                    break;
                case "SP":
                    cbEstado.setSelectedItem("SP");
                    break;
                case "TO":
                    cbEstado.setSelectedItem("TO");
                    break;
            }
        }
        tfCidade.setText(tbCliente.getModel().getValueAt(seleciona, 9).toString());
        tfEndereco.setText(tbCliente.getModel().getValueAt(seleciona, 10).toString());
        tfBairro.setText(tbCliente.getModel().getValueAt(seleciona, 11).toString());
        tfNumero.setText(tbCliente.getModel().getValueAt(seleciona, 12).toString());
        tfComplemento.setText(tbCliente.getModel().getValueAt(seleciona, 13).toString());
        tfCEP.setText(tbCliente.getModel().getValueAt(seleciona, 14).toString());

       
      
    }

    public void editarUsuario() {

        String sql = "update cliente set nome = ?, rg = ?, datanasc = ?, sexo = ?, fone1 = ?, fone2 = ?, email = ?, estado = ?, cidade = ?, endereco = ?, bairro = ?, numero = ?, complemento = ?, cep = ? where cpf = ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfNome.getText());
            pst.setString(2, tfRG.getText());

            pst.setString(3, tfDataNasc.getText());

            if ("Masculino".equals(cbSexo.getSelectedItem())) {
                pst.setString(4, "Masculino");
            } else if ("Feminino".equals(cbSexo.getSelectedItem())) {
                pst.setString(4, "Feminino");
            }

            pst.setString(5, tfFone1.getText());
            pst.setString(6, tfFone2.getText());
            pst.setString(7, tfEmail.getText());
            if ("AC".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "AC");

            } else if ("AL".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "AL");

            } else if ("AM".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "AM");

            } else if ("AP".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "AP");

            } else if ("BA".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "BA");

            } else if ("CE".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "CE");

            } else if ("DF".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "DF");

            } else if ("ES".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "ES");

            } else if ("GO".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "GO");

            } else if ("MA".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "MA");

            } else if ("MG".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "MG");

            } else if ("MS".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "MS");

            } else if ("MT".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "MT");

            } else if ("PA".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "PA");

            } else if ("PB".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "PB");

            } else if ("PE".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "PE");

            } else if ("PI".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "PI");

            } else if ("PR".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "PR");

            } else if ("RJ".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "RJ");

            } else if ("RN".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "RN");

            } else if ("RO".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "RO");

            } else if ("RR".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "RR");

            } else if ("RS".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "RS");

            } else if ("SC".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "SC");

            } else if ("SE".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "SE");

            } else if ("SP".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "SP");

            } else if ("TO".equals(cbEstado.getSelectedItem())) {
                pst.setString(8, "TO");
            }
            pst.setString(9, tfCidade.getText());
            pst.setString(10, tfEndereco.getText());
            pst.setString(11, tfBairro.getText());
            pst.setString(12, tfNumero.getText());
            pst.setString(13, tfComplemento.getText());
            pst.setString(14, tfCEP.getText());
            pst.setString(15, tfCPF.getText());
            String dia = tfDataNasc.getText().substring(0, 2);
            String mes = tfDataNasc.getText().substring(3, 5);
            String ano = tfDataNasc.getText().substring(6, 10);
            String startDate = dia + "-" + mes + "-" + ano;
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date date;
            try {
                date = sdf1.parse(startDate);
                java.sql.Date dataSql = new java.sql.Date(date.getTime());
                pst.setDate(3, dataSql);

            } catch (ParseException ex) {
                Logger.getLogger(CadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario Atualizado com sucesso!", "Usuario Atualizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarusuarios();

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    public void deletarUsuario() {

        String sql = "delete from cliente where cpf = ?";

        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, tfCPF.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuario Deletado com sucesso!", "Usuario Deletado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            listarusuarios();
        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void limparCampos() {

        tfNome.setText("");
        tfCPF.setText("");
        tfRG.setText("");
        tfDataNasc.setText("");
        cbSexo.setSelectedItem("Masculino");
        tfFone1.setText("");
        tfFone2.setText("");
        tfEmail.setText("");
        cbEstado.setSelectedItem("AC");
        tfCidade.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCEP.setText("");
        tfNome.requestFocusInWindow();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        cbSexo = new javax.swing.JComboBox();
        tfRG = new javax.swing.JTextField();
        tfDataNasc = new javax.swing.JFormattedTextField();
        tfCPF = new javax.swing.JFormattedTextField();
        tfEndereco = new javax.swing.JTextField();
        tfNumero = new javax.swing.JTextField();
        tfBairro = new javax.swing.JTextField();
        tfComplemento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        tfCEP = new javax.swing.JFormattedTextField();
        tfFone1 = new javax.swing.JFormattedTextField();
        tfFone2 = new javax.swing.JFormattedTextField();
        tfEmail = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btDeletar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btCadastrar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();
        tfPesquisar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setTitle("CadastroUsuario");

        jLabel1.setText("Nome:");

        jLabel2.setText("Sexo:");

        jLabel3.setText("CPF:");

        jLabel4.setText("RG:");

        jLabel5.setText("Endereço:");

        jLabel6.setText("UF:");

        jLabel7.setText("Telefone1:");

        jLabel8.setText("Telefone2:");

        jLabel9.setText("Email:");

        jLabel10.setText("Nº:");

        jLabel11.setText("Bairro:");

        jLabel12.setText("Complemento:");

        jLabel13.setText("Data Nascimento:");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));
        cbSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexoActionPerformed(evt);
            }
        });

        try {
            tfDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDataNasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDataNascActionPerformed(evt);
            }
        });

        try {
            tfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setText("Cidade:");

        jLabel15.setText("CEP:");

        try {
            tfCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCliente);

        tfPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisarKeyReleased(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ico_buscar.png"))); // NOI18N
        jLabel16.setText("Pesquisar:");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCidade))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfRG, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 188, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNome)
                    .addComponent(jLabel3)
                    .addComponent(tfCPF)
                    .addComponent(jLabel4)
                    .addComponent(tfRG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSexo)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfDataNasc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfEndereco)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNumero)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfBairro)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfComplemento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfCidade)
                    .addComponent(jLabel15)
                    .addComponent(tfCEP)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfFone1)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfFone2)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        CadastraUsuario();
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        deletarUsuario();
    }//GEN-LAST:event_btDeletarActionPerformed

    private void tbClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClienteMouseClicked
        mostrarItens();
    }//GEN-LAST:event_tbClienteMouseClicked

    private void tfPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarKeyReleased
        pesquisarusuarios();
    }//GEN-LAST:event_tfPesquisarKeyReleased

    private void cbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSexoActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        editarUsuario();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void tfDataNascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDataNascActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDataNascActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbCliente;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCEP;
    private javax.swing.JFormattedTextField tfCPF;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfComplemento;
    private javax.swing.JFormattedTextField tfDataNasc;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JFormattedTextField tfFone1;
    private javax.swing.JFormattedTextField tfFone2;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfPesquisar;
    private javax.swing.JTextField tfRG;
    // End of variables declaration//GEN-END:variables

}
