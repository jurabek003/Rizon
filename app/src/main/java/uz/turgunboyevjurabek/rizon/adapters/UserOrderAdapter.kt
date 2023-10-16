package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.ilhomjon.rizonuz.databinding.ItemOrderRvBinding
import uz.turgunboyevjurabek.rizon.madels.userOrders.Order
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

class UserOrderAdapter(val rvAction:RvAction,  val list: ArrayList<Order> = ArrayList()):RecyclerView.Adapter<UserOrderAdapter.Vh>() {
    inner class Vh(val itemOrderRvBinding: ItemOrderRvBinding):ViewHolder(itemOrderRvBinding.root){
        fun onBind(orders: Order, position: Int){
            Glide.with(itemView).load("${ApiClient.PHOTO_BASE_URL}${orders.product.photo_link}").into(itemOrderRvBinding.itemImg)
            itemOrderRvBinding.itemName.text = orders.product.name
            itemOrderRvBinding.itemPrice.text = "${orders.product.price}uzs"
            itemOrderRvBinding.itemCount.text = "${orders.amount}ta"
            itemOrderRvBinding.itemSumPrice.text = "${orders.summa}uzs"

            itemOrderRvBinding.btnStop.setOnClickListener {
                rvAction.deleteOrder(orders, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
}

interface RvAction{
    fun deleteOrder(orders: Order, position: Int)
}