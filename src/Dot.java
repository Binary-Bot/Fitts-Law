import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.Color.red;

public class Dot implements MouseListener {
    protected int radius;
    protected Color color;
    protected double x;
    protected double y;

    public Dot(Point center, int radius, Color color) {
        this.x = center.x;
        this.y = center.y;
        this.radius = radius;
        this.color = color;
    }

    public Point getCenter() {
        return new Point((int)x, (int)y);
    }

    public void setCenter(Point p){
        x = p.x;
        y = p.y;
    }

    public int getTop(){
        return (int) y -radius;
    }
    public int getBottom(){
        return (int) y + radius;
    }
    public int getLeft(){
        return (int) x - radius;
    }
    public int getRight(){
        return (int) x +radius;
    }

    public Rectangle getRegion(){
        return  new Rectangle(getTop(),getLeft(), 2*radius, 2 *radius);
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(getLeft(), getTop(), radius*2, radius*2);
        g.setColor(Color.BLACK);
        g.drawOval(getLeft(), getTop(), radius*2, radius*2);
    }

    public boolean isInside(Point p){
        Point center = new Point((int)x,(int)y);
        return p.distance(center)<radius;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
