package uz.turgunboyevjurabek.rizon

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.turgunboyevjurabek.rizon.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.rizon.fragments.HomeFragment
import uz.turgunboyevjurabek.rizon.fragments.orderFragment.OrderFragment
import uz.turgunboyevjurabek.rizon.fragments.productsFragment.ProductFragment
import uz.turgunboyevjurabek.rizon.fragments.profileFragment.ProfileFragment
import uz.turgunboyevjurabek.rizon.utils.AppObject

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AppObject.binding = binding
        val navController = findNavController(R.id.my_navigation_host)
        binding.btnNavigation.setupWithNavController(navController)
        binding.imgNotification.setOnClickListener {
        navController.navigate(R.id.notificationFragment)

        }
    }
}