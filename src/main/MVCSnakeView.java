/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Lawrence Li
 * Section: 8am
 * Date: 4/21/20
 * Time: 11:11 AM
 *
 * Project: csci205_final_project_sp2020
 * Package: main
 * Class: MVCSnakeView
 *
 * Description:
 * MVC snake view
 * ****************************************
 */
package
        main;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import static main.GameAsset.MyRotate.LEFT;
import static main.GameAsset.MyRotate.RIGHT;

/** The MVC snake view class */
public class MVCSnakeView{

    /** Main display window for the snake game */
    private Pane root;

    /** The MVC Snake Model */
    //private MVCSnakeModel theModel;

    /** Fixed width of the window */
    private final int WIDTH = 800;
    /** Fixed height of the window */
    private final int HEIGHT = 600;

    /** The player character */
    private GameAsset player;

    /** MVC Snake model */
    private MVCSnakeModel theModel;

    /**
     * initializes lists of items and the pane to a certain size
     *
     * @author Christopher Asbrock
     */
    public MVCSnakeView() {
        this.theModel = new MVCSnakeModel();
        this.root = new Pane();
        this.root.setStyle("-fx-background-color: dark;");
        this.root.setPrefSize(WIDTH, HEIGHT);

        makeSnake();
        setUpWalls();
    }

    /**
     * Creates and adds a snake (a object including a list of SnakeTail)
     */
    private void makeSnake() {
        this.player = new Snake();
        this.player.setVelocity(1,0);
        SnakeUtil.addToGame(this.root, this.player, this.WIDTH/4.0, this.HEIGHT/2.0);
    }

    /**
     * creates and adds a wall to the pane and list of walls
     * @param width - the width of the wall
     * @param height - the height of the wall
     * @param posX - the x position to place the wall
     * @param posY - the y position to place the wall
     */
    private void makeWall(double width, double height, double posX, double posY) {
        Rectangle wall = new Rectangle(width, height, Color.DARKRED);
        theModel.getListOfWalls().add(wall);
        SnakeUtil.addToGame(this.root, wall, posX ,posY);
    }

    /**
     * sets up 4 rectangles around the parameter of the pane to act as walls
     */
    private void setUpWalls() {
        makeWall(30, this.root.getPrefHeight(),0 ,0);
        makeWall(30, root.getPrefHeight(),root.getPrefWidth() - 30 ,0);
        makeWall(root.getPrefWidth(), 30,0 ,0);
        makeWall(root.getPrefWidth(), 30,0 ,root.getPrefHeight() - 30);
    }

    /**
     * when colliding with an Item this determines the instance type and adds or removes pieces of the snake
     * accordingly
     *
     * @author Christopher Asbrock
     *
     * @param item - the idem being collided with
     */
    private void handleItemCollision(GameAsset item)
    {
        if (item instanceof Potion) {
            for (int i = 0; i < theModel.getPotionLength(); i++)
                this.root.getChildren().add(((Snake) this.player).addTail());
        } else if (item instanceof Poison) {
            for (int i = 0; i < theModel.getPoisonLength(); i++)
                this.root.getChildren().removeAll(((Snake) player).removeTail());
        } else {
            for (int i = 0; i < theModel.getFoodLength(); i++)
                this.root.getChildren().add(((Snake) this.player).addTail());
        }
    }

    /**
     * updates the player based on whether they are active or not,
     * if they are it will update them and their pieces,
     * if not it will remove them from the game
     */
    private void updatePlayer()
    {
        if (this.player.isNoLongerActive())
        {
            this.root.getChildren().removeAll(((Snake) this.player).getSnakeTails());
            ((Snake) this.player).getSnakeTails().clear();
            this.root.getChildren().removeAll(this.player);
            this.player = null;
        }
        else
        {
            this.player.updateAsset();
        }
    }

    /**
     * UpdateView when called by the controller will use the corresponding list of coordinates in
     * controller to update the size of this classes SnakeTails list to the same size,
     * then update the positioning of each tail piece based on the presented data.
     */
    private void updateView() {
        //TODO - updateView
    }

    public Pane getRoot() { return root; }

    public int getWIDTH() { return WIDTH; }

    public int getHEIGHT() { return HEIGHT; }

    public GameAsset getPlayer() { return player; }
}