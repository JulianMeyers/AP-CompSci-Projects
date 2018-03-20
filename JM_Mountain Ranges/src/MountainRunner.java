
public class MountainRunner {

	public static void main(String[] args) {
		MountainRange range1 = new MountainRange();
		MountainRange range2 = new MountainRange();
		MountainRange range3 = new MountainRange();
		Mountain mountain1 = new Mountain(5,5);
		Mountain mountain2 = new Mountain(8,4);
		Mountain mountainA = new Mountain(5,3);
		Mountain mountainB = new Mountain(9,4);
		Mountain mountainC = new Mountain(2,4);
		range3.addMountain(mountainA);
		range3.addMountain(mountainC);
		range2.addMountain(mountainA);
		range2.addMountain(mountainB);
		range1.addMountain(mountain1);
		range1.addMountain(mountain2);
		range1.simpleMap();
		range2.simpleMap();
		range3.simpleMap();
	}

}
