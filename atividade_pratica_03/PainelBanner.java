import javax.swing.*;
import java.awt.*;

public class PainelBanner extends JPanel{
    ImageIcon bannerImage;

    public PainelBanner(String path) {
        this.bannerImage = new ImageIcon(path);

        JLabel bannerLabel = new JLabel();
        bannerLabel.setIcon(bannerImage);

        this.setSize(1280, 100);
        this.add(bannerLabel);
        this.setVisible(true);
    }
}
