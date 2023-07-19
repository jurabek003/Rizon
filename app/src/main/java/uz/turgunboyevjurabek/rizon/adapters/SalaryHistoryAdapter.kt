package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.rizon.databinding.ItemHistoryRvBinding
import uz.turgunboyevjurabek.rizon.databinding.ItemSalaryRvBinding
import uz.turgunboyevjurabek.rizon.madels.PurchaseHistory
import uz.turgunboyevjurabek.rizon.madels.SalaryHistory

class SalaryHistoryAdapter(val list:ArrayList<SalaryHistory>,val selectSalary: SelectSalary):RecyclerView.Adapter<SalaryHistoryAdapter.Vh>() {
    inner class Vh(val itemSalaryRvBinding: ItemSalaryRvBinding): RecyclerView.ViewHolder(itemSalaryRvBinding.root){
        fun onBind(salaryHistory: SalaryHistory,position: Int){
            itemSalaryRvBinding.showLayout.setOnClickListener {
                selectSalary.clickSalary(salaryHistory,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemSalaryRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

}
interface SelectSalary{
    fun clickSalary(salaryHistory: SalaryHistory,position: Int)
}