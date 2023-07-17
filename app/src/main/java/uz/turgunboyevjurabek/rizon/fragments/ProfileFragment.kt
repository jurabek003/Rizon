package uz.turgunboyevjurabek.rizon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.ShajaraRvAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentProfilBinding
import uz.turgunboyevjurabek.rizon.madels.userProfil.Shajara

class ProfileFragment : Fragment() {
    private val binding by lazy { FragmentProfilBinding.inflate(layoutInflater) }
    private lateinit var shajaraRvAdapter: ShajaraRvAdapter
    private lateinit var list: ArrayList<Shajara>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        list= ArrayList()
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        list.addAll(listOf(Shajara(),))
        shajaraRvAdapter=ShajaraRvAdapter(list)
        binding.rvShajara.adapter=shajaraRvAdapter


        return binding.root
    }
}