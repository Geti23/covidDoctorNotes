import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class DocumentViewer extends JFrame{
    private JPanel panel1;
    private JLabel imageLabel;

    public DocumentViewer(String path) {
        setPreferredSize(new Dimension(600,900));
        setContentPane(panel1);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Patient Examination Document");
        new Thread(()->{
            try {
                Storage storage = StorageOptions.newBuilder().setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("src/main/resources/docnotes-19b4a-firebase-adminsdk-2aawv-144071d7aa.json")))
                        .build()
                        .getService();
                Blob blob = storage.get(BlobId.of("docnotes-19b4a.appspot.com",path));
                blob.downloadTo(Paths.get(path));
                imageLabel.setText("");
                loadImage(path,imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void loadImage(String path, JLabel pictureLabel){
        pictureLabel.setIcon(null);
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image resized = img.getScaledInstance(pictureLabel.getWidth(),pictureLabel.getHeight(),
                    Image.SCALE_SMOOTH);
            pictureLabel.setIcon(new ImageIcon(resized));
            pictureLabel.revalidate();
            pictureLabel.repaint();
            pictureLabel.update(pictureLabel.getGraphics());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
