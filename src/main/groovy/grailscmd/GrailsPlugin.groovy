package grailscmd

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This plugin exposes some of the Grails commands. This plugin doesnt pretend to
 * handle any dependencies but to serve as a way of integration a Grails application
 * in a Gradle Multimodule project.
 *
 * @author @marioggar
 *
 */
class GrailsPlugin implements Plugin<Project> {

    static final String TEST_FRAGMENT = 'test-app'

    void apply(Project project) {
       createTestingTasks(project)
       createRunAppTasks(project)
    }

    /**
     * This method creates Grails tasks for testing.
     *
     * @param project Gradle project
     */
    void createTestingTasks(Project project) {
        testingCommands.each { name, data ->
            project.task(type: GrailsTask, description: data.description, name) { task ->
                task.args = "$TEST_FRAGMENT ${data.args} ${project.properties.only ?: ''}"
            }
        }
    }

    /**
     * This method returns all information needed to create the testing Gradle tasks.
     *
     * @return A map with the testing tasks information
     */
    Map<String,String> getTestingCommands() {
        return [
            'unit': [
                description:'All unit tests',
                args:'unit:'
            ],
            'integration': [
                description:'All integration tests',
                args:'integration:'
            ]
        ]
    }

    /**
     * This method creates Grails tasks for running the application.
     *
     * @param project Gradle project
     */
    void createRunAppTasks(Project project) {
        project.task(type: GrailsTask, description: "Runs The application", 'run-app') { task ->
            task.args = "run"
        }
        project.task(type: GrailsTask, description: "Runs The application with the WAR file", 'run-war') { task ->
            task.args = "run-war"
        }
    }

}
