package uz.turgunboyevjurabek.rizon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.turgunboyevjurabek.rizon.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.rizon.fragments.HomeFragment
import uz.turgunboyevjurabek.rizon.fragments.OrderFragment
import uz.turgunboyevjurabek.rizon.fragments.ProductFragment
import uz.turgunboyevjurabek.rizon.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val homeFragment=HomeFragment()
        val productFragment=ProductFragment()
        val orderFragment=OrderFragment()
        val profileFragment=ProfileFragment()
        binding.btnNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btn_home->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,homeFragment)
                        .commit()
                }
                R.id.btn_savat->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,productFragment)
                        .commit()
                }
                R.id.btn_karzinku->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,orderFragment)
                        .commit()
                }
                R.id.btn_user->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,profileFragment)
                        .commit()
                }

            }

            true
        }
    }
}