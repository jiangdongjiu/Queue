import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Maze {
    private Point startingPoint;
    private int rowLength;
    private int columnLength;
    private char[][] charMaze;
    private boolean isSearched = false;
    private Stack<Point> pathStack = new Stack<>();


    public Maze(String fileName){
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

            List<Integer> rowsAndColumns = new ArrayList<>();
            for (String s : lines.get(0).split(" +")) {
                Integer parseInt = Integer.parseInt(s);
                rowsAndColumns.add(parseInt);
            }

            rowLength = rowsAndColumns.get(0);
            columnLength = rowsAndColumns.get(1);

            List<Integer> startingPointRowColumn = Arrays.stream(lines.get(1).split(" +"))
                    .map(Integer::parseInt).collect(Collectors.toList());
            startingPoint = new Point(startingPointRowColumn.get(0), startingPointRowColumn.get(1));

            lines = lines.subList(2, lines.size());

            charMaze = new char[rowLength][];
            for (int i = 0; i < rowLength; i++){
                charMaze[i] = lines.get(i).toCharArray();
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public Maze(int startingRow, int startingColumn, char[][] existingMaze) {
        char startingChar = existingMaze[startingRow][startingColumn];
        if (startingChar == 'E' || startingChar == 'W') {
            throw new UnsupportedOperationException();
        }
        startingPoint = new Point(startingRow, startingColumn);
        charMaze = existingMaze;
        rowLength = existingMaze.length;
        columnLength = existingMaze[0].length;
    }

    public int getRowLength() {
        return this.rowLength;
    }

    public int getColumnLength() {
        return this.columnLength;
    }

    public Point getStartingPoint() {
        return this.startingPoint;
    }

    public char[][] getMaze() {
        return this.charMaze;
    }

    public String printMaze() {
        StringBuilder maze = new StringBuilder();
        for (char[] line : this.charMaze) {
            String lineStr = new String(line);
            maze.append(lineStr).append("\n");
        }
        return maze.toString().trim();
    }

    public String breadthFirstSearch() {
        String returnedValue = "";
        isSearched = true;
        if (charMaze[this.startingPoint.getRow()][this.startingPoint.getColumn()]  == 'E'){
            throw new UnsupportedOperationException();
        }
        else {
            charMaze[this.startingPoint.getRow()][this.startingPoint.getColumn()] = 'V';
        }

        Queue<Point> queue = new Queue<>();
        queue.enqueue(this.startingPoint);

        while (!queue.isEmpty() &&
                charMaze[queue.front().getRow()][queue.front().getColumn()] != 'E') {

            Point p = queue.front();
            charMaze[p.getRow()][p.getColumn()] = 'V';

            if (charMaze[p.getRow()+1][p.getColumn()] == ' ' ||
                    charMaze[p.getRow()+1][p.getColumn()] == 'E' ) {
                queue.enqueue(new Point(p.getRow()+1, p.getColumn(), p));
            }
            if (charMaze[p.getRow()][p.getColumn()+1] == ' ' ||
                    charMaze[p.getRow()][p.getColumn()+1] == 'E') {
                queue.enqueue(new Point(p.getRow(), p.getColumn()+1, p));
            }
            if (charMaze[p.getRow()][p.getColumn()-1] == ' ' ||
                    charMaze[p.getRow()][p.getColumn()-1] == 'E') {
                queue.enqueue(new Point(p.getRow(), p.getColumn()-1, p));
            }
            if (charMaze[p.getRow()-1][p.getColumn()] == ' ' ||
                    charMaze[p.getRow()-1][p.getColumn()] == 'E') {
                queue.enqueue(new Point(p.getRow()-1, p.getColumn(), p));
            }
            queue.dequeue();
        }

        if (queue.isEmpty()){
            returnedValue = "No exit found in maze!\n\n" + printMaze();
        }
        else {
            Point currentPoint = queue.front();
            pathStack.push(currentPoint);
            String path = currentPoint.toString();

            while (currentPoint.getParent() != null){
                pathStack.push(currentPoint.getParent());
                this.charMaze[currentPoint.getRow()][currentPoint.getColumn()] = '.';
                currentPoint = currentPoint.getParent();
                path = currentPoint.toString() + "\n" + path;
            }
            this.charMaze[startingPoint.getRow()][startingPoint.getColumn()] = '.';
            path += "\n";
            this.charMaze[queue.front().getRow()][queue.front().getColumn()] = 'E';

            returnedValue = "Path to follow from Start " + pathStack.getHead().getElement() +
                    " to Exit " + queue.front() + " - " + pathStack.getSize() + " steps:\n" +
                    path + printMaze();
        }

        return returnedValue;
    }

    public Stack<Point> getPathToFollow(){
        if (!this.isSearched) {
            throw new UnsupportedOperationException();
        }
        Stack<Point> returnedStack = null;

        try {
            returnedStack = (Stack<Point>) pathStack.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return returnedStack;
    }
}
