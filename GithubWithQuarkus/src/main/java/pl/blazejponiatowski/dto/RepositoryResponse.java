package pl.blazejponiatowski.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RepositoryResponse {

    private String repositoryName;
    private String ownerLogin;
    private List<GitHubBranch> branches;

    public RepositoryResponse(String repositoryName, String ownerLogin, List<GitHubBranch> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    @JsonProperty("repositoryName")
    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @JsonProperty("ownerLogin")
    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    @JsonProperty("branches")
    public List<GitHubBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<GitHubBranch> branches) {
        this.branches = branches;
    }
}
