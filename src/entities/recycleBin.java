package entities;

import java.awt.Image;


public class recycleBin {
    String color;
    Image trashImage;


    public recycleBin(String color){
        TypeOfTrash(color);
    }
    
    private void TypeOfTrash(String type) {

        switch (type) {
            case "vidro":
                trashImage = new ImageAsset("../assets/lixo-amarelo.png").getImage();
                break;
            case "plastico":
                trashImage = new ImageAsset("../assets/lixo-laranja.png").getImage();
                break;
            case "papel":
            trashImage = new ImageAsset("../assets/lixo-verde.png").getImage();
                break;
            case "organico":
            trashImage = new ImageAsset("../assets/lixo-vermelho.png").getImage();
                break;
        
            default:
                break;
        }

    }

    public Image getImage(){
        return trashImage;
    }
}
