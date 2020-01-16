package br.curitiba.android.clean.domain.fake

import br.curitiba.android.clean.domain.model.Project
import java.util.*

object FakeProjectFactory {

    fun randomUUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(
            randomUUUID(), randomUUUID(), randomUUUID(),
            randomUUUID(), randomUUUID(), randomUUUID(),
            randomUUUID(), randomBoolean()
        )
    }

    fun makeProjectList(size: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(size) {
            projects.add(makeProject())
        }
        return projects
    }
}