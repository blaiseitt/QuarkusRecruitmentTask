package pl.blazejponiatowski.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubCommit {

    private String sha;

    @JsonProperty("sha")
    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
