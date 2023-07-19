package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import uz.turgunboyevjurabek.rizon.databinding.FragmentSelectBinding
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

class SelectFragment : Fragment() {
    private val binding by lazy { FragmentSelectBinding.inflate(layoutInflater) }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        selectItem()

        return binding.root
    }

    private fun selectItem() {
        val name=arguments?.getString("keyName")
        val price=arguments?.getInt("keyPrice")
        val photo=arguments?.getString("keyPhoto")
        val about= arguments?.getString("keyAbout")
        Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${photo}").into(binding.selectItemImg)
        binding.selectItemName.text=name.toString()
        binding.selectItemPrice.text="${price.toString()}uzs"
        binding.selectItemAbout.text=about.toString()
    }
}