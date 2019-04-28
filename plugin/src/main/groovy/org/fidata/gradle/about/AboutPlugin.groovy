package org.fidata.gradle.about

import groovy.transform.CompileStatic
import org.fidata.about.About
import org.gradle.api.Plugin
import org.gradle.api.Project

@CompileStatic
class AboutPlugin implements Plugin<Project> {
  public static final String ABOUT_EXTENSION_NAME = 'about'

  @Override
  void apply(Project project) {
    final About about = About.readFromFile(project.file("${ project.name }.ABOUT"))

    project.extensions.add ABOUT_EXTENSION_NAME, about
  }
}
