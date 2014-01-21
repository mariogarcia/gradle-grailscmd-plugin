package com.bitability.gradle.plugin.grails

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.bitability.gradle.plugin.command.CommandPlugin

/**
 *
 * @author @marioggar
 *
 */
class GrailsPlugin extends CommandPlugin {

    void apply(Project project) {

        def script = project.buildscript.classLoader.getResource('com/bitability/gradle/plugin/grails/commands.groovy').text
        def config = new ConfigSlurper().parse(script)

        createTasksFrom(project, config)
    }

}
