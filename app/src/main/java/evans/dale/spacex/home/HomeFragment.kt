package evans.dale.spacex.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import evans.dale.spacex.SpaceXApplication
import evans.dale.spacex.databinding.HomeFragmentBinding
import evans.dale.spacex.service.LaunchInfo
import evans.dale.spacex.utils.ViewModelInjectors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        ViewModelInjectors.HomeInjector(
            requireActivity().application,
            (requireActivity().application as SpaceXApplication).appContainer.spaceXRepo
        )
    }

    lateinit var launchesAdapter: LaunchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return HomeFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
            viewLifecycleOwner.lifecycle.addObserver(this@HomeFragment.viewModel)
            createRecycler(this)
        }.root
    }

    private fun createRecycler(binding: HomeFragmentBinding) = binding.apply {
        launchesAdapter = LaunchAdapter(object : DiffUtil.ItemCallback<LaunchInfo>() {
            override fun areItemsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean {
                return oldItem == newItem
            }
        }, requireActivity().application, this@HomeFragment::mediaClick)
        recycler.adapter = launchesAdapter
        recycler.layoutManager = LinearLayoutManager(context)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel?.getLaunchItems()?.collectLatest { pagingData ->
                launchesAdapter.submitData(pagingData)
            }
        }
        launchesAdapter.snapshot().items
    }

    private fun mediaClick(item: LaunchInfo) =
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMediaDialog(
                item.links.video,
                item.links.articleLink,
                item.links.wikipedia
            )
        )
}