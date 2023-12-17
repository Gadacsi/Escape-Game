/**
 * @author Kristof
 * Java Intermediate
 * Final Assignment(5)
 * December 19th 2023
 * Escape Game
 * version 2.0
 **/

import javax.swing.*;

/**
 * abstract class to the base of all entities
 */
public abstract class Entity {
    public ImageIcon imageIcon; // image of character
    public int mapX; // map horizontal location
    public int mapY; // map vertical location
    private char label; // label for character
    private int strength; // the hit damage caused by character
    private int health; // the amount of damage the character can take
    private int hunger;
    private int thirst;
    private int stepsTaken; // TODO: 2023-12-16 save score to text file

    /**
     * methods to set the instance variables
     */
    public void setLabel(char label) {
        this.label = label;
    }
    public char getLabel() {
        return this.label;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getStrength() {
        return this.strength;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return this.health;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    public int getHunger() {
        return this.hunger;
    }
    public void setThirst(int thirst) {
        this.thirst = thirst;
    }
    public int getThirst() {
        return this.thirst;
    }
    public void setStepsTaken(int stepsTaken) {
        this.stepsTaken = stepsTaken;
    }
    public int getStepsTaken() {
        return this.stepsTaken;
    }
}
