import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PainelListagem extends JPanel implements ActionListener {
    DefaultTableModel tabelaModel;
    JTable tabela;

    JTextField filtroTitulo;

    String column[]={"Titulo","Autor","Ano",
                    "Paginas", "Editora", "Idioma"
                    ,"Edicao", "Categoria", "Descricao", "Genero", "Alugado Por"};

    public PainelListagem() {
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

        JButton botaoDevolver = new JButton("Devolver");
        botaoDevolver.addActionListener(this);

        JButton botaoAlugar = new JButton("Alugar");
        botaoAlugar.addActionListener(this);

        painelSul.add(BotaoRemoverLivro);
        painelSul.add(botaoDevolver);
        painelSul.add(botaoAlugar);

        this.add(painelSul, BorderLayout.SOUTH);

        this.loadTabela();
        JScrollPane scrollPane = new JScrollPane(tabela,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(1200, 350));

        this.add(scrollPane, BorderLayout.CENTER);
    }


    public void loadTabela() {
        System.out.println("Carregando tabela...");
        this.tabelaModel = new DefaultTableModel(column, 0);
        this.tabela = new JTable(this.tabelaModel);

        this.tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
        this.tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
        this.tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.tabela.getColumnModel().getColumn(4).setPreferredWidth(180);
        this.tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(8).setPreferredWidth(200);
        this.tabela.getColumnModel().getColumn(9).setPreferredWidth(70);
        this.tabela.getColumnModel().getColumn(10).setPreferredWidth(100);

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

            if (row != -1) {
                JDialog dialog = new JDialog();

                JLabel labelCpf = new JLabel("CPF:");
                TextField textFieldCpf = new TextField();
                textFieldCpf.setPreferredSize(new Dimension(200, 25));
                JButton botaoConfirmar = new JButton("Confirmar");
                botaoConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (textFieldCpf.getText().equals("")) {
                            JOptionPane.showMessageDialog(dialog, "Preencha o campo CPF");
                        }
                        dialog.dispose();
                    }
                });

                dialog.setTitle("Alugar Livro");
                dialog.setSize(300, 120);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setResizable(false);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLayout(new FlowLayout());

                dialog.add(labelCpf);
                dialog.add(textFieldCpf);
                dialog.add(botaoConfirmar);
                dialog.setVisible(true);

                if (textFieldCpf.getText().equals("")) {
                    return;
                } else {
                    tabelaModel.setValueAt(textFieldCpf.getText(), row, 10);
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
                    tabelaModel.setValueAt("Disponivel", row, 10);
                    this.saveTableData(tabela);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para devolver!");
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
}
