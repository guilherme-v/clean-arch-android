package br.curitiba.android.clean.data.remote

import br.curitiba.android.clean.data.model.ProjectData
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectData>> 
}
