package se.hkr;

public class Tile {

    private boolean visited;

    public Tile () {
        visited = false;
    }

    public boolean getVisited () {
        return visited;
    }

    public void setVisited (boolean visited) {
        this.visited = visited;
    }

    public void flipVisited () {
        visited = !visited;
    }

    @Override
    public String toString (){
        return (visited ? "O" : "X") + "\t";
    }
}
