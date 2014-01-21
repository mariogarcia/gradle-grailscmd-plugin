
tasks {
    'unit' {
        command = 'grails'
        args = 'test-app unit:'
        group = 'Grails'
        description = 'Runs all integration tests'
    }
    'integration' {
        command = 'grails'
        args = 'test-app integration:'
        group = 'Grails'
        description = 'Runs all unit tests'
    }
    'run-app' {
        command = 'grails'
        args = 'run-app'
        group = 'Grails'
        description = 'Running web app with embbeded Tomcat'
    }
}
