package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.R
import uz.turgunboyevjurabek.rizon.adapters.ShajaraRvAdapter
import uz.ilhomjon.rizonuz.databinding.FragmentProfilBinding
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse
import uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo.PatchUserChangeInfoRequest
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status
import java.text.SimpleDateFormat
import java.util.Date

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

        binding.myRoot.visibility = View.GONE

        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        getMainApi(SimpleDateFormat("yyyy-MM").format(Date()))

        return binding.root
    }

    fun getMainApi(date:String){ //"2023-03"
        profileViewModel.getUsersProfile(MySharedPreference.token, date)
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
    }

    @SuppressLint("SetTextI18n")
    fun showProfile(getUserProfileResponse: GetUserProfileResponse){
        val user = getUserProfileResponse.user
        editing(PatchUserChangeInfoRequest(
            user.address,
            user.dateOfBirth,
            user.first_name,
            user.last_name,
            user.passport,
            user.phone_number,
            user.phoneNumTwo
        ))
        binding.apply {
            myRoot.visibility = View.VISIBLE
            if (user.photo!=null){
            Picasso.get().load(ApiClient.PHOTO_BASE_URL+user.photo.toString()).into(imageProfile)
            }
            firstName.setText(user.first_name)
            lastName.setText(user.last_name)
            tvStatus.setText(user.user_status)
            tvPhoneNumber1.setText(user.phone_number)
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

            idRaqam.setOnClickListener {

                val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Label", binding.tvId.text.toString())
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(context, "Id nusxalandi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var shajaraNumber = 0
    fun showShajara(getUserProfileResponse: GetUserProfileResponse){
        shajaraRvAdapter = ShajaraRvAdapter(requireActivity())
        shajaraRvAdapter.list.addAll(getUserProfileResponse.user_tree[shajaraNumber])

        binding.apply {
            rvShajara.adapter = shajaraRvAdapter
            tvIzdoshlarSoni.text = "Izdosh ${getUserProfileResponse.user_tree[0].size} ta"

            imgNext.setOnClickListener {
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
            imgBefore.setOnClickListener {
                if (shajaraNumber>0){
                    shajaraNumber--
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

    private fun editing(patchUserChangeInfoRequest: PatchUserChangeInfoRequest){
        edtFalse()
        binding.apply {
            btnSave.setOnClickListener {
                edtFalse()
                btnSave.alpha=.35f

//userchaneinfo
                patchUserChangeInfoRequest.first_name = firstName.text.toString().trim()
                patchUserChangeInfoRequest.last_name = lastName.text.toString().trim()
                patchUserChangeInfoRequest.passport = tvPasportSeriya.text.toString().trim()+tvPassportRaqam.text.toString().trim()
                patchUserChangeInfoRequest.address = tvAddress.text.toString().trim()
                patchUserChangeInfoRequest.phone_number = tvPhoneNumber1.text.toString().trim()
                patchUserChangeInfoRequest.phoneNumTwo = tvPhoneNumber2.text.toString().trim()
                patchUserChangeInfoRequest.dateOfBirth = "${tvTugilganYil.text}-${tvTugilganOy.text}-${tvTugilganKun.text}"

                profileViewModel.changeUserInfo(MySharedPreference.token, patchUserChangeInfoRequest)
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
                                binding.progressUserProducts.visibility = View.GONE
                                getMainApi(SimpleDateFormat("yyyy-MM").format(Date()))

                            }
                        }
                    }

            }
            btnEdit.setOnClickListener {
                btnSave.alpha=1f
                edtTrue()
            }

        }
    }
    private fun edtFalse(){
        binding.apply {
        firstName.isEnabled=false
        lastName.isEnabled=false
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
            firstName.isEnabled=true
            lastName.isEnabled=true
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
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Profil"
        AppObject.binding.materialCardViewCalendar.visibility = View.VISIBLE
        AppObject.binding.materialCardViewCalendar.setOnClickListener {

            val datePickerDialog = DatePickerDialog(binding.root.context)

            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                if (month/10 >= 1)
                getMainApi("$year-$month")
                else
                    getMainApi("$year-0$month")

            }

            datePickerDialog.show()

        }

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