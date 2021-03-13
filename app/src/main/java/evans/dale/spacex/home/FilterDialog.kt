package evans.dale.spacex.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import evans.dale.spacex.R
import evans.dale.spacex.databinding.FilterDialogBinding
import evans.dale.spacex.utils.Event
import evans.dale.spacex.utils.SharedEventsVM
import java.time.LocalDate

class FilterDialog : DialogFragment(), View.OnClickListener {

    private val sharedEventVM: SharedEventsVM by activityViewModels()

    private lateinit var spinner: Spinner

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.filter_title))

            setView(FilterDialogBinding.inflate(layoutInflater).apply {
                onClick = this@FilterDialog

                val years = List(LocalDate.now().year - 2005) { it + 2006 }

                val adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    years
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter

                this@FilterDialog.spinner = spinner
            }.root)
        }.create()

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.acs -> filter(SharedEventsVM.Sort.ASCENDING)
            R.id.desc -> filter(SharedEventsVM.Sort.DESCENDING)
        }
    }

    private fun filter(sort: SharedEventsVM.Sort) {
        val year = "${spinner.selectedItem}".toIntOrNull()
        year?.let {
            sharedEventVM.filterEvent.value = Event(year to sort)
        }
        this.dismiss()
    }
}
