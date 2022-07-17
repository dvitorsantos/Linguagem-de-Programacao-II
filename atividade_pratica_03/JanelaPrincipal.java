import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JanelaPrincipal extends JFrame implements ActionListener {
    PainelCadastro painelCadastro;
    PainelListagem painelListagem;

    PainelBanner painelBanner;

    JPanel painelPrincipal;
    public JanelaPrincipal() {
        setTitle("Janela Principal");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        this.painelPrincipal = new JPanel();

        this.painelBanner = new PainelBanner("src/images/banner.jpg");
        this.painelCadastro = new PainelCadastro(100, 50);
        this.painelListagem = new PainelListagem();

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

        //Opção Configurações
        JMenuItem menuItemConfiguracoes = new JMenuItem("Trocar Banner");
        menuItemConfiguracoes.addActionListener(this);
        menuOutros.add(menuItemConfiguracoes);

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
            this.painelListagem = new PainelListagem();
            this.painelPrincipal.add(this.painelListagem);
            this.painelListagem.setVisible(true);
            this.painelCadastro.setVisible(false);
        } else if (e.getActionCommand().equals("Trocar Banner")) {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file.showSaveDialog(null);

            File novoBanner = file.getSelectedFile();


                this.painelBanner.changeImage(novoBanner.getPath());
                System.out.println(novoBanner.getPath());
                JOptionPane.showMessageDialog(null, "Banner alterado com sucesso!");

        }
    }
}
