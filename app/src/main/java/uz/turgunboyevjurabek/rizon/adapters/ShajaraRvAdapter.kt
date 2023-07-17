package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemShajaraRvBinding
import uz.turgunboyevjurabek.rizon.madels.userProfil.Shajara

class ShajaraRvAdapter(val list: ArrayList<Shajara>):RecyclerView.Adapter<ShajaraRvAdapter.Vh>() {
    inner class Vh(val itemShajaraRvBinding: ItemShajaraRvBinding):ViewHolder(itemShajaraRvBinding.root){
        fun  onBind(shajara: Shajara){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemShajaraRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int=list.size
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}