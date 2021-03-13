package evans.dale.spacex.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import evans.dale.spacex.R
import evans.dale.spacex.databinding.MediaDialogBinding
import evans.dale.spacex.utils.SharedEventsVM


class MediaDialog : DialogFragment(), View.OnClickListener {

    private val sharedEventVM: SharedEventsVM by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.filter_title))

            setView(MediaDialogBinding.inflate(layoutInflater).apply {
                onClick = this@MediaDialog
            }.root)
        }.create()

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.wiki -> {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://spaceflightnow.com/2020/06/30/spacex-launches-its-first-mission-for-u-s-space-force/"))
                startActivity(myIntent)
            }
        }
    }
}
