package uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.databinding.ItemJuniorRvBinding
import uz.turgunboyevjurabek.rizon.madels.promotion.ProductsInterval
import uz.turgunboyevjurabek.rizon.madels.promotion.pager.Junior
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

class RvAdapterJunior(val list: ArrayList<ProductsInterval>, val itemSelect: ItemSelect):RecyclerView.Adapter<RvAdapterJunior.Vh>() {
    inner class Vh(val itemJuniorRvBinding: ItemJuniorRvBinding):ViewHolder(itemJuniorRvBinding.root){
        fun onBind(junior: ProductsInterval, position: Int){

            itemJuniorRvBinding.tvName.text = junior.name
            itemJuniorRvBinding.tvPrice.text = junior.coupon.toString()
            Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${junior.photo}").into(itemJuniorRvBinding.imagePromotions)

            itemJuniorRvBinding.root.setOnClickListener {
                itemSelect.select(junior,position)
            }
            // dialogda chiqishi uchun
            itemJuniorRvBinding.cardBuy.setOnClickListener {
                itemSelect.dialogSelect(junior,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemJuniorRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
}
interface ItemSelect{
    fun select(junior: ProductsInterval, position: Int)
    fun dialogSelect(junior: ProductsInterval, position: Int)
}