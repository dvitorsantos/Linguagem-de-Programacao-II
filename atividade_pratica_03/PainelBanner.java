import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PainelBanner extends JPanel{
    String imagePath;
    JLabel bannerLabel;

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

    public void changeImage(String path) {
        this.bannerLabel.setIcon(new ImageIcon(path));

        FileWriter fWriter = null;
        try {
            fWriter = new FileWriter("src/data/banner.txt");
            fWriter.write(path);
            fWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
