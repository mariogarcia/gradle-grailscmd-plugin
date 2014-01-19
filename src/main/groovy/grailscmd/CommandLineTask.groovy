package grailscmd

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * This task can be used to execute any type of command line.
 *
 * @author @marioggar
 */
class CommandLineTask extends DefaultTask {

    String command
    String args

    @TaskAction
    def executeTask() {
        new Executor().execute(command, args.split().toList())
    }

}
