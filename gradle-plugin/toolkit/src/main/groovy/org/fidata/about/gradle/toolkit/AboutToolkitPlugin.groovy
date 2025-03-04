// SPDX-Copyright: ©  Basil Peace
// SPDX-License-Identifier: Apache-2.0
package org.fidata.about.gradle.toolkit

import static org.gradle.language.base.plugins.LifecycleBasePlugin.CHECK_TASK_NAME
import static org.gradle.language.base.plugins.LifecycleBasePlugin.VERIFICATION_GROUP
import static org.gradle.api.plugins.JavaBasePlugin.DOCUMENTATION_GROUP
import groovy.transform.CompileStatic
import org.fidata.about.gradle.AbstractAboutPlugin
import org.fidata.about.gradle.toolkit.tasks.AboutAttrib
import org.fidata.about.gradle.toolkit.tasks.AboutCheck
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider
import org.gradle.language.base.plugins.LifecycleBasePlugin

@CompileStatic
class AboutToolkitPlugin implements Plugin<Project> {
  /**
   * aboutCheck task name
   */
  static final String ABOUT_CHECK_TASK_NAME = 'aboutCheck'
  /**
   * aboutAttrib task name
   */
  static final String ABOUT_ATTRIB_TASK_NAME = 'aboutAttrib'

  @Override
  void apply(Project project) {
    project.plugins.apply AboutToolkitBasePlugin

    project.plugins.withType(AbstractAboutPlugin) { AbstractAboutPlugin aboutPlugin ->
      TaskProvider<AboutCheck> aboutCheckProvider = project.tasks.register(ABOUT_CHECK_TASK_NAME, AboutCheck) { AboutCheck aboutCheck ->
        aboutCheck.group = VERIFICATION_GROUP
        aboutCheck.description = ' Validates that the format of project\'s .ABOUT file is correct'
        aboutCheck.location.set aboutPlugin.aboutFile
        // TODO: configure: etc.
      }
      project.plugins.withType(LifecycleBasePlugin) {
        project.tasks.named(CHECK_TASK_NAME).configure { Task check ->
          check.dependsOn aboutCheckProvider
        }
      }
      TaskProvider<AboutAttrib> aboutAttribProvider = project.tasks.register(ABOUT_ATTRIB_TASK_NAME, AboutAttrib) { AboutAttrib aboutAttrib ->
        aboutAttrib.group = DOCUMENTATION_GROUP
        aboutAttrib.description = 'Generates an attribution document from project\'s .ABOUT file.'
        aboutAttrib.location.set aboutPlugin.aboutFile
        // TODO: configure: set output etc.
      }
    }
  }
}
