package com.example.blogappjp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.blogappjp.R
import com.example.blogappjp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        val user = FirebaseAuth.getInstance().currentUser
        Glide.with(this).load(user?.photoUrl).centerCrop().into(binding.imgProfile)
        binding.imgProfile
        binding.txtProfileName.text = user?.displayName
        Log.d("Usuario","fotourl: ${user?.photoUrl}, nombre: ${user?.displayName} ")
    }

}