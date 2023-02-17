package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

public class FileTreeImplDONE implements FileTree {
    public static void main(String[] args) throws IOException {
        String path = "src/test/resources/test8";
        String hierarchy = buildHierarchy(Path.of(path), "", null);
        System.out.println(hierarchy);
    }

    @Override
    public Optional<String> tree(Path path) {
        String hierarchy;
        if (path == null || !path.toFile().exists())
            return Optional.empty();
        try {
            hierarchy = buildHierarchy(path, "", null);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(hierarchy);
    }

    private static String buildHierarchy(Path path, String depth, Boolean isLast) throws IOException {
        StringBuilder sb = new StringBuilder();
        File directory = new File(path.toUri());
        if (directory.isFile()) {
            return directory.getName() + " " + directory.length() + " bytes\n";
        }
        long size = getDirectorySize(directory);
        sb.append(directory.getName() + " " + size + " bytes\n");
        File[] files = Arrays.stream(directory.listFiles()).sorted(FileTreeImplDONE::fileNameComparing).toArray(File[]::new);

        String prefix = getPrefix(depth, isLast);

        for (int i = 0; i < files.length; i++) {
            isLast = i == files.length - 1;
            addFullPrefix(isLast, sb, prefix);
            if (files[i].isDirectory()) {
                sb.append(buildHierarchy(Path.of(files[i].getCanonicalPath()), prefix, isLast));
            } else {
                sb.append(files[i].getName() + " " + files[i].length() + " bytes\n");
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

    private static int fileNameComparing(File x1, File x2) {
        if (x1.isDirectory() && x2.isFile())
            return -1;
        else if (x1.isFile() && x2.isDirectory())
            return 1;
        else return x1.getName().compareToIgnoreCase(x2.getName());
    }

    private static long getDirectorySize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length += getDirectorySize(file);
            }
        }
        return length;
    }
}