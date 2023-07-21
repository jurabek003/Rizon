package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.rizon.databinding.ItemSalaryRvBinding
import uz.turgunboyevjurabek.rizon.madels.salary.SalaryHistory

class SalaryHistoryAdapter(val selectSalary: SelectSalary, val list:ArrayList<SalaryHistory> = ArrayList()):RecyclerView.Adapter<SalaryHistoryAdapter.Vh>() {
    inner class Vh(val itemSalaryRvBinding: ItemSalaryRvBinding): RecyclerView.ViewHolder(itemSalaryRvBinding.root){
        fun onBind(salaryHistory: SalaryHistory,position: Int){
            itemSalaryRvBinding.showLayout.setOnClickListener {
                selectSalary.clickSalary(salaryHistory,position)
            }
            itemSalaryRvBinding.tvDate.text = salaryHistory.date
            itemSalaryRvBinding.salaryItemSana.text = salaryHistory.date
            itemSalaryRvBinding.tvKupon.text = salaryHistory.coupon.toString()
            itemSalaryRvBinding.tvShaxsiyAylanma.text = salaryHistory.user_score.toString()
            itemSalaryRvBinding.tvShaxsiySotuvBonusi.text = salaryHistory.personal_bonus.toString()
            itemSalaryRvBinding.tvUstozlikBonusi.text = salaryHistory.forMentorship.toString()
            itemSalaryRvBinding.tvJamoaviyAylanma.text = salaryHistory.user_tree_score.toString()
            itemSalaryRvBinding.tvJamoaviyBonus.text = salaryHistory.collective_bonus_amount.toString()
            itemSalaryRvBinding.tvExtraBonus.text = salaryHistory.extra_bonus.toString()
            itemSalaryRvBinding.tvTolnadi.text = salaryHistory.paid.toString()
            itemSalaryRvBinding.tvQarz.text= salaryHistory.debt.toString()
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