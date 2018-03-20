import java.util.ArrayList;

public class MountainRange {
	private int width = 0;
	private ArrayList<Mountain> mountains = new ArrayList<Mountain>();
	MountainRange(){
		recalculateWidth();
	}
	
	public void addMountain(Mountain mountain) {
		mountains.add(mountain);
		recalculateWidth();
	}
	
	public int getNumMountains() {
		return mountains.size();
	}
	
	public Mountain tallestMountain() {
		int maxHeight = 0;
		Mountain maxMountain = null; 
		for (Mountain mountain:mountains) {
			if (mountain.getPeakHeight() > maxHeight) {
				maxHeight = mountain.getPeakHeight();
				maxMountain = mountain;
			}
		}
		return maxMountain;
	}
	
	public int heightAtLocation(int loc) {
		int height = 0;
		for (Mountain mountain:mountains) {
			if (mountain.heightAt(loc) > height)
				height = mountain.heightAt(loc);
		}
		return height;
	}

	public int altitudeVariance() {
	    int variance;
	    int max = tallestMountain().getPeakHeight();
	    int min = 0;
	    for (int i = 0; i<width; i++) {
	        if (heightAtLocation(i) < min) {
	            min = heightAtLocation(i);
            }
        }
        variance = max-min;
	    return variance;
    }

    public void simpleMap() {
	    for (int q = altitudeVariance(); q>0; q--) {
	        System.out.print(q);
	        for (int p=0; p<width; p++) {
	            System.out.print(calculateSymbol(q,p));
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i=0; i<width; i++) {
	        if (i<10) {
                System.out.print(i % 10);
            } else {
	            System.out.print(Math.floorDiv(i,10));
            }
        }
        System.out.println();
        System.out.print(" ");
        for (int i=-10; i<width-10; i++) {
            if (i<10 && i>=0) {
                System.out.print(i % 10);
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public String calculateSymbol(int row, int position) {
	    if (heightAtLocation(position) == row) {
	        return checkNeighbors(position);
        } else {
	        return " ";
        }
    }

    public String checkNeighbors(int position) {
	    int left = heightAtLocation(position - 1);
	    int center = heightAtLocation(position);
	    int right = heightAtLocation(position + 1);
	    String str = " ";
	    if (left<right) {
	        str = "/";
        }
        if (left>right) {
	        str = "\\";
        }
        if (center>left&&center>right) {
	        str = "A";
        }
        if (center<left&&center<right) {
	        str = "V";
        }
        if (center == left || center == right) {
	        str = "-";
        }
        if (position == 0) {
	        str = "/";
        }
        if (position == width-1) {
	        str = "\\";
        }
        return str;
    }

    public void recalculateWidth() {
	    int maxWidth = 0;
	    for (Mountain mountain:mountains) {
	        int currentWidth = mountain.getWidth();
	        if (currentWidth>maxWidth) {
	            maxWidth = currentWidth;
            }
        }
        width = maxWidth;
    }
}
