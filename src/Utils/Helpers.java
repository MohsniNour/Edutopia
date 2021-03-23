package Utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author rayen
 */
public class Helpers {

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(File sourceFile) {
        String fname = sourceFile.getName();
        String path = Config.BASE_URL + fname;
        System.out.println(Config.BASE_URL + fname);
        File destinationFile = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(
                    destinationFile);

            int bufferSize;
            byte[] bufffer = new byte[512];
            while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                fileOutputStream.write(bufffer, 0, bufferSize);
            }
            fileInputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fname;
    }


}
