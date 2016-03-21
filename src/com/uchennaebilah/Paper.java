package com.uchennaebilah;

/**
 * Created by Uchenna on 3/18/2016.
 */
public class Paper {
    private int sheets;

    public Paper() {
        this.sheets = 250;
    }

    public void reload ()
    {
        this.sheets = 250;
        System.out.println("Paper reload to " + this.sheets + " sheets.");
    }

    public int checkPaper(){
        return this.sheets;
    }

    public void usePaper()
    {
        this.sheets -= 1;
    }
}
