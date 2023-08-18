package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.ShajaraRvAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentProfilBinding
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse
import uz.turgunboyevjurabek.rizon.madels.usersProfile.UserTree
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "ProfileFragment"
class ProfileFragment : Fragment() {
    private val binding by lazy { FragmentProfilBinding.inflate(layoutInflater) }
    private lateinit var shajaraRvAdapter: ShajaraRvAdapter
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MySharedPreference.init(binding.root.context)

        editing()

        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        profileViewModel.getUsersProfile(MySharedPreference.token, "2023-03")
            .observe(requireActivity()){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        binding.progressUserProducts.visibility = View.VISIBLE
                    }
                    Status.ERROR ->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        binding.progressUserProducts.visibility = View.GONE
                        Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS ->{
                        Log.d(TAG, "onCreate: ${it.data}")
//                        myProductsAdapter.list.addAll(it.data?.products!!)
//                        myProductsAdapter.notifyDataSetChanged()
                        showProfile(it.data!!)
                        showShajara(it.data)
                        binding.progressUserProducts.visibility = View.GONE
                    }
                }
            }


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun showProfile(getUserProfileResponse: GetUserProfileResponse){
        val user = getUserProfileResponse.user
        binding.apply {
            if (user.photo!=null){
            Picasso.get().load(ApiClient.PHOTO_BASE_URL+user.photo.toString()).into(imageProfile)
            }
            userName.setText("${user.first_name} ${user.last_name}")
            tvStatus.setText(user.user_status)
            tvPhoneNumber1.setText(user.phoneNum)
            tvPhoneNumber2.setText(user.phoneNumTwo)

            umimiyDaromad.text = user.toString()
            tvId.text = user.user_id.toString()
            tvAddress.setText(user.address)
            umimiyKupon.text = user.coupon.toString()
            umimiyDaromad.text = user.salary.toString()
            tvPasportSeriya.setText(user.passport.substring(0, 2))
            tvPassportRaqam.setText(user.passport.substring(2))
            tvTugilganKun.setText(user.dateOfBirth.substring(8))
            tvTugilganOy.setText(user.dateOfBirth.substring(5, 7))
            tvTugilganYil.setText(user.dateOfBirth.substring(0, 4))
        }
    }

    var shajaraNumber = 0
    fun showShajara(getUserProfileResponse: GetUserProfileResponse){
        shajaraRvAdapter = ShajaraRvAdapter()
        shajaraRvAdapter.list.addAll(getUserProfileResponse.user_tree[shajaraNumber])

        binding.apply {
            rvShajara.adapter = shajaraRvAdapter
            tvIzdoshlarSoni.text = "Izdosh ${getUserProfileResponse.user_tree[0].size} ta"

            linerAvlod.setOnClickListener {
                    if (shajaraNumber<getUserProfileResponse.user_tree.size-1){
                        shajaraNumber++
                    }else{
                        shajaraNumber = 0
                    }
                    shajaraRvAdapter.list.clear()
                    shajaraRvAdapter.list.addAll(getUserProfileResponse.user_tree[shajaraNumber])
                    shajaraRvAdapter.notifyDataSetChanged()

                    tvAvldNumber.text = "${shajaraNumber + 1} - Avlod"

                    tvIzdoshlarSoni.text =
                        "Izdosh ${getUserProfileResponse.user_tree[shajaraNumber].size} ta"
                }


        }
    }

    private fun editing(){
        edtFalse()
        binding.apply {
            btnSave.setOnClickListener {
                edtFalse()
                btnSave.alpha=.35f
                Toast.makeText(requireActivity(), "Saved", Toast.LENGTH_SHORT).show()
            }
            btnEdit.setOnClickListener {
                btnSave.alpha=1f
                edtTrue()
            }

        }
    }
    private fun edtFalse(){
        binding.apply {
        userName.isEnabled=false
        tvStatus.isEnabled=false
        tvPhoneNumber1.isEnabled=false
        tvPhoneNumber2.isEnabled=false
        tvAddress.isEnabled=false
        tvPasportSeriya.isEnabled=false
        tvPassportRaqam.isEnabled=false
        tvTugilganKun.isEnabled=false
        tvTugilganOy.isEnabled=false
        tvTugilganYil.isEnabled=false


        }
    }
    private fun edtTrue(){
        binding.apply {
            userName.isEnabled=true
            tvStatus.isEnabled=true
            tvPhoneNumber1.isEnabled=true
            tvPhoneNumber2.isEnabled=true
            tvAddress.isEnabled=true
            tvPasportSeriya.isEnabled=true
            tvPassportRaqam.isEnabled=true
            tvTugilganKun.isEnabled=true
            tvTugilganOy.isEnabled=true
            tvTugilganYil.isEnabled=true

        }
    }
    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Profil"
        AppObject.binding.materialCardViewCalendar.visibility = View.VISIBLE

        binding.btnSotuv.setOnClickListener {
            findNavController().navigate(R.id.purchaseHistoryFragment)
        }
        binding.btnMaosh.setOnClickListener {
            findNavController().navigate(R.id.salaryHistoryFragment)
        }
        binding.btnKupon.setOnClickListener {
            findNavController().navigate(R.id.kuponTransferFragment)
        }

    }
}