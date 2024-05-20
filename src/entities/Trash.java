package entities;

import java.awt.Image;

public class Trash {
    
    Image trashImage;
    int trashIndex;
    
    String[] type = {"Vidro","Plastico","Papel","Organico","Metal","Eletronico"};
    String trashType;
    int lastIndex = type.length - 1;


 
    public void setRandomTrash(){
        trashIndex = new RandomNumber(0,lastIndex).getRandomNumber();
        TypeOfTrash();
    }
    private void TypeOfTrash() {
        trashType = type[trashIndex];

        switch (type[trashIndex]) {
            case "vidro":
                System.out.println("Lixo:" + type[trashIndex]);
                break;
            case "Plastico":
            System.out.println("Lixo:" + type[trashIndex]);
                break;
            case "Papel":
            System.out.println("Lixo:" + type[trashIndex]);
                break;
            case "Organico":
            System.out.println("Lixo:" + type[trashIndex]);
                break;
            default:
                break;
        }

    }

}
