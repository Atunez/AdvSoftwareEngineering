package Behavioural;

import java.util.ArrayList;

// Simulating Achievements from video games!

interface Achievements {
    abstract void update(String status);
}

class NoDmgTaken implements Achievements {
    @Override
    public void update(String status) {
        if(status.contains("health:100"))
            System.out.println("You took no damage!");
    }
}

class DiedOnBoss implements Achievements {
    @Override
    public void update(String status) {
        if(status.contains("died:100"))
            System.out.println("Its ok to fail :)");
    }
}


class DefeatedBoss {
    private ArrayList<Achievements> obs;

    public DefeatedBoss(){
        this.obs = new ArrayList<>();
        obs.add(new NoDmgTaken());
        obs.add(new DiedOnBoss());
    }

    public void killedBoss(String state){
        for(Achievements a : obs){
            a.update(state);
        }
    }
}

public class Observer {
    public static void main(String[] args) {
        DefeatedBoss boss = new DefeatedBoss();
        boss.killedBoss("health:100 died:99");
        boss.killedBoss("health:100 died:100");
    }
}
