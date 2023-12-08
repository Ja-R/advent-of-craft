package ci.dependencies;

public class Project {
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    private final boolean buildsSuccessfully;
    private final TestStatus testStatus;

    private Project(boolean buildsSuccessfully, TestStatus testStatus) {
        this.buildsSuccessfully = buildsSuccessfully;
        this.testStatus = testStatus;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public boolean doesNotHaveTest() {
        return !this.hasTests();
    }

    public boolean runTestsSucceed() {
        return SUCCESS.equals(this.runTests());
    }

    public boolean runDeploymentSucceed() {
        return SUCCESS.equals(this.deploy());
    }

    private boolean hasTests() {
        return testStatus != TestStatus.NO_TESTS;
    }

    private String runTests() {
        return testStatus == TestStatus.PASSING_TESTS ? SUCCESS : FAILURE;
    }

    private String deploy() {
        return buildsSuccessfully ? SUCCESS : FAILURE;
    }

    public static class ProjectBuilder {
        private boolean buildsSuccessfully;
        private TestStatus testStatus;

        public ProjectBuilder setTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public ProjectBuilder setDeploysSuccessfully(boolean buildsSuccessfully) {
            this.buildsSuccessfully = buildsSuccessfully;
            return this;
        }

        public Project build() {
            return new Project(buildsSuccessfully, testStatus);
        }
    }
}
