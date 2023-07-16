package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.databinding.ItemOrderRvBinding
import uz.turgunboyevjurabek.rizon.madels.userOrders.Orders

class UserOrderAdapter(val list: ArrayList<Orders>):RecyclerView.Adapter<UserOrderAdapter.Vh>() {
    inner class Vh(val itemOrderRvBinding: ItemOrderRvBinding):ViewHolder(itemOrderRvBinding.root){
        fun onBind(orders: Orders){
            itemOrderRvBinding.itemImg.setImageResource(R.drawable.argenta)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}