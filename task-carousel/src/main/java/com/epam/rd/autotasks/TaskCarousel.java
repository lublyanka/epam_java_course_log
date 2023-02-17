package com.epam.rd.autotasks;

import java.util.Arrays;
import java.util.Objects;

public class TaskCarousel {
    private int nextTaskIndex;
    private final Task[] tasks;
    private int taskCount;

    public TaskCarousel(int capacity) {
        this.tasks = new Task[capacity];
        this.nextTaskIndex = 0;
        this.taskCount = 0;
    }

    public boolean addTask(Task task) {
        if (task == null)
            return false;
        else if (task.isFinished())
            return false;
        else if (this.isFull())
            return false;
        else {
            tasks[findNextFreeIndex()] = task;
            taskCount++;
        }
        return true;
    }

    public boolean execute() {
        if (isEmpty())
            return false;
        while (tasks[nextTaskIndex %= tasks.length] == null) {
            nextTaskIndex++;
        }
        tasks[nextTaskIndex].execute();
        if (tasks[nextTaskIndex].isFinished()) {
            tasks[nextTaskIndex] = null;
            taskCount--;
        }
        nextTaskIndex++;
        return true;
    }

    public boolean isFull() {
        return taskCount==tasks.length;
    }

    public boolean isEmpty() {
        return taskCount==0;
    }

    private int findNextFreeIndex() {
        int nextFreeIndex = -1;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                nextFreeIndex = i;
                break;
            }
        }
        return nextFreeIndex;
    }
}
