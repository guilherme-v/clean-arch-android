package br.curitiba.android.clean.mobile_ui.views.bookmarked

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.curitiba.android.clean.mobile_ui.R
import br.curitiba.android.clean.mobile_ui.injection.factory.ViewModelFactory
import br.curitiba.android.clean.presentation.model.ProjectUI
import br.curitiba.android.clean.presentation.resource.Resource
import br.curitiba.android.clean.presentation.resource.ResourceState
import br.curitiba.android.clean.presentation.view.bookmarked.BookmarkedProjectsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_bookmarked_projects.*
import javax.inject.Inject

class BookmarkedProjectsFragment : Fragment() {

    @Inject lateinit var adapter: BookmarkedProjectsAdapter
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BookmarkedProjectsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarked_projects, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookmarkedProjectsViewModel::class.java)
        viewModel.itemsResource.observe(viewLifecycleOwner, Observer { handleItemsResource(it) })
    }

    private fun handleItemsResource(itemsRes: Resource<List<ProjectUI>>?) {
        if (itemsRes == null) {
            return
        }

        progress.visibility = if (itemsRes.state == ResourceState.LOADING)
            View.VISIBLE else View.GONE

        adapter.projectsList = itemsRes.data ?: emptyList()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        recycler_projects.layoutManager = LinearLayoutManager(context)
        recycler_projects.adapter = adapter
    }
}
