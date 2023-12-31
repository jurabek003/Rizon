package uz.turgunboyevjurabek.rizon.fragments.profileFragment.coupons

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.adapters.KuponTransferAdapter
import uz.ilhomjon.rizonuz.databinding.FragmentKuponTransferBinding
import uz.turgunboyevjurabek.rizon.madels.coupon.transfer.PostCouponTransferRequest
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "KuponTransferFragment"

class KuponTransferFragment : Fragment() {
    private val binding by lazy { FragmentKuponTransferBinding.inflate(layoutInflater) }
    private lateinit var kuponTransferAdapter: KuponTransferAdapter
    private lateinit var couponViewModel: CouponViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MySharedPreference.init(binding.root.context)
        kuponTransferAdapter = KuponTransferAdapter()
        binding.rvKupon.adapter = kuponTransferAdapter

        couponViewModel = ViewModelProvider(requireActivity())[CouponViewModel::class.java]

        getKuponsAdaptersShow()
        transferCoupons()

        return binding.root
    }

    private fun getKuponsAdaptersShow() {
        couponViewModel.getCoupons(MySharedPreference.token)
            .observe(requireActivity()) {
                when (it.status) {
                    Status.ERROR -> {
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                        binding.myProgressBar.visibility = View.GONE
                        Log.d(TAG, "onCreateView: ${it.message}")
                    }

                    Status.LOADING -> {
                        binding.myProgressBar.visibility = View.VISIBLE
                        Log.d(TAG, "onCreateView: loading")
                    }

                    Status.SUCCESS -> {
                        Log.d(TAG, "onCreateView: ${it.data}")
                        binding.apply {
                            val data = it.data!!
                            tvYuboruvchi.text = data.user.first_name
                            tvKupon.text = data.user.coupon.toString()
                        }

                        binding.myProgressBar.visibility = View.GONE
                        kuponTransferAdapter.list.clear()
                        kuponTransferAdapter.list.addAll(it.data?.transfers!!)
                        kuponTransferAdapter.notifyDataSetChanged()
                    }
                }
            }

    }

    private fun transferCoupons() {
        addTransfer(null)
        binding.edtId.addTextChangedListener {
            if (it.toString().trim().isNotEmpty()) {
                couponViewModel.getNameUser(MySharedPreference.token, it.toString().trim())
                    .observe(requireActivity()) {
                        when (it.status) {
                            Status.LOADING -> {
                                binding.tvFullName.text = "Yuklanmoqda..."
                            }

                            Status.ERROR -> {
                                binding.tvFullName.text = "Bu ID da foydalanuvchi topilmadi"
                            }

                            Status.SUCCESS -> {
                                binding.tvFullName.text =
                                    "${it.data?.first_name} ${it.data?.last_name}"
                                addTransfer(it.data?.id)
                            }
                        }
                    }
            }
        }
    }

    private fun addTransfer(id: String?) {
        binding.btnCreditCard.setOnClickListener {

            if (id == null) {
                Toast.makeText(
                    AppObject.binding.root.context,
                    "Avval qabul qiluvchi Id sini to'g'ri kiriting",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (binding.edtKupon.text.isNotBlank()) {
                    val postCouponTransferRequest = PostCouponTransferRequest(
                        "Ixtiyoriy",
                        id,
                        binding.edtKupon.text.toString().toInt()
                    )
                    couponViewModel.postTransferCoupons(
                        MySharedPreference.token,
                        postCouponTransferRequest
                    )
                        .observe(requireActivity()) {
                            when (it.status) {
                                Status.ERROR -> {
                                    binding.myProgressBar.visibility = View.GONE
                                    val dialog = AlertDialog.Builder(binding.root.context)
                                    dialog.setMessage("Kupon o'tkazish uchun o'zingizda eng kamida 6 000 kupon bo'lishi va o'tkazma miqdori eng kamida 1 000 bo'lishi kerak")
                                    dialog.setPositiveButton(
                                        "Tushundim",
                                        object : DialogInterface.OnClickListener {
                                            override fun onClick(
                                                dialog: DialogInterface?,
                                                which: Int
                                            ) {

                                            }
                                        });
                                    dialog.show()
                                }

                                Status.LOADING -> {
                                    binding.myProgressBar.visibility = View.VISIBLE
                                }

                                Status.SUCCESS -> {
                                    binding.myProgressBar.visibility = View.GONE
                                    val dialog = AlertDialog.Builder(binding.root.context)
                                    dialog.setMessage("O'tkazma muvaffaqiyatli amalga oshirildi")
                                    dialog.setPositiveButton(
                                        "Yopish",
                                        object : DialogInterface.OnClickListener {
                                            override fun onClick(
                                                dialog: DialogInterface?,
                                                which: Int
                                            ) {

                                            }
                                        });
                                    dialog.show()
                                    getKuponsAdaptersShow()
                                }
                            }
                        }
                } else {
                    Toast.makeText(context, "Avval kupon miqdorini kiriting", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Kupon transfer"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
    }
}