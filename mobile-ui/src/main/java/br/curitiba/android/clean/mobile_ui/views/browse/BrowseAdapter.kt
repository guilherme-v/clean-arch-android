package br.curitiba.android.clean.mobile_ui.views.browse

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

class BrowseAdapter : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    private var items: List<ProjectUI> = emptyList()
    private var listener: ItemClickListener? = null

    interface ItemClickListener {
        fun onClick(item: ProjectUI, pos: Int)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView = view.findViewById(R.id.image_owner_avatar)
        var ownerNameText: TextView = view.findViewById(R.id.text_owner_name)
        var projectNameText: TextView = view.findViewById(R.id.text_project_name)
        var bookmarkedImage: ImageView = view.findViewById(R.id.image_bookmarked)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.ownerNameText.text = ownerName
            holder.projectNameText.text = fullName

            Glide.with(holder.itemView.context)
                .load(ownerAvatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)

            holder.view.setOnClickListener { listener?.onClick(this, position) }

            val starResource =
                if (isBookmarked) R.drawable.ic_star_black else R.drawable.ic_star_border_black
            holder.bookmarkedImage.setImageResource(starResource)
        }
    }

    fun setItems(items: List<ProjectUI>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }
}