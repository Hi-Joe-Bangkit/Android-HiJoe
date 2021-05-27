package id.capstone.hijoe.ui.onboarding.placeholder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.capstone.hijoe.databinding.FragmentSecondBoardingBinding
import id.capstone.hijoe.ui.onboarding.listener.OnClickBoardingListener

class SecondBoardingFragment : Fragment() {

    private lateinit var binding: FragmentSecondBoardingBinding

    private lateinit var mOnClickBoardingListener: OnClickBoardingListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnClickBoardingListener = context as OnClickBoardingListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${this.javaClass.simpleName} must implement OnClickBoardingListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSecondBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDoneBoarding.setOnClickListener {
            mOnClickBoardingListener.onButtonClicked(it)
        }
    }
}