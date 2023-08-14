package fr.isen.valentin_ranc.webrattrapage

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import fr.isen.valentin_ranc.webrattrapage.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    var user: User? = null
    lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as? User

        Picasso.get().load(user?.picture?.large).into(binding.profileImageView)

        val name = user?.name?.title + " " + user?.name?.first + " " + user?.name?.last
        val age = user?.dob?.age.toString() + " ans"
        val registered = "Compte créé il y a " + user?.registered?.age + " ans"
        val location = "Habite en " + user?.location?.country + "\nDans la ville de " + user?.location?.city + " à " + user?.location?.state + " \n"
        val street = "Au " + user?.location?.street?.number + " " + user?.location?.street?.name

        val information = registered + "\n\n\n" + location + "\n" + street

        binding.nameTextView.text = name
        binding.genreTextView.text = user?.gender
        binding.ageTextView.text = age
        binding.informationTextView.text = information
        binding.emailTextView.text = user?.email
        binding.phoneTextView.text = user?.phone
    }
}