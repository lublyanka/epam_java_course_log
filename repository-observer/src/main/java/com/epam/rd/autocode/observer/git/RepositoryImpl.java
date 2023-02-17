package com.epam.rd.autocode.observer.git;

import java.util.*;

class RepositoryImpl implements Repository {
    private final List<WebHook> webHookList;
    private final Map<String, Branch> branchList;



    public RepositoryImpl() {
        String[] defaultBranchNames = new String[]{"main"};
        branchList = new HashMap<>();
        webHookList = new ArrayList<>();
        Arrays.stream(defaultBranchNames).forEach(name -> branchList.put(name, new Branch(name)));
    }

    @Override
    public Branch getBranch(String name) {
        return branchList.getOrDefault(name, null);
    }

    @Override
    public Branch newBranch(Branch sourceBranch, String name) {
        boolean isNamePresent = branchList.getOrDefault(name, null) != null;
        boolean isRepoContainsBranch = !branchList.containsValue(sourceBranch);
        if (isNamePresent || isRepoContainsBranch)
            throw new IllegalArgumentException();
        Branch copyBranch = sourceBranch.getCopyWithName(name);
        branchList.put(name, copyBranch);
        return copyBranch;
    }

    @Override
    public Commit commit(Branch branch, String author, String[] changes) {
        Commit commit = branch.addCommit(new Commit(author, changes));
        List<Commit> commitList = new ArrayList<>();
        commitList.add(commit);
        Event event = new Event(Event.Type.COMMIT, branch, commitList);
        webHookList.forEach(webHook -> webHook.onEvent(event));
        return commit;
    }

    @Override
    public void merge(Branch sourceBranch, Branch targetBranch) {
        List<Commit> commitList = new ArrayList<>();
        Set<Commit> commitsList = targetBranch.getCommits();
        sourceBranch.getCommits().forEach(commit -> {
            if (!commitsList.contains(commit)) commitList.add(commit);
        });
        if (commitList.size() > 0) {
            commitList.forEach(targetBranch::addCommit);
            Event event = new Event(Event.Type.MERGE, targetBranch, commitList);
            webHookList.forEach(webHook -> webHook.onEvent(event));
        }
    }

    @Override
    public void addWebHook(WebHook webHook) {
        webHookList.add(webHook);
    }
}