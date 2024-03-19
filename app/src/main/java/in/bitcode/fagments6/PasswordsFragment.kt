package `in`.bitcode.fagments6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PasswordsFragment : Fragment() {

    private lateinit var recyclerPasswords : RecyclerView;
    private lateinit var btnAddPassword : FloatingActionButton

    private lateinit var passwordsAdapter: PasswordsAdapter

    private val passwords = ArrayList<PasswordModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.passwords_fragment, null)
        initData()
        initViews(view)
        initListeners()
        return view
    }

    private fun initListeners() {
        btnAddPassword.setOnClickListener {
            val addPasswordFragment = AddPasswordFragment()

            //addPasswordFragment.onPasswordAddedListener = MyOnPasswordAddedListener()
            addPasswordFragment.onPasswordAddedListener =
                object : AddPasswordFragment.OnPasswordAddedListener {
                    override fun onPasswordAdded(username: String, password: String) {
                        passwords.add(
                            PasswordModel(
                                101,
                                username,
                                password
                            )
                        )
                        passwordsAdapter.notifyItemInserted(passwords.size-1)
                    }
                }

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, addPasswordFragment, null)
                .addToBackStack(null)
                .commit()
        }

        passwordsAdapter.onPasswordClickListener =
            object : PasswordsAdapter.OnPasswordClickListener {
                override fun onPasswordClick(position: Int, passwordModel: PasswordModel) {
                    //add fragment to show details
                    val passwordDetailsFragment = PasswordDetailsFragment()

                    val input = Bundle()
                    input.putSerializable(PasswordModel.KEY_PASSWORD_MODEL, passwordModel)
                    passwordDetailsFragment.arguments = input


                    passwordDetailsFragment.show(parentFragmentManager, null)

                    /*parentFragmentManager.beginTransaction()
                        .add(R.id.mainContainer, passwordDetailsFragment, null)
                        .addToBackStack(null)
                        .commit()*/
                }
            }
    }

    private inner class MyOnPasswordAddedListener : AddPasswordFragment.OnPasswordAddedListener {
        override fun onPasswordAdded(username: String, password: String) {
            passwords.add(
                PasswordModel(
                    101,
                    username,
                    password
                )
            )
            passwordsAdapter.notifyItemInserted(passwords.size-1)
        }
    }

    private fun initData() {
        passwords.add(PasswordModel(1, "vishal@bitcode.in", "vishal@123", PasswordModel.CATEGORY_EMAIL))
        passwords.add(PasswordModel(2, "vishal", "vishal@123", PasswordModel.CATEGORY_ECOMMERCE))
        passwords.add(PasswordModel(3, "vishal123", "vishal@123", PasswordModel.CATEGORY_SOCIAL_MEDIA))
        passwords.add(PasswordModel(4, "vishal@gmail.com", "vishal@123", PasswordModel.CATEGORY_EMAIL))
    }

    private fun initViews(view : View) {
        recyclerPasswords = view.findViewById(R.id.recyclerPasswords)
        recyclerPasswords.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        passwordsAdapter = PasswordsAdapter(passwords)
        recyclerPasswords.adapter = passwordsAdapter

        btnAddPassword = view.findViewById(R.id.btnAddPassword)
    }

}