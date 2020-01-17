package br.curitiba.android.clean.mobile_ui.views.browse

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
import br.curitiba.android.clean.presentation.view.browse.BrowseProjectsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_browse_projects.*
import javax.inject.Inject

class BrowseProjectsFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseAdapter: BrowseAdapter

    private lateinit var viewModel: BrowseProjectsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browse_projects, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BrowseProjectsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBrowseRecycler()
        swipe.setOnRefreshListener { viewModel.loadProjects() }
        viewModel.itemsResource.observe(viewLifecycleOwner, Observer { handleItemsResource(it) })
    }

    private fun handleItemsResource(itemsRes: Resource<List<ProjectUI>>?) {
        if (itemsRes?.state == null) {
            return
        }

        if (itemsRes.state == ResourceState.LOADING && itemsRes.data.isNullOrEmpty()) {
            progress.visibility = View.VISIBLE
            return
        }
        else if (itemsRes.state == ResourceState.SUCCEEDED) {
            progress.visibility = View.GONE
            swipe.post { swipe.isRefreshing = false }

            browseAdapter.setItems(itemsRes.data ?: emptyList())
            browseAdapter.notifyDataSetChanged()
        }
    }

    private fun setupBrowseRecycler() {
        projects_recycler_view.layoutManager = LinearLayoutManager(context)

        browseAdapter = BrowseAdapter().apply {
            setListener(object : BrowseAdapter.ItemClickListener{
                override fun onClick(item: ProjectUI, pos: Int) {
                    if (item.isBookmarked) {
                        viewModel.unbookmarkProject(item.id)
                    } else {
                        viewModel.bookmarkProject(item.id)
                    }
                    notifyItemChanged(pos)
                }
            })
        }
        projects_recycler_view.adapter = browseAdapter
    }
}
