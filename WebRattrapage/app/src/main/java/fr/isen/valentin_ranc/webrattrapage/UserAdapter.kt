package fr.isen.valentin_ranc.webrattrapage


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.widget.TextView


class UserAdapter(private val userList: List<User>, val clickListener: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImageView: ImageView = itemView.findViewById(R.id.userImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val ageCountryTextView: TextView = itemView.findViewById(R.id.ageCountryTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]

        Log.e("ImageURL",currentUser.picture.medium)

        Picasso.get().load(currentUser.picture.medium).into(holder.userImageView)

        val name = currentUser.name.title + " " + currentUser.name.first + " " + currentUser.name.last
        val ageCountry = currentUser.dob.age.toString() + " ans à " + currentUser.location.country

        holder.nameTextView.text = name
        holder.ageCountryTextView.text = ageCountry

        holder.itemView.setOnClickListener {
            clickListener(currentUser)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
