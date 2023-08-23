package uz.turgunboyevjurabek.rizon.fragments.notification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.adapters.NotificationAdapter
import uz.ilhomjon.rizonuz.databinding.FragmentNotificationBinding
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "NotificationFragment"
class NotificationFragment : Fragment() {
    private val binding by lazy { FragmentNotificationBinding.inflate(layoutInflater) }
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notificationViewModel: NotificationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        notificationAdapter=NotificationAdapter()
        binding.rvNotification.adapter=notificationAdapter

        MySharedPreference.init(binding.root.context)

        notificationViewModel = ViewModelProvider(requireActivity())[NotificationViewModel::class.java]

        notificationViewModel.getUsersOrders(MySharedPreference.token)
            .observe(requireActivity()){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        binding.myProgressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        binding.myProgressBar.visibility = View.GONE
                        Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS ->{
                        notificationAdapter.list.clear()
                        Log.d(TAG, "onCreate: ${it.data}")
                        notificationAdapter.list.clear()
                        notificationAdapter.list.addAll(it.data?.notifications!!)
                        notificationAdapter.notifyDataSetChanged()
                        binding.myProgressBar.visibility = View.GONE
                    }
                }
            }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Bildirishnoma"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
    }
}