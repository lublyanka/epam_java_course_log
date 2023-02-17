package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {
    private boolean isReadyToComplete;
    private boolean isFinished;


    @Override
    public void execute() {
        if(isReadyToComplete)
            isFinished=true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void complete() {
        isReadyToComplete = true;
    }
}