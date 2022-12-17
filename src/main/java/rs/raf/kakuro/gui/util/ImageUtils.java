package rs.raf.kakuro.gui.util;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

public class ImageUtils {

    /**
     * Loads an icon from the resource directory.
     * @param name icon name
     * @return icon if name exist, otherwise null
     */
    public static Icon loadIcon(String name) {
        URL iconURL = ResourceUtils.getImagePath(name);

        if (iconURL != null)
            return new ImageIcon(iconURL);

        return null;
    }

    /**
     * Loads an image from the resource directory.
     * @param name image name
     * @return image if name exist, otherwise null
     */
    public static Image loadImage(String name) {
        URL imageURL = ResourceUtils.getImagePath(name);

        if (imageURL != null)
            return new ImageIcon(imageURL).getImage();

        return null;
    }

}
