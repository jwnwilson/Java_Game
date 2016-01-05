package com.jwnwilson.JavaGame;
import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int min = 10;
    private int max = 600;
    private int left;
    private int right;
    private int top;
    private int bottom;

    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;

    private int ySpeed;
    private int xSpeed;
    private int speed=10;

    public int x = 0;
    public int y = 0;
    public int width;
    public int height;
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 up, 1 left, 0 down, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        Random rnd = new Random();
        xSpeed = rnd.nextInt(speed) - 5;
        ySpeed = rnd.nextInt(speed) - 5;
        x = rnd.nextInt(max - min + 1) + min;
        y = rnd.nextInt(max - min + 1) + min;
    }

    private int getAnimationRow() {
        /*double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;*/
        int modx = Math.abs(xSpeed);
        int mody = Math.abs(ySpeed);
        int direction = 0;
        // if we're going more left or right
        if (modx > mody) {
            if (xSpeed > 0) {
                direction = 2;
            } else {
                direction = 1;
            }
        } else {
            if (ySpeed > 0) {
                direction = 0;
            } else {
                direction = 3;
            }
        }
        return direction;
    }

    private void update() {
        if (x > gameView.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > gameView.getHeight() - height - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public void collisionAction(){
        xSpeed *= -1;
        ySpeed *= -1;
    }

    public Rect getBounds() {
        return new Rect(x-width/2, y+height/2, x+width/2, y-height/2);
    }

    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }

    public boolean isCollition(Sprite s1) {
        return (Math.abs(x - s1.x) * 2 < (width + s1.width)) &&
                (Math.abs(y - s1.y) * 2 < (height + s1.height));
    }
}