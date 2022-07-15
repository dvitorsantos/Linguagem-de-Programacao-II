import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PainelCadastro extends JPanel implements ActionListener {

    JTextField textFieldTitulo;
    JTextField textFieldAutor;
    JTextField textFieldAno;
    JTextField textFieldPaginas;
    JTextField textFieldEditora;
    JTextField textFieldIdioma;
    JTextField textFieldEdicao;
    JTextField textFieldCategoria;
    JTextField textFieldDescricao;
    JTextField textFieldGenero;
    public PainelCadastro(int width, int height) {
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());
        painel.setSize(1, 1);
        painel.setPreferredSize(new Dimension(700, 400));

JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new BorderLayout());
        JLabel labelTitulo = new JLabel("Título");
        this.textFieldTitulo = new JTextField();
        this.textFieldTitulo.setPreferredSize(new Dimension(300, 25));
        painelTitulo.add(labelTitulo, BorderLayout.NORTH);
        painelTitulo.add(textFieldTitulo, BorderLayout.CENTER);
JPanel painelAutor = new JPanel();
        painelAutor.setLayout(new BorderLayout());
        JLabel labelAutor = new JLabel("Autor");
        this.textFieldAutor = new JTextField();
        this.textFieldAutor.setPreferredSize(new Dimension(300, 25));
        painelAutor.add(labelAutor, BorderLayout.NORTH);
        painelAutor.add(textFieldAutor, BorderLayout.CENTER);
JPanel painelAno = new JPanel();
        painelAno.setLayout(new BorderLayout());
        JLabel labelAno = new JLabel("Ano");
        this.textFieldAno = new JTextField();
        this.textFieldAno.setPreferredSize(new Dimension(300, 25));
        painelAno.add(labelAno, BorderLayout.NORTH);
        painelAno.add(textFieldAno, BorderLayout.CENTER);
JPanel painelPaginas = new JPanel();
        painelPaginas.setLayout(new BorderLayout());
        JLabel labelPaginas = new JLabel("Páginas");
        this.textFieldPaginas = new JTextField();
        this.textFieldPaginas.setPreferredSize(new Dimension(300, 25));
        painelPaginas.add(labelPaginas, BorderLayout.NORTH);
        painelPaginas.add(textFieldPaginas, BorderLayout.CENTER);
JPanel painelEditora = new JPanel();
        painelEditora.setLayout(new BorderLayout());
        JLabel labelEditora = new JLabel("Editora");
        this.textFieldEditora = new JTextField();
        this.textFieldEditora.setPreferredSize(new Dimension(300, 25));
        painelEditora.add(labelEditora, BorderLayout.NORTH);
        painelEditora.add(textFieldEditora, BorderLayout.CENTER);
JPanel painelIdioma = new JPanel();
        painelIdioma.setLayout(new BorderLayout());
        JLabel labelIdioma = new JLabel("Idioma");
        this.textFieldIdioma = new JTextField();
        this.textFieldIdioma.setPreferredSize(new Dimension(300, 25));
        painelIdioma.add(labelIdioma, BorderLayout.NORTH);
        painelIdioma.add(textFieldIdioma, BorderLayout.CENTER);
JPanel painelEdicao = new JPanel();
        painelEdicao.setLayout(new BorderLayout());
        JLabel labelEdicao = new JLabel("Edição");
        this.textFieldEdicao = new JTextField();
        this.textFieldEdicao.setPreferredSize(new Dimension(300, 25));
        painelEdicao.add(labelEdicao, BorderLayout.NORTH);
        painelEdicao.add(textFieldEdicao, BorderLayout.CENTER);
JPanel painelCategoria = new JPanel();
        painelCategoria.setLayout(new BorderLayout());
        JLabel labelCategoria = new JLabel("Categoria");
        this.textFieldCategoria = new JTextField();
        this.textFieldCategoria.setPreferredSize(new Dimension(300, 25));
        painelCategoria.add(labelCategoria, BorderLayout.NORTH);
        painelCategoria.add(textFieldCategoria, BorderLayout.CENTER);
JPanel painelDescricao = new JPanel();
        painelDescricao.setLayout(new BorderLayout());
        JLabel labelDescricao = new JLabel("Descrição");
        this.textFieldDescricao = new JTextField();
        this.textFieldDescricao.setPreferredSize(new Dimension(300, 25));
        painelDescricao.add(labelDescricao, BorderLayout.NORTH);
        painelDescricao.add(textFieldDescricao, BorderLayout.CENTER);
JPanel painelGenero = new JPanel();
        painelGenero.setLayout(new BorderLayout());
        JLabel labelGenero = new JLabel("Gênero");
        this.textFieldGenero = new JTextField();
        this.textFieldGenero.setPreferredSize(new Dimension(300, 25));
        painelGenero.add(labelGenero, BorderLayout.NORTH);
        painelGenero.add(textFieldGenero, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BorderLayout());
        painelBotoes.setPreferredSize(new Dimension(600, 25));
        JButton buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(this);
        painelBotoes.add(buttonCadastrar, BorderLayout.EAST);

        painel.add(painelTitulo);
        painel.add(painelAutor);
        painel.add(painelAno);
        painel.add(painelPaginas);
        painel.add(painelEditora);
        painel.add(painelIdioma);
        painel.add(painelEdicao);
        painel.add(painelCategoria);
        painel.add(painelDescricao);
        painel.add(painelGenero);
        painel.add(painelBotoes);

        this.add(painel, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Cadastrar")) {
            try {
                System.out.println("Cadastrando...");
                FileWriter fWriter = new FileWriter(
                        "C:\\Users\\Vitor\\IdeaProjects\\TerceiraAvaliacao\\src\\data\\livros.txt", true);
                fWriter.write("\n" +
                        textFieldTitulo.getText() + ";" +
                        textFieldAutor.getText() + ";" +
                        textFieldAno.getText() + ";" +
                        textFieldPaginas.getText() + ";" +
                        textFieldEditora.getText() + ";" +
                        textFieldIdioma.getText() + ";" +
                        textFieldEdicao.getText() + ";" +
                        textFieldCategoria.getText() + ";" +
                        textFieldDescricao.getText() + ";" +
                        textFieldGenero.getText());
                fWriter.close();
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
