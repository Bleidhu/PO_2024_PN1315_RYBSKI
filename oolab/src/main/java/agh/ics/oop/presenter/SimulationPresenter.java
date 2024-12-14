package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveslisttextfield;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Label infolabel;

    private int minY;
    private int maxY;
    private int minX;
    private int maxX;
    private int width;
    private int heigth;
    private int cellHeigth;
    private int cellWidth;


    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }

    private void updateStoredConstraints() {
        var boundaries = worldMap.getCurrentBounds();
        minY = boundaries.lowerLeft().getY();
        minX = boundaries.lowerLeft().getX();
        maxY = boundaries.upperRight().getY();
        maxX = boundaries.upperRight().getX();

        width = maxX - minX;
        heigth = maxY - minY;

        cellWidth = Math.round(300 / (width + 1));
        cellHeigth = Math.round(300 / (heigth + 1));


    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void setXYLabel() {
        var label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        mapGrid.getRowConstraints().add(new RowConstraints(cellHeigth));
    }

    private void setLabelsOx() {
        for (int i = 0; i < width + 1; ++i) {
            var label = new Label(String.format("%d", (minX + i)));
            mapGrid.add(label, i + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        }
    }

    private void setLabelsOy() {
        for (int i = 0; i < heigth + 1; ++i) {
            var label = new Label(String.format("%d", (maxY - i)));
            mapGrid.add(label, 0, i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(cellHeigth));
        }
    }

    private void addElementsToMap() {
        for (var element : worldMap.getElements()) {
            if (worldMap.isOccupied(element.getPosition())) {
                var label = new Label(element.toString());
                var pos = element.getPosition();
                mapGrid.add(label, pos.getX() - minX + 1, maxY - pos.getY() + 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }

    public void drawMap(String input) {
        clearGrid();
        updateStoredConstraints();
        setXYLabel();
        setLabelsOx();
        setLabelsOy();
        addElementsToMap();
        mapGrid.setPrefSize(600, 600);
        infolabel.setText(input);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(message);
        });

    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        try {
            List<MoveDirection> moves = OptionsParser.parseOptions(moveslisttextfield.getText().split(" "));
            List<Vector2d> positions = List.of(new Vector2d(1, 1));
            var grassField = new GrassField(2, 0);
            this.setWorldMap(grassField);
            grassField.addObserver(this);
            var simulation = new Simulation(positions, moves, grassField);
            var simulationEngine = new SimulationEngine(List.of(simulation));
            simulationEngine.runAsync();
        } catch (IllegalArgumentException e) {
            infolabel.setText("This moves combination is invalid");
        }

    }
}
