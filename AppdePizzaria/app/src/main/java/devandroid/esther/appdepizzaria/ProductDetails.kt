package devandroid.esther.appdepizzaria

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import devandroid.esther.appdepizzaria.databinding.ActivityProductDetailsBinding
import java.text.DecimalFormat

class ProductDetails : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    var amount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val imgPoduct = intent.extras!!.getInt("imgProduct")
        val name = intent.extras!!.getString("name")
        val price = intent.extras!!.getString("price")!!.toDouble()
        var newPrice = price
        val decimalFormat = DecimalFormat.getCurrencyInstance()

        binding.imgProduct.setBackgroundResource(imgPoduct)
        binding.txtProductName.text = "$name"
        binding.txtPrice.text = decimalFormat.format(price)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnIncrease.setOnClickListener {
            if (amount == 1){
                binding.txtAmount.text = "2"
                newPrice += price
                amount = 2
            }else if (amount == 2){
                binding.txtAmount.text = "3"
                newPrice += price
                amount = 3
            }
            binding.txtPrice.text = decimalFormat.format(newPrice)
        }

        binding.btnToDecrease.setOnClickListener {
            if (amount == 3){
                binding.txtAmount.text = "2"
                newPrice -= price
                amount = 2
            }else if (amount == 2){
                binding.txtAmount.text = "1"
                newPrice -= price
                amount = 1
            }
            binding.txtPrice.text = decimalFormat.format(newPrice)
        }

        binding.btnConfirm.setOnClickListener {
            val mustard = binding.btnMustard
            val ketchup = binding.btnKetchup
            val lemonSoda = binding.btnLemonSoda
            val juice = binding.btnJuice

            val saucesAndDrinks = when{
                mustard.isChecked -> {
                    "Mustard"
                }
                ketchup.isChecked -> {
                    "Ketchup"
                }
                lemonSoda.isChecked -> {
                    "Lemon Soda"
                }
                juice.isChecked -> {
                    "Juice"
                }
                else -> {
                    ""
                }
            }
            val intent = Intent(this, Payment::class.java)
            intent.putExtra("name", name)
            intent.putExtra("amount", amount)
            intent.putExtra("total", newPrice)
            intent.putExtra("saucesAndDrinks", saucesAndDrinks)
            startActivity(intent)
        }
    }
}