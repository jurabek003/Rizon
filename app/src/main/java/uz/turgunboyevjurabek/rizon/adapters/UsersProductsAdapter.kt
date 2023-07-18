package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import uz.turgunboyevjurabek.rizon.databinding.ItemProductRvBinding
import uz.turgunboyevjurabek.rizon.madels.usersProducts.Product
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

class MyProductsAdapter (val list:ArrayList<Product> = ArrayList()):Adapter<MyProductsAdapter.Vh>(){

    inner class Vh(val itemRv:ItemProductRvBinding):ViewHolder(itemRv.root){
        fun onBind(product: Product, position:Int){
            itemRv.itemName.text = product.name
            itemRv.itemPrice.text = "${product.price} UZS"

            Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${product.photo_link}").into(itemRv.itemImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemProductRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
}