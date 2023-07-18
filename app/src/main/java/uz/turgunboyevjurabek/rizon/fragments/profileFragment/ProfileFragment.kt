package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import uz.turgunboyevjurabek.rizon.adapters.ShajaraRvAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentProfilBinding
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient
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
        // Inflate the layout for this fragment
        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        profileViewModel.getUsersProfile("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg5ODIzNTQ1LCJpYXQiOjE2ODkzOTE1NDUsImp0aSI6IjNmNDBhZTIxOTRiMjQ2YjFiOTdiODA4NDhmMjliODllIiwidXNlcl9pZCI6MTU4fQ.FvJu6ND6sHW2pBNXb8cEn_DKY4ruXwqMCSkGt6C7k6Q", "2023-03")
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
                        binding.progressUserProducts.visibility = View.GONE
                    }
                }
            }


        return binding.root
    }

    fun showProfile(getUserProfileResponse: GetUserProfileResponse){
        val user = getUserProfileResponse.user
        binding.apply {
            Picasso.get().load(ApiClient.PHOTO_BASE_URL+user.photo.toString()).into(imageProfile)
            userName.text = "${user.first_name} ${user.last_name}"
            umimiyDaromad.text = user.toString()
            tvId.text = user.user_id.toString()
            tvPhoneNumber1.text = user.phoneNum
            tvPhoneNumber2.text = user.phoneNumTwo
            tvAddress.text = user.address
            umimiyKupon.text = user.coupon.toString()
            umimiyDaromad.text = user.salary.toString()
            tvStatus.text = user.user_status
        }
    }
}