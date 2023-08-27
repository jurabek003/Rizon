package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.rizonuz.databinding.ItemPromotionBuyHistoryBinding
import uz.turgunboyevjurabek.rizon.madels.promotion.Purchase
import java.text.SimpleDateFormat

class PromotionBuyHistoryAdapter(var list:ArrayList<Purchase> = ArrayList()): RecyclerView.Adapter<PromotionBuyHistoryAdapter.Vh>() {

         inner class Vh(val itemRvBinding: ItemPromotionBuyHistoryBinding):RecyclerView.ViewHolder(itemRvBinding.root){

             fun onBind(myContact: Purchase){
                 itemRvBinding.tvName.text = myContact.promotion.name
                 itemRvBinding.tvValue.text = myContact.coupon.toString()

                 val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
                 itemRvBinding.tvDate.text = SimpleDateFormat("dd.MM.yyyy").format(formatter.parse(myContact.created_time))
             }
         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
             return Vh(ItemPromotionBuyHistoryBinding.inflate(LayoutInflater.from(parent.context),parent, false))
         }

         override fun getItemCount(): Int = list.size

         override fun onBindViewHolder(holder: Vh, position: Int) {
             holder.onBind(list[position])
         }
     }