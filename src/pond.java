import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class pond {
    static Random r = new Random();
    public static int pondTemp = 70;
    public static ArrayList<Frog> genNewFrogs() {

        ArrayList<Frog> frogs = new ArrayList<Frog>(10);
        for (int i = 0; i <= 10; i++) {
            int gen = r.nextInt(2);
            char der;
            if (gen == 0) {
                der = 'F';
            }
            else {
                der = 'M';
            }
            Frog f = new Frog(r.nextInt(6), 1, r.nextInt(10) + 15, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)), r.nextInt(10)+90, der);
            frogs.add(f);
        }
        return frogs;
    }
    public static void main(String[] args) {
        ArrayList<Frog> startFrogs = genNewFrogs();
        for (Frog f : startFrogs) {
            System.out.println(f);
        }
    }
}
