/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorymanagementsimulator.frontend;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

/**
 *
 * @author josemanuque
 */
public class PageComponent extends JComponent{
    private int length;
    private int height;
    private int x;
    private int y;
    private Color c;



    public PageComponent(int length, int height) {
        this.length = length;
        this.height = height;
        this.x = x;
        this.y = y;
        this.c = Color.WHITE;
        setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, length, height);
    }

    public PageComponent(int x, int y, int length, int height) {
        this.length = length;
        this.height = height;
        this.x = x;
        this.y = y;
        this.c = Color.WHITE;
        setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, length, height);
    }
    
    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
    	return this.c;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (this.c == null){
            return;
        }
        g.setColor(this.c);
        g.fillRect(0, 0, length, height);
    }
}
