package com.uchennaebilah;

/**
 * Created by Uchenna on 3/18/2016.
 */
public class Toner {

    private int bwToner;
    private int colorToner;

    public Toner(int bwToner, int colorToner) {
        if (bwToner > -1 && bwToner <= 100 ) {
            this.bwToner = bwToner;
        }
        else {
            this.bwToner = -1;
        }

        if (colorToner > -1 && colorToner <= 100 ) {
            this.colorToner = colorToner;
        }
        else {
            this.colorToner = -1;
        }
    }

    public void refillBW(int amount)
    {
        if (amount > 0) {
            if ((100 - this.bwToner) <= amount)
                this.bwToner = 100;
            else if ((100 - this.bwToner) > amount)
                this.bwToner += amount;
        }

        System.out.println("Black and White toner refilled to " + checkBWToner() + "%");
    }

    public void refillColor(int amount)
    {
        if (amount > 0) {
            if ((100 - this.colorToner) < amount)
                this.colorToner = 100;
            else if ((100 - this.colorToner) > amount)
                this.colorToner += amount;
        }

        System.out.println("Color toner refilled to " + checkColorToner() + "%");
    }

    public int checkBWToner(){
        return this.bwToner;
    }

    public int checkColorToner(){
        return this.colorToner;
    }

    public void useBWToner()
    {
        this.bwToner -= 1;
    }

    public void useColorToner()
    {
        this.colorToner -= 1;
    }
}
