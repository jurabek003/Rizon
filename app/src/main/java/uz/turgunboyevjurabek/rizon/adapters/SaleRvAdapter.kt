package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.databinding.ItemRvSaleBinding
import uz.turgunboyevjurabek.rizon.madels.UserMain.Discount
import uz.turgunboyevjurabek.rizon.madels.UserMain.ProductSalesData2
import uz.turgunboyevjurabek.rizon.madels.sale.Sale
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

class SaleRvAdapter(val list: ArrayList<Discount> = ArrayList() ):RecyclerView.Adapter<SaleRvAdapter.Vh>() {
    inner class Vh(val itemRvSaleBinding: ItemRvSaleBinding):ViewHolder(itemRvSaleBinding.root){
        fun onBind(sale:Discount){
            itemRvSaleBinding.thtSale.text=sale.discount+"% chegirma"

            Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${sale.photo_link}").into(itemRvSaleBinding.itemImg)
            itemRvSaleBinding.tvName.text = sale.name
            itemRvSaleBinding.tvAbout.text = sale.about
            itemRvSaleBinding.tvDate.text = sale.lifetime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvSaleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}