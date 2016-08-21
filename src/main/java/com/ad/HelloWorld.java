package com.ad;

public class HelloWorld {

    private String printMessage;

    public String getPrintMessage() {
        return printMessage;
    }

    public void setPrintMessage(String printMessage) {
        this.printMessage = printMessage;
    }

    public void showMessage() {
        System.out.println(printMessage);
    }

}