package com.example.registrationform

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registrationform.databinding.UserRecyclerLayoutBinding
import com.example.registrationform.registration.data.UserDetailsData
import com.example.registrationform.room.UsersDatabase
import java.io.ByteArrayInputStream

class UserRecyclerAdapter(val context: Context):RecyclerView.Adapter<UserRecyclerAdapter.UsersViewHolder>() {
    var users = listOf<UserDetailsData>()
    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding=UserRecyclerLayoutBinding.bind(itemView)
        fun setName(name:String){
            binding.userFullName.setText(name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_recycler_layout,parent,false)
        val usersViewHolder = UsersViewHolder(view)
        return usersViewHolder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val first_name = users[position].basicRegistrationDetailsData.firstName
        val last_name = users[position].basicRegistrationDetailsData.lastName
        val profile_photo = users[position].basicRegistrationDetailsData.profilePhoto
        holder.setName("$first_name $last_name")
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,UserDetailsActivity::class.java)
            intent.putExtra("user",users[position])
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
        val byteArrayNew:ByteArray = Base64.decode(profile_photo, Base64.DEFAULT)
        val inputStream = ByteArrayInputStream(byteArrayNew)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        holder.binding.profilePhotoImageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return users.size
    }
    fun updateUsers(users:List<UserDetailsData>){
        this.users = users
        notifyDataSetChanged()

    }
}