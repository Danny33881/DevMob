package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ActivityDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActivityDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            activity = it.getSerializable(ARG_ACTIVITY) as? Activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arrow = view.findViewById<ImageView>(R.id.icons_arrow)

        arrow.setOnClickListener(){
            parentFragmentManager.popBackStack()
        }

        val length: TextView = view.findViewById(R.id.length)
        val time: TextView = view.findViewById(R.id.time)
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val whenWas: TextView = view.findViewById(R.id.when_was)

        length.text = activity?.length
        time.text = activity?.time
        activityName.text = activity?.name
        whenWas.text = activity?.whenWas

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private const val ARG_ACTIVITY = "activity_arg"

        @JvmStatic
        fun newInstance(activity: Activity) =
            ActivityDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ACTIVITY, activity)
                }
            }
    }
}
