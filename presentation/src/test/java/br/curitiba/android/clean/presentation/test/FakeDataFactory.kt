package br.curitiba.android.clean.presentation.test

import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.presentation.model.ProjectUI
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object FakeDataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProjectView() =
        ProjectUI(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomBoolean()
        )

    fun makeProject() =
        Project(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomBoolean()
        )

    fun makeProjectUI() =
        ProjectUI(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomBoolean()
        )

    fun makeProjectUIList(count: Int): List<ProjectUI> {
        val projects = mutableListOf<ProjectUI>()
        repeat(count) {
            projects.add(makeProjectView())
        }
        return projects
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}