package com.efimchick.ifmo.io.filetree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import static java.nio.file.FileVisitResult.CONTINUE;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    public LinkedList<TreeStructure> structureList = new LinkedList<>();
    public TreeStructure root;

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attr) {
        TreeStructure newFile = new TreeStructure(file, attr.size());
        if (root == null)
            root = newFile;
        else {
            structureList.getLast().size += attr.size();
            structureList.getLast().children.add(newFile);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {

        TreeStructure newBranch = new TreeStructure(dir, new ArrayList<TreeStructure>(), 0, true);
        if (root == null)
            root = newBranch;
        else {
            TreeStructure parent = structureList.getLast();
            parent.children.add(newBranch);
        }
        structureList.addLast(newBranch);
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                              IOException exc) {
        TreeStructure current = structureList.removeLast();
        if (!structureList.isEmpty())
            structureList.getLast().size += current.size;

        return CONTINUE;
    }
}