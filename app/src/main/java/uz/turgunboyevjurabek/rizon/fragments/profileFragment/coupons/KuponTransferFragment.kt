package uz.turgunboyevjurabek.rizon.fragments.profileFragment.coupons

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.adapters.KuponTransferAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentKuponTransferBinding
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "KuponTransferFragment"
class KuponTransferFragment : Fragment() {
    private val binding by lazy { FragmentKuponTransferBinding.inflate(layoutInflater) }
    private lateinit var kuponTransferAdapter: KuponTransferAdapter
    private lateinit var couponViewModel: CouponViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MySharedPreference.init(binding.root.context)
        kuponTransferAdapter = KuponTransferAdapter()
        binding.rvKupon.adapter = kuponTransferAdapter

        couponViewModel = ViewModelProvider(requireActivity())[CouponViewModel::class.java]
        couponViewModel.getCoupons(MySharedPreference.token)
            .observe(requireActivity()){
                when(it.status){
                    Status.ERROR->{
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                        binding.myProgressBar.visibility = View.GONE
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }
                    Status.LOADING->{
                        binding.myProgressBar.visibility = View.VISIBLE
                        Log.d(TAG, "onCreateView: loading")
                    }
                    Status.SUCCESS->{
                        Log.d(TAG, "onCreateView: ${it.data}")
                        binding.apply {
                        val data = it.data!!
                            tvYuboruvchi.text = data.user.first_name
                            tvKupon.text = data.user.coupon.toString()
                        }

                        binding.myProgressBar.visibility = View.GONE
                        kuponTransferAdapter.list.clear()
                        kuponTransferAdapter.list.addAll(it.data?.transfers!!)
                        kuponTransferAdapter.notifyDataSetChanged()
                    }
                }
            }



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text="Kupon transfer"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
    }
}