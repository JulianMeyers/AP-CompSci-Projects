/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sierpinskitriangle;

import java.awt.*;
import javax.swing.JPanel;

/**]
 *
 * @author harlan.howe
 */
public class SierpinskiPanel extends JPanel {
        private int myDepth;
        
        public SierpinskiPanel()
        {
            super();
            myDepth = 1;
        }
        
        public void setDepth(int depth)
        {
            if (depth>0)
                myDepth = depth;
            repaint();
            
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            //TODO - you write this (and any associated (hint: recursive) methods.)
            // Note: the paintComponent method is _not_ the recursive one. You can call
        	    // a recursive method from it, though.
            //Width 1000, height 800
            int offSet = (int) (350/(Math.sqrt(3)));
        	newDrawTriangle(g,myDepth,0,0,500, 710,980, 0);
        	    // temporary code - you'll want to replace this.
//        		g.drawString("Num levels to display: "+myDepth, 100, 100);
        		// Note: "myDepth" is the starting depth level, tied to the slider. 
        		// You should not change it. However, you can make a copy of it and change that.
        }

        private void drawTriangle(Graphics g,int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            if (depth == 1) {
                int[] xpoints = {x1, x2, x3};
                int[] ypoints = {y1, y2, y3};
                g.drawPolygon(xpoints, ypoints, 3);
            } else {
                lowerLeftTriangle(g,depth,x1,y1,x2,y2, x3, y3);
                lowerRightTriangle(g, depth, x1,y1, x2, y2, x3, y3);
                topTriangle(g, depth, x1,y1, x2, y2, x3, y3);
            }
        }

        private void lowerLeftTriangle (Graphics g, int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            int topRightX = (x1 + x2)/2;
            int topRightY = (y1 + y2)/2;
            int topLeftY = topRightY;
            int bottomY = y1;
            int topLength = (x3 - x2)/2;
            int bottomX = x1 - topLength;
            int topLeftX = topRightX - topLength;
            drawTriangle(g,depth-1, bottomX, bottomY, topLeftX, topLeftY, topRightX, topRightY);
        }

        private void lowerRightTriangle (Graphics g, int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            int topLeftX = (x1 + x3)/2;
            int topLeftY = (y1 + y3)/2;
            int topRightY = topLeftY;
            int bottomY = y1;
            int topLength = (x3 - x2)/2;
            int bottomX = x1 + topLength;
            int topRightX = topLeftX + topLength;
            drawTriangle(g,depth-1, bottomX, bottomY, topLeftX, topLeftY, topRightX, topRightY);
        }

        private void topTriangle (Graphics g, int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            int topLength = (x3 - x2)/2;
            int bottomX = (x3 + x2)/2;
            int bottomY = y2;
            int height = (y2-y1)/2;
            int topRightY = bottomY + height;
            int topLeftY = bottomY + height;
            int topRightX = bottomX - topLength/2;
            int topLeftX = bottomX + topLength/2;
            drawTriangle(g,depth-1, bottomX, bottomY, topLeftX, topLeftY, topRightX, topRightY);
        }

        private void newDrawTriangle (Graphics g,int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            if (depth == 1) {
                int[] xpoints = {x1, x2, x3};
                int[] ypoints = {y1, y2, y3};
                Color color = Color.BLUE;
                g.setColor(color);
                g.fillPolygon(xpoints, ypoints, 3);
            } else  {
                newLowerLeftTriangle(g,depth,x1,y1,x2,y2, x3, y3);
                newLowerRightTriangle(g, depth, x1,y1, x2, y2, x3, y3);
                newTopTriangle(g, depth, x1,y1, x2, y2, x3, y3);
            }
        }

        private void newLowerLeftTriangle (Graphics g,int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            int topX = (x1+x2)/2;
            int topY = (y1+y2)/2;
            int bottomLeftX = x1;
            int bottomLeftY = y1;
            int bottomRightX = (x1+x3)/2;
            int bottomRightY = y1;
            newDrawTriangle(g,depth-1,bottomLeftX,bottomLeftY,topX,topY,bottomRightX,bottomRightY);
        }

        private void newLowerRightTriangle (Graphics g,int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            int topX = (x2+x3)/2;
            int topY = (y2+y3)/2;
            int bottomLeftX = (x1+x3)/2;
            int bottomLeftY = y3;
            int bottomRightX = x3;
            int bottomRightY = y3;
            newDrawTriangle(g,depth-1,bottomLeftX,bottomLeftY,topX,topY,bottomRightX,bottomRightY);
        }

        private void newTopTriangle (Graphics g,int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
            int topX = x2;
            int topY = y2;
            int bottomLeftX = (x1+x2)/2;
            int bottomLeftY = (y1+y2)/2;
            int bottomRightX = (x2+x3)/2;
            int bottomRightY = (y2+y3)/2;
            newDrawTriangle(g,depth-1,bottomLeftX,bottomLeftY,topX,topY,bottomRightX,bottomRightY);
        }
}
