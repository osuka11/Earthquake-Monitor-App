package com.example.earthquake_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquake_app.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EARTHQUAKE = "earthquake_key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val earthquake = bundle.getParcelable<Earthquake>(EARTHQUAKE)
        setupView(earthquake,binding)


    }

    private fun setupView(earthquake: Earthquake?, binding: ActivityDetailBinding) {

        if (earthquake != null){
            binding.magText.text = getString(R.string.magnitude_format,earthquake.magnitude)
            binding.longitudeText.text =getString(R.string.longitude_format,earthquake.longitude)
            binding.latitudeText.text = getString(R.string.latitude_format,earthquake.latitude)
            binding.placeText.text = earthquake.place
            binding.dateText.text = setupDateFormat(earthquake.time)
        }else{
            Toast.makeText(this,"Earthquake no Available",Toast.LENGTH_SHORT).show()
        }
    }
    private fun setupDateFormat(myTimeStamp:Long):String{
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date(myTimeStamp)
        return simpleDateFormat.format(date)
    }
}