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
Image selectedTrashBg;
Image gameEndScore;
Image screenEndGame;
Image StartGameScreen;

int width;
int height;
int recycleBinYPosition;

recycleBin selectBin;

private int SelectedRecycleBinIndex = 0;

 int timing = 15;
int restingTrash = 20;

// pontuação e sequência de acertos
int Score = 0;
int combo = 0;
int maxCombo = 0;
boolean gameEnd = true;
boolean gameStart = true;

// variaveis do tempo
Timer gameLoop;
Timer cloudLoop;
Timer characterPoseChange;
Timer gameTime;

 //Imagens dos lixos
 recycleBin redTrash; 
 recycleBin BlueTrash; 
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

String quebraLinha = System.getProperty("line.separator");

character character = new character();
setFont handleSelectedTrashText = new setFont(); 
setFont timeText = new setFont(); 
setFont selectedBinText = new setFont(); 
setFont trashQuantity = new setFont(); 
setFont gameScoreText = new setFont(); 
setFont gameComboText = new setFont(); 

setFont gameEndFont = new setFont();

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
    selectedTrashBg = new ImageAsset("../assets/selectedTrashBg.png").getImage();

    screenEndGame = new ImageAsset("../assets/End_game_screen.png").getImage();
    StartGameScreen = new ImageAsset("../assets/Start_game_screen.png").getImage();
    //tipos de lixos
    redTrash = new recycleBin("Plastico");
    BlueTrash = new recycleBin("Papel");
    YellowTrash = new recycleBin("Metal");
    GreenTrash = new recycleBin("Vidro");
    
     //definição da localização X das Lixeiras
     redTrash.setLocationX((width/2 )   - 600 - (redTrash.getImage().getWidth(null)/2));
     GreenTrash.setLocationX((width/2)  - 200 - (GreenTrash.getImage().getWidth(null)/2));
     YellowTrash.setLocationX((width/2) + 200 - (YellowTrash.getImage().getWidth(null)/2));
     BlueTrash.setLocationX((width/2) + 500 - (BlueTrash.getImage().getWidth(null)/2));

    
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

    
    handleSelectedTrashText.setText(randomTrash.trashName, 192,width/2 + 70,35);

    timeText.setText(String.valueOf(timing), 60, 0 ,70);
    selectedBinText.setText(selectBin.getName(), 500, 0 ,32);
    trashQuantity.setText("restante: " + restingTrash, 240, 0 ,25);
    
    gameComboText.setTextCenter(false);
    gameScoreText.setTextCenter(false);
    trashQuantity.setColor("034400");
    
    
    // Metodos de desenhar na tela
    Draw(g);
    
    if(!gameEnd){
        gameScoreText.setText("Pontos :" + Score, 50, 40, 60);
        gameComboText.setText("Combo: " + combo, 100, 40, 50);

        character.draw(g);
        randomTrash.Draw(g);
        handleSelectedTrashText.Draw(g);
        timeText.Draw(g);
        //selectedBinText.Draw(g);
        trashQuantity.Draw(g);
        gameComboText.Draw(g);
        gameScoreText.Draw(g);
      
    }
    else if(gameEnd && !gameStart){
        
        gameScoreText.setText(String.valueOf(Score), height/2 - 70, width/2+20, 120);
        gameComboText.setText(String.valueOf(maxCombo), height/2 + 70, width/2+20, 120);
        
        gameComboText.Draw(g);
        gameScoreText.Draw(g);
    
    }
   
    

  
}



private void  Draw(Graphics g){
    //background
    g.drawImage(sky, 0, 0,width,sky.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(cloud,cloudX, 0,cloud.getWidth(getFocusCycleRootAncestor()),cloud.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(backgroundImage, 0, 0,width,height,null);
    
   

    
  if(gameStart){
    g.drawImage(StartGameScreen, width/2 - StartGameScreen.getWidth(null)/2, height/2 - StartGameScreen.getHeight(null)/2, StartGameScreen.getWidth(null), StartGameScreen.getHeight(null),null);
  }

    if(!gameEnd){
        g.drawImage(redTrash.getImage(), redTrash.getLocationX(), recycleBinYPosition,redTrash.getImage().getWidth(getFocusCycleRootAncestor()),redTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);
        g.drawImage(GreenTrash.getImage(), GreenTrash.getLocationX(), recycleBinYPosition,GreenTrash.getImage().getWidth(getFocusCycleRootAncestor()),GreenTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);
        g.drawImage(YellowTrash.getImage(), YellowTrash.getLocationX(), recycleBinYPosition,YellowTrash.getImage().getWidth(getFocusCycleRootAncestor()),YellowTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);
        g.drawImage(BlueTrash.getImage(), BlueTrash.getLocationX(), recycleBinYPosition,BlueTrash.getImage().getWidth(getFocusCycleRootAncestor()),BlueTrash.getImage().getHeight(getFocusCycleRootAncestor()),null);

        
        g.drawImage(timerForm, width/2 - (timerForm.getWidth(null)/2), -20, timerForm.getWidth(null),timerForm.getHeight(null),null);
        g.drawImage(selectedTrashBg, width/2 - (selectedTrashBg.getWidth(null)/2), 90, selectedTrashBg.getWidth(null),selectedTrashBg.getHeight(null),null);

        g.drawImage(arrow.getImage(), arrow.x, arrow.y, arrow.getImage().getWidth(getFocusCycleRootAncestor()),arrow.getImage().getHeight(getFocusCycleRootAncestor()),null);
    }

    

    if (gameEnd && !gameStart){
        g.drawImage(screenEndGame, width/2 - screenEndGame.getWidth(null)/2, height/2 - screenEndGame.getHeight(null)/2, screenEndGame.getWidth(null), screenEndGame.getHeight(null), getFocusCycleRootAncestor());
    }
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
        selectBin = BlueTrash;
            arrow.setLocationX(BlueTrash.getLocationX()+50);
            break;
        default:
            break;
    }
  
}

@Override
public void actionPerformed(ActionEvent e) {
    cloudLoop.start();

    if(timing <= 0 || restingTrash == 0){
        gameEnd = true;
        
    }
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
            timing +=1;
            restingTrash -= 1;
            combo +=1;

            if (combo >= 10){
                Score += 10;
            }
            else if (combo >= 20){
                Score += 20;
            }
            else{
                Score += 2;
            }
        }
        else{
            timing -=2;
            combo = 0;
        }

        if(maxCombo < combo || maxCombo == 0){
            maxCombo = combo;
        }
        
        randomTrash.setRandomTrash();

    }
    
    if(gameEnd){
        Score = 0;
        maxCombo = 0;
        combo = 0;
        gameEnd = false;

        timing = 15;
        restingTrash= 20;
        
        gameStart = false;

        gameLoop.start();
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
