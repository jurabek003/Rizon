package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemRvSaleBinding
import uz.turgunboyevjurabek.rizon.madels.sale.Sale

class SaleRvAdapter(val list: ArrayList<Sale> = ArrayList() ):RecyclerView.Adapter<SaleRvAdapter.Vh>() {
    inner class Vh(val itemRvSaleBinding: ItemRvSaleBinding):ViewHolder(itemRvSaleBinding.root){
        fun onBind(sale:Sale){
            itemRvSaleBinding.thtSale.text=sale.sale.toString()+"% chegirma"

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