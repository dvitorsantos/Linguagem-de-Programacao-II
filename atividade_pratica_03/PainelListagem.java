import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PainelListagem extends JPanel implements ActionListener {
    private DefaultTableModel tabelaModel;
    private JTable tabela;

    private JTextField filtroTitulo;

    private double multa;
    private String column[]={"Titulo","Autor","Ano",
                    "Paginas", "Editora", "Idioma"
                    ,"Edicao", "Categoria", "Descricao", "Genero", "Alugado Por", "Alugado Até"};

    public PainelListagem(double multa) {
        this.multa = multa;
        this.setLayout(new BorderLayout());

        filtroTitulo = new JTextField();
        filtroTitulo.setPreferredSize(new Dimension(500, 25));
        JButton botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.addActionListener(this);

        JPanel painelNorte = new JPanel();
        painelNorte.setLayout(new FlowLayout());
        painelNorte.setPreferredSize(new Dimension(700, 50));
        painelNorte.add(filtroTitulo);
        painelNorte.add(botaoFiltrar);
        this.add(painelNorte, BorderLayout.NORTH);

        JPanel painelSul = new JPanel();
        painelSul.setLayout(new FlowLayout(FlowLayout.RIGHT));
        painelSul.setPreferredSize(new Dimension(700, 50));

        JButton BotaoRemoverLivro = new JButton("Remover Livro");
        BotaoRemoverLivro.addActionListener(this);

        JButton botaoPagarMulta = new JButton("Pagar multa");
        botaoPagarMulta.addActionListener(this);

        JButton botaoDevolver = new JButton("Devolver");
        botaoDevolver.addActionListener(this);

        JButton botaoAlugar = new JButton("Alugar");
        botaoAlugar.addActionListener(this);

        painelSul.add(BotaoRemoverLivro);
        painelSul.add(botaoPagarMulta);
        painelSul.add(botaoDevolver);
        painelSul.add(botaoAlugar);

        this.add(painelSul, BorderLayout.SOUTH);

        this.loadTabela();
        JScrollPane scrollPane = new JScrollPane(tabela,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(1400, 350));

        this.add(scrollPane, BorderLayout.CENTER);
    }


    public void loadTabela() {
        this.tabelaModel = new DefaultTableModel(column, 0);
        this.tabela = new JTable(this.tabelaModel);

        this.tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
        this.tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
        this.tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
        this.tabela.getColumnModel().getColumn(4).setPreferredWidth(180);
        this.tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(8).setPreferredWidth(200);
        this.tabela.getColumnModel().getColumn(9).setPreferredWidth(70);
        this.tabela.getColumnModel().getColumn(10).setPreferredWidth(100);
        this.tabela.getColumnModel().getColumn(11).setPreferredWidth(80);

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("./src/data/livros.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                tabelaModel.addRow(line.split(";"));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
        this.tabela.setFillsViewportHeight(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Filtrar")) {
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) this.tabela.getModel()));
            sorter.setRowFilter(RowFilter.regexFilter(this.filtroTitulo.getText()));

            this.tabela.setRowSorter(sorter);
        } else if (e.getActionCommand().equals("Alugar")) {
            int row = this.tabela.getSelectedRow();

            if (!tabelaModel.getValueAt(row, 10).equals("Disponivel")) {
                JOptionPane.showMessageDialog(this, "Livro não disponível para alugar!");
                return;
            }

            if (row != -1) {
                JDialog dialog = new JDialog();

                JLabel labelCpf = new JLabel("Cpf:");
                TextField textFieldCpf = new TextField();
                textFieldCpf.setPreferredSize(new Dimension(200, 25));
                JLabel labelData = new JLabel("Devolver em:");
                TextField textFieldData = new TextField();
                textFieldData.setPreferredSize(new Dimension(150, 25));
                JButton botaoConfirmar = new JButton("Confirmar");
                botaoConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (textFieldCpf.getText().equals("") || textFieldData.getText().equals("")) {
                            JOptionPane.showMessageDialog(dialog, "Preencha todos os campos!");
                        }
                        dialog.dispose();
                    }
                });

                LocalDate dataDevolucao = LocalDate.now();
                textFieldData.setText(dataDevolucao.plusDays(7).toString());

                dialog.setTitle("Alugar Livro");
                dialog.setSize(300, 200);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setResizable(false);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLayout(new FlowLayout());

                dialog.add(labelCpf);
                dialog.add(textFieldCpf);
                dialog.add(labelData);
                dialog.add(textFieldData);
                dialog.add(botaoConfirmar);
                dialog.setVisible(true);

                if (textFieldCpf.getText().equals("") ) {
                    return;
                } else {
                    tabelaModel.setValueAt(textFieldCpf.getText(), row, 10);
                    tabelaModel.setValueAt(textFieldData.getText(), row, 11);
                    this.saveTableData(tabela);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para alugar!");
            }
        } else if (e.getActionCommand().equals("Devolver")) {
            int row = this.tabela.getSelectedRow();

            if (row != -1) {
                int resultPainelConfirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente devolver o livro?", "Devolver Livro", JOptionPane.YES_NO_OPTION);

                if (resultPainelConfirmacao == JOptionPane.YES_OPTION) {
                    LocalDate dataHoje = LocalDate.now();
                    LocalDate dataDevolucao = LocalDate.parse(tabelaModel.getValueAt(row, 11).toString());
                    if (dataHoje.isAfter(LocalDate.parse(tabelaModel.getValueAt(row, 11).toString()))) {
                        int quantidadeDiasAtraso = Math.abs((int) ChronoUnit.DAYS.between(dataHoje, dataDevolucao));
                        JOptionPane.showMessageDialog(this, "A data de devolução já passou! Deve ser pago uma multa de R$" +
                                String.format("%,.2f", quantidadeDiasAtraso * multa));
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Livro devolvido sem multa cobrada.");
                        tabelaModel.setValueAt("Disponivel", row, 10);
                        tabelaModel.setValueAt("Disponivel", row, 11);
                        this.saveTableData(tabela);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para devolver!");
            }
        } else if (e.getActionCommand().equals("Pagar multa")) {
            int row = this.tabela.getSelectedRow();
            if (row != -1) {
                JOptionPane.showMessageDialog(null, "Multa paga e livro devolvido!");
                tabelaModel.setValueAt("Disponivel", row, 10);
                tabelaModel.setValueAt("Disponivel", row, 11);
                this.saveTableData(tabela);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para pagar a multa!");
            }

        } else  if (e.getActionCommand().equals("Remover Livro")) {
            int row = this.tabela.getSelectedRow();

            if (row != -1) {
                int resultPainelConfirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o livro?", "Devolver Livro", JOptionPane.YES_NO_OPTION);
                if (resultPainelConfirmacao == JOptionPane.YES_OPTION) {
                    tabelaModel.removeRow(row);
                    this.saveTableData(tabela);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para remover!");
            }
        }
    }

    public void saveTableData(JTable table) {
        try
        {
            StringBuilder stringBuilder = new StringBuilder();
            FileWriter writer = new FileWriter("./src/data/livros.txt");
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    stringBuilder.append(table.getValueAt(i, j));

                    if (j != table.getColumnCount() - 1) {
                        stringBuilder.append(";");
                    }
                }
                if (i != table.getRowCount() - 1) {
                    stringBuilder.append("\n");
                }
            }
            writer.write(stringBuilder.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
}
