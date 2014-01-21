package com.bitability.gradle.plugin.command

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * This task can be used to execute any type of command line.
 *
 * @author @marioggar
 */
class CommandTask extends DefaultTask {

    File baseDir
    String command
    String args

    @TaskAction
    def executeTask() {
        new Executor().execute(
            command,
            args.split().toList(),
            baseDir ?: path
        )
    }

}
