package uz.turgunboyevjurabek.rizon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.rizonuz.databinding.ItemSalaryRvBinding
import uz.turgunboyevjurabek.rizon.madels.salary.SalaryHistory
import java.text.SimpleDateFormat

class SalaryHistoryAdapter(val selectSalary: SelectSalary, val list:ArrayList<SalaryHistory> = ArrayList()):RecyclerView.Adapter<SalaryHistoryAdapter.Vh>() {
    inner class Vh(val itemSalaryRvBinding: ItemSalaryRvBinding): RecyclerView.ViewHolder(itemSalaryRvBinding.root){
        fun onBind(salaryHistory: SalaryHistory,position: Int){
            itemSalaryRvBinding.showLayout.setOnClickListener {
                selectSalary.clickSalary(salaryHistory,position)
            }

            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
            itemSalaryRvBinding.tvDate.text = SimpleDateFormat("dd.MM.yyyy").format(formatter.parse(salaryHistory.created_time))

            itemSalaryRvBinding.tvOylik.text = salaryHistory.salary.toString()
            itemSalaryRvBinding.tvFilial.text = salaryHistory.warehouse.name
            itemSalaryRvBinding.salaryItemSana.text = salaryHistory.month
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