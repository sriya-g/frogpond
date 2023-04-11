import java.awt.*;
import java.util.Random;

public class Frog {
    static Random r = new Random();
    private int age; //age in minutes
    private int gen;//must be 1 or above
    private Color color; //RGB color
    private int health; //0 to 100, 0 = dead
    private boolean isAlive;
    private int weight; //ideal range depends on age
    private char gender; //"M" or "F"
    private boolean canBreed; //depends on gender and health
    public Frog() {
        this.age = 0;
        this.gen = 1;
        this.color = null;
        this.health = 0;
        this.isAlive = false;
        this.weight = 0;
        this.gender = 'M';
        this.canBreed = false;
    }
    public Frog(int age, int gen, int weight, Color color, int health, char gender) {
        this.age = age;
        this.gen = gen;
        this.weight = weight;
        this.color = color;
        this.health = health;
        this.isAlive = (calcHealth() > 0);
        this.gender = gender;
        this.canBreed = (health >= 70 && age > 3);
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }
    public int getGen() {
        return gen;
    }
    public void setGen(int gen) {
        if (gen > 1) {
            this.gen = gen;
        }
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if (health >= 0 && health <= 100) {
            this.health = health;
        }
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public void setWeight(int weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }
    public int getWeight() {
        return weight;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public char getGender() {
        return gender;
    }
    public int calcHealth() {
        if (age < 3 && (weight < 10 || weight > 30)) {
            health = health - 20;
        }
        if (age > 3 && (weight < 15 || weight > 40)) {
            health = health - 20;
        }
        if (age > 6) {
            health = health - 5*(age - 6);
        }
        return health;
    }
    public void calcHealth(int pondTemp) {
        if (age < 3 && (weight < 10 || weight > 30)) {
            health = health - 20;
        }
        if (age > 3 && (weight < 15 || weight > 40)) {
            health = health - 20;
        }
        if (age > 6) {
            health = health - 5*(age - 6);
        }
        if (pondTemp < 35) {
            health = health - Math.abs(pondTemp - 35);
        }
        if (pondTemp > 80) {
            health = health - (pondTemp - 80);
        }
        if ((canBreed == true && health < 70) || (canBreed == false && health > 70 && age > 3)) {
            canBreed = !canBreed;
        }
        if (health <= 0) {
            isAlive = false;
        }
    }
    public boolean getCanBreed() {
        return (health >= 70 && age > 3);
    }
    public String toString() {
        return "Gen: "+gen+" Age: "+age+" Color:"+color+" Weight: "+weight+" Gender: "+gender+" Health: "+health;
    }
    public Frog breedWith(Frog otherFrog) {
        Frog babyFrog = null;
        if (canBreed == true && otherFrog.getCanBreed() == true) {
            if ((gender == 'F' && otherFrog.getGender() == 'M') || (gender == 'M' && otherFrog.getGender() == 'F')) {
                babyFrog = new Frog();
                babyFrog.setGen(Math.max(gen, otherFrog.getGen())+1);
                int colNumb = r.nextInt(10);
                if (colNumb < 4) {
                    babyFrog.setColor(color);
                }
                else if (colNumb < 8) {
                    babyFrog.setColor(otherFrog.getColor());
                }
                else {
                    babyFrog.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                }
                babyFrog.setHealth((health+otherFrog.getHealth())/2);
                babyFrog.setIsAlive(true);
                babyFrog.setWeight(12);
                int g = r.nextInt(2);
                if (g == 1) {
                    babyFrog.setGender('F');
                }
                else {
                    babyFrog.setGender('M');
                }
            }
        }
        return babyFrog;
    }
}
