import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GeneticWalkerPanel extends JPanel implements GeneticWalkersConstants, MouseListener
{
	private GW_Cell[][] theGrid;
	private String[] walkerCommands;
	private int startR, startC, endR, endC;
	private double [] walkerScores;
	private int generationCount;
	private GeneticWalkerFrame myWindow;
	
	public GeneticWalkerPanel(GeneticWalkerFrame window)
	{
		super();
		myWindow = window;
		theGrid = new GW_Cell[NUM_ROWS][NUM_COLS];
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c] = new GW_Cell(r,c);
		walkerCommands = new String[NUM_WALKERS];
		walkerScores = new double[NUM_WALKERS];
		
		updateStart(NUM_ROWS/2,NUM_COLS/2);
		updateEnd(NUM_ROWS/4,NUM_COLS/4);
		
		addMouseListener(this);
		generationCount = 0;
	}
	/**
	 * change the value of the startR and startC variables, and update the grid to reflect this.
	 * @param r
	 * @param c
	 */
	public void updateStart(int r, int c)
	{
		theGrid[startR][startC].setStartLoc(false);
		startR =r;
		startC =c;
		theGrid[startR][startC].setStartLoc(true);
	}
	/**
	 * change the value of the endR and endC variables, and update the grid to reflect this.
	 * @param r
	 * @param c
	 */
	public void updateEnd(int r, int c)
	{
		theGrid[endR][endC].setFinishLoc(false);
		endR =r;
		endC =c;
		theGrid[endR][endC].setFinishLoc(true);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c].drawSelf(g);
	}
	/**
	 * select NUM_STEPS random numbers from 0-3, inclusive, for each of the NUM_WALKERS walkers.
	 */
	public void randomizeWalkers()
	{
		for (int w=0; w<NUM_WALKERS; w++)
		{
			walkerCommands[w] = "";
			for (int i=0; i<NUM_STEPS; i++)
				walkerCommands[w]+=(int)(Math.random()*4);
		}
		myWindow.updateLabels(walkerCommands);
	}
	/**
	 * resets the appearance of the grid.
	 */
	public void clearAllDots()
	{
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c].clearDots();
		repaint();
	}
	//====================================== Button Command Handlers ===============================
	public void doResetWalkers()
	{
		clearAllDots();
		randomizeWalkers();
		generationCount = 0;
	}
	// user just pressed the "Run N Generations" button.
	public void doNGenerations(int N)
	{
		for (int i=0; i<N; i++)
			doGeneration();
	}
	/**
	 * finds the trail for each of the Walkers, scores them, and performs genetic algorithm to
	 * prepare for next cycle.
	 */
	public void doGeneration()
	{
	    clearAllDots();
		parseWalkerStrings();
		doDarwin();
		generationCount++;
		repaint();
//		System.out.println("Done");
		
	}
	/**
	 * for each walker, starts at the start location and follows the commands in theWalker to follow
	 * it's path through the grid, then scores the result. If a walker encounters the end point, it
	 * finishes early.
	 */
	public void parseWalkerStrings()
	{
		for (int w=0; w<NUM_WALKERS; w++)
		{
			int r=startR;
			int c=startC;
			int wastedMoves = 0;
			theGrid[r][c].displayDotN(w);
			int steps = 0; // Note: "steps" will still be in scope after the loop is over.
            ArrayList<ArrayList<Integer>> placesBeen = new ArrayList<>(); //array of arrays consisting of r, c
			for (steps=0; steps<NUM_STEPS; steps++) {
                int bearing = Integer.parseInt(walkerCommands[w].substring(steps, steps + 1));
                switch (bearing) {
                    case NORTH:
                        if (r - 1 > -1) {
                            if (theGrid[r - 1][c].isBlocked()) {
                                wastedMoves++;
                            } else {
                                r -= 1;
                            }
                        }
                        break;// breaks out of "switch," not "for."
                    case EAST:
                        if (c + 1 < NUM_COLS) {
                            if (theGrid[r][c + 1].isBlocked()) {
                                wastedMoves++;
                            } else {
                                c += 1;
                            }
                        }
                        break;// breaks out of "switch," not "for."
                    case SOUTH:
                        if (r + 1 < NUM_ROWS) {
                            if (theGrid[r + 1][c].isBlocked()) {
                                wastedMoves++;
                            } else {
                                r += 1;
                            }
                        }
                        break;// breaks out of "switch," not "for."
                    case WEST:
                        if (c - 1 > -1) {
                            if (theGrid[r][c - 1].isBlocked()) {
                                wastedMoves++;
                            } else {
                                c -= 1;
                            }
                        }
                        break;// breaks out of "switch," not "for."
                }
                ArrayList<Integer> position = new ArrayList<>();
                position.add(r);
                position.add(c);
                placesBeen.add(position);
                // if this is one of the first four walkers (from the most successful parents),
                // display it on the grid as dots.
                if (w < 4)
                    theGrid[r][c].displayDotN(w);
                // if we are at the endpoint, exit the loop early.
                if (r == endR && c == endC)
                    break; // breaks out of "for."
            }
			
			// Score this run. We want the LOWEST score possible.
			// if we didn't reach the goal, then our score is a positive distance from where
			//     we finished to where the goal is.
			// However, if we did reach the goal (and left the loop early), the result is the 
			//     negative of the number of remaining steps we had when we exited the loop.
            int score;

			//Consider disabling, is supposed to add detection for wasting moves by returning to a previous position
            for (ArrayList<Integer> pos:placesBeen) {
                for (ArrayList<Integer> place:placesBeen) {
                    if (pos.get(0) == place.get(0) && pos.get(1) == place.get(1)) {
                        wastedMoves++;
                    }//Check to make sure isn't the same
                }
            }

            if (r != endR || c != endC) {
                //didn't get to end
                score = 5*((Math.abs(r-endR) + Math.abs(c-endC)) + wastedMoves);
            } else {
                //got to end
                score = 5*(steps - NUM_STEPS) + wastedMoves;
            }
            walkerScores[w] = score;
			
			//----------------------------------
		}
		myWindow.updateLabels(walkerCommands);
	}
	
	
	/**
	 * examines the scores for all the walkers and returns a list of the walker numbers, ranked by best -> worst.
	 * For example, suppose scores is [1.0, 2.0, 1.414, 4.0, 2.828, -2]... this would suggest that the rank array
	 * will return [5, 0, 2, 1, 4, 3]. That would mean that walker #5 was best, #0 was second best,
	 * etc... and #3 was the worst.
	 * @return - a list of walker numbers, ranked by scoring.
	 */
	public int[] rankWalkers()
	{
		// making a copy of the scores that we can mess with.
		double [] scoresCopy = new double[NUM_WALKERS];
		for (int i=0; i<NUM_WALKERS;i++)
			scoresCopy[i] = walkerScores[i];
        ArrayList<Integer> rankOfPos = new ArrayList<>();
		int[] ranks = new int[NUM_WALKERS];
		// my suggestion. find the lowest value in scorescopy, and put the number of which walker
		// had that number into the ranks list. Then, in scorescopy replace the best score with a 
		// ridiculously high number, so now the (previously) second-best number is the best.
		// Done
        double scoreMinVal = Double.MAX_VALUE;
        int scoreMinPos = 0;
		for (int walkers = 0; walkers<NUM_WALKERS; walkers++) {
            for (int i = 0; i < scoresCopy.length; i++) {
                if (scoresCopy[i] < scoreMinVal) {
                    scoreMinVal = scoresCopy[i];
                    scoreMinPos = i;
                }
            }
            scoresCopy[scoreMinPos] = Double.MAX_VALUE;
            scoreMinVal = Double.MAX_VALUE;
            rankOfPos.add(scoreMinPos);
        }
        for (int i = 0; i<rankOfPos.size(); i++) {
		    ranks[i] = rankOfPos.get(i);
        }
		//---------------------------------
		return ranks;
	
	}
	
	/**
	 * Based on the rankings of the walkers and the list of pairings in GENETIC_PAIRINGS, "mate" 
	 * various walkers with each other - they will each produce "fraternal twins," which you should
	 * add to the newWalkerList and then update the list of walkers on the screen.
	 */
	public void doDarwin()
	{
		int[] ranks = rankWalkers();
		String[] newWalkerList = new String[NUM_WALKERS];
        for (int i = 0; i < GENETIC_PAIRINGS.length*2; i+=2) {
            int walker1 = ranks[GENETIC_PAIRINGS[i/2][0]];
            int walker2 = ranks[GENETIC_PAIRINGS[i/2][1]];
            String[] result;
            result = haveSex(walker1, walker2);
            newWalkerList[i] = result[0];
            newWalkerList[i+1] = result[1];
        }
        //TODO: update the list of walkers on the screen
		
		//-------------------------------------
		myWindow.updateGeneration(generationCount);
//		System.out.println("---------------------------\nGeneration: "+generationCount+"\n------------------------------");
//		for (int w=0; w<NUM_WALKERS; w++)
//			System.out.println(newWalkerList[ranks[w]]+"\t"+walkerScores[ranks[w]]);
//		// -------------------------------------
		walkerCommands = newWalkerList; // now make this new list the starting point for the next generation.
	}
	
	/**
	 * take the commands for two walkers, randomly select a point in the middle and swap the second parts
	 * of the command lists to create two new command lists. 
	 * Here's an example, but I'm going to put it in terms of letters, not numbers: 
	 *   If you have commands    [ABCDEFG] and [abcdefg], 
	 *   you might wind up with  [ABCdefg] and [abcDEFG]. 
	 * 
	 * Additionally, if a 
	 * random 0-1 number is less than MUTATION_RATE, randomly change one command number in one child 
	 * with a random value 0-3, inclusive. (So we might, in fact get [abcDEhG].)
	 * 
	 * Here's an example with numbers:  [0101010101] and [2323232323] might make:
	 *                                  [0101012323] and [2023230101]
	 * (Note: the second child has a mutation in the second position.)
	 * 
	 * (Note 2: The extension to this activity has more you might do here.
	 * 
	 * @param consentingAdult1 - index of the first parent in the walkerCommands list
	 * @param consentingAdult2 - index of the second parent in the walkerCommands list
	 * @return an array with two commands.
	 */
	public String[] haveSex(int consentingAdult1, int consentingAdult2)
	{
		String[] result = new String[2];
		String adult1 = walkerCommands[consentingAdult1];
		String adult2 = walkerCommands[consentingAdult2];
		//Splitting into parts
        int splitA =  (int)(adult1.length()/2 + (Math.random()*3)-2);
//        int splitB =  (int)(adult2.length()/2 + (Math.random()*3)-2);
        String part1A, part1B, part2A, part2B;
        part1A = adult1.substring(0,splitA);
        part1B = adult1.substring(splitA);
        part2A = adult2.substring(0,splitA);//was B
        part2B = adult2.substring(splitA);//was B
        //Adding strings together
        result[0] = part1A + part2B;
        result[1] = part2A + part1B;
        //Random mutation
        if (Math.random()<MUTATION_RATE) {
            if ((int)(Math.random()*2)<1) {
                int randomPosition = (int) (Math.random()*(result[0].length()-1)+1);
                int randomValue = (int) (Math.random() * 4);
                String newResult = result[0].substring(0,randomPosition-1) + randomValue + result[0].substring(randomPosition);
                result[0] = newResult;
            } else {
                int randomPosition = (int) (Math.random()*(result[1].length()-1)+1);
                int randomValue = (int) (Math.random() * 4);
                String newResult = result[1].substring(0,randomPosition-1) + randomValue + result[1].substring(randomPosition);
                result[1] = newResult;
            }
        }

        //Aliens
        if ((int) Math.random()*10 == 1) {
            result[0] = "";
            for (int i=0; i<NUM_STEPS; i++)
                result[0]+=(int)(Math.random()*4);
        }


		//-------------------------
		return result;
	
	}
	// ==================================  MOUSE RESPONSE METHODS ===========================
	/**
	 * turns on or off the block at the location that the user clicked.
	 * @param mouseX
	 * @param mouseY
	 */
	public void toggleBlockAt(int mouseX, int mouseY)
	{
		int col = (mouseX-LEFT_MARGIN)/CELL_SIZE;
		int row = (mouseY-TOP_MARGIN)/CELL_SIZE;
		
		if (row>-1 && row<NUM_ROWS && col>-1 && col < NUM_COLS)
			if (theGrid[row][col].isBlocked())
				theGrid[row][col].setBlocked(false);
			else
				theGrid[row][col].setBlocked(true);
		repaint();
		
	
	}
	/**
	 * changes the location of the starting square to where the user clicked.
	 * @param mouseX
	 * @param mouseY
	 */
	public void setStartLoc(int mouseX, int mouseY)
	{
		int col = (mouseX-LEFT_MARGIN)/CELL_SIZE;
		int row = (mouseY-TOP_MARGIN)/CELL_SIZE;
		if (row>-1 && row<NUM_ROWS && col>-1 && col < NUM_COLS)
		{
			updateStart(row,col);
		}
		repaint();
	}

	/**
	 * changes the location of the ending square to where the user clicked.
	 * @param mouseX
	 * @param mouseY
	 */
	public void setEndLoc(int mouseX, int mouseY)
	{
		int col = (mouseX-LEFT_MARGIN)/CELL_SIZE;
		int row = (mouseY-TOP_MARGIN)/CELL_SIZE;
		if (row>-1 && row<NUM_ROWS && col>-1 && col < NUM_COLS)
		{
			updateEnd(row,col);
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (myWindow.isInPlayMode())
			return;
		int currentMode = myWindow.whichEditSubmode();
		if (currentMode == TOGGLE_BLOCK_MODE)
			toggleBlockAt(e.getX(),e.getY());
		if (currentMode == SET_START_MODE)
			setStartLoc(e.getX(),e.getY());
		if (currentMode == SET_END_MODE)
			setEndLoc(e.getX(),e.getY());
	}

	
	// ----------- required mouselistener events we aren't using.....
	@Override
	public void mouseClicked(MouseEvent e)
	{	
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	
}
