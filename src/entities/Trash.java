package entities;

import java.awt.Image;
import java.awt.*;
public class Trash {
    
    Image trashImage;
    int trashIndex;
    
    String[] type = {"Vidro","Plastico","Papel","Metal",};
    String trashName;
    String trashType;
    int lastIndex = type.length - 1;


 
    public void setRandomTrash(){
        trashIndex = new RandomNumber(0,lastIndex).getRandomNumber();
        TypeOfTrash();
    }
   
    private void TypeOfTrash() {
        trashType = type[trashIndex];
        switch (type[trashIndex]) {
            case "Plastico":
                trashName = "copo descartavel";
                trashImage = new ImageAsset("../assets/copo_descartavel.png").getImage();
                break;
            case "Metal":
                trashName = "lata";
                trashImage = new ImageAsset("../assets/lata.png").getImage();
                break;
            case "Vidro":
                trashName = "garrafa de vidro";
                trashImage = new ImageAsset("../assets/garrafa_de_vidro.png").getImage();
                break;
            case "Papel":
                trashName = "papel amassado";
                trashImage = new ImageAsset("../assets/papel.png").getImage();
                break;
        
            default:
                break;
        }

    }

    public void  Draw(Graphics g){

        g.drawImage(trashImage,837 - trashImage.getWidth(null)/2 , 177 - trashImage.getHeight(null)/2,trashImage.getWidth(null),trashImage.getHeight(null), null);
    }


}
