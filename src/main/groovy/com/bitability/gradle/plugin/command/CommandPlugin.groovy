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
        def k = definition.key
        def v = definition.value

        project.task(
            type: CommandTask,
            description: v.description,k) { task ->
                if (v.baseDir) {
                    task.baseDir = v.baseDir
                }
                if (v.group) {
                    task.group = v.group
                }
                task.command = v.command
                task.args = v.args
                task.dependsOn = initializeWithList(v.dependsOn)
                task.mustRunAfter = initializeWithList(v.mustRunAfter)
        }
    }

    List initializeWithList(Object possible) {
        return possible ?: []
    }

}

