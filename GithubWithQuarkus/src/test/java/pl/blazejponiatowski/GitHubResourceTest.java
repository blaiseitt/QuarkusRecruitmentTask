package pl.blazejponiatowski;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GitHubResourceTest {

    @Test
    public void testGetRepositories_HappyPath() {

        String username = "octocat";

        given()
                .when().get("/github/repos/" + username)
                .then()
                .statusCode(200)
                .body("$.size()", is(6))
                .body("[0].repositoryName", equalTo("git-consortium"))
                .body("[0].ownerLogin", equalTo(username))
                .body("[0].branches.size()", is(1))
                .body("[0].branches[0].name", equalTo("master"))
                .body("[0].branches[0].commit.sha", equalTo("b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e"))

                .body("[1].repositoryName", equalTo("hello-worId"))
                .body("[1].ownerLogin", equalTo(username))
                .body("[1].branches.size()", is(1))
                .body("[1].branches[0].name", equalTo("master"))
                .body("[1].branches[0].commit.sha", not(emptyOrNullString()))

                .body("[2].repositoryName", equalTo("Hello-World"))
                .body("[2].branches.size()", is(3))
                .body("[2].branches[0].name", equalTo("master"))
                .body("[2].branches[0].commit.sha", not(emptyOrNullString()))
                .body("[2].branches[1].name", equalTo("octocat-patch-1"))
                .body("[2].branches[1].commit.sha", not(emptyOrNullString()))
                .body("[2].branches[2].name", equalTo("test"))
                .body("[2].branches[2].commit.sha", not(emptyOrNullString()))

                .body("[3].repositoryName", equalTo("octocat.github.io"))
                .body("[3].branches.size()", is(2))
                .body("[3].branches[0].name", equalTo("gh-pages"))
                .body("[3].branches[0].commit.sha", not(emptyOrNullString()))
                .body("[3].branches[1].name", equalTo("master"))
                .body("[3].branches[1].commit.sha", not(emptyOrNullString()))

                .body("[4].repositoryName", equalTo("Spoon-Knife"))
                .body("[4].branches.size()", is(3))
                .body("[4].branches[0].name", equalTo("change-the-title"))
                .body("[4].branches[0].commit.sha", not(emptyOrNullString()))
                .body("[4].branches[1].name", equalTo("main"))
                .body("[4].branches[1].commit.sha", not(emptyOrNullString()))
                .body("[4].branches[2].name", equalTo("test-branch"))
                .body("[4].branches[2].commit.sha", not(emptyOrNullString()))

                .body("[5].repositoryName", equalTo("test-repo1"))
                .body("[5].branches.size()", is(1))
                .body("[5].branches[0].name", equalTo("gh-pages"))
                .body("[5].branches[0].commit.sha", not(emptyOrNullString()));
    }
}
