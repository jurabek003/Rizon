package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.databinding.FragmentBioBinding

class BioFragment : Fragment() {
   private val binding by lazy { FragmentBioBinding.inflate(layoutInflater) }
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



        return  binding.root
    }

}