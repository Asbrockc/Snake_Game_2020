/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Lawrence Li
 * Section: 8am
 * Date: 4/16/20
 * Time: 11:44 PM
 *
 * Project: csci205_final_project_sp2020
 * Package: main
 * Class: Snake
 *
 * Description:
 * The Snake class
 * ****************************************
 */
package
        network;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Snake extends GameAsset {

    /** The x,y velocity and rotation angle of the Snake */
    private double velocityX;
    private double velocityY;
    private double angle;

    /** SnakeTails with references to its parents */
    private LinkedList<SnakeTail> snakeTails;

    public Snake() {
        super(15, Color.RED);
        snakeTails = new LinkedList<>();
    }

    public LinkedList<SnakeTail> getSnakeTails() { return snakeTails; }

    /** Add a tail */
    public GameAsset addTail() {
        SnakeTail snakeTail;
        if (snakeTails.size() == 0) {
            // The first SnakeTail attaches to the head
            snakeTail = new SnakeTail(this);
            snakeTail.id = 1;
        } else {
            // Other SnakeTail attach to its most recent SnakeTail
            snakeTail = new SnakeTail(snakeTails.getLast());
            snakeTail.id = snakeTails.getLast().id + 1;
        }
        snakeTails.add(snakeTail);
        return snakeTail;
    }

    /** Remove a tail */
    public GameAsset removeTail() {
        if (!(snakeTails.size() == 0 || snakeTails.size() == 1)) {
            return snakeTails.removeLast();
        }

        return null;
    }

    @Override
    public void updateAsset() {
        super.updateAsset();
        for (SnakeTail tail : snakeTails) {
            tail.updateAsset();
        }
    }
}