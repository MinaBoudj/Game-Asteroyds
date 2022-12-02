package view;
/*
 * @autor Maylis
*/

public class Time {
    private int second;

    public Time(int second){
        this.second = second;
    }

    public int getcurrentsec(){
        return second;
    }

    public int oneSecPassed(){
        return second++;
    }
}
