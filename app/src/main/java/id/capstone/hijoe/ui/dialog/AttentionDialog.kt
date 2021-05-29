package id.capstone.hijoe.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.capstone.hijoe.abstraction.BaseDialogFragment
import id.capstone.hijoe.databinding.FragmentDialogAttentionBinding
import id.capstone.hijoe.util.extension.viewLifecycle

class AttentionDialog(
        private val params: Params
) : BaseDialogFragment() {

    private var binding: FragmentDialogAttentionBinding by viewLifecycle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDialogAttentionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewCreated() {
        isCancelable = false

        with(binding) {
            tvAttentionTitle.text = params.title
            tvAttentionContent.text = params.content
            btnAttentionDone.setOnClickListener { dismiss() }
        }
    }

    data class Params(
            val title: String,
            val content: String
    )

}