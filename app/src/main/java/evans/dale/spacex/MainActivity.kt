package evans.dale.spacex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import evans.dale.spacex.databinding.ActivityMainBinding
import evans.dale.spacex.service.LaunchInfo
import evans.dale.spacex.utils.ViewModelInjectors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityVM by viewModels {
        ViewModelInjectors.MainActivityInjector(
            application,
            (application as SpaceXApplication).appContainer.spaceXRepo
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)

        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel

            createRecycler(this)

//            viewModel?.launchesInfo?.observe(this@MainActivity) {
//                launchesAdapter.items = it
//                launchesAdapter.notifyDataSetChanged()
//            }
        }
        setContentView(binding.root)
    }

    private fun createRecycler(binding: ActivityMainBinding) = binding.apply {
        val launchesAdapter = LaunchAdapter(object : DiffUtil.ItemCallback<LaunchInfo>() {
            override fun areItemsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean {
                // Id is unique.
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean {
                return oldItem == newItem
            }
        })
        recycler.adapter = launchesAdapter
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel?.getLaunchItems()?.collectLatest { pagingData ->
                launchesAdapter.submitData(pagingData)
            }
        }
    }
}