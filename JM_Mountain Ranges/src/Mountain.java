
public class Mountain {
	private int position, peakHeight;
	Mountain(int inPos, int inHeight) {
		position = inPos;
		peakHeight = inHeight;
	}
	
	/**
	 * takes a x-pos and returns what the height should be
	 * @param loc
	 * @return height at that location
	 */
	public int heightAt(int loc) {
		int returnHeight = 0;
		int difference = Math.abs(position - loc);
		if (difference < peakHeight) {
			returnHeight = peakHeight - difference;
		}
		return returnHeight;
	}
	
	public int getWidth() {
		return peakHeight+position+1;
	}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPeakHeight() {
        return peakHeight;
    }

    public void setPeakHeight(int peakHeight) {
        this.peakHeight = peakHeight;
    }
}
