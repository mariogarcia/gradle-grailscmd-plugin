gradle-grailscmd-plugin
=======================

A Gradle plugin to invoke Grails commands

Goal
----

The origin of this project was to integrate a Grails application in a bigger
Gradle multimodule application without having to move the Grails dependencies
to the build.gradle file.

We only wanted to use Gradle for continuos integration purposes but keep on using
Grails directly for development. There were a couple of reasons to do so:

- The actual grails/gradle plugin doesnt have a way to use interactive mode
- Everything takes longer than using Grails directly

Installation
------------

Because I haven't uploaded the binaries to any global repository yet, you should download
the source code and make your build.gradle script depend on this source code.

<pre>
    //build.gradle

    buildscript {
        repositories {

        }
        dependencies {

        }
    }

</pre>

Once you've declared plugin dependencies you can now apply the plugin:

<pre>

    apply plugin: 'grails'

</pre>

Now you can invoke 'gradle tasks' and check out the available 'Grails task' group and see
the available tasks.
