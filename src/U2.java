public class U2 extends Rocket{
    public U2 () {
        cost = 120;
        weight = 18000;
        maxWeight = 29000;
        launchExplosion = 0.0;
        landingCrash = 0.0;
        currentWeight = weight;
    }

    public boolean launch() {

        int random = (int)(Math.random()*100 +1);
        this.launchExplosion = 4.0 * (this.currentWeight - this.weight) /
                (this.maxWeight - this.weight);

        return this.launchExplosion <= random;
    }

    public boolean land() {

        int random = (int)(Math.random() * 100 + 1);
        this.landingCrash = 8.0 * (this.currentWeight - this.weight) /
                (this.maxWeight - this.weight);
        return this.landingCrash <= random;
    }
}
