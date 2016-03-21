package com.uchennaebilah;

// HOW TO MAKE THIS PRINTER PRINT
// <instance>.pressPower() - turns on/off the Printer
// <instance>.printDocument() - prints the pages according to the values initialized for the object.
// <instance>.printedPages() - tells you how many pages have been printed so far
// <instance>.clearPrinter() - after responding to any printer error (like with a real printer),
//                             you must first clear the printer by pressing the button.
//                             This method represents that annoying blinking button.
// <instance>.refillBWToner(x) - Imagine you are Australian and you are forced to refill your printer's
//                               BW toner by pouring in new ink and not replacing the cartridge (like they
//                               do in good ol' USA). Refill the toner by a certain amount. PS: It caps at 100%.
// <instance>.refillColorToner(x) - Same as above but for color.
// <instance>.checkPrinter() - Check if your Printer is on or off
// <instance>.reloadPaper() - Reload your paper. 250sheets in a packet of paper.
// System.out.println(<instance>.checkPaper()) - See how much paper is left
// System.out.println(<instance>.checkBWToner()) - See how much BW Toner is left
// System.out.println(<instance>.checkColorToner()) - See how much Color Toner is left


public class Main {

    public static void main(String[] args) {
        Toner costcoBrand = new Toner(100,100);
        Paper staplesBrand = new Paper();

        //Printer(<tonerObject>,<paperObject>,<sheetsOfPaperLoaded>,<pagesToPrint>,<nameOfDocument>,<useBWink>,<enableDuplexPrinting>)

        // isDuplex is set to "true" in this instance of Printer
        // This means the printer prints on both sides of one sheet of paper.

        Printer xerox = new Printer(costcoBrand,staplesBrand,300,"Homework",true,true);

        xerox.checkPrinter();
        System.out.println("__________________________________");
    }
}
