import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;

public class JanelaPrincipal extends JFrame implements ActionListener {
    private PainelCadastro painelCadastro;
    private PainelListagem painelListagem;

    private PainelBanner painelBanner;
    private double multa;

    private JPanel painelPrincipal;
    public JanelaPrincipal() {
        setTitle("Gerenciador de Biblioteca");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/data/multa.txt"));
            this.multa = Double.parseDouble(reader.readLine());
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.painelPrincipal = new JPanel();

        this.painelBanner = new PainelBanner("src/images/banner.jpg");
        this.painelCadastro = new PainelCadastro(100, 50);
        this.painelListagem = new PainelListagem(multa);

        this.painelPrincipal.add(this.painelCadastro);
        this.painelPrincipal.add(this.painelListagem);

        this.painelBanner.setVisible(true);
        this.painelCadastro.setVisible(true);
        this.painelListagem.setVisible(false);

        this.add(painelBanner, BorderLayout.NORTH);
        this.add(painelPrincipal, BorderLayout.CENTER);

        setJMenuBar(menuPrincipal());
    }

    public JMenuBar menuPrincipal() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCadastrar = new JMenu("Cadastrar");
        JMenu menuListar = new JMenu("Listar");
        JMenu menuOutros = new JMenu("Configurações");

        //Opção Cadastrar
        JMenuItem menuItemCadastrar = new JMenuItem("Cadastro de Livros");
        menuItemCadastrar.addActionListener(this);
        menuCadastrar.add(menuItemCadastrar);

        //Opção Listar
        JMenuItem menuItemListar = new JMenuItem("Listagem de Livros");
        menuItemListar.addActionListener(this);
        menuListar.add(menuItemListar);

        //Opção Trocar Banner
        JMenuItem menuItemTrocarBanner = new JMenuItem("Trocar Banner");
        menuItemTrocarBanner.addActionListener(this);
        menuOutros.add(menuItemTrocarBanner);

        JMenuItem menuItemValorMulta = new JMenuItem("Trocar Valor da Multa");
        menuItemValorMulta.addActionListener(this);
        menuOutros.add(menuItemValorMulta);

        menuBar.add(menuCadastrar);
        menuBar.add(menuListar);
        menuBar.add(menuOutros);

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cadastro de Livros")) {
            this.painelCadastro.setVisible(true);
            this.painelListagem.setVisible(false);
        } else if (e.getActionCommand().equals("Listagem de Livros")) {
            this.painelListagem = new PainelListagem(multa);
            this.painelPrincipal.add(this.painelListagem);
            this.painelListagem.setVisible(true);
            this.painelCadastro.setVisible(false);
        } else if (e.getActionCommand().equals("Trocar Banner")) {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file.showSaveDialog(null);

            File novoBanner = file.getSelectedFile();

                this.painelBanner.changeImage(novoBanner.getPath());
                JOptionPane.showMessageDialog(null, "Banner alterado com sucesso!");

        } else if (e.getActionCommand().equals("Trocar Valor da Multa")) {
            String valor = JOptionPane.showInputDialog("Digite o valor da multa:");

            this.multa = Double.parseDouble(valor);
            this.painelListagem.setMulta(this.multa);
            try {
                FileWriter fWriter = new FileWriter("src/data/multa.txt");
                fWriter.write(valor);
                fWriter.close();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
