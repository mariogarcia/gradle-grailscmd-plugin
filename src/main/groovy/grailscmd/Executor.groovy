package grailscmd

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
     *
     * @return
     *
     */
    Result execute(String command, List<String> arguments) {
        return executeOrLogException { execute(([] << command) + arguments) }
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
     *
     * @return
     *
     */
    Result execute(List<String> arguments) {
        return populateResultWithProcess(getProcessFor(arguments))
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
     * @return a Process instance
     */
    Process getProcessFor(List<String> arguments) {

        return launchProcess(arguments) { process ->
            process.waitFor()
        }

    }

    /**
     * This method executes the given closure with the process at hand and eventually
     * it returns the process
     *
     * @param process
     * @param closure
     * @return the process
     */
    Process launchProcess(List<String> arguments, Closure closure) {

        return arguments.execute().with {
            consumeProcessOutput(System.out, System.err)
            closure.call(delegate)
            delegate
        }

    }

}
