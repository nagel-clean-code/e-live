package com.example.e_live

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.data.storage.models.SessionResultModelImpl
import com.example.e_live.databinding.ActivityMainBinding
import com.example.e_live.presentation.contract.Navigator
import com.example.e_live.presentation.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        binding.bottomNavigationView.setOnItemSelectedListener {
            val bufFragment = when (it.itemId) {
                R.id.home -> HomeFragment()
                R.id.store -> StoreFragment()
                R.id.basket -> BasketFragment()
                R.id.profile -> ProfileFragment()
                else -> throw IllegalArgumentException()
            }
            replaceFragment(bufFragment)
            true
        }
        setupListenerResult()
        if (savedInstanceState == null) {
            val session = viewModel.getSession()
            if (session == null) {
                showFragmentLogin()
            } else {
//                showFragmentSearch(session) //FIXME вроде бы не нужно открывать главное окно, оно будет открыто по умолчанию
            }
        }
//        auth = Firebase.auth
//        val currentUser = auth.currentUser        //FIXME
//        signWhyAnonymous()
//        updateUI(currentUser)

    }

    private fun setupListenerResult() {
        supportFragmentManager.setFragmentResultListener(
            Constants.FRAGMENT_LOGIN,
            this
        ) { _, bundle ->
            val sessionModel: SessionResultModelImpl =
                bundle.get(Constants.SESSION_RESULT_IMPL) as SessionResultModelImpl
            if (sessionModel.mail!!.isNotBlank()) {
                this.showFragmentHome()
            } else { //Если результат пустой, значит отправляем на форму регистрации
                this.showFragmentRegistration()
            }
        }

//        supportFragmentManager.setFragmentResultListener(
//            Constants.FRAGMENT_CONFIRMATION,
//            this
//        ) { _, bundle ->
//            val modelResult: LoginStep2Model =
//                bundle.get(Constants.LOGIN_STEP_2_MODEL) as LoginStep2Model
//            saveSession(modelResult)
//            showFragmentSearch(modelResult)
//
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_placeholder, fragment)
            .commit()
    }

//    private fun updateUI(user: FirebaseUser?) {
//        if (user == null) {
//            Toast.makeText(this, "Не авторизирован", Toast.LENGTH_LONG).show()
//        }
//    }

//    private fun signWhyAnonymous() {
//        auth.signInAnonymously()
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "Sign in success", Toast.LENGTH_LONG).show()
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    Toast.makeText(this, "sign in fails", Toast.LENGTH_LONG).show()
//                    updateUI(null)
//                }
//            }
//    }

    override fun showFragmentLogin() {
        replaceFragment(LoginFragment.newInstance())
    }

    override fun showFragmentHome() {
        replaceFragment(HomeFragment())
    }

    override fun showFragmentRegistration() {
        replaceFragment(RegistrationFragment())
    }

    override fun visibleNavigationMenu(flag: Boolean) {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).visibility =
            if (flag) View.VISIBLE else View.GONE
    }

    override fun goBack() {
        if (supportFragmentManager.backStackEntryCount != 1) {
            onBackPressed()
        }
    }
}