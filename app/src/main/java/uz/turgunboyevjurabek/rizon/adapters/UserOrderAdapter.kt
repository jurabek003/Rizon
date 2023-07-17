package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.databinding.ItemOrderRvBinding
import uz.turgunboyevjurabek.rizon.madels.userOrders.Order

class UserOrderAdapter(val list: ArrayList<Order> = ArrayList()):RecyclerView.Adapter<UserOrderAdapter.Vh>() {
    inner class Vh(val itemOrderRvBinding: ItemOrderRvBinding):ViewHolder(itemOrderRvBinding.root){
        fun onBind(orders: Order){
            Picasso.get().load("https://rizonwebapp.pythonanywhere.com${orders.product.photo_link}").into(itemOrderRvBinding.itemImg)
            itemOrderRvBinding.itemName.text = orders.product.name
            itemOrderRvBinding.itemPrice.text = "${orders.product.price}"
            itemOrderRvBinding.itemCount.text = "${orders.amount}"
            itemOrderRvBinding.itemSumPrice.text = "${orders.summa}"

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