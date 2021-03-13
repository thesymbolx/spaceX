package evans.dale.spacex.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import evans.dale.spacex.R
import evans.dale.spacex.databinding.MediaDialogBinding


class MediaDialog : DialogFragment(), View.OnClickListener {

    val args: MediaDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.filter_title))

            setView(MediaDialogBinding.inflate(layoutInflater).apply {
                onClick = this@MediaDialog
            }.root)
        }.create()

    override fun onClick(v: View?) {

        val myIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                when (v?.id) {
                    R.id.wiki -> args.wikiUrl
                    R.id.video -> args.videoUrl
                    R.id.article -> args.articleUrl
                    else -> args.articleUrl
                }
            )
        )
        startActivity(myIntent)
    }
}
