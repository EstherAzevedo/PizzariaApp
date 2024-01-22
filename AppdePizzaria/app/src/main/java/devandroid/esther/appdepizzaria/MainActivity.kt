package devandroid.esther.appdepizzaria

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.recyclerview.widget.GridLayoutManager
import devandroid.esther.appdepizzaria.adapter.ProductAdapter
import devandroid.esther.appdepizzaria.databinding.ActivityMainBinding
import devandroid.esther.appdepizzaria.listitems.Products
import devandroid.esther.appdepizzaria.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val products = Products()
    private val productList: MutableList<Product> = mutableListOf()
    var clicked = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        CoroutineScope(Dispatchers.IO).launch {
            products.getProducts().collectIndexed{index, value ->
                for (p in value){
                    productList.add(p)
                }
            }
        }

        val recyclerViewProducts = binding.recyclerViewProducts
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProducts.setHasFixedSize(true)
        productAdapter = ProductAdapter(this, productList)
        recyclerViewProducts.adapter = productAdapter

        //setContentView(R.layout.activity_main)

        binding.btnAll.setOnClickListener {
            clicked = true
            if(clicked){
                binding.btnAll.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btnAll.setTextColor(Color.WHITE)
                binding.btnChicken.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnChicken.setTextColor(R.color.dark_gray)
                binding.btnKebab.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnKebab.setTextColor(R.color.dark_gray)
                binding.btnPizza.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnPizza.setTextColor(R.color.dark_gray)

                binding.recyclerViewProducts.visibility = View.INVISIBLE
                binding.txtListTitle.text = "All"

            }
        }

        binding.btnChicken.setOnClickListener {
            clicked = true
            if(clicked){
                binding.btnChicken.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btnChicken.setTextColor(Color.WHITE)
                binding.btnAll.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnAll.setTextColor(R.color.dark_gray)
                binding.btnKebab.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnKebab.setTextColor(R.color.dark_gray)
                binding.btnPizza.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnPizza.setTextColor(R.color.dark_gray)

                binding.recyclerViewProducts.visibility = View.INVISIBLE
                binding.txtListTitle.text = "Chicken"

            }
        }
        binding.btnPizza.setOnClickListener {
            clicked = true
            if(clicked){
                binding.btnPizza.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btnPizza.setTextColor(Color.WHITE)
                binding.btnChicken.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnChicken.setTextColor(R.color.dark_gray)
                binding.btnAll.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnAll.setTextColor(R.color.dark_gray)
                binding.btnKebab.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnKebab.setTextColor(R.color.dark_gray)

                binding.recyclerViewProducts.visibility = View.VISIBLE
                binding.txtListTitle.text = "Pizza"

            }
        }

        binding.btnKebab.setOnClickListener {
            clicked = true
            if(clicked){
                binding.btnKebab.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btnKebab.setTextColor(Color.WHITE)
                binding.btnPizza.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnPizza.setTextColor(R.color.dark_gray)
                binding.btnChicken.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnChicken.setTextColor(R.color.dark_gray)
                binding.btnAll.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btnAll.setTextColor(R.color.dark_gray)


                binding.recyclerViewProducts.visibility = View.INVISIBLE
                binding.txtListTitle.text = "Kebab"

            }
        }

    }
}