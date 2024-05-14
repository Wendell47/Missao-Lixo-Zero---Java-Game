package entities;

import java.awt.*;

import javax.swing.ImageIcon;

public class ImageAsset {
    private Image image;
    
    /**
     * Construtor para criar um objeto ImageAsset com base no caminho da imagem.
     *
     * @param src O caminho da imagem.
     */
    public  ImageAsset(String src) {

        loadImage(src);
     
    }
    /**
     * Carrega a imagem com base no caminho fornecido.
     *
     * @param src O caminho da imagem.
     */

    private void loadImage(String src) {
        image = new ImageIcon(getClass().getResource(src)).getImage();
    }

    /**
     * Obtém a imagem carregada, redimensionada para ajustar a escala.
     *
     * @return A imagem redimensionada.
     */
    public Image getImage() {

    image = image.getScaledInstance(image.getWidth(null), image.getHeight(null), Image.SCALE_SMOOTH);

    return image;
    }
}
