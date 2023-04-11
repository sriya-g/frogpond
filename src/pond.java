import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class pond {
    static Random r = new Random();
    public static int pondTemp = 70;
    public static int food = 600;
    public static ArrayList<Frog> pond = new ArrayList<Frog>(5);
    public static ArrayList<Frog> genNewFrogs() {

        ArrayList<Frog> frogs = new ArrayList<Frog>(10);
        for (int i = 0; i < 10; i++) {
            int gen = r.nextInt(2);
            char der;
            if (gen == 0) {
                der = 'F';
            }
            else {
                der = 'M';
            }
            Frog f = new Frog(r.nextInt(6)+1, 1, r.nextInt(10) + 15, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)), r.nextInt(10)+90, der);
            frogs.add(f);
        }
        return frogs;
    }
    public static String formatFrogs(ArrayList<Frog> list) {
        String frogs = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                frogs = frogs + (i+1)+". "+ list.get(i).toString() +"\n";
            }
        }
        return frogs;
    }
    public static void calcTurn() {
        boolean maleCanBreed = false;
        for (int i = 0; i < pond.size(); i++) {
            if (food > 0) {
                food = food - 5;
                pond.get(i).setWeight(pond.get(i).getWeight()+3);
            }
            pond.get(i).calcHealth(pondTemp);
            if (!pond.get(i).getIsAlive()) {
                pond.remove(i);
            }
        }
        for (int i = 0; i < pond.size(); i++) {
            if (pond.get(i) != null) {
                if (pond.get(i).getGender() == 'M' && pond.get(i).getCanBreed()) {
                    maleCanBreed = true;
                }
            }
        }
        for (int i = 0; i < pond.size(); i++) {
            if (pond.get(i) != null) {
                pond.get(i).setAge(pond.get(i).getAge()+1);
                if (pond.get(i).getCanBreed() && pond.get(i).getGender() == 'F') {
                    if (maleCanBreed) {
                        Frog maleToBreedWith = pond.get(r.nextInt(pond.size()));
                        while (maleToBreedWith.getGender() != 'M' && !maleToBreedWith.getCanBreed()) {
                            maleToBreedWith = pond.get(r.nextInt(pond.size()));
                        }
                        Frog newBaby = pond.get(i).breedWith(maleToBreedWith);
                        if (newBaby != null) {
                            pond.add(newBaby);
                        }
                    }
                }
            }
        }
        food = food + r.nextInt(15);
        pondTemp = pondTemp + r.nextInt(5)-2;
    }
    public static void main(String[] args) {
        ArrayList<Frog> startFrogs = genNewFrogs();
        JOptionPane.showMessageDialog(null, "Welcome to your new pond!!!");
        boolean isLegit = false;
        String[] frogNums = new String[5];
        while (!isLegit) {
            String adoptedFrogs = JOptionPane.showInputDialog("Here are the frogs you can choose from:\n" +formatFrogs(startFrogs)+"\nEnter the numbers of the 5 frogs you want below, separated by commas");
            frogNums = adoptedFrogs.split(",");
            while (frogNums.length != 5) {
                adoptedFrogs = JOptionPane.showInputDialog("Here are the frogs you can choose from:\n" +formatFrogs(startFrogs)+"\nEnter the numbers of the 5 frogs you want below, separated by commas");
                frogNums = adoptedFrogs.split(",");
            }
            int[] frogInts = new int[frogNums.length];
            for (int i = 0; i < frogNums.length; i++) {
                frogInts[i] = Integer.parseInt(frogNums[i].strip());
            }
            if (Arrays.stream(frogInts).max().getAsInt() <= 10) {
                isLegit = true;
            }
        }
        for (String s : frogNums) {
            pond.add(startFrogs.get(Integer.parseInt(s.strip())-1));
        }
        while(pond.size() > 0) {
            JOptionPane.showMessageDialog(null, "The current temperature is "+pondTemp+"\n"+"Current food available: "+food+"\n"+"Here are your frogs: \n"+formatFrogs(pond));
            calcTurn();
        }
        JOptionPane.showMessageDialog(null, "Sadly, all of your frogs have died. Game over.");
    }
}
