package com.cloudstream.googledrive.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cloudstream.googledrive.R
import com.cloudstream.googledrive.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    
    companion object {
        private const val RC_SIGN_IN = 9001
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupGoogleSignIn()
        setupClickListeners()
    }
    
    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope("https://www.googleapis.com/auth/drive.readonly"))
            .requestEmail()
            .build()
        
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }
    
    private fun setupClickListeners() {
        binding.buttonSignIn.setOnClickListener {
            signIn()
        }
        
        binding.buttonSignOut.setOnClickListener {
            signOut()
        }
        
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
    
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    
    private fun signOut() {
        googleSignInClient.signOut().addOnCompleteListener {
            updateUI(null)
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                updateUI(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Sign in failed: ${e.message}", Toast.LENGTH_LONG).show()
                updateUI(null)
            }
        }
    }
    
    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            binding.textViewStatus.text = "Signed in as: ${account.email}"
            binding.buttonSignIn.visibility = android.view.View.GONE
            binding.buttonSignOut.visibility = android.view.View.VISIBLE
            binding.textViewStatus.visibility = android.view.View.VISIBLE
            
            Toast.makeText(this, "Successfully signed in!", Toast.LENGTH_SHORT).show()
        } else {
            binding.textViewStatus.text = "Not signed in"
            binding.buttonSignIn.visibility = android.view.View.VISIBLE
            binding.buttonSignOut.visibility = android.view.View.GONE
            binding.textViewStatus.visibility = android.view.View.GONE
        }
    }
    
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }
}
