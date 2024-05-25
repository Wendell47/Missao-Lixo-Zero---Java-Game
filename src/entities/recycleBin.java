package entities;

import java.awt.Image;


public class recycleBin {
    String color;
    Image trashImage;
    String name;
    int locationX;
    String Type;

    public recycleBin(String color){
        TypeOfTrash(color);

    }
    
    private void TypeOfTrash(String type) {
        name = type;

        switch (type) {
            case "Plastico":
                trashImage = new ImageAsset("../assets/lixo-Vermelho.png").getImage();
                break;
            case "Metal":
                trashImage = new ImageAsset("../assets/lixo-amarelo.png").getImage();
                break;
            case "Vidro":
                trashImage = new ImageAsset("../assets/lixo-verde.png").getImage();
                break;
            case "Papel":
                trashImage = new ImageAsset("../assets/lixo-azul.png").getImage();
                break;
        
            default:
                break;
        }

    }

    public boolean handleReceiveTrash(String type){

        if(type == name){
            return true;
        }
        else{
            return false;
        }
    }
    public void setLocationX(int locationX){
        this.locationX = locationX;
    }

    public int getLocationX(){
        return locationX;
    }
    public Image getImage(){
        return trashImage;
    }

    public String getName(){
        return name;
    }
}
