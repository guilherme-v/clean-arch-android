package br.curitiba.android.clean.mobile_ui.fake

import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.presentation.model.ProjectUI
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object FakeFactory {

    fun randomUuid(): String {
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

    fun makeProjectUI(): ProjectUI {
        return ProjectUI(
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomBoolean()
        )
    }

    fun makeProject(): Project {
        return Project(
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomBoolean()
        )
    }


    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}