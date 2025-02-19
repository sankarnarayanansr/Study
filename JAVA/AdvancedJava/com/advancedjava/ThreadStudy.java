package com.advancedjava;

class ThreadStudy {
    public static void main(String[] args) {
        
        for (int x=0;x<3;x+=1){
            RunParallel rpl = new RunParallel(x);
            Thread th = new Thread(rpl);
            th.start();
            if (x==1){
            try {
                th.join(); //waits for execution
            } catch (InterruptedException ex) {
            }
        }

        }
    }
}