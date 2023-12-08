package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;
import ci.dependencies.RunDeploymentStatus;
import ci.dependencies.RunTestStatus;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        RunTestStatus runTestStatus = this.runTests(project);
        RunDeploymentStatus runDeploymentStatus = this.runDeployment(project, runTestStatus);
        this.notify(runTestStatus, runDeploymentStatus);
    }

    private RunTestStatus runTests(Project project) {
        if (project.doesNotHaveTest()) {
            log.info("No tests");
            return RunTestStatus.SUCCEED;
        }

        if (project.runTestsSucceed()) {
            log.info("Tests passed");
            return RunTestStatus.SUCCEED;
        }

        log.error("Tests failed");
        return RunTestStatus.FAILED;
    }

    private RunDeploymentStatus runDeployment(Project project, RunTestStatus runTestStatus) {
        if (RunTestStatus.FAILED.equals(runTestStatus)) {
            return RunDeploymentStatus.FAILED;
        }

        if (project.runDeploymentSucceed()) {
            log.info("Deployment successful");
            return RunDeploymentStatus.SUCCEED;
        }

        log.error("Deployment failed");
        return RunDeploymentStatus.FAILED;
    }

    private void notify(RunTestStatus runTestStatus, RunDeploymentStatus runDeploymentStatus) {
        if (config.sendEmailSummary()) {
            sendNotification(runTestStatus, runDeploymentStatus);
            return;
        }

        log.info("Email disabled");
    }

    private void sendNotification(RunTestStatus runTestStatus, RunDeploymentStatus runDeploymentStatus) {
        log.info("Sending email");
        if (RunTestStatus.SUCCEED.equals(runTestStatus)) {
            String deploymentMessage = getDeploymentMessage(runDeploymentStatus);
            emailer.send(deploymentMessage);
            return;
        }

        emailer.send("Tests failed");
    }

    private String getDeploymentMessage(RunDeploymentStatus runDeploymentStatus) {
        return RunDeploymentStatus.SUCCEED.equals(runDeploymentStatus)
                ? "Deployment completed successfully"
                : "Deployment failed";
    }
}
