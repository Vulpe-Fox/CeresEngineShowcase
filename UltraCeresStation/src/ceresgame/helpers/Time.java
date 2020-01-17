/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.helpers;

/**
 *
 * @author pintt3963
 */
public class Time {
    
    public long startTime;
    public long currentTime;
    private boolean firstTime = true;
    
    public Time(long startTime) {
        this.startTime = startTime;
    }
    
    public void updateTime() {
        if (this.firstTime) {
            this.startTime = System.currentTimeMillis();
            this.currentTime = System.currentTimeMillis();
            this.firstTime = false;
        } else {
            this.currentTime = System.currentTimeMillis();
            System.out.println(currentTime);
        }
    }
}
