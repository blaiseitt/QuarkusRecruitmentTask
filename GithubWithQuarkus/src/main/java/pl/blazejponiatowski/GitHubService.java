package pl.blazejponiatowski;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pl.blazejponiatowski.dto.GitHubBranch;
import pl.blazejponiatowski.dto.GitHubRepository;

import java.util.List;

@RegisterRestClient
@Path("")
public interface GitHubService {

    @GET
    @Path("/users/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GitHubRepository>> getRepositories(@PathParam("username") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GitHubBranch>> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
