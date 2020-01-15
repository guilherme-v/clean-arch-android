package br.curitiba.android.data.remote

import br.curitiba.android.data.model.ProjectData
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectData>> 
}
