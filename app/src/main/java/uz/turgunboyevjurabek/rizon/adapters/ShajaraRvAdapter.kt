package uz.turgunboyevjurabek.rizon.adapters

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ilhomjon.rizonuz.databinding.ItemShajaraRvBinding
import uz.turgunboyevjurabek.rizon.madels.usersProfile.UserTree
import uz.turgunboyevjurabek.rizon.utils.AppObject

class ShajaraRvAdapter(val activity:Activity, val list: ArrayList<UserTree> = ArrayList()):RecyclerView.Adapter<ShajaraRvAdapter.Vh>() {
    inner class Vh(val itemShajaraRvBinding: ItemShajaraRvBinding):ViewHolder(itemShajaraRvBinding.root){
        fun  onBind(shajara: UserTree){
            itemShajaraRvBinding.itemId.text = shajara.user_id.toString()
            itemShajaraRvBinding.itemId.setOnClickListener {

                val clipboardManager = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                val clipData = ClipData.newPlainText("Label", itemShajaraRvBinding.itemId.text.toString())
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(
                    itemShajaraRvBinding.root.context,
                    "Id dan nusxa olindi",
                    Toast.LENGTH_SHORT
                ).show()
            }
            itemShajaraRvBinding.itemBonus.text = shajara.bonus_for_followers_status.toString()
            itemShajaraRvBinding.itemStatus.text = shajara.user_status
            itemShajaraRvBinding.itemIsmFamiliya.text = "${shajara.first_name} ${shajara.last_name}"
            itemShajaraRvBinding.itemPhoneNumber.text = shajara.phone_number
            itemShajaraRvBinding.linerPhone.setOnClickListener {
            if (shajara.phone_number.first()=='+')
                startActivity(AppObject.binding.root.context, Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:${shajara.phone_number}")), bundleOf())
                else
                startActivity(AppObject.binding.root.context, Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:+${shajara.phone_number}")), bundleOf())
            }
            itemShajaraRvBinding.itemShaxsiyBall.text = shajara.personal_bonus.toString()
            itemShajaraRvBinding.itemJamoaviyBall.text = shajara.user_tree_score.toString()
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