
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
    'war' {
        command = 'grails'
        args = 'prod war'
        group = 'Grails'
        description = 'Building WAR file for production'
    }
    'refresh-dependencies' {
        command = 'grails'
        args = 'refresh-dependencies'
        group = 'Grails'
        description = 'Refresh application dependencies'
    }
    'clean-app' {
        command = 'grails'
        args = 'clean'
        group = 'Grails'
        description = 'Cleaning Grails project'
    }
}
