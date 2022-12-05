package view;

import java.util.Timer;
import java.util.TimerTask;


class Task extends TimerTask {
    private long counter;
    public Task(long counter){
        this.counter = counter;
    }
    public void run(){
        counter--;
        System.out.println("Time left "+ counter);
    }
    public long getCount(){
        return counter;}
}

public class Time_left {
    private boolean running;
    private Task task;
    private Timer timer;

    public Time_left(){
        timer = new Timer(true);
    }

    public boolean isRinging(){ return running;}

    public void startRinging(long end){
        running = true;
        task = new Task(end);
        timer.scheduleAtFixedRate(task,0, 1000);
    }

    public void doIt(){
        running = false;
        System.out.println(task.getCount()+ "times");
        task.cancel();
    }

    public static void main(String args[]){
        long end = 3;
        Time_left timer = new Time_left();
        timer.startRinging(end);
        try {
            Thread.sleep((end-1)*1000);
        }
        catch (InterruptedException e){
        }
        timer.doIt();
    }
}