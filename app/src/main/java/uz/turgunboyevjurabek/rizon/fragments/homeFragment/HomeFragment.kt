package uz.turgunboyevjurabek.rizon.fragments.homeFragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anychart.charts.Pie
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import uz.ilhomjon.rizonuz.R
import uz.ilhomjon.rizonuz.databinding.FragmentHomeBinding
import uz.turgunboyevjurabek.rizon.adapters.SaleRvAdapter
import uz.turgunboyevjurabek.rizon.madels.UserMain.ProductSalesData2
import uz.turgunboyevjurabek.rizon.madels.UserMain.SalaryData
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status


private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var saleRvAdapter: SaleRvAdapter
    private lateinit var pie: Pie
    private lateinit var pieChart: PieChart

    lateinit var barChart: BarChart

    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet

    // on below line we are creating array list for bar data
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        getApi()

        return binding.root
    }

    lateinit var homeViewModel: HomeViewModel
    private fun getApi() {

        MySharedPreference.init(binding.root.context)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        val dialog = AlertDialog.Builder(binding.root.context)
            .setTitle("Authorizationda xatolik")
            .setMessage("Qaytadan login parol kiritishingiz kerak.")
            .setPositiveButton("Login") { v, d ->
                findNavController().navigate(R.id.authFragment)
            }
            .setNegativeButton("Kerak emas") { v, d ->
                requireActivity().finish()
            }
            .create()
        homeViewModel.getUsersMain(MySharedPreference.token)
            .observe(requireActivity()) {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d(TAG, "onCreate: Loading")
                        binding.myProgressBar.visibility = View.VISIBLE
                        binding.homeScrollview.visibility = View.INVISIBLE
                    }

                    Status.ERROR -> {
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        binding.myProgressBar.visibility = View.GONE
//                        Toast.makeText(AppObject.binding.root.context, "Error main ${it.message}", Toast.LENGTH_SHORT).show()
                        if (it.message!!.lowercase().contains("unauth") ){
//                            findNavController().popBackStack()
                            dialog.setCancelable(false)
                            dialog.show()
                        }
                    }

                    Status.SUCCESS -> {
                        dialog.cancel()
                        Log.d(TAG, "onCreate: ${it.data}")
                        binding.myProgressBar.visibility = View.GONE
                        binding.homeScrollview.visibility = View.VISIBLE
                        saleAdapter(it.data?.product_sales_data2 as? ArrayList)
                        diagram(it.data?.product_sales_data2)
                        diagram2(it.data?.salary_data)
                        shareLink(it.data?.follower_link!!, it.data.sale_link)
                    }
                }
            }
    }

    //share links
    private fun shareLink(izdoshLink:String, sotuvLink:String){
        binding.btnIzdosh.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_TEXT, izdoshLink)
            startActivity(i)
        }

        binding.btnSotuv.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_TEXT, sotuvLink)
            startActivity(i)
        }
    }

    ////umumiy maosh grafigi // salary-data
    private fun diagram2(list: List<SalaryData>?) {
        barChart = binding.barChartView

        // on below line we are calling get bar
        // chart data to add data to our array list
        val barChart: BarChart = binding.barChartView

        val dates = listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        val values = listOf(10f, 50f, 700f, 120f, 340f, 214f, 567f, 6666f, 999f, 112f, 2222f, 1212f)
        // BarEntry ma'lumotlarini tayyorlash
        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in values.indices) {
            entries.add(BarEntry(i.toFloat(), values[i]))
        }

        // X-osi bo'yicha datalarni joylash
        val barDataSet = BarDataSet(entries, "Ma'lumotlar")
        barDataSet.color = Color.BLUE
        // X-osi bo'yicha nomlarni joylash
        val xAxisLabels = dates.toTypedArray()
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true

        // BarChart konfiguratsiyalari
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setFitBars(true)
        barChart.description.isEnabled = false
        barChart.animateY(1000)

        // Barchartni yangilash
        barChart.invalidate()
    }

    //maxsulot sotuv ulushi// products-selas-data2
    // ekranda korin yapti lekin figmadagidek emas
    private fun diagram(list: List<ProductSalesData2>?) {
        pieChart = binding.myPieChart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f)
        // on below line we are setting hole

        pieChart.setDrawHoleEnabled(false)  // o'rtadagi yumaloqni boshqarish
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(50f))
        entries.add(PieEntry(30f))
        entries.add(PieEntry(10f))
        entries.add(PieEntry(40f))

        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries, "Mobile OS")

        // on below line we are setting icons.
        dataSet.setDrawIcons(true)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 0f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.purple_200))
        colors.add(resources.getColor(R.color.yellow))
        colors.add(resources.getColor(R.color.red))
        colors.add(resources.getColor(R.color.black))

        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.WHITE)
        pieChart.setData(data)

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()


    }

    //chegirmali maxsulotlar
    private fun saleAdapter(list:ArrayList<ProductSalesData2>?) {
        saleRvAdapter = SaleRvAdapter()
        saleRvAdapter.list.clear()
        if (list!=null)
        saleRvAdapter.list.addAll(list)
        binding.rvSale.adapter = saleRvAdapter
        saleRvAdapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Asosiy panel"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
}