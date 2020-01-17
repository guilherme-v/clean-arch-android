package br.curitiba.android.clean.mobile_ui.views.bookmarked

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.curitiba.android.clean.mobile_ui.R
import br.curitiba.android.clean.presentation.model.ProjectUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class BookmarkedProjectsAdapter @Inject constructor() :
    RecyclerView.Adapter<BookmarkedProjectsAdapter.ViewHolder>() {

    var projectsList: List<ProjectUI> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_bookmarked_projects, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = projectsList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectsList[position]
        holder.ownerNameText.text = project.ownerName
        holder.projectNameText.text = project.fullName

        Glide.with(holder.itemView.context)
            .load(project.ownerAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.avatarImage)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView = view.findViewById(R.id.image_owner_avatar)
        var ownerNameText: TextView = view.findViewById(R.id.text_owner_name)
        var projectNameText: TextView = view.findViewById(R.id.text_project_name)
    }
}