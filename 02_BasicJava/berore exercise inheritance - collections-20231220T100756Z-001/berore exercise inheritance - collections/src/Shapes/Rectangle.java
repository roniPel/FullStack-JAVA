package Shapes;

public class Rectangle extends Shape{
    private int width,height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void Print(){
        System.out.print("Rectangle: ");
        super.Print();
        System.out.println(" width="+getWidth()+" height="+getHeight());
    }
    public Rectangle(int x,int y,int w,int h){
        super(x,y);
        setWidth(w);
        setHeight(h);
    }
}
