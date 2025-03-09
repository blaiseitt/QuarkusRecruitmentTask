# **GitHub Repository & Branch Fetcher API**

## **Overview**
This application is a **REST API** that interacts with GitHub's API to fetch repositories and their branches for a given user. It is built using **Quarkus** and leverages **Mutiny** for reactive programming.

### **Purpose**
- Fetch repositories for a given GitHub user.
- Filter out forked repositories.
- Retrieve branches for each repository.
- Return the data as a structured JSON response.
- Handle errors gracefully, such as non-existing users.

---

## How It Works (Step by Step)

**User sends a GET request** to:
   ```
   http://localhost:8080/github/repos/{username}
   ```
where `{username}` is a GitHub username.

 **The app calls GitHub's API** to fetch the user's repositories.

**Forked repositories are filtered out**, keeping only original repositories.

**For each repository**, another API call fetches its branches.

**The API responds with JSON**, structured as:
   ```json
   [
       {
           "repositoryName": "repo-name",
           "ownerLogin": "username",
           "branches": [
               {
                   "name": "branch-name",
                   "commit": {
                       "sha": "commit-sha"
                   }
               }
           ]
       }
   ]
   ```

**If the user does not exist**, an error response is returned:
   ```json
   {
       "status": 404,
       "message": "User not found"
   }
   ```

---

## **How to Run the App**

### **Prerequisites**
- Install [Java 17+](https://adoptium.net/)
- Install [Maven](https://maven.apache.org/)
- Have an active internet connection (to fetch data from GitHub API)

### **Start the Application**

1. **Clone the repository**
   ```sh
   git clone https://github.com/QuarkusRecruitmentTask.git
   cd QuarkusRecruitmentTask\GithubWithQuarkus
   ```

2. **Run the application using Quarkus**
   ```sh
   mvn quarkus:dev
   ```
   The app will start at:
   ```
   http://localhost:8080
   ```

---

## **Running Tests**

### **Run Tests**

1. **Execute tests with Maven**
   ```sh
   mvn test
   ```
---

## **Technologies Used**
- **Quarkus** (Java framework)
- **Mutiny** (Reactive programming)
- **REST Assured** (Testing)
- **GitHub REST API** (Data source)
- **Maven** (Dependency management)

---
