package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.R
import uz.ilhomjon.rizonuz.databinding.FragmentBioBinding
import uz.turgunboyevjurabek.rizon.madels.promotion.ProductsInterval
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

class BioFragment : Fragment() {
   private val binding by lazy { FragmentBioBinding.inflate(layoutInflater) }
    lateinit var productsInterval: ProductsInterval
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bio fragmentdan promotionFragmentga utish onCreate da  yozdimdim, --
        // -- onResume ga yozsam ilovadan chiqib ketib qolyapti
        binding.imgBackBio.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        productsInterval = arguments?.getSerializable("keyProductInterval") as ProductsInterval
        binding.tvName.text = productsInterval.name
        binding.tvName2.text = productsInterval.coupon.toString()
//        binding.tvAboit.text = productsInterval.pause

        Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${productsInterval.photo}").into(binding.imagePromotions)
        binding.btnOrder.setOnClickListener {

            //xarid qilish

        }
        return  binding.root
    }

}