package entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import utils.screenSize;

public class MainGame extends JPanel  implements ActionListener, KeyListener{

private int cloudX = 0;

Image backgroundImage;
Image cloud;
Image sky;
Image trash;
Image timerForm;


int width;
int height;
int recycleBinYPosition;

recycleBin selectBin;

private int SelectedRecycleBinIndex = 0;

 int timing = 40;

// variaveis do tempo
Timer gameLoop;
Timer cloudLoop;
Timer characterPoseChange;
Timer gameTime;

 //Imagens dos lixos
 recycleBin redTrash; 
 recycleBin OrangeTrash; 
 recycleBin YellowTrash; 
 recycleBin GreenTrash; 

 //assets
 Trash randomTrash = new Trash();

 
 Arrow arrow;

class cloud{
    int x = 0;
    Image image;

    cloud(Image image){
        this.image = image;
    }
}
character character = new character();

public MainGame(){
    
    screenSize screenSize = new screenSize();
    width = screenSize.boardWidth;
    height = screenSize.boardHeight;
    recycleBinYPosition = height/2 - 150;
    setPreferredSize(new Dimension(width,height)); 
    setFocusable(true);
    addKeyListener(this);

    cloud = new ImageAsset("../assets/cloud.png").getImage();
    sky = new ImageAsset("../assets/sky.png").getImage();
    timerForm = new ImageAsset("../assets/timer_form.png").getImage();
    //tipos de lixos
    redTrash = new recycleBin("Eletronico");
    OrangeTrash = new recycleBin("Plastico");
    YellowTrash = new recycleBin("Metal");
    GreenTrash = new recycleBin("Vidro");
    
     //definição da localização X das Lixeiras
     redTrash.setLocationX((width/2 )   - 600 - (redTrash.getImage().getWidth(null)/2));
     GreenTrash.setLocationX((width/2)  - 200 - (GreenTrash.getImage().getWidth(null)/2));
     YellowTrash.setLocationX((width/2) + 200 - (YellowTrash.getImage().getWidth(null)/2));
     OrangeTrash.setLocationX((width/2) + 500 - (OrangeTrash.getImage().getWidth(null)/2));

    
    arrow = new Arrow();

    handleSelectedRecycleBin();
    
    arrow.setLocationY(recycleBinYPosition - 150);
 
    backgroundImage = new ImageIcon(getClass().getResource("../assets/background.png")).getImage();
    backgroundImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    randomTrash.setRandomTrash();

    cloudLoop = new Timer(25,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            CloudMoving();
            arrow.Animation();
        }
    });

    characterPoseChange = new Timer(40,new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            character.timer -= 1;
            
            if(character.timer == 0){
                
                characterPoseChange.stop();
                character.animate("normal");
                character.timer = 5;
            }
        }


    });

    gameTime = new Timer(1000,new ActionListener() {
        public void actionPerformed(ActionEvent e ){
            
            if (timing > 0){
                timing--;

            }
        }
    } );
    gameLoop = new Timer(1000/75,this);
    gameLoop.start();

    gameTime.start();
}



private void CloudMoving(){
   
    cloudX += 1;
    
    if (cloudX > getWidth()) {
        cloudX = -100; // Reposicione a nuvem quando sair da tela
    }
}


    


public void  paintComponent(Graphics g){
    super.paintComponent(g);
    Draw(g);
    character.draw(g);
    
 
}

public void setGameTime(){
    
}


private void  Draw(Graphics g){
    //background
    g.drawImage(sky, 0, 0,width,sky.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(cloud,cloudX, 0,cloud.getWidth(getFocusCycleRootAncestor()),cloud.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(backgroundImage, 0, 0,width,height,null);
    
    g.drawImage(redTrash.getImage(), redTrash.getLocationX(), recycleBinYPosition,redTrash.getImage().getWidth(getFocusCycleRootAncestor()),redTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(GreenTrash.getImage(), GreenTrash.getLocationX(), recycleBinYPosition,GreenTrash.getImage().getWidth(getFocusCycleRootAncestor()),GreenTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(YellowTrash.getImage(), YellowTrash.getLocationX(), recycleBinYPosition,YellowTrash.getImage().getWidth(getFocusCycleRootAncestor()),YellowTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(OrangeTrash.getImage(), OrangeTrash.getLocationX(), recycleBinYPosition,OrangeTrash.getImage().getWidth(getFocusCycleRootAncestor()),OrangeTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);

    g.drawImage(arrow.getImage(), arrow.x, arrow.y, arrow.getImage().getWidth(getFocusCycleRootAncestor()),arrow.getImage().getHeight(getFocusCycleRootAncestor()),null);
    
    g.drawImage(timerForm, width/2 - (timerForm.getWidth(null)/2), -20, timerForm.getWidth(null),timerForm.getHeight(null),null);
    Graphics2D g2d = (Graphics2D) g;
     
    g.setColor(Color.white);
    Font fonte = new Font("Arial", Font.BOLD,70);
    FontMetrics metrics = g.getFontMetrics(fonte);
    int larguraTexto = metrics.stringWidth(randomTrash.trashType);
    
    int larguraTexto2 = metrics.stringWidth(selectBin.getName());
    
    int x = (getWidth() - larguraTexto) / 2;
    int x2 = (getWidth() - larguraTexto2) / 2;


    g2d.setFont(fonte);


    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawString(String.valueOf(timing),  width/2 - 30,  60);

    g.drawString(randomTrash.trashType, x, 200);
    
    g.drawString(selectBin.getName(),x2,300 );

   
    
}



public void handleSelectedRecycleBin(){


    switch (SelectedRecycleBinIndex) {
        
        case 0:
            selectBin = YellowTrash;
            arrow.setLocationX(YellowTrash.getLocationX()+50);
            break;
        
        case 1:
        selectBin = GreenTrash;
            arrow.setLocationX(GreenTrash.getLocationX()+50);
            break;
        
        case 2:
        selectBin = redTrash;
            arrow.setLocationX(redTrash.getLocationX()+50);
            break;
    
        case 3:
        selectBin = OrangeTrash;
            arrow.setLocationX(OrangeTrash.getLocationX()+50);
            break;
        default:
            break;
    }
  
}

@Override
public void actionPerformed(ActionEvent e) {
    cloudLoop.start();

    repaint();
}

@Override
public void keyPressed(KeyEvent e){
   
    if (e.getKeyCode() == KeyEvent.VK_LEFT){
        
        if(SelectedRecycleBinIndex == 3){SelectedRecycleBinIndex = 0;} 
        else{SelectedRecycleBinIndex = SelectedRecycleBinIndex+1;}
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT){

        if(SelectedRecycleBinIndex == 0){SelectedRecycleBinIndex = 3;}
        else{
            SelectedRecycleBinIndex = SelectedRecycleBinIndex-1;
        }
           
    }

    if (e.getKeyCode() == KeyEvent.VK_SPACE){
       
        character.animate("trowing");

        
        characterPoseChange.start();

        if(randomTrash.trashType == selectBin.name){
            timing +=3;
        }
        else{
            timing -=1;
        }
        randomTrash.setRandomTrash();

    }
    
    handleSelectedRecycleBin();

}
@Override
public void keyTyped(KeyEvent e) {
 
}

@Override
public void keyReleased(KeyEvent e) {
    
}
}
