package flowfield;

import javafx.geometry.Point2D;

public class FlowFieldGrid {

    private Cell[][] grid;
    private int width;
    private int height;
    private double cellSize;

    public FlowFieldGrid(int width, int height, double cellSize) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.cellSize = cellSize;
        initializeGrid();
    }

    private void initializeGrid() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                grid[y][x] = new Cell();
            }
        }
    }

    //Used to update the direction vectors when the PLAYER_MOVED event is called
    public void updateField(Point2D playerPosition) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Cell cell = grid[y][x];
                Point2D cellPosition = new Point2D(
                        (x + 0.5) * cellSize,
                        (y + 0.5) * cellSize
                );
                Point2D direction = playerPosition.subtract(cellPosition).normalize();
                cell.setDirection(direction);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Cell getCell(int x, int y) {
        return this.grid[y][x];
    }

    public double getCellSize() {
        return this.cellSize;
    }
}
