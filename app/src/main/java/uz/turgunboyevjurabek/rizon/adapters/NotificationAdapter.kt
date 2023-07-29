package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemNotificationRvBinding
import uz.turgunboyevjurabek.rizon.madels.notification.Notification
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class NotificationAdapter(val list: ArrayList<Notification> = ArrayList()) :
    RecyclerView.Adapter<NotificationAdapter.Vh>() {
    inner class Vh(val itemNotificationRvBinding: ItemNotificationRvBinding) :
        ViewHolder(itemNotificationRvBinding.root) {
        fun onBind(notification: Notification) {
            itemNotificationRvBinding.itemTvNotification.text = notification.message
            itemNotificationRvBinding.tvRizon.text = notification.title
            val inputDate = notification.date
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
            itemNotificationRvBinding.tvDate.text = SimpleDateFormat("dd.MM.yyyy").format(formatter.parse(inputDate))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemNotificationRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}