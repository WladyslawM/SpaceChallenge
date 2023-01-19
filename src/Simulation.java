import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    public Simulation() {

    }

    ArrayList<Item> loadItems(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        ArrayList<Item> items = new ArrayList<>();

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] oneItem = line.split("=");
            items.add(new Item(oneItem[0], Integer.valueOf(oneItem[1])));
        }

        System.out.println(fileName + " contains " + items.size() + " items");

        return items;
    }

    ArrayList<Rocket> loadU1(ArrayList<Item> list) {
        ArrayList<Rocket> fleet = new ArrayList<>();
        Rocket r = new U1();

        int itemCounter = 1;
        int rocketCounter = 1;

        System.out.println("U1 Rocket weight = " + r.weight + "; maxWeight = "
        + r.maxWeight);

        for (Item i: list) {

            while (!r.canCarry(i)) {
                rocketCounter++;
                fleet.add(r);
                r = new U1();
            }
            r.carry(i);
            itemCounter++;
        }
        fleet.add(r);
        System.out.println("U1 fleet contains " + fleet.size() + " rockets");

        return fleet;
    }

    ArrayList<Rocket> loadU2(ArrayList<Item> list) {
        ArrayList<Rocket> fleet = new ArrayList<>();
        Rocket r = new U2();

        int itemCounter = 1;
        int rocketCounter = 1;

        System.out.println("U2 Rocket weight = " + r.weight + "; maxWeight = "
                + r.maxWeight);

        for (Item i : list) {

            while (!r.canCarry(i)) {
                rocketCounter++;
                fleet.add(r);
                r = new U2();
            }
            r.carry(i);
            itemCounter++;
        }
        fleet.add(r);
        System.out.println("U2 fleet contains " + fleet.size() + " rockets");

        return fleet;
    }

    int runSimulation(ArrayList<Rocket> list) {
        int num = 0;
        int indexSuccess = 1;

        for (Rocket r : list) {

            while (!r.launch()) {
                r.launch();
                num++;
            }

            while (!r.land()) {
                r.land();
                num++;
            }
            indexSuccess++;
        }
        int budget = list.get(0).cost * (list.size() + num);
        System.out.println(list.size() + " rockets and " + num + " extra " +
                "trials = " + (list.size() + num) + " in total");
        return budget;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Simulation simul = new Simulation();

        ArrayList<Item> phase1 = simul.loadItems("listItem1.txt");
        ArrayList<Item> phase2 = simul.loadItems("listItem2.txt");

        //Rocket U1
        ArrayList<Rocket> u1FleetPhase1 = simul.loadU1(phase1);
        ArrayList<Rocket> u1FleetPhase2 = simul.loadU1(phase2);

        System.out.println("U1 rocket cost = 100");
        int budgetU1phase1 = simul.runSimulation(u1FleetPhase1);
        System.out.println("Budget of U1 fleet for phase 1 = " +
                budgetU1phase1 + " (millions)");

        int budgetU1phase2 = simul.runSimulation(u1FleetPhase2);
        System.out.println("Budget of U1 fleet for phase 2 = " +
                budgetU1phase2 + " (millions)");

        System.out.println("Total budget of U1 fleet = " +
                (budgetU1phase1 + budgetU1phase2) + " (millions)");

        //Rocket U2
        ArrayList<Rocket> u2FleetPhase1 = simul.loadU2(phase1);
        ArrayList<Rocket> u2FleetPhase2 = simul.loadU2(phase2);

        System.out.println("U2 rocket cost = 120");
        int budgetU2phase1 = simul.runSimulation(u2FleetPhase1);
        System.out.println("Budget of U2 fleet for phase 1 = " +
                budgetU2phase1 + " (millions)");

        int budgetU2phase2 = simul.runSimulation(u2FleetPhase2);
        System.out.println("Budget of U2 fleet for phase 2 = " +
                budgetU2phase2 + " (millions)");

        System.out.println("Total budget of U1 fleet = " +
                (budgetU2phase1 + budgetU2phase2) + " (millions)");

    }

}
