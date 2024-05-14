package entities;

import java.awt.Image;

public class Trash {
    
    Image trashImage;
    
    public Trash(Image trashImage) {

    }

    public void TypeOfTrash(String type) {

        switch (type) {
            case "vidro":
                trashImage = new ImageAsset("./assets/lixo-amarelo.png").getImage();

                break;
            case "plastico":
                trashImage = new ImageAsset("./assets/lixo-amarelo.png").getImage();
                break;
            case "papel":
            trashImage = new ImageAsset("./assets/lixo-amarelo.png").getImage();
                break;
            case "organico":
            trashImage = new ImageAsset("./assets/lixo-amarelo.png").getImage();
                break;
        
            default:
                break;
        }

    }

}
