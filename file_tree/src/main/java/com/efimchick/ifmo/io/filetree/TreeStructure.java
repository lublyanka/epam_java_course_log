package com.efimchick.ifmo.io.filetree;

import java.nio.file.Path;
import java.util.ArrayList;

public class TreeStructure {
    final Path path;
    final String filename;
    ArrayList<TreeStructure> children;
    long size;
    boolean isDirectory;

    public TreeStructure(Path path, ArrayList<TreeStructure> children, long size, boolean isDirectory){
        this.path = path;
        this.filename = path.getFileName().toString();
        this.size = size;
        this.children = children;
        this.isDirectory = isDirectory;
    }

    public TreeStructure(Path path, long size){
        this.path = path;
        this.filename = path.getFileName().toString();
        this.size = size;
    }
}

