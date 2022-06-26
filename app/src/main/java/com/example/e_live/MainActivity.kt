package com.example.e_live

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.data.storage.models.SessionResultModelImpl
import com.example.e_live.Constants.Companion.TYPE_ICON_DEFAULT
import com.example.e_live.Constants.Companion.TYPE_ICON_GO_BACK
import com.example.e_live.databinding.ActivityMainBinding
import com.example.e_live.presentation.contract.CustomAction
import com.example.e_live.presentation.contract.HasCustomActionToolbar
import com.example.e_live.presentation.contract.Navigator
import com.example.e_live.presentation.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var mActionBarToolbar: Toolbar? = null
    private val viewModel: MainViewModel by viewModels()
    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.frame_layout_placeholder)!!
    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
        binding.bottomNavigationView
            .setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> this.showFragmentHome()
                    R.id.store -> this.showFragmentStore()
                    R.id.basket -> this.showFragmentBasket()
                    R.id.profile -> this.showFragmentProfile()
                    else -> throw IllegalArgumentException()
                }
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
        initActionBarToolbar()
//        auth = Firebase.auth
//        val currentUser = auth.currentUser        //FIXME
//        signWhyAnonymous()
//        updateUI()

    }

    private fun updateUI() {
        val fragment = currentFragment
        if (fragment is HasCustomActionToolbar)
            createCustomToolbarAction(fragment.getCustomAction())
    }

    private fun createCustomToolbarAction(action: CustomAction) {

        when (action.typeIcon) {
            TYPE_ICON_GO_BACK -> {
                mActionBarToolbar?.navigationIcon = null
                setSupportActionBar(mActionBarToolbar)
                supportActionBar?.setDisplayShowTitleEnabled(false)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
                supportActionBar?.title = action.textToolbar
                binding.maActionBar.textToolbar.text = action.textToolbar
                mActionBarToolbar!!.setNavigationOnClickListener { goBack() }
            }
            TYPE_ICON_DEFAULT -> {
                supportActionBar?.title = action.textToolbar
                mActionBarToolbar?.navigationIcon = null
                setSupportActionBar(mActionBarToolbar)
                binding.maActionBar.textToolbar.text = action.textToolbar
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }
        }
    }

    private fun initActionBarToolbar(): Toolbar? {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = findViewById<View>(R.id.toolbar) as Toolbar
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar)
            }
        }
        return mActionBarToolbar
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
        binding.maActionBar.toolbar.visibility = View.GONE
        replaceFragment(LoginFragment.newInstance())
    }

    override fun showFragmentHome() {
        binding.maActionBar.toolbar.visibility = View.GONE
        replaceFragment(HomeFragment())
    }

    override fun showFragmentRegistration() {
        binding.maActionBar.toolbar.visibility = View.GONE
        replaceFragment(RegistrationFragment())
    }

    override fun showFragmentStore() {
        binding.maActionBar.toolbar.visibility = View.VISIBLE
        replaceFragment(StoreFragment())
    }

    override fun showFragmentBasket() {
        binding.maActionBar.toolbar.visibility = View.VISIBLE
        replaceFragment(BasketFragment())
    }

    override fun showFragmentProfile() {
        binding.maActionBar.toolbar.visibility = View.VISIBLE
        replaceFragment(ProfileFragment())
    }

    override fun visibleNavigationMenu(flag: Boolean) {
        binding.bottomNavigationView.visibility = if (flag) View.VISIBLE else View.GONE
    }

    override fun goBack() {
        if (supportFragmentManager.backStackEntryCount != 1) {
            onBackPressed()
        }
    }
}