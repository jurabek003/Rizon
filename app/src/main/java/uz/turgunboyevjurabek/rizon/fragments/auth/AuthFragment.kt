package uz.turgunboyevjurabek.rizon.fragments.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.databinding.FragmentAuthBinding
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthRequest
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "AuthFragment"
class AuthFragment : Fragment() {

    private val binding by lazy { FragmentAuthBinding.inflate(layoutInflater) }
    lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        MySharedPreference.init(binding.root.context)

        binding.btnSign.setOnClickListener {
            val postAuthRequest = PostAuthRequest(
                binding.edtPassword.text.toString(),
                binding.edtUserName.text.toString()
            )

            authViewModel.getToken(postAuthRequest)
                .observe(requireActivity()) {
                    when (it.status) {
                        Status.LOADING -> {
                            Log.d(TAG, "onCreate: Loading")
                            binding.myProgressBar.visibility = View.VISIBLE
                            binding.myContainerSing.isEnabled = false
                        }

                        Status.ERROR -> {
                            Log.d(TAG, "onCreate: Error ${it.message}")
                            binding.myProgressBar.visibility = View.GONE
                            Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                        }

                        Status.SUCCESS -> {
                            Log.d(TAG, "onCreate: ${it.data}")
                            binding.myProgressBar.visibility = View.GONE
                            binding.myContainerSing.isEnabled = true
                            MySharedPreference.token = it.data?.access!!
                            findNavController().popBackStack()
                            AppObject.binding.btnNavigation.visibility = View.VISIBLE
                            AppObject.binding.appPanel.visibility = View.VISIBLE
                            findNavController().navigate(R.id.homeFragment)
                        }
                    }
                }

        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.btnNavigation.visibility = View.GONE
        AppObject.binding.appPanel.visibility = View.GONE
    }
}