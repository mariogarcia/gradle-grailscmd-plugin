package com.bitability.gradle.plugin.frege

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.LanguageBasePlugin

class FregePlugin implements Plugin<Project> {

    void apply(Project project) {
        project.plugins.apply(BasePlugin)
        project.plugins.apply(LanguageBasePlugin)

    }

}
