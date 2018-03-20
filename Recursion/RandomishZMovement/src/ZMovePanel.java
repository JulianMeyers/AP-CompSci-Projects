import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZMovePanel extends JPanel {
    int screenWidth;
    int screenHeight;
    int[][] points;
    ArrayList<ArrayList<Integer>> zPointList = new ArrayList<>();

    public ZMovePanel(int inScreenWidth, int inScreenHeight) {
        super();
        screenWidth = inScreenWidth;
        screenHeight = inScreenHeight;
        points = generate3Points();
        //points = generate4Points();
        zPointList.add(generateRandomPointArrayList());
    }

    public void calculatePoints3() {
        zPointList.clear();
        zPointList.add(generateRandomPointArrayList());
        for (int i = 0; i<50000; i++) {
            zPointList.add(nextZPos3());
        }

    }

    public void calculatePoints4() {
        zPointList.clear();
        zPointList.add(generateRandomPointArrayList());
        for (int i = 0; i<50000; i++) {
            zPointList.add(nextZPos4());
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //calculatePoints3();
        calculatePoints3();
//        System.out.println("I got here");
//        System.out.println(points.length);
//        System.out.println(zPointList.size());
        g.setColor(Color.BLACK);
        for (ArrayList<Integer> point:zPointList){
//            System.out.println("X = " + point.get(0) + " Y = " + point.get(1));
            g.fillOval(point.get(0), point.get(1), 5, 5);
        }
        g.setColor(Color.BLUE);
        for (int[] point: points) {
//            System.out.println("X = " +point[0] + " Y = " + point[1]);
            g.fillRect(point[0],point[1],10,10);
        }
        //draw stuff here
    }

    //Add own stuff here
    public int[][] generate3Points() {
        int[][] points = {generateRandomPointArray(), generateRandomPointArray(), generateRandomPointArray()};
        return points;
    }

    public int[][] generate4Points() {
        int[][] points = {generateRandomPointArray(), generateRandomPointArray(), generateRandomPointArray(), generateRandomPointArray()};
        return points;
    }

    public int[] generateRandomPointArray() {
        int x0 = (int) (Math.random() * (screenWidth-5));
        int y0 = (int) (Math.random() * (screenHeight-5));
        int[] point = {x0, y0};
        return point;
    }

    public ArrayList<Integer> generateRandomPointArrayList() {
        int[] pointArray = generateRandomPointArray();
        ArrayList<Integer> point = new ArrayList<>();
        point.add(pointArray[0]);
        point.add(pointArray[1]);
        return point;
    }

    public ArrayList<Integer> nextZPos3() {
        int whichPoint = (int) (Math.random() * 3);
        int newX = (zPointList.get(zPointList.size()-1).get(0) + points[whichPoint][0])/2;
        int newY = (zPointList.get(zPointList.size()-1).get(1) + points[whichPoint][1])/2;
        ArrayList<Integer> point = new ArrayList<>();
        point.add(newX);
        point.add(newY);
        return point;
    }

    public ArrayList<Integer> nextZPos4() {
        int whichPoint = (int) (Math.random() * 4);
        int newX = (zPointList.get(zPointList.size()-1).get(0) + points[whichPoint][0])/2;
        int newY = (zPointList.get(zPointList.size()-1).get(1) + points[whichPoint][1])/2;
        ArrayList<Integer> point = new ArrayList<>();
        point.add(newX);
        point.add(newY);
        return point;
    }
}
