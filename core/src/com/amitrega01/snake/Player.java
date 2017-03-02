package com.amitrega01.snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.amitrega01.snake.MyGame.HEIGHT;
import static com.amitrega01.snake.MyGame.WIDTH;
import static com.amitrega01.snake.MyGame.scl;

/**
 * Created by amitr on 02.03.2017.
 */

public class Player {
    public int playerX;
    public int playerY;
    public int dir;
    int[] tailX;
    int[] tailY;
    public int length;


    public Player(int x, int y) {
        this.playerX = x;
        this.playerY = y;
        length = 0;
        tailY = new int[100];
        tailX = new int[100];


    }

    public void setDir(int d) {
        dir = d;
    }

    public void addLength() {
        length++;

    }

    public void move() {
        int prevX = tailX[0];
        int prevY = tailY[0];
        int prevX2, prevY2;
        tailX[0] = playerX;
        tailY[0] = playerY;
        for (int i = 1; i <= length; i++) {
            prevX2 = tailX[i];
            prevY2 = tailY[i];
            tailX[i] = prevX;
            tailY[i] = prevY;
            prevX = prevX2;
            prevY = prevY2;
        }
        switch (dir) {
            case 0:
                break;
            case 1: //up
                playerY += scl;
                break;
            case 2: //down;
                playerY -= scl;
                break;
            case 3: //right
                playerX += scl;
                break;
            case 4: //left;
                playerX -= scl;
                break;
        }

            if (playerX < 0) playerX = WIDTH - scl;
            if (playerX > WIDTH - scl) playerX = 0;
            if (playerY < 0) playerY = HEIGHT - scl;
            if (playerY > HEIGHT - scl) playerY = 0;


    }
    public void draw(ShapeRenderer shape) {

        float dr = 1f / (length);
        float g = 0.384f +dr;
        float r = 0.173f +dr;
        shape.setColor(0.173f,0.384f,  0.878f, 1);
        shape.rect(playerX, playerY, scl, scl);//gracz


        for (int i = 0; i < length; i++) {//ogon
            shape.setColor(r,g,  0.878f, 1);
            shape.rect(tailX[i], tailY[i], scl, scl);
            r+=dr;
            g+=dr;
        }
    }
    public boolean checkCollision() {
        for (int i=0;i<length;i++) {
            if (tailX[i] == playerX && tailY[i] == playerY)  return false;
        }
        return true;
    }

}
