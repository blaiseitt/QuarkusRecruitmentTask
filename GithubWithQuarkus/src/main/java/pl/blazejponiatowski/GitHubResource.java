package pl.blazejponiatowski;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pl.blazejponiatowski.dto.ErrorResponse;
import pl.blazejponiatowski.dto.GitHubBranch;
import pl.blazejponiatowski.dto.GitHubRepository;
import pl.blazejponiatowski.dto.RepositoryResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/github")
public class GitHubResource {

    @Inject
    @RestClient
    GitHubService gitHubService;

    @GET
    @Path("/repos/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getRepositories(@PathParam("username") String username) {

        Uni<List<GitHubRepository>> repositories = gitHubService.getRepositories(username);

        return repositories.onItem().transformToUni(repos -> {
            if (repos == null || repos.isEmpty()) {
                return Uni.createFrom().item(Response.ok(Collections.emptyList()).build());
            }

            List<GitHubRepository> nonForkRepos = repos.stream()
                    .filter(repo -> !repo.isFork())
                    .collect(Collectors.toList());

            List<Uni<RepositoryResponse>> repoUnis = nonForkRepos.stream()
                    .map(this::getRepoBranches)
                    .collect(Collectors.toList());
            return Uni.combine().all().unis(repoUnis)
                    .with(repoResponses -> Response.ok(repoResponses).build());
        }).onFailure().recoverWithItem(e -> createErrorResponse("User not found"));
    }

    private Uni<RepositoryResponse> getRepoBranches(GitHubRepository repository) {
        String owner = repository.getOwner().getLogin();
        String repoName = repository.getName();

        Uni<List<GitHubBranch>> branches = gitHubService.getBranches(owner, repoName);
        return branches
                .onItem().transform(b -> new RepositoryResponse(repoName, owner, b))
                .onFailure().recoverWithItem(throwable -> new RepositoryResponse(repoName, owner, Collections.emptyList()));
    }

    private Response createErrorResponse(String message) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(404, message))
                .build();
    }

}
