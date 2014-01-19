package grailscmd

import org.gradle.api.tasks.TaskAction

/**
 * This task is just a wrapper for invoking 'grails' commands through user's shell
 * with a given number of parameters
 *
 * @author @marioggar
 *
 */
class GrailsTask extends CommandLineTask {

    static final String COMMAND = 'grails'
    static final String GROUP = 'grails'

    GrailsTask() {
        this.group = GROUP
    }

    @TaskAction
    def executeTask() {
        new Executor().execute(COMMAND, args.split().toList())
    }

}
