import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PainelListagem extends JPanel implements ActionListener {
    DefaultTableModel tabelaModel;
    JTable tabela;

    JTextField filtroTitulo;

    String column[]={"Titulo","Autor","Ano",
                    "Paginas", "Editora", "Idioma"
                    ,"Edicao", "Categoria", "Descricao", "Genero"};

    public PainelListagem() {
        this.setLayout(new BorderLayout());

        JLabel nomeTela = new JLabel("Listagem de Livros");

        filtroTitulo = new JTextField();
        filtroTitulo.setPreferredSize(new Dimension(500, 25));
        JButton botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.addActionListener(this);

        JPanel painelFiltros = new JPanel();
        painelFiltros.setLayout(new FlowLayout());
        painelFiltros.setSize(100, 25);
        painelFiltros.add(filtroTitulo);
        painelFiltros.add(botaoFiltrar);
        this.add(painelFiltros, BorderLayout.NORTH);
        this.loadTabela();
        JScrollPane scrollPane = new JScrollPane(tabela,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(1000, 350));

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
        this.tabela.getColumnModel().getColumn(4).setPreferredWidth(200);
        this.tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(8).setPreferredWidth(200);
        this.tabela.getColumnModel().getColumn(9).setPreferredWidth(80);

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
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(((DefaultTableModel) this.tabela.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(this.filtroTitulo.getText()));

        this.tabela.setRowSorter(sorter);
    }
}
