package Shapes;

public class Circle extends Shape{
    private  int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void Print(){
        System.out.println("Circle: x="+getX()+" y="+getY()+" radius="+getRadius());
    }
    public Circle(int x,int y,int r){
        super(x,y);
        setRadius(r);
    }
}
