package flowfield;

public class FlowFieldGrid {

    private Cell[][] grid;
    private int width;
    private int height;

    public FlowFieldGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                grid[y][x] = new Cell();
            }
        }
    }

    public DebugOverlay createDebugOverlay(int cellSize) {
        return new DebugOverlay(this, cellSize);
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
}
