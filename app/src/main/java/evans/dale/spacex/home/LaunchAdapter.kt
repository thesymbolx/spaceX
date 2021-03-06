package evans.dale.spacex.home

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import evans.dale.spacex.databinding.LaunchItemBinding
import evans.dale.spacex.service.LaunchInfo

class LaunchAdapter(
    diffCallback: DiffUtil.ItemCallback<LaunchInfo>,
    val application: Application,
    val itemClick: (LaunchInfo) -> Unit) :
        PagingDataAdapter<LaunchInfo, LaunchAdapter.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LaunchItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            getItem(position)?.apply {
                viewModel = LaunchVM(
                        missionName,
                        unixTimeStamp,
                        links.smallMissionPatch,
                        launchSuccess,
                        rocket.name,
                        rocket.type,
                        application)
            }
        }

        holder.binding.root.setOnClickListener {
            itemClick(snapshot().items[position])
        }
    }

    class ViewHolder(val binding: LaunchItemBinding) : RecyclerView.ViewHolder(binding.root)
}
