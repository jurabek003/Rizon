package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.KuponTransferAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentKuponTransferBinding
import uz.turgunboyevjurabek.rizon.madels.KuponTransfer
import uz.turgunboyevjurabek.rizon.utils.AppObject


class KuponTransferFragment : Fragment() {
    private val binding by lazy { FragmentKuponTransferBinding.inflate(layoutInflater) }
    private lateinit var kuponTransferAdapter: KuponTransferAdapter
    private lateinit var list: ArrayList<KuponTransfer>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        list= ArrayList()
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        list.add(KuponTransfer(123,"jurabek",999))
        kuponTransferAdapter=KuponTransferAdapter(list)
        binding.rvKupon.adapter=kuponTransferAdapter


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text="Kupon transfer"
    }
}