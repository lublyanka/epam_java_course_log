package com.epam.rd.autocode.observer.git;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Branch{

    private final String name;
    private final Set<Commit> commits;

    public Branch(final String name) {
        Objects.requireNonNull(name);
        this.commits = new HashSet<>();
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Branch branch = (Branch) o;
        return name.equals(branch.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<Commit> getCommits() {
        return commits;
    }

    protected Commit addCommit(Commit commit){
        commits.add(commit);
        return commit;
    }

    protected Branch getCopyWithName(String name){
        Branch branch = new Branch(name);
        branch.commits.addAll(commits);
        return branch;
    }

}
