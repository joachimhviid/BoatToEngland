package flowfield;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DebugOverlay {
    private FlowFieldGrid grid;
    private boolean isVisible = false;
    private final double cellSize;
    private Canvas arrowCanvas;
    private GraphicsContext arrowGC;

    public DebugOverlay(FlowFieldGrid grid) {
        this.grid = grid;
        this.cellSize = grid.getCellSize();
        double width = grid.getWidth() * cellSize;
        double height = grid.getHeight() * cellSize;

        this.arrowCanvas = new Canvas(width, height);
        this.arrowGC = arrowCanvas.getGraphicsContext2D();
    }

    public void toggleVisibility() {
        isVisible = !isVisible;

        arrowCanvas.setVisible(isVisible);

        if (isVisible) {
            refreshArrows();
        }
    }

    private void drawArrowForCell(Cell cell, int x, int y) {
        Point2D direction = cell.getDirection().normalize();
        double scale = 1.7;

        double centerX = x * cellSize;
        double centerY = y * cellSize;

        double arrowLength = cellSize * scale * 0.2;
        double endX = centerX + direction.getX() * arrowLength;
        double endY = centerY + direction.getY() * arrowLength;

        arrowGC.strokeLine(centerX, centerY, endX, endY);

        double arrowHeadSize = cellSize * scale * 0.1;
        double angle = Math.atan2(direction.getY(), direction.getX());

        double x1 = endX + arrowHeadSize * Math.cos(angle - Math.PI / 2);
        double y1 = endY + arrowHeadSize * Math.sin(angle - Math.PI / 2);

        double x2 = endX + arrowHeadSize * Math.cos(angle + Math.PI / 2);
        double y2 = endY + arrowHeadSize * Math.sin(angle + Math.PI / 2);

        double x3 = endX + arrowHeadSize * Math.cos(angle);
        double y3 = endY + arrowHeadSize * Math.sin(angle);

        arrowGC.fillPolygon(new double[]{x1, x2, x3}, new double[]{y1, y2, y3}, 3);
    }

    public void refreshArrows() {
        arrowCanvas.setLayoutX(grid.getGridPosition().getX());
        arrowCanvas.setLayoutY(grid.getGridPosition().getY());

        arrowGC.clearRect(0, 0, arrowCanvas.getWidth(), arrowCanvas.getHeight());

        arrowGC.setLineWidth(2);
        arrowGC.setStroke(Color.GRAY);
        arrowGC.setFill(Color.GRAY);

        for(int y = 0; y < grid.getHeight(); y++) {
            for(int x = 0; x < grid.getWidth(); x++) {
                Cell cell = grid.getCell(x, y);
                drawArrowForCell(cell, x, y);
            }
        }

    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public Canvas getArrowCanvas() {
        return arrowCanvas;
    }
}
