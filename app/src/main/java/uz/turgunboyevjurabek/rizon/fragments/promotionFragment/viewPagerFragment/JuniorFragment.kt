package uz.turgunboyevjurabek.rizon.fragments.promotionFragment.viewPagerFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.databinding.DialogPromotionBuyBinding
import uz.ilhomjon.rizonuz.databinding.FragmentJuniorBinding
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.ItemSelect
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.RvAdapterJunior
import uz.turgunboyevjurabek.rizon.fragments.promotionFragment.PromotionViewModel
import uz.turgunboyevjurabek.rizon.madels.promotion.ProductsInterval
import uz.turgunboyevjurabek.rizon.madels.promotion.post.PostBuyPromotionRequest
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "JuniorFragment"
//promotionItemFragment
class JuniorFragment : Fragment(),ItemSelect {
    private val binding by lazy { FragmentJuniorBinding.inflate(layoutInflater) }
    private lateinit var rvAdapterJunior: RvAdapterJunior
    private lateinit var list:List<ProductsInterval>
    private lateinit var promotionViewModel: PromotionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        rvAdapterJunior = RvAdapterJunior(list as ArrayList<ProductsInterval>, this)
        binding.rvJunior.adapter = rvAdapterJunior

        promotionViewModel = ViewModelProvider(requireActivity())[PromotionViewModel::class.java]

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun select(junior: ProductsInterval, position: Int) {
//        findNavController().navigate(R.id.bioFragment, bundleOf("keyProductInterval" to junior))
    }

    override fun dialogSelect(junior: ProductsInterval, position: Int) {
        val mdialog=MaterialAlertDialogBuilder(requireContext()).create()
        val dialogPromotionBuyBinding=DialogPromotionBuyBinding.inflate(layoutInflater)
        mdialog.setView(dialogPromotionBuyBinding.root)

        Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${junior.photo}").into(dialogPromotionBuyBinding.imagePromotions)
        dialogPromotionBuyBinding.tvName.text = junior.name
        dialogPromotionBuyBinding.tvNarx.text = junior.coupon.toString()

        dialogPromotionBuyBinding.btnBuy.setOnClickListener {

            val postBuyPromotionRequest = PostBuyPromotionRequest(1, junior.id)
            promotionViewModel.postBuyPromotion(MySharedPreference.token, postBuyPromotionRequest)
                .observe(requireActivity()){
                    when(it.status){
                        Status.LOADING ->{
                            Log.d(TAG, "onCreate: Loading")
                            dialogPromotionBuyBinding.myProgressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR->{
                            Log.d(TAG, "onCreate: Error ${it.message}")
                            dialogPromotionBuyBinding.myProgressBar.visibility = View.GONE
                            mdialog.cancel()
                            val dialog = AlertDialog.Builder(binding.root.context)
                            dialog.setTitle("Xatolik")
                            dialog.setMessage("Sizda yetarli kuponlar bo'lmasligi mumkin. Iltimos tekshirib qayta urinib ko'ring")
                            dialog.setPositiveButton("Tushundim"
                            ) { dialog, which -> }
                            dialog.show()
                        }
                        Status.SUCCESS ->{
                            Log.d(TAG, "onCreate: ${it.data}")
                            dialogPromotionBuyBinding.myProgressBar.visibility = View.GONE
                            mdialog.cancel()

                            val dialog = AlertDialog.Builder(binding.root.context)
                            dialog.setMessage(it?.data?.message)
                            dialog.show()
                            dialog.setPositiveButton("ok"
                            ) { dialog, which -> }
                        }
                    }
                }


        }

        mdialog.show()

        dialogPromotionBuyBinding.edtCancel.setOnClickListener {
            mdialog.cancel()
        }


    }

    fun promotionBuy(postBuyPromotionRequest: PostBuyPromotionRequest){

    }

    companion object{
        fun newInstance(list: List<ProductsInterval>):Fragment{
            val fj = JuniorFragment()
            fj.list = list
            return fj
        }
    }
}