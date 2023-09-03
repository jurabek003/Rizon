package uz.turgunboyevjurabek.rizon.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.rizonuz.databinding.ItemRvDiagramRoundBinding


class My_diagramRoundRvAdapter(var list:ArrayList<MyDiagramObject> = ArrayList()): RecyclerView.Adapter<My_diagramRoundRvAdapter.Vh>() {

         inner class Vh(val itemRvBinding: ItemRvDiagramRoundBinding):RecyclerView.ViewHolder(itemRvBinding.root){

             fun onBind(myContact: MyDiagramObject){
                 itemRvBinding.tvName.text = myContact.name
                 itemRvBinding.viewColor.setBackgroundColor(myContact.color)
             }
         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
             return Vh(ItemRvDiagramRoundBinding.inflate(LayoutInflater.from(parent.context),parent, false))
         }

         override fun getItemCount(): Int = list.size

         override fun onBindViewHolder(holder: Vh, position: Int) {
             holder.onBind(list[position])
         }
     }
data class MyDiagramObject (val name:String, val color:Int)
