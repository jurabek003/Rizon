package uz.turgunboyevjurabek.rizon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.UserOrderAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentOrderBinding
import uz.turgunboyevjurabek.rizon.madels.userOrders.Orders

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    private val binding by lazy { FragmentOrderBinding.inflate(layoutInflater) }
    private lateinit var userOrderAdapter: UserOrderAdapter
    private lateinit var list: ArrayList<Orders>
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Bu yerda shunchaki testlash uchun resakle viewga list berib ushlatib ko'rdim
        list= ArrayList()
        list.addAll(listOf(Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),
            Orders(R.drawable.argenta,"Argeta 100ml",100,200000),))

        userOrderAdapter=UserOrderAdapter(list)
        binding.rvUsersOrders.adapter=userOrderAdapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}