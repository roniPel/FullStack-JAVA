package Shapes;

import java.io.Serializable;

public abstract class Shape implements Serializable {
    private int x;
    private  int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Shape(int x1,int y1){
        x=x1;
        y=y1;
    }
    public void Print(){
        System.out.print("x="+x+" y="+y);
    }
}
