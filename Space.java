public class Space {
    private boolean hasMine;
    private boolean isCovered;
    private boolean isFlagged;
    private int adjacentMines;


    //constructor, does this need input parameters?
    public Space() {
        this.hasMine = false;
        this.isCovered = true;  
        this.isFlagged = false;
        this.adjacentMines = 0;
    }


    //getters and setters
    public boolean hasMine() {
        return hasMine;
    }


    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }


    public boolean isCovered() {
        return isCovered;
    }


    public void uncover() {
        this.isCovered = false;
    }


    public boolean isFlagged() {
        return isFlagged;
    }


    public void toggleFlag() {
        this.isFlagged = !this.isFlagged;
    }


    public int getAdjacentMines() {
        return adjacentMines;
    }


    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }


    //toString method


    public String toString(){
        if (isFlagged) {
            return  Color.CYAN + " >" + Color.RESET;
        }
        if (isCovered) {
            return ".";
        }
        if (hasMine) {
            return Color.RED + " *" + Color.RESET;
        }
        if (adjacentMines == 0) {
            return " ";
        }
        return Color.NUM[adjacentMines-1] + " " + adjacentMines + Color.RESET;
    }
}

