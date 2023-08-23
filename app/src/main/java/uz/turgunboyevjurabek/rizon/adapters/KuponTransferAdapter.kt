package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ilhomjon.rizonuz.databinding.ItemKuponRvBinding
import uz.turgunboyevjurabek.rizon.madels.coupon.Transfer

class KuponTransferAdapter(val list:ArrayList<Transfer> = ArrayList()):RecyclerView.Adapter<KuponTransferAdapter.Vh>() {
    inner class Vh(val itemKuponRvBinding: ItemKuponRvBinding):ViewHolder(itemKuponRvBinding.root){
        fun onBind(transfer: Transfer){

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