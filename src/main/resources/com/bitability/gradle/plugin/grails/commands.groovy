
tasks {
    'unit' {
        command = 'grails'
        args = 'test-ap punit:'
        description = 'Runs all integration tests'
    }
    'integration' {
        command = 'grails'
        args = 'integration:'
        description = 'Runs all unit tests'
    }
}
