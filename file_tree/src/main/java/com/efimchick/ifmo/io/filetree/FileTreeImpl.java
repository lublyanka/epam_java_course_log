package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;


public class FileTreeImpl implements FileTree {
    EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

    @Override
    public Optional<String> tree(Path path) {

        MyFileVisitor solution = new MyFileVisitor();

        if (path == null || !path.toFile().exists())
            return Optional.empty();

        try {
            Files.walkFileTree(path, options, 20, solution);

        } catch (IOException e) {
            return Optional.empty();
        }

        /*solution.structureList = solution.structureList.stream().sorted((x1, x2) -> {
            return x1.path.compareTo(x2.path);
           *//* if (x1.children == null && !(x2.children == null))
                return 1;
            if (!(x1.children == null) && x2.children == null)
                return -1;
            else return x1.filename.compareToIgnoreCase(x2.filename);*//*
        }).collect(Collectors.toCollection(LinkedList<TreeStructure>::new));*/
        //System.out.println("All links:");
        //System.out.println(printTree(solution.structureList, "", null));
        return Optional.of(buildHierarchy(solution.root,"", null));//printTree(solution.structureList, "", null).describeConstable();
    }


    private static String buildHierarchy(TreeStructure treeStructure, String depth, Boolean isLast){
        StringBuilder sb = new StringBuilder();


        if (!treeStructure.isDirectory) {
            return treeStructure.filename + " " + treeStructure.size + " bytes\n";
        }
        sb.append(treeStructure.filename + " " + treeStructure.size + " bytes\n");

        ArrayList<TreeStructure> childrenList = treeStructure.children.stream().sorted(FileTreeImpl::fileNameComparing).collect(Collectors.toCollection(ArrayList::new));

        String prefix = getPrefix(depth, isLast);

        for (int i = 0; i < childrenList.size(); i++) {
            isLast = i == childrenList.size() - 1;
            addFullPrefix(isLast, sb, prefix);
            if (childrenList.get(i).isDirectory) {
                sb.append(buildHierarchy(childrenList.get(i), prefix, isLast));
            } else {
                sb.append(childrenList.get(i).filename + " " + childrenList.get(i).size + " bytes\n");
            }
        }
        return sb.toString();
    }

    private static void addFullPrefix(Boolean isLast, StringBuilder sb, String pref) {
        sb.append(pref);
        if (isLast) {
            sb.append("└─ ");
        } else {
            sb.append("├─ ");
        }
    }

    private static String getPrefix(String depth, Boolean isLast) {
        return depth + (isLast == null ? "" : isLast ? "   " : "│  ");
    }

    private static int fileNameComparing(TreeStructure x1, TreeStructure x2) {
        if (x1.isDirectory && !x2.isDirectory)
            return -1;
        else if (!x1.isDirectory && x2.isDirectory)
            return 1;
        else return x1.filename.compareToIgnoreCase(x2.filename);
    }
    /*private String printTree(ArrayList<TreeStructure> treeStructure, String rootPrefix, Boolean isLast) {
        StringBuilder sb = new StringBuilder();
        if(treeStructure.size()==1)
        {
            return treeStructure.get(0).filename + " " + treeStructure.get(0).size + " bytes\n";
        }
        for (int i = 0; i < treeStructure.size(); i++) {
            String rootprefix = getPrefix(rootPrefix, isLast);
            sb.append(rootprefix);
            isLast = i == treeStructure.size() - 1;
            addFullPrefix(isLast, sb, rootprefix);
            sb.append(" ").append(treeStructure.get(i).filename)
                    .append(" ").append(treeStructure.get(i).size).append(" bytes")
                    .append("\n");
            if (treeStructure.get(i).children != null)
                printTree(treeStructure.get(i).children, rootprefix, isLast);

        }

        return sb.toString();
    }*/

    /*private static String getPrefix(String depth, Boolean isLast) {
        return depth + (isLast == null ? "" : isLast ? "   " : "│  ");
    }

    private static void addFullPrefix(Boolean isLast, StringBuilder sb, String pref) {
        sb.append(pref);
        if (isLast) {
            sb.append("└─ ");
        } else {
            sb.append("├─ ");
        }
    }*/
}