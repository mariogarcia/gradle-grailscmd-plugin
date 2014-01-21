package com.bitability.gradle.plugin.command

/**
 * An abstraction of the command line to be able to call commands and receive
 * feedback of the execution of those calls.
 *
 * @author @marioggar
 */
class Executor {

    /**
     * This method executes the command lines passed as first argument with
     * the arguments passed as second parameter.
     *
     * It returns an object with the execution result information
     *
     * @param command
     * @param arguments
     * @param baseDir
     *
     * @return
     *
     */
    Result execute(String command, List<String> arguments, File baseDir = new File('.')) {
        return executeOrLogException {
            execute(([] << command) + arguments, baseDir)
        }
    }

    /**
     * This method just executes a given closure. If something wrong happens
     * It only logs the exception's message and throws the exception again
     *
     * @param closure The code we want to execute
     * @return the closure's result
     */
    Result executeOrLogException(Closure closure) {
        try { return closure.call() } catch (e) { println e.message; throw e }
    }

    /**
     * This method executes the command and arguments passed as parameter
     *
     * @param arguments
     * @param baseDir
     *
     * @return
     *
     */
    Result execute(List<String> arguments, File baseDir) {
        return populateResultWithProcess(getProcessFor(arguments, baseDir))
    }

    /**
     * Don't want to use any assignment, so at most I'm using constructor population
     */
    Result populateResultWithProcess(Process prc) {
        new Result(succeed: prc.exitValue() == 0)
    }

    /**
     * This method builds a Process instance from the arguments
     * passed as parameter
     *
     * @param arguments
     * @param baseDir
     * @return a Process instance
     */
    Process getProcessFor(List<String> arguments, File baseDir) {
        return launchProcess(arguments, baseDir) { process ->
            process.waitFor()
        }
    }

    /**
     * This method executes the given closure with the process at hand and eventually
     * it returns the process
     *
     * @param process
     * @param baseDir
     * @param closure
     * @return the process
     */
    Process launchProcess(List<String> arguments, File baseDir, Closure closure) {
        return new ProcessBuilder(arguments).
            redirectOutput(ProcessBuilder.Redirect.INHERIT).
            directory(baseDir).
            start().with {
                closure.call(delegate())
                delegate
            }
    }

}
