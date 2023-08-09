package uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemJuniorRvBinding
import uz.turgunboyevjurabek.rizon.madels.pager.Junior

class RvAdapterJunior(val list: ArrayList<Junior>,val itemSelect: ItemSelect):RecyclerView.Adapter<RvAdapterJunior.Vh>() {
    inner class Vh(val itemJuniorRvBinding: ItemJuniorRvBinding):ViewHolder(itemJuniorRvBinding.root){
        fun onBind( junior: Junior,position: Int){


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
    fun select(junior: Junior,position: Int)
    fun dialogSelect(junior: Junior,position: Int)
}