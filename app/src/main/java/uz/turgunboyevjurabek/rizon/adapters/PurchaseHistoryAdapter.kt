package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ilhomjon.rizonuz.databinding.ItemHistoryRvBinding
import uz.turgunboyevjurabek.rizon.madels.userOrders.Order
import java.text.SimpleDateFormat

class PurchaseHistoryAdapter(val list:ArrayList<Order> = ArrayList()):RecyclerView.Adapter<PurchaseHistoryAdapter.Vh>() {
    inner class Vh(val itemHistoryRvBinding: ItemHistoryRvBinding):ViewHolder(itemHistoryRvBinding.root){
        fun onBind(order: Order){
            itemHistoryRvBinding.tvShartnomaId.text = order.id.toString()
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
            itemHistoryRvBinding.tvSana.text = SimpleDateFormat("dd.MM.yyyy").format(formatter.parse(order.date))
            itemHistoryRvBinding.tvMiqdori.text = order.amount.toString()
            itemHistoryRvBinding.tvSumma.text = "${order.product.price*order.amount}"
            itemHistoryRvBinding.tvTuri.text = order.product.product_type
//            itemHistoryRvBinding.tvAddress.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
       return Vh(ItemHistoryRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}