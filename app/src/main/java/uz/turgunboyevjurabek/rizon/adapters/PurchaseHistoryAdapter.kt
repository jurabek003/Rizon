package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemHistoryRvBinding
import uz.turgunboyevjurabek.rizon.madels.PurchaseHistory

class PurchaseHistoryAdapter(val list:ArrayList<PurchaseHistory>):RecyclerView.Adapter<PurchaseHistoryAdapter.Vh>() {
    inner class Vh(val itemHistoryRvBinding: ItemHistoryRvBinding):ViewHolder(itemHistoryRvBinding.root){
        fun onBind(purchaseHistory: PurchaseHistory){

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