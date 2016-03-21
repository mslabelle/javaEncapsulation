package com.uchennaebilah;

/**
 * Created by Uchenna on 3/18/2016.
 */
public class Printer {
    private Toner toner;
    private Paper paper;
    private boolean paperIssue = false;
    private boolean bwTonerIssue = false;
    private boolean colorTonerIssue = false;
    private int currentPage = 1;
    private int documentPages;
    private int printedPages = 0;
    private String documentName;
    private boolean isErrored = false;
    private boolean inBW;
    private boolean isOn = false;
    private boolean isDuplex;

    public Printer(Toner toner, Paper paper, int documentPages, String documentName, boolean inBW, boolean isDuplex) {
        this.toner = toner;
        this.paper = paper;
        this.documentPages = documentPages;
        this.documentName = documentName;
        this.inBW = inBW;
        this.isDuplex = isDuplex;
    }

    public void printDocument()
    {
        if (isOn)
        {
            if (!isErrored) {
                if (paper.checkPaper() > 0 && !inBW && toner.checkColorToner() > 0) {
                    System.out.println("Printing document of " + documentPages + " page(s) in Color.");
                    this.printingColor();
                }

                else if (paper.checkPaper() > 0 && inBW && toner.checkBWToner() > 0) {
                    System.out.println("Printing document of " + documentPages + " page(s) in Black and White.");
                    this.printingBW();
                }
            }

            else {
                assessPrinter();
                System.out.println("Press button to clear printer if all issues have been resolved.");
            }
        }

        else
            System.out.println("Printer is currently switched off!");
    }

    public void checkPrinter() {
        if (this.isOn){
            System.out.println("Computer: Printer is on");
        }
        else
            System.out.println("Computer: Printer is off");
    }

    public void pressPower()
    {
        if (!isOn) {
            isOn = true;
            System.out.println("Printer is now on.");
            assessPrinter();
        }
        else
        {
            isOn = false;
            System.out.println("Printer is now off.");
        }

    }

    public void reloadPaper(){
        paper.reload();
        assessPrinter();
    }

    public void refillColorToner(int amount){
        toner.refillColor(amount);
        assessPrinter();
    }

    public void refillBWToner(int amount){
        toner.refillBW(amount);
        assessPrinter();
    }

    public void clearPrinter(){
        this.pressButtonToClear();
    }

    public void printedPages(){
        System.out.println("Printed a total of " + printedPages + " pages to date.");
    }

    private void assessPrinter(){
        if (paper.checkPaper() == 0)
            System.out.println("No paper in the tray. Please load paper.");

        if (toner.checkBWToner() == 0)
            System.out.println("No BW toner. Please add a new cartridge.");

        if (toner.checkColorToner() == 0)
            System.out.println("No Color toner. Please add a new cartridge.");

        System.out.println("Assessment Complete.");
    }

    private void pressButtonToClear()
    {
        if (paperIssue && (paper.checkPaper() > 0)) {
            System.out.println("Paper issue cleared.");
            paperIssue = false;
        }

        if (bwTonerIssue && (toner.checkBWToner() > 0)) {
            System.out.println("Black and white toner issue cleared.");
            bwTonerIssue = false;
        }

        if (colorTonerIssue && (toner.checkColorToner() > 0)) {
            System.out.println("Color toner issues cleared.");
            colorTonerIssue = false;
        }

        if ((!paperIssue && !bwTonerIssue && inBW) || (!paperIssue && !colorTonerIssue && !inBW)) {
            System.out.println("All issues cleared.");
            isErrored = false;
            continuePrinting();
        }
    }

    private void printingBW()
    {
        if (!paperIssue && !bwTonerIssue) {
            boolean onSided = true;

            for (int i=currentPage;i<=documentPages;i++)
            {
                if (paper.checkPaper() == 0) {
                    paperIssue = true;
                    isErrored = true;
                    System.out.println("Out of paper. Cannot complete the print job. " + documentName + ": " + currentPage +"/"+documentPages + " page(s) printed.");
                    this.currentPage++;
                    break;
                }

                if (toner.checkBWToner() == 0) {

                    isErrored = true;
                    bwTonerIssue = true;
                    System.out.println("Out of Black and White toner. Cannot complete the print job. " + documentName + ": " + currentPage + "/" + documentPages + " page(s) printed.");
                    this.currentPage++;
                    break;
                }

                this.currentPage = i;

                toner.useBWToner();
                printOut(onSided);
                onSided = !onSided;
            }

            if (currentPage == documentPages) {
                System.out.println("Printing complete.");
                currentPage = 1;
            }
        }
    }

    private void printingColor()
    {
        if (!paperIssue && !colorTonerIssue) {
            boolean onSided = true;

            for (int i=currentPage;i<=documentPages;i++)
            {
                if (paper.checkPaper() == 0) {
                    paperIssue = true;
                    isErrored = true;
                    System.out.println("Out of paper. Cannot complete the print job. " + documentName + ": " + currentPage +"/"+documentPages + " page(s) printed.");
                    this.currentPage++;
                    break;
                }

                if (toner.checkColorToner() == 0) {
                    isErrored = true;
                    colorTonerIssue = true;
                    System.out.println("Out of Color toner. Cannot complete the print job. " + documentName + ": " + currentPage + "/" + documentPages + " page(s) printed.");
                    this.currentPage++;
                    break;
                }

                this.currentPage = i;

                toner.useColorToner();

                printOut(onSided);
                onSided = !onSided;


            }

            if (currentPage == documentPages) {
                System.out.println("Printing complete.");
                currentPage = 1;
            }
        }
    }

    private void printOut(boolean onSided)
    {
        if (isDuplex){
            if(onSided) {
                System.out.println("Printing page " + currentPage + "/" + documentPages + ".");
                paper.usePaper();
                this.printedPages++;
            }
            else
                System.out.println("Duplex Printing page " + currentPage + "/" + documentPages + ".");
        }

        else
        {
            System.out.println("Printing page " + currentPage + "/" + documentPages + ".");
            this.printedPages++;
            paper.usePaper();
        }
    }
    private void continuePrinting() {
        int remainingPages = documentPages-currentPage;

        if (inBW) {
            System.out.println("Printing remainder of document " + documentName + " (" + currentPage + "/" + documentPages + "):  " + remainingPages + " page(s) to be printed in Black and White.");
            printingBW();
        }
        else {
            System.out.println("Printing remainder of document " + documentName + " (" + currentPage + "/" + documentPages + "):  " + remainingPages + " page(s) to be printed in Color.");
            printingColor();
        }
    }

    public Toner getToner() {
        return toner;
    }

    public Paper getPaper() {
        return paper;
    }
}
