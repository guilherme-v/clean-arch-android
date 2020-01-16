package br.curitiba.android.data.fake

import br.curitiba.android.data.model.ProjectData
import br.curitiba.android.domain.model.Project
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object FakeProjectFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

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


    fun makeProjectData(): ProjectData {
        return ProjectData(
            randomString(), randomString(), randomString(),
            randomString(), randomString(), randomString(),
            randomString(), randomBoolean()
        )
    }

    fun makeProjectDataList(size: Int): List<ProjectData> {
        val projects = mutableListOf<ProjectData>()
        repeat(size) {
            projects.add(makeProjectData())
        }
        return projects
    }


    fun makeProject(): Project {
        return Project(
            randomString(), randomString(), randomString(),
            randomString(), randomString(), randomString(),
            randomString(), randomBoolean()
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