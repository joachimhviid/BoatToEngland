package flowfield;

import javafx.geometry.Point2D;

public class FlowFieldGrid {

    private Cell[][] grid;
    private int width;
    private int height;
    private double cellSize;
    private Point2D origin;

    public FlowFieldGrid(int width, int height, double cellSize, Point2D playerStartPosition) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.cellSize = cellSize;
        this.origin = new Point2D(
                playerStartPosition.getX() - (width * cellSize) / 2,
                playerStartPosition.getY() - (height * cellSize) / 2
        );
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
                ).add(origin);
                Point2D direction = playerPosition.subtract(cellPosition).normalize();
                cell.setDirection(direction);
            }
        }
    }

    public void centerGridOnPlayer(Point2D playerPosition) {
        double offSetX = playerPosition.getX() - (this.width * this.cellSize / 2);
        double offSetY = playerPosition.getY() - (this.height * this.cellSize / 2);

        this.origin = new Point2D(offSetX, offSetY);
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

    public Point2D getOrigin() {
        return origin;
    }
}
