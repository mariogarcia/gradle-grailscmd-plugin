package com.bitability.gradle.plugin.command

import org.gradle.api.Plugin
import org.gradle.api.Project

class CommandPlugin implements Plugin<Project> {

    void apply(Project project) {
        parseCommandLineFile(project)
    }

    void parseCommandLineFile(final Project project) {
        def file = new File(project.props.cmdLineFile)
        def conf = new ConfigSlurper().parse(file)

        createTasksFrom(project, conf)
    }

    void createTasksFrom(Project project, Map configuration) {
        configuration.tasks.each { taskDefinition ->
            createTaskWith(project, taskDefinition)
        }
    }

    void createTaskWith(Project project, Object definition) {
        definition.with {
            project.task(
                type: CommandLineTask,
                description: definition.description,
                description.name) { task ->
                    if (baseDir) {
                        task.baseDir = baseDir
                    }
                    task.command = command
                    task.args = definition.
                    task.dependsOn = dependsOn
                    task.mustRunAfter = mustRunAfter
            }
        }
    }

}

