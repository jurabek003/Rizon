package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.rizon.databinding.ItemShajaraRvBinding
import uz.turgunboyevjurabek.rizon.madels.usersProfile.X1

class ShajaraRvAdapter(val list: ArrayList<X1>):RecyclerView.Adapter<ShajaraRvAdapter.Vh>() {
    inner class Vh(val itemShajaraRvBinding: ItemShajaraRvBinding):ViewHolder(itemShajaraRvBinding.root){
        fun  onBind(shajara: X1){
            itemShajaraRvBinding.itemId.text = shajara.user_id.toString()
            itemShajaraRvBinding.itemBonus.text = shajara.bonus_for_followers_status.toString()
            itemShajaraRvBinding.itemStatus.text = shajara.user_status
            itemShajaraRvBinding.itemIsmFamiliya.text = "${shajara.first_name} ${shajara.last_name}"
            itemShajaraRvBinding.itemPhoneNumber.text = shajara.phoneNum
            itemShajaraRvBinding.itemShaxsiyBall.text = shajara.extra_bonus.toString()
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