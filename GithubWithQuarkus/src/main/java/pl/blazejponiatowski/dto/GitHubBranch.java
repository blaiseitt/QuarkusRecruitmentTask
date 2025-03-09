package pl.blazejponiatowski.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubBranch {

    private String name;
    private GitHubCommit commit;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("commit")
    public GitHubCommit getCommit() {
        return commit;
    }

    public void setCommit(GitHubCommit commit) {
        this.commit = commit;
    }
}
