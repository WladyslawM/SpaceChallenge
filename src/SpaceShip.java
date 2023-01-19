
    public interface SpaceShip {
        boolean launch(); //return tru or false

        boolean land();

        boolean canCarry(Item item);

        int carry(Item item);
    }

