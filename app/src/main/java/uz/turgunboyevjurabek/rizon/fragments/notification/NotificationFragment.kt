package uz.turgunboyevjurabek.rizon.fragments.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.NotificationAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentNotificationBinding
import uz.turgunboyevjurabek.rizon.madels.Notification

class NotificationFragment : Fragment() {
    private val binding by lazy { FragmentNotificationBinding.inflate(layoutInflater) }
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var list: ArrayList<Notification>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        list=ArrayList()
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))
        list.add(Notification("jurabek","30.12.2003"))

        notificationAdapter=NotificationAdapter(list)
        binding.rvNotification.adapter=notificationAdapter

        return binding.root
    }
}