package com.advancedjava;
public class RunParallel implements Runnable {
    int threadNum = -1;
    RunParallel(int num){
        this.threadNum=num;

    }
    @Override
    public void run() {
        for (int x=0;x<3;x+=1){
            System.out.println("From "+ this.threadNum + " " + x);
        }
    }
}