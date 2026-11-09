import java.util.ArrayList;

public class MazeSolver {

    public static boolean solveMaze(char[][] maze, int row, int col, ArrayList<String> path, MazeVisualizer visualizer) {

        // Your solution here
    	
    	//checking if we have reached the end of the maze and if we did 
    	if(maze[row][col] == 'E') {
    		return true; //return true
    	}
    	
    	//checking if the current position is invalid like if its out of bounds and if it is 
    	if(row <0 || row >= maze.length || col < 0 || col >= maze[0].length) {
    		return false; //return false
    	}
    	
    	//checking if its already visited (+) or the wall (#) and if it is 
    	if (maze[row][col] == '#' || maze[row][col] == '+') {
            return false; //return false
        }
    	
    	maze[row][col] = '+'; //mark current position as visited
    	
    	visualizer.repaint(); //we should up date visualization
    	 
    	//delay to make the animation much more smoother
    	try {
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		Thread.currentThread().interrupt();    	
    	}
    	
    	//now we try and move in all 4 possible directions; up, down, right, and left
    	
    	//here we try moving up which would mean row - 1
    	if(solveMaze(maze, row-1, col, path, visualizer)) {
    		path.add("up");
    		return true;
    	}
    	
    	//here we try moving down which would be row + 1
    	if(solveMaze(maze, row+1, col, path, visualizer)) {
    		path.add("down");
    		return true;
    	}
    	
    	//here we try moving left which would be col - 1
    	if(solveMaze(maze, row, col-1, path, visualizer)) {
    		path.add("left");
    		return true;
    	}
    	
    	//here we try moving right which would be col + 1
    	if(solveMaze(maze, row, col+1, path, visualizer)) {
    		path.add("right");
    		return true;
    	}
    	
    	maze[row][col] = ' '; //unmark here the current position where we change + back to ' '
    	
    	visualizer.repaint(); //we should up date visualization
    	
    	//delay to make the animation much more smoother
    	try {
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		Thread.currentThread().interrupt();
    	}
    	
    	return false; //return false to show that this path doesn't lead to solution
    }

    public static void main(String[] args) {
        Maze maze = new Maze("C:\\Users\\patel\\eclipse-workspace\\3assignment_maze_jbp\\src\\maze1txt"); // to change the maze you're solving, change this filename (maze1.txt, maze2.txt, maze3.txt, maze4.txt, or maze5.txt)
        maze.printMaze();

        ArrayList<String> path = new ArrayList<>();

        // create a frame to display the maze
        MazeVisualizer visualizer = new MazeVisualizer(maze.getMaze(), path);
        visualizer.display();

        if (solveMaze(maze.getMaze(), maze.getStartRow(), maze.getStartCol(), path, visualizer)) {
            System.out.println("Maze solved:");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.println(path.get(i));
            }
        } else {
            System.out.println("No solution found for the maze.");
        }
    }
}
