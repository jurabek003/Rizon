package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemKuponRvBinding
import uz.turgunboyevjurabek.rizon.madels.KuponTransfer

class KuponTransferAdapter(val list:ArrayList<KuponTransfer>):RecyclerView.Adapter<KuponTransferAdapter.Vh>() {
    inner class Vh(val itemKuponRvBinding: ItemKuponRvBinding):ViewHolder(itemKuponRvBinding.root){
        fun onBind(kuponTransfer: KuponTransfer){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemKuponRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}