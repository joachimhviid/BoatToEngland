package flowfield;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class DebugOverlay {
    private FlowFieldGrid grid;
    private Group overlayGroup = new Group();
    private boolean isVisible = false;
    private final double cellSize;

    public DebugOverlay(FlowFieldGrid grid, int cellSize) {
        this.grid = grid;
        this.cellSize = cellSize;
    }

    public Node getOverlay() {
        return overlayGroup;
    }

    public void toggleVisibility() {
        isVisible = !isVisible;
        overlayGroup.setVisible(isVisible);
        if (isVisible) {
            drawOverlay();
        }
    }

    private void drawOverlay() {
        overlayGroup.getChildren().clear();

        for(int y = 0; y < grid.getHeight(); y++) {
            for(int x = 0; x < grid.getWidth(); x++) {
                Cell cell = grid.getCell(x, y);
                drawCell(cell, x, y);
            }
        }
    }

    private void drawCell(Cell cell, int x, int y) {
        //Old code, remember to delete later
//        Point2D direction = cell.getDirection();
//        Line line = new Line(
//                x * cellSize + cellSize / 2,
//                y * cellSize + cellSize / 2,
//                x * cellSize + cellSize / 2 + direction.getX() * 10,
//                y * cellSize + cellSize / 2 + direction.getY() * 10
//        );
//        line.setStrokeWidth(2);
//        line.setStroke(Color.GRAY);
//        overlayGroup.getChildren().add(line);

        Rectangle cellBorder = new Rectangle(
                x * cellSize,
                y * cellSize,
                cellSize,
                cellSize
        );
        cellBorder.setStroke(Color.GRAY);
        cellBorder.setFill(null);

        overlayGroup.getChildren().add(cellBorder);

        drawArrowForCell(cell, x, y);

    }

    private void drawArrowForCell(Cell cell, int x, int y) {
        Point2D direction = cell.getDirection().normalize();
        double scale = 2;

        double centerX = x * cellSize + cellSize * 0.5;
        double centerY = y * cellSize + cellSize * 0.5;

        double arrowLength = cellSize * scale;
        double endX = centerX + direction.getX() * arrowLength;
        double endY = centerY + direction.getY() * arrowLength;

        Line arrowBody = new Line(centerX, centerY, endX, endY);
        arrowBody.setStrokeWidth(2);
        arrowBody.setStroke(Color.GRAY);

        Polygon arrowHead = new Polygon();
        double arrowHeadSize = 2 * scale;
        double angle = Math.atan2(direction.getY(), direction.getX());

        double x1 = endX + arrowHeadSize * Math.cos(angle - Math.PI / 2);
        double y1 = endY + arrowHeadSize * Math.sin(angle - Math.PI / 2);

        double x2 = endX + arrowHeadSize * Math.cos(angle + Math.PI / 2);
        double y2 = endY + arrowHeadSize * Math.sin(angle + Math.PI / 2);

        double x3 = endX + arrowHeadSize * Math.cos(angle);
        double y3 = endY + arrowHeadSize * Math.sin(angle);

        arrowHead.getPoints().addAll(new Double[]{
                x1, y1,
                x2, y2,
                x3, y3
        });

        arrowHead.setFill(Color.GRAY);

        overlayGroup.getChildren().addAll(arrowBody, arrowHead);
    }
}
