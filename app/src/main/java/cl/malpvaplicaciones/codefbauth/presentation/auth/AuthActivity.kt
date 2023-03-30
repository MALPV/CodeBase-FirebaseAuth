package cl.malpvaplicaciones.codefbauth.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import cl.malpvaplicaciones.codefbauth.R
import cl.malpvaplicaciones.codefbauth.databinding.ActivityAuthBinding
import cl.malpvaplicaciones.codefbauth.presentation.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        loadObservers()

        binding.apply {
            btnLogin.setOnClickListener {
                authViewModel.login(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }

            btnRegister.setOnClickListener {
                authViewModel.register(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }
        }
    }

    private fun loadObservers(){
        authViewModel.loginState.observe(this) { state ->
            when (state) {
                is ScreenState.Success -> {
                    hideProgressDialog()
                    //startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is ScreenState.Error  -> {
                    hideProgressDialog()
                    Toast.makeText(this, state.exception.message, Toast.LENGTH_SHORT).show()
                }
                is ScreenState.Loading  -> {
                    showProgressDialog()
                }
            }
        }

        authViewModel.registerState.observe(this) { state ->
            when (state) {
                is ScreenState.Success -> {
                    hideProgressDialog()
                    //startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is ScreenState.Error  -> {
                    hideProgressDialog()
                    Toast.makeText(this, state.exception.message, Toast.LENGTH_SHORT).show()
                }
                is ScreenState.Loading  -> {
                    showProgressDialog()
                }
            }
        }
    }

    private fun showProgressDialog() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        binding.progress.visibility = View.GONE
    }

}