public class U1 extends Rocket {

    public U1 () {
        cost = 100;
        weight = 10000;
        maxWeight = 18000;
        launchExplosion = 0.0;
        landingCrash = 0.0;
        currentWeight = weight;
    }

    public boolean launch() {

        int random = (int)(Math.random()*100 +1);
        this.launchExplosion = 5.0 * (this.currentWeight - this.weight) /
                (this.maxWeight - this.weight);

        return this.launchExplosion <= random;
    }

    public boolean land() {

        int random = (int)(Math.random() * 100 + 1);
        this.landingCrash = 1.0 * (this.currentWeight - this.weight) /
                (this.maxWeight - this.weight);
        return this.landingCrash <= random;
    }
}
