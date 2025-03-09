package pl.blazejponiatowski.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubOwner {

    private String login;

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
