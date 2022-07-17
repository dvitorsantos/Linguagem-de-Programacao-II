import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Optional;

public class PainelBanner extends JPanel{
    private String imagePath;
    private JLabel bannerLabel;

    public PainelBanner(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/data/banner.txt"));
            this.imagePath = reader.readLine();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.bannerLabel = new JLabel();
        this.bannerLabel.setIcon(new ImageIcon(this.imagePath));
        this.setPreferredSize(new Dimension(1000, 200));
        this.add(bannerLabel);
        this.setVisible(true);
    }

    public void changeImage(String path) throws IOException {
        ArrayList<String> extensoesPermitidas = new ArrayList<String>();
        extensoesPermitidas.add("jpg");
        extensoesPermitidas.add("png");
        Optional<String> extensao = retornaExtensaoDoArquivo(path);

        if (extensao.isPresent()) {
            if (extensoesPermitidas.contains(extensao.get())) {
                this.imagePath = path;
                this.bannerLabel.setIcon(new ImageIcon(this.imagePath));
                try {
                    FileWriter fWriter = new FileWriter("src/data/banner.txt");
                    fWriter.write(path);
                    fWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IOException("Extensão inválida");
            }
        } else {
            throw new IOException("Arquivo não encontrado");
        }


    }

    public Optional<String> retornaExtensaoDoArquivo(String path) {
        return Optional.ofNullable(path)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1));
    }
}
