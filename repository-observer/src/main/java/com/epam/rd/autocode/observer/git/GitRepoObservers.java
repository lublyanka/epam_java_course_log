package com.epam.rd.autocode.observer.git;

import com.epam.rd.autocode.observer.git.WebHooks.CommitToBranchWebHook;
import com.epam.rd.autocode.observer.git.WebHooks.MergeToBranchWebHook;

public class GitRepoObservers {
    public static Repository newRepository() {
        return new RepositoryImpl();
    }

    public static WebHook mergeToBranchWebHook(String branchName) {
        return new MergeToBranchWebHook(branchName);
    }

    public static WebHook commitToBranchWebHook(String branchName) {
        return new CommitToBranchWebHook(branchName);
    }
}
